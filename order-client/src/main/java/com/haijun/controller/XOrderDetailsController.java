package com.haijun.controller;


import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RestController;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.haijun.service.IXOrderDetailsService;
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
@RequestMapping("/xOrderDetails")
public class XOrderDetailsController {
	@Autowired
	private IXOrderDetailsService detailsService;
	
	/**
	 * 根据orderId查询订单明细
	 * @param dataJson
	 * @return
	 */
	@PostMapping("getDetailsByOrderId")
	public ResultMsg selectOrderDetails(String dataJson) {
		ResultMsg msg = new ResultMsg(500, "系统错误");
		String orderId = "";
		JSONObject json = null;
		if(StringUtils.isBlank(dataJson)) {
			return new ResultMsg(500, "后台传参为空");
		}
		json = JSON.parseObject(dataJson);
		if(!StringUtils.isBlank(json.getString("orderId"))) {
			orderId = json.getString("orderId");
		}
		
		
		List<Map<String, Object>> lists = detailsService.selectOrderDetail(orderId);
		msg.setCode(200);
		msg.setData(lists);
		msg.setMsg("success");
		return msg; 
	}
	
	/**
	 * 删除订单明细
	 * @param dataJson
	 * @return
	 */
	@Transactional
	@PostMapping("delOrderDetails")
	public ResultMsg delOrderDetails(String dataJson) {
		if(StringUtils.isNotBlank(dataJson)) {
			JSONObject jb = JSON.parseObject(dataJson);
			if(jb.getInteger("id") != null) {
				Integer id = jb.getInteger("id");
				if(detailsService.removeById(id)) {
					return new ResultMsg(200, "该明细已成功删除！");
				}
			}
		}
		return new ResultMsg(500,"删除失败！");
	}
	/**
	 * 出货时查询出订单明细作为出货对象
	 * @param dataJson
	 * @return
	 */
	@PostMapping("/getOrderDetailForSelect")
	public ResultMsg getOrderDetailForSelect(String dataJson) {
		if(StringUtils.isNotBlank(dataJson)) {
			JSONObject jb = JSON.parseObject(dataJson);
			if(jb.getString("orderId")!=null) {
				String orderId = jb.getString("orderId");
				List<Map<String, Object>> list = detailsService.getOrderDetailForSelect(orderId);
				return new ResultMsg(200, "success", list);
			}
		}
		return new ResultMsg(500, "系统错误！");
	}
}
