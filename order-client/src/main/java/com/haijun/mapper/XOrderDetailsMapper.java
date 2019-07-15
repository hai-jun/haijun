package com.haijun.mapper;

import com.haijun.model.XOrderDetails;

import java.util.List;
import java.util.Map;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author funton
 * @since 2019-04-15
 */
public interface XOrderDetailsMapper extends BaseMapper<XOrderDetails> {
	public List<Map<String, Object>> selectOrderDetail(String orderId);
	/**
	 * 查询出订单明细，作为出货时使用
	 * @param orderId
	 * @return
	 */
	public List<Map<String, Object>> getOrderDetailForSelect(String orderId);
}
