package com.haijun.service.impl;

import com.haijun.model.XJinhuoDetails;
import com.haijun.mapper.XJinhuoDetailsMapper;
import com.haijun.service.IXJinhuoDetailsService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author funton
 * @since 2019-04-15
 */
@Service
public class XJinhuoDetailsServiceImpl extends ServiceImpl<XJinhuoDetailsMapper, XJinhuoDetails> implements IXJinhuoDetailsService {
	
	@Autowired
	private XJinhuoDetailsMapper detailsMapper;
	@Override
	public List<Map<String, Object>> selectDetails(String jinhuoNumber) {
		return detailsMapper.selectDetails(jinhuoNumber);
	}

}
