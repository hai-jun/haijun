package com.haijun.service;

import com.haijun.model.XSell;

import java.util.List;

import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author funton
 * @since 2019-04-15
 */
public interface IXSellService extends IService<XSell> {
	/**
	 * 查询一条订单的出货记录
	 * @param orderNum
	 * @return
	 */
	public List<XSell> getSellLists(String orderNum);
	/**
	 * 查询月度收入
	 * @param year
	 * @param month
	 * @return
	 */
	public double totalPriceByMonth(String year,int month);
}
