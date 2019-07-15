package com.haijun.service;

import com.haijun.model.XOrder;

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
public interface IXOrderService extends IService<XOrder> {
	/**
	 * 分页按条件查询订单
	 * @param page
	 * @param orderId
	 * @param companyName
	 * @param createDate
	 * @return
	 */
	public List<XOrder> getOrderListPage(Page<XOrder> page,String orderId,String companyName,String createDateOn,String createEnd,Integer orderStatus);
	/**
	 * 主动关闭订单
	 * @param param
	 * @return
	 */
	public int closeOrder(String orderId,Integer orderStatus,String endDate);
	/**
	 * 统计折线图报表
	 * @param year
	 * @return
	 */
	public List<Integer> getFinished(String year);
	public List<Integer> getAddedOrderCount(String year);
}
