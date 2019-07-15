package com.haijun.service.impl;

import com.haijun.model.XPictures;
import com.haijun.mapper.XPicturesMapper;
import com.haijun.service.IXPicturesService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author funton
 * @since 2019-05-15
 */
@Service
public class XPicturesServiceImpl extends ServiceImpl<XPicturesMapper, XPictures> implements IXPicturesService {
	@Autowired
	private XPicturesMapper picMapper;
	@Override
	public List<String> loadAllPrc() {
		return picMapper.loadAllPrc();
	}

}
