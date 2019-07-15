package com.haijun.mapper;

import com.haijun.model.XOrder;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Select;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author funton
 * @since 2019-04-15
 */
public interface XOrderMapper extends BaseMapper<XOrder> {
	/**
	 * 分页按条件查询订单
	 * @param page
	 * @param orderId
	 * @param companyName
	 * @param createDate
	 * @return
	 */
	public List<XOrder> getOrderListPage(Page page, Map<String, Object> param);
	/**
	 * 主动关闭订单
	 * @param param
	 * @return
	 */
	public int closeOrder(Map<String, Object> param);
	/**
	 * 统计折线图报表
	 * @param year
	 * @return
	 */
	public List<Map<String, Object>> getFinished(String year);
	
	@Select("SELECT COUNT(1) count, LEFT(o.create_date, 7) date\r\n" + 
			"FROM tx_order o\r\n" + 
			"WHERE o.create_date LIKE CONCAT(#{year},'-%')\r\n" + 
			"GROUP BY LEFT(o.create_date, 7)")
	public List<Map<String, Object>> getAddedOrderCount(String year);
}