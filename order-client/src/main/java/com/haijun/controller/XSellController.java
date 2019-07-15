package com.haijun.controller;


import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.interceptor.TransactionAspectSupport;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RestController;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.haijun.model.XSell;
import com.haijun.model.XSellDetails;
import com.haijun.service.IXSellDetailsService;
import com.haijun.service.IXSellService;
import com.haijun.utils.ResultMsg;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author funton
 * @since 2019-04-15
 */
@RestController
@RequestMapping("/xSell")
public class XSellController {
	@Autowired
	private IXSellService sellService;
	@Autowired
	private IXSellDetailsService detailService;
	
	/**
	 * 查询一条订单的出货记录
	 * @param dataJson
	 * @return
	 */
	@PostMapping("/selectSellLists")
	public ResultMsg selectSellLists(String dataJson) {
		if(StringUtils.isNotBlank(dataJson)) {
			JSONObject jb = JSON.parseObject(dataJson);
			if(jb.getString("orderNum")!=null) {
				String orderNum = jb.getString("orderNum");
				List<XSell> sellLists = sellService.getSellLists(orderNum);
				return new ResultMsg(200, "success", sellLists);
			}
		}
		return new ResultMsg();
	}
	/**
	 * 保存出货信息
	 * @param dataJson
	 * @return
	 */
	@Transactional
	@PostMapping("/addSellList")
	public ResultMsg addSellList(String dataJson) {
		if(StringUtils.isNotBlank(dataJson)) {
			JSONObject jb = JSON.parseObject(dataJson);
			if(jb.getString("sellInfo")!=null&&jb.getString("sellDetails")!=null) {
				String jSellInfo = jb.getString("sellInfo");
				String jSellDetails = jb.getString("sellDetails");
				
				XSell sell = JSON.parseObject(jSellInfo, XSell.class);
				
				List<XSellDetails> sellDetails = JSON.parseArray(jSellDetails, XSellDetails.class);
				if(sell!=null&&sellDetails!=null) {
					//判断出货单号是否已存在
					
					if(sellService.getOne(new QueryWrapper<XSell>().eq("sell_numbers", sell.getSellNumbers()))!=null) {
						return new ResultMsg(500, "该出货单号已存在,请换一个");
					}
					
					//当出货信息和出货明细都不为空时，首先插入出货明细
					if(detailService.saveBatch(sellDetails)) {
						//当出货明细插入成功之后插入出货信息
						//设置出货日期为当前日期
						Date date = new Date();
						SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
						String dateStr = format.format(date);
						sell.setSellDate(dateStr);
						if(sellService.save(sell)) {
							//都插入成功之后返回
							return new ResultMsg(200, "success");
						}else {
							//当出货信息插入失败之后要回滚插入的出货明细
							TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();  
						}
					}
				}
			}
		}
		return new ResultMsg(500, "新增出货单失败！");
	}
	/**
	 * 获取收入列表
	 * @param dataJson
	 * @return
	 */
	@PostMapping("/getShouruList")
	public ResultMsg getShouruList(String dataJson) {
		if(StringUtils.isNotBlank(dataJson)) {
			JSONObject jb = JSON.parseObject(dataJson);
			if(StringUtils.isNotBlank(jb.getString("year"))) {
				String yearDate = jb.getString("year");
				String year = yearDate.substring(0, 4);
				List<Double> priceList = new ArrayList<Double>();
				for (int i = 1; i < 13; i++) {
					double totalPrice = sellService.totalPriceByMonth(year, i);
					
					priceList.add(totalPrice);
				}
				return new ResultMsg(200, "success", priceList);
			}
		}
		return new ResultMsg(500, "统计月收入异常！");
	}
	
}
