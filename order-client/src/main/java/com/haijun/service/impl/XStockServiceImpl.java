package com.haijun.service.impl;

import com.haijun.model.XStock;
import com.haijun.mapper.XStockMapper;
import com.haijun.service.IXStockService;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
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
public class XStockServiceImpl extends ServiceImpl<XStockMapper, XStock> implements IXStockService {
	@Autowired
	private XStockMapper stockMapper;
	@Override
	public List<XStock> getStock(Page<XStock> page) {
		return stockMapper.getStock(page);
	}
	@Override
	public int updataStock(Integer goodsId, double goodsCount) {
		return stockMapper.updataStock(goodsId, goodsCount);
	}
	@Override
	public List<Map<String, Object>> selectStock() {
		return stockMapper.selectStock();
	}
	@Override
	public List<String> getGoodsTypeList() {
		return stockMapper.getGoodsTypeList();
	}

}
