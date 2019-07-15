package com.haijun.service.impl;

import com.haijun.model.XSysLogs;
import com.haijun.mapper.XSysLogsMapper;
import com.haijun.service.IXSysLogsService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author funton
 * @since 2019-05-17
 */
@Service
public class XSysLogsServiceImpl extends ServiceImpl<XSysLogsMapper, XSysLogs> implements IXSysLogsService {
	
	@Autowired
	private XSysLogsMapper logsMapper;
	@Override
	public List<XSysLogs> selectLogs() {
		return logsMapper.selectLogs();
	}

}
