package com.haijun.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.haijun.util.ResultMsg;


@Service
public class HystrixLoginFeignFallback implements LoginFeignApi{

	private Logger logger = LoggerFactory.getLogger(HystrixLoginFeignFallback.class);
	
	@Override
	public ResultMsg login(String userName,String password) {
		logger.error("注册服务调用失败，参数："+userName);
		return new ResultMsg(500, "注册服务调用失败");
	}

}
