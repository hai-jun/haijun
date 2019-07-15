package com.haijun.controller;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RestController;

import com.haijun.model.XSysLogs;
import com.haijun.service.IXSysLogsService;
import com.haijun.utils.ResultMsg;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author funton
 * @since 2019-05-17
 */
@RestController
@RequestMapping("/xSysLogs")
public class XSysLogsController {
	@Autowired
	private IXSysLogsService sysLogsService;
	
	@PostMapping("/selectLogs")
	public ResultMsg selectLogs() {
		List<XSysLogs> list = sysLogsService.selectLogs();
		if(list!=null) {
			return new ResultMsg(200, "success", list);
		}
		return new ResultMsg(500, "获取日志信息失败！");
	}
}
