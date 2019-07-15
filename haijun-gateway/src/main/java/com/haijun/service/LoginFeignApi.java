package com.haijun.service;

import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.haijun.util.ResultMsg;


@FeignClient(name = "haijun-order-client", fallback = HystrixLoginFeignFallback.class)
public interface LoginFeignApi {

	@RequestMapping(value = "/xUser/login", method = RequestMethod.POST)
	public ResultMsg login(@RequestParam("userName")String userName,@RequestParam("password") String password);
}
