package com.haijun.mapper;

import com.haijun.model.XSysLogs;

import java.util.List;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author funton
 * @since 2019-05-17
 */
public interface XSysLogsMapper extends BaseMapper<XSysLogs> {
	/**
	 * 获取全部日志信息，按日期倒序排列
	 * @return
	 */
	public List<XSysLogs> selectLogs();
}
