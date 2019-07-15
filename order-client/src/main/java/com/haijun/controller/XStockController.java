package com.haijun.controller;


import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.haijun.model.XStock;
import com.haijun.service.IXStockService;
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
@RequestMapping("/xStock")
public class XStockController {
	@Autowired
	private IXStockService xss;
	/**
	 *  分页查询库存
	 * @param dataJson
	 * @return
	 */
	@PostMapping("getStock")
	public ResultMsg getStock(String dataJson) {
		int currentPage = 1;
		JSONObject jb = null;
		Page<XStock> page = new Page<XStock>();
		if(!StringUtils.isBlank(dataJson)) {
			jb = JSON.parseObject(dataJson);
			if(jb.getInteger("current")!=null) {
				currentPage = jb.getInteger("current");
				page.setSize(10);
				page.setCurrent(currentPage);
				List<XStock> stockLists = xss.getStock(page);
				Map<String, Object> maps = new HashMap<String, Object>();
				maps.put("total", page.getTotal());
				maps.put("stockLists", stockLists);
				return new ResultMsg(200, "查询成功", maps);
			}
			
		}
		return new ResultMsg(500, "系统异常！");
	}
	/**
	 * 修改库存
	 * @param dataJson
	 * @return
	 */
	@Transactional
	@PostMapping("updateStock")
	public ResultMsg updateStock(String dataJson) {
		ResultMsg msg = new ResultMsg(500, "修改库存失败");
		Integer goodsId = null;
		double goodsCount = 0;
		JSONObject jb = null;
		if(StringUtils.isBlank(dataJson)) {
			return new ResultMsg(500, "参数异常");
		}
		jb = JSON.parseObject(dataJson);
		goodsId = jb.getInteger("goodsId");
		
		if(jb.getDouble("goodsCount") == null) {
			return new ResultMsg(500, "数目异常为null");
		}
		goodsCount = jb.getDouble("goodsCount");
		int res = xss.updataStock(goodsId, goodsCount);
		if(res>0) {
			return new ResultMsg(200, "修改成功");
		}
		return msg;
	}
	@PostMapping("selectStock")
	public ResultMsg selectStock() {
		List<Map<String, Object>> list = xss.selectStock();
		return new ResultMsg(200,"success",list);
	}
	/**
	 * 删除一条库存信息
	 * @param dataJson
	 * @return
	 */
	@Transactional
	@PostMapping("delStock")
	public ResultMsg delStock(String dataJson) {
		if(StringUtils.isNotBlank(dataJson)) {
			JSONObject jb = JSON.parseObject(dataJson);
			if(jb.getInteger("goodsId")!=null) {
				Integer goodsId = jb.getInteger("goodsId");
				if(xss.removeById(goodsId)) {
					return new ResultMsg(200, "删除成功");
				}
			}
		}
		return new ResultMsg(500, "删除失败！");
	}
	/**
	 * 新增库存信息
	 * @param dataJson
	 * @return
	 */
	@Transactional
	@PostMapping("/addStock")
	public ResultMsg addStock(String dataJson) {
		if(StringUtils.isNotBlank(dataJson)) {
			JSONObject jb = JSON.parseObject(dataJson);
			if(jb.getString("goodsName") != null) {
				String goodsName = jb.getString("goodsName");
				//判断goodsName是否已存在
				if(xss.getOne(new QueryWrapper<XStock>().eq("goods_name", goodsName))!=null) {
					return new ResultMsg(500, "名称已存在！");
				}
				
				XStock stock = new XStock();
				stock.setGoodsName(goodsName);
				if(xss.save(stock)) {
					return new ResultMsg(200, "新增成功！");
				}
			}
		}
		return new ResultMsg(500, "系统异常！");
	}
	@PostMapping("/getGoodsTypeList")
	public ResultMsg getGoodsTypeList() {
		List<String> nameList = xss.getGoodsTypeList();
		if(nameList!=null) {
			return new ResultMsg(200, "success", nameList);
		}
		return new ResultMsg(500, "获取铁线类型失败！");
	}
}
