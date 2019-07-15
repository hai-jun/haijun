package com.haijun.service;

import com.haijun.model.XJinhuoDetails;

import java.util.List;
import java.util.Map;

import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author funton
 * @since 2019-04-15
 */
public interface IXJinhuoDetailsService extends IService<XJinhuoDetails> {
	
	/**
	 * 根据进货单号查询明细
	 * @param jinhuoNumber
	 * @return
	 */
	public List<Map<String, Object>> selectDetails(String jinhuoNumber);
}
