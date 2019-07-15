package com.haijun.service;

import com.haijun.model.XStock;

import java.util.List;
import java.util.Map;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author funton
 * @since 2019-04-15
 */
public interface IXStockService extends IService<XStock> {
	/**
	 * 按照商品名称模糊查询库存
	 * @param goodsName
	 * @return
	 */
	public List<XStock> getStock(Page<XStock> page);
	/**
	 * 更新库存
	 * @param goodsId
	 * @param goodsCount
	 * @return
	 */
	public int updataStock(Integer goodsId, double goodsCount);
	/**
	 * 查询库存，用作下拉框展示
	 * @return
	 */
	public List<Map<String, Object>> selectStock();
	/**
	 * 查询所有的铁线名称
	 * @return
	 */
	public List<String> getGoodsTypeList();
}
