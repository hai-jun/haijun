package com.haijun.service;

import com.haijun.model.XSysLogs;

import java.util.List;

import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author funton
 * @since 2019-05-17
 */
public interface IXSysLogsService extends IService<XSysLogs> {
	/**
	 * 获取全部日志信息，按日期倒序排列
	 * @return
	 */
	public List<XSysLogs> selectLogs();
}
