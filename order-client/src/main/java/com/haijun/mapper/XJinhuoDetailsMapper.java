package com.haijun.mapper;

import com.haijun.model.XJinhuoDetails;

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
public interface XJinhuoDetailsMapper extends BaseMapper<XJinhuoDetails> {
	
	/**
	 * 根据进货单号查询明细
	 * @param jinhuoNumber
	 * @return
	 */
	public List<Map<String, Object>> selectDetails(String jinhuoNumber);
}
