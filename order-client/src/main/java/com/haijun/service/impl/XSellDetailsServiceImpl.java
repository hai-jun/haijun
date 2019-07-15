package com.haijun.service.impl;

import com.haijun.model.XSellDetails;
import com.haijun.mapper.XSellDetailsMapper;
import com.haijun.service.IXSellDetailsService;
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
public class XSellDetailsServiceImpl extends ServiceImpl<XSellDetailsMapper, XSellDetails> implements IXSellDetailsService {
	@Autowired
	private XSellDetailsMapper detailsMapper;
	@Override
	public List<Map<String, Object>> selectSellDetails(String sellNumber) {
		return detailsMapper.selectSellDetails(sellNumber);
	}
	@Override
	public List<Map<String, Object>> getGoodsTotalList(String date) {
		return detailsMapper.getGoodsTotalList(date);
	}

}
