package com.haijun.service;

import com.haijun.model.XJinhuo;

import java.util.List;

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
public interface IXJinhuoService extends IService<XJinhuo> {
	/**
	 * 根据条件分页查询进货列表
	 * @param page
	 * @param jinhuoNumber
	 * @param jinhuoCompany
	 * @param jinhuoDate
	 * @return
	 */
	public List<XJinhuo> getJHList(Page<XJinhuo> page,String jinhuoNumber,String jinhuoCompany,String jinhuoDate);
	/**
	 * 根据月份获取支出
	 * @param year
	 * @param month
	 * @return
	 */
	public double getZhichu(String year,int month);
}
