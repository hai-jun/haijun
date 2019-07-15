package com.haijun.mapper;

import com.haijun.model.XJinhuo;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

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
public interface XJinhuoMapper extends BaseMapper<XJinhuo> {
	/**
	 * 根据条件分页查询进货列表
	 * @param page
	 * @param map
	 * @return
	 */
	public List<XJinhuo> getJHList(Page<XJinhuo> page,Map<String, Object> map);
	
	/**
	 * 根据月份获取支出
	 * @param year
	 * @param month
	 * @return
	 */
	public double getZhichu(@Param("year")String year,@Param("month")int month);
}
