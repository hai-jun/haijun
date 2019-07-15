package com.haijun.service;

import com.haijun.model.XOrderDetails;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author funton
 * @since 2019-04-15
 */
public interface IXOrderDetailsService extends IService<XOrderDetails> {
	/**
	 * 根据订单id查询出订单明细前端展示所需要的字段
	 * @param orderId
	 * @return
	 */
	public List<Map<String, Object>> selectOrderDetail(@Param("orderId")String orderId);
	
	/**
	 * 查询出订单明细，作为出货时使用
	 * @param orderId
	 * @return
	 */
	public List<Map<String, Object>> getOrderDetailForSelect(String orderId);
}
