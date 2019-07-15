package com.haijun.controller;


import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.codec.digest.DigestUtils;
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
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.haijun.model.XJinhuo;
import com.haijun.model.XJinhuoDetails;
import com.haijun.model.XUser;
import com.haijun.service.IXJinhuoDetailsService;
import com.haijun.service.IXJinhuoService;
import com.haijun.service.IXUserService;
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
@RequestMapping("/xJinhuo")
public class XJinhuoController {
	@Autowired
	private IXJinhuoService jhService;
	@Autowired
	private IXJinhuoDetailsService jhDetailsService;
	@Autowired
	private IXUserService userService;
	
	/**
	 * 新增进货信息和进货明细
	 * @param dataJson
	 * @return
	 */
	@Transactional
	@PostMapping("addJinhuo")
	public ResultMsg addJinhuo(String dataJson) {
		if(StringUtils.isNotBlank(dataJson)) {
			JSONObject jb = JSON.parseObject(dataJson);
			if(StringUtils.isNotBlank(jb.getString("JinhuoInfo"))) {
				XJinhuo jinHuo = JSON.parseObject(jb.getString("JinhuoInfo"), XJinhuo.class);
				String num = jinHuo.getJinhuoNumber();
				jinHuo.setJinhuoDate(jinHuo.getJinhuoDate().toString().substring(0, 10));
				if(StringUtils.isNotBlank(jb.getString("JinhuoDetails"))) {
					List<XJinhuoDetails> lists = JSON.parseArray(jb.getString("JinhuoDetails"), XJinhuoDetails.class);
					for (XJinhuoDetails xJinhuoDetails : lists) {
						xJinhuoDetails.setJinhuoNumbers(num);
					}
					if(jhDetailsService.saveBatch(lists)) {
						if(jhService.save(jinHuo)) {
							return new ResultMsg(200, "success");
						}else {
							TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();  
						}
					}
				}
			}
		}
		return new ResultMsg();
	}
	/**
	 * 查询进货单
	 * @param dataJson
	 * @return
	 */
	@PostMapping("getJHList")
	public ResultMsg getJHList(String dataJson) {
		if(StringUtils.isNotBlank(dataJson)) {
			JSONObject jb = JSON.parseObject(dataJson);
			String jinhuoNumber = "";
			if(StringUtils.isNotBlank(jb.getString("jinhuoNumber"))) {
				jinhuoNumber = jb.getString("jinhuoNumber");
			}
			String jinhuoCompany = "";
			if(StringUtils.isNotBlank(jb.getString("jinhuoCompany"))) {
				jinhuoCompany = jb.getString("jinhuoCompany");
			}
			String jinhuoDate = "";
			if(StringUtils.isNotBlank(jb.getString("jinhuoDate"))) {
				jinhuoDate = jb.getString("jinhuoDate").substring(0, 7);
			}
			Integer currentPage = jb.getInteger("currentPage");
			Page<XJinhuo> page = new Page<XJinhuo>();
			page.setCurrent(currentPage);
			List<XJinhuo> list = jhService.getJHList(page, jinhuoNumber, jinhuoCompany, jinhuoDate);
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("list", list);
			map.put("total", page.getTotal());
			return new ResultMsg(200, "success", map);
		}
		return new ResultMsg();
	}
	@Transactional
	@PostMapping("/JHDel")
	public ResultMsg jinhuoDel(String dataJson) {
		if(StringUtils.isNotBlank(dataJson)) {
			JSONObject jb = JSON.parseObject(dataJson);
			if(jb.getInteger("userId") != null&&StringUtils.isNotBlank(jb.getString("password"))) {
				Integer userId = jb.getInteger("userId");
				String password = DigestUtils.sha256Hex(DigestUtils.md5Hex(jb.getString("password")));
				XUser user = userService.getOne(new QueryWrapper<XUser>().eq("id", userId).eq("password", password));
				if(user != null) {
					//验证密码成功，执行删除
					if(StringUtils.isNotBlank(jb.getString("jinhuoNumber"))) {
						String jinhuoNumber = jb.getString("jinhuoNumber");
						//删除明细
						if(jhDetailsService.remove(new QueryWrapper<XJinhuoDetails>().eq("jinhuo_numbers", jinhuoNumber))) {
							//删除此进货列表
							if(jhService.remove(new QueryWrapper<XJinhuo>().eq("jinhuo_number", jinhuoNumber))) {
								return new ResultMsg(200, "删除完成，此进货单已不可恢复");
							}
						}
					}
				}else {
					return new ResultMsg(500, "密码错误，没有权限删除该进货单！");
				}
			}
		}
		
		return new ResultMsg(500, "删除失败！");
	}
	/**
	 * 获取支出列表
	 * @param dataJson
	 * @return
	 */
	@PostMapping("/getZhichuList")
	public ResultMsg getZhichuList(String dataJson) {
		if(StringUtils.isNotBlank(dataJson)) {
			JSONObject jb = JSON.parseObject(dataJson);
			if(StringUtils.isNotBlank(jb.getString("year"))) {
				String yearDate = jb.getString("year");
				String year = yearDate.substring(0, 4);
				List<Double> priceList = new ArrayList<Double>();
				for (int i = 1; i < 13; i++) {
					double price = jhService.getZhichu(year, i);
					priceList.add(price);
				}
				return new ResultMsg(200, "success", priceList);
			}
		}
		return new ResultMsg(500, "统计月支出异常！");
	}
}
