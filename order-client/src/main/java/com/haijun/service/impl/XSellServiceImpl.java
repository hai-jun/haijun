package com.haijun.service.impl;

import com.haijun.model.XSell;
import com.haijun.mapper.XSellMapper;
import com.haijun.service.IXSellService;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
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
 * @since 2019-04-15
 */
@Service
public class XSellServiceImpl extends ServiceImpl<XSellMapper, XSell> implements IXSellService {

	@Autowired
	private XSellMapper sellMapper;
	@Override
	public List<XSell> getSellLists(String orderNum) {
		
		return sellMapper.selectList(new QueryWrapper<XSell>().eq("order_id", orderNum));
	}
	@Override
	public double totalPriceByMonth(String year, int month) {
		return sellMapper.totalPriceByMonth(year, month);
	}

}
