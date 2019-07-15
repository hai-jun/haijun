package com.haijun.service.impl;

import com.haijun.model.SkipApi;
import com.haijun.mapper.SkipApiMapper;
import com.haijun.service.ISkipApiService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author funton
 * @since 2019-05-13
 */
@Service
public class SkipApiServiceImpl extends ServiceImpl<SkipApiMapper, SkipApi> implements ISkipApiService {
	@Autowired
	private SkipApiMapper skipApiMapper;
	@Override
	public SkipApi verification(String url) {
		
		return skipApiMapper.verification(url);
	}
}
