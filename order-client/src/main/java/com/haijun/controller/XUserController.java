package com.haijun.controller;


import java.util.HashMap;
import java.util.Map;

import org.apache.commons.codec.digest.DigestUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.haijun.model.XUser;
import com.haijun.service.IXUserService;
import com.haijun.utils.ResultMsg;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author funton
 * @since 2019-04-17
 */
@RestController
@RequestMapping("/xUser")
public class XUserController {
	@Autowired
	public IXUserService userService;
	/**
	 * 登录
	 * @param userName
	 * @param password
	 * @return
	 */
	@PostMapping("/login")
	public ResultMsg login(@RequestParam(value = "userName")String userName,@RequestParam(value = "password") String password) {
		password = DigestUtils.sha256Hex(DigestUtils.md5Hex(password));
		XUser user = userService.getOne(new QueryWrapper<XUser>().eq("user_name", userName).eq("password", password));	
	
		
		if(user!=null) {
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("userName", user.getUserName());
			map.put("userId", user.getId());
			map.put("password", user.getPassword());
			map.put("discription", user.getDiscription());
			map.put("phone", user.getPhone());
			return new ResultMsg(200, "登录成功", map);
		}else {
			return new ResultMsg(500, "用户名或密码错误！");
		}
	}
	/**
	 * 注册
	 * @return
	 */
	@Transactional
	@PostMapping("register")
	public ResultMsg register(String dataJson) {
		ResultMsg msg = new ResultMsg(500, "注册失败");
		JSONObject jb = null;
		String userName = "";
		String password = "";
		String phone = "";
		String discription = "";
		if(StringUtils.isBlank(dataJson)) {
			return new ResultMsg(400, "请求参数为空");
		}
		jb = JSON.parseObject(dataJson);
		
		if(StringUtils.isBlank(jb.getString("userName"))) {
			return new ResultMsg(400, "用户名不能为空");
		}
		if(StringUtils.isBlank(jb.getString("password"))){
			return new ResultMsg(400, "密码不能为空");
		}
		if(StringUtils.isBlank(jb.getString("phone"))){
			return new ResultMsg(400, "手机号不能为空");
		}
		userName = jb.getString("userName");
		password = DigestUtils.sha256Hex(DigestUtils.md5Hex(jb.getString("password")));
		phone = jb.getString("phone");
		discription = jb.getString("discription");
		
		XUser user = new XUser(userName, password, phone, discription);
		if(userService.save(user)) {
			msg = new ResultMsg(200, "注册成功", user);
		}else {
			msg = new ResultMsg(500, "注册失败");
		}
		
		return msg;
	}
	/**
	 * 修改用户信息
	 * @param dataJson
	 * @return
	 */
	@Transactional
	@PostMapping("changeUser")
	public ResultMsg changeUser(String dataJson) {
		ResultMsg msg = new ResultMsg(500, "修改失败");
		JSONObject jb = null;
		Integer userId = null;
		String userName = "";
		String phone = "";
		String discription = "";
		if(StringUtils.isBlank(dataJson)) {
			return new ResultMsg(400, "请求参数为空");
		}
		jb = JSON.parseObject(dataJson);
		if(StringUtils.isBlank(jb.getString("userId"))) {
			return new ResultMsg(400, "用户id不能为空");
		}
		if(StringUtils.isBlank(jb.getString("userName"))) {
			return new ResultMsg(400, "用户名不能为空");
		}
		
		userId = jb.getInteger("userId");
		userName = jb.getString("userName");
		phone = jb.getString("phone");
		discription = jb.getString("discription");
		
		XUser user = new XUser(userName, phone, discription);
		if(userService.update(user, new UpdateWrapper<XUser>().eq("id", userId))) {
			msg = new ResultMsg(200, "修改成功", user);
		}else {
			msg = new ResultMsg(500, "修改失败");
		}
		return msg;
	}
	/**
	 * 修改密码
	 * @param dataJson
	 * @return
	 */
	@Transactional
	@PostMapping("changePwd")
	public ResultMsg changePwd(String dataJson) {
		JSONObject jb = null;
		if(StringUtils.isNotBlank(dataJson)) {
			jb = JSON.parseObject(dataJson);
			Integer userId = jb.getInteger("userId");
			String oldPwd = DigestUtils.sha256Hex(DigestUtils.md5Hex(jb.getString("oldPwd")));
			String newPwd = DigestUtils.sha256Hex(DigestUtils.md5Hex(jb.getString("newPwd")));
			XUser user = userService.getOne(new QueryWrapper<XUser>().eq("id", userId).eq("password", oldPwd));
			if(user == null) {
				return new ResultMsg(400, "原密码错误");
			}
			user.setPassword(newPwd);
			if(userService.updateById(user)) {
				return new ResultMsg(200, "修改成功，请重新登录！");
			}
		}
		return new ResultMsg();
	}
	/**
	 * 获取用户信息
	 * @param dataJson
	 * @return
	 */
	@PostMapping("getUserById")
	public ResultMsg getUserById(String dataJson) {
		ResultMsg msg = new ResultMsg(500, "获取用户信息失败");
		JSONObject jb = null;
		Integer userId = null;
		if(StringUtils.isBlank(dataJson)) {
			return new ResultMsg(400, "请求参数为空");
		}
		jb = JSON.parseObject(dataJson);
		
		if(StringUtils.isBlank(jb.getString("userId"))) {
			return new ResultMsg(400, "用户id不能为空");
		}
		userId = jb.getInteger("userId");
		XUser user = userService.getById(userId);
		if(user!=null) {
			msg = new ResultMsg(200, "获取用户信息成功", user);
		}
		return msg;
	}
	
}
