package com.haijun.mapper;

import com.haijun.model.XSell;

import org.apache.ibatis.annotations.Param;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author funton
 * @since 2019-04-15
 */
public interface XSellMapper extends BaseMapper<XSell> {
	/**
	 * 查询月度收入
	 * @param year
	 * @param month
	 * @return
	 */
	public double totalPriceByMonth(@Param("year")String year,@Param("month")int month);
}
