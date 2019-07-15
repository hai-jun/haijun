package com.haijun.controller;


import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RestController;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.haijun.config.Audience;
import com.haijun.model.Usertoken;
import com.haijun.service.IUsertokenService;
import com.haijun.service.LoginFeignApi;
import com.haijun.util.JwtHelper;
import com.haijun.util.ResultMsg;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author funton
 * @since 2019-04-24
 */
@RestController
@RequestMapping("/usertoken")
public class UsertokenController {
	@Autowired
	private LoginFeignApi loginFeign;
	@Autowired
	private Audience audience;
	@Autowired
	private IUsertokenService userTokenService;
	@SuppressWarnings("unchecked")
	@PostMapping("login.do")
	public ResultMsg login(String dataJson) {
		ResultMsg msg = new ResultMsg(500, "登录失败");
		if (StringUtils.isBlank(dataJson)) {
			return new ResultMsg(400, "参数不合法");
		}
		JSONObject jb = JSON.parseObject(dataJson);
		if(StringUtils.isBlank(jb.getString("userName"))) {
			return new ResultMsg(400, "用户名不能为空");
		}
		if(StringUtils.isBlank(jb.getString("password"))) {
			return new ResultMsg(400, "密码不能为空");
		}
		
		msg = loginFeign.login(jb.getString("userName"),jb.getString("password"));
		if(msg.getCode()!=200) {
			return msg;
		}
		Map<String, Object> userMap = (Map<String, Object>) msg.getData();
		String token = JwtHelper.createJWT(userMap, audience.getBase64Secret());
		Usertoken userToken = userTokenService.getOne(new QueryWrapper<Usertoken>().eq(true, "userName", 
				(userMap.get("userName")).toString()).eq(true, "userId", Integer.parseInt((userMap.get("userId").toString()))));
		if(userToken==null) {
			//新增该用户的token
			userTokenService.save(new Usertoken(Integer.parseInt((userMap.get("userId").toString())), userMap.get("userName").toString(), token));
		}else {
			userToken.setToken(token);
			//替换该用户的token
			userTokenService.update(userToken,new UpdateWrapper<Usertoken>().eq(true, "userId", userToken.getUserId()).eq(true, "userName", userToken.getUserName()));
		}
		userMap.put("token", token);
		return new ResultMsg(200, "success", userMap);
	}
}
