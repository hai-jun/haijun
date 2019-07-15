package com.haijun.service.impl;

import com.haijun.model.XUser;
import com.haijun.mapper.XUserMapper;
import com.haijun.service.IXUserService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author funton
 * @since 2019-04-17
 */
@Service
public class XUserServiceImpl extends ServiceImpl<XUserMapper, XUser> implements IXUserService {

	@Override
	public int login(String userName, String password) {
		
		return 0;
	}

}
