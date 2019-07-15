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
import com.haijun.service.IXJinhuoDetailsService;
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
@RequestMapping("/xJinhuoDetails")
public class XJinhuoDetailsController {
	@Autowired
	private IXJinhuoDetailsService jhDetails;
	
	@PostMapping("selectDetails")
	public ResultMsg selectDetails(String dataJson) {
		if(StringUtils.isNotBlank(dataJson)) {
			JSONObject jb = JSON.parseObject(dataJson);
			if(StringUtils.isNotBlank(jb.getString("jinhuoNumber"))) {
				String jinhuoNumber = jb.getString("jinhuoNumber");
				List<Map<String, Object>> list = jhDetails.selectDetails(jinhuoNumber);
				return new ResultMsg(200, "success", list);
			}
		}
		return new ResultMsg(500,"系统错误！");
	}
	
	@Transactional
	@PostMapping("/delJHDetails")
	public ResultMsg delJHDetails(String dataJson) {
		if(StringUtils.isNotBlank(dataJson)) {
			JSONObject jb = JSON.parseObject(dataJson);
			if(jb.getInteger("id")!=null) {
				Integer id = jb.getInteger("id");
				if(jhDetails.removeById(id)) {
					return new ResultMsg(200, "success");
				}else {
					return new ResultMsg(500, "删除失败！");
				}
			}
		}
		return new ResultMsg(500, "系统异常！");
	}
	
}
