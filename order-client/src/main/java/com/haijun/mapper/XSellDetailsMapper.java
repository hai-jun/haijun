package com.haijun.mapper;

import com.haijun.model.XSellDetails;

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
public interface XSellDetailsMapper extends BaseMapper<XSellDetails> {
	/**
	 * 查询出货明细
	 * @param sellNumber
	 * @return
	 */
	public List<Map<String, Object>> selectSellDetails(String sellNumber);
	/**
	 * 根据月份查询出货的商品的名称和数量
	 * @param date
	 * @return
	 */
	public List<Map<String, Object>> getGoodsTotalList(String date);
}
