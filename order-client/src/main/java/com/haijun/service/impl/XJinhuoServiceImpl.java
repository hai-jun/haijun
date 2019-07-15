package com.haijun.service.impl;

import com.haijun.model.XJinhuo;
import com.haijun.mapper.XJinhuoMapper;
import com.haijun.service.IXJinhuoService;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author funton
 * @since 2019-04-15
 */
@Service
public class XJinhuoServiceImpl extends ServiceImpl<XJinhuoMapper, XJinhuo> implements IXJinhuoService {
	@Autowired
	private XJinhuoMapper jhMapper;
	@Override
	public List<XJinhuo> getJHList(Page<XJinhuo> page, String jinhuoNumber, String jinhuoCompany, String jinhuoDate) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("jinhuoNumber", jinhuoNumber);
		map.put("jinhuoCompany", jinhuoCompany);
		map.put("jinhuoDate", jinhuoDate);
		
		return jhMapper.getJHList(page, map);
	}
	@Override
	public double getZhichu(String year, int month) {
		return jhMapper.getZhichu(year, month);
	}

}
