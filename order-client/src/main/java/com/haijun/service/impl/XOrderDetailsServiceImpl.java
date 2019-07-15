package com.haijun.service.impl;

import com.haijun.model.XOrderDetails;
import com.haijun.mapper.XOrderDetailsMapper;
import com.haijun.service.IXOrderDetailsService;
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
public class XOrderDetailsServiceImpl extends ServiceImpl<XOrderDetailsMapper, XOrderDetails> implements IXOrderDetailsService {
	@Autowired
	private XOrderDetailsMapper detailsMapper;
	@Override
	public List<Map<String, Object>> selectOrderDetail(String orderId) {
		return detailsMapper.selectOrderDetail(orderId);
	}
	@Override
	public List<Map<String, Object>> getOrderDetailForSelect(String orderId) {
		return detailsMapper.getOrderDetailForSelect(orderId);
	}

}
