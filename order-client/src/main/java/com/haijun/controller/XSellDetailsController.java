package com.haijun.controller;


import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RestController;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.haijun.service.IXSellDetailsService;
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
@RequestMapping("/xSellDetails")
public class XSellDetailsController {
	@Autowired
	private IXSellDetailsService detailsService;
	
	@PostMapping("/selectSellDetails")
	public ResultMsg selectSellDetails(String dataJson) {
		if(StringUtils.isNotBlank(dataJson)) {
			JSONObject jb = JSON.parseObject(dataJson);
			if(StringUtils.isNotBlank(jb.getString("sellNumber"))) {
				String sellNumber = jb.getString("sellNumber");
				List<Map<String, Object>> list = detailsService.selectSellDetails(sellNumber);
				return new ResultMsg(200, "success", list);
			}
		}
		return new ResultMsg(500, "系统错误");
	}
	@PostMapping("getGoodsTotalList")
	public ResultMsg getGoodsTotalList(String dataJson) {
		if(StringUtils.isNotBlank(dataJson)) {
			JSONObject jb = JSON.parseObject(dataJson);
			if(StringUtils.isNotBlank(jb.getString("date"))) {
				String date = jb.getString("date").substring(0,7);
				
				List<Map<String, Object>> list = detailsService.getGoodsTotalList(date);
				return new ResultMsg(200, "success", list);
			}
		}
		return new ResultMsg(500, "获取货物占比失败！");
	}
}
