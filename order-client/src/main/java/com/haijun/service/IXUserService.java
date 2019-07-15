package com.haijun.service;

import com.haijun.model.XUser;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author funton
 * @since 2019-04-17
 */
public interface IXUserService extends IService<XUser> {
	/**
	 * 用户登录
	 * @param userName
	 * @param password
	 * @return
	 */
	public int login(String userName,String password);
}
