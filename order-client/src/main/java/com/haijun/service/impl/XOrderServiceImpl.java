package com.haijun.service.impl;

import com.haijun.model.XOrder;
import com.haijun.mapper.XOrderMapper;
import com.haijun.service.IXOrderService;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;

import java.util.ArrayList;
import java.util.Date;
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
public class XOrderServiceImpl extends ServiceImpl<XOrderMapper, XOrder> implements IXOrderService {
	@Autowired
	private XOrderMapper orderMapper;
	@Override
	public List<XOrder> getOrderListPage(Page<XOrder> page, String orderId, String companyName, String createDateOn,String createDateEnd,Integer orderStatus) {
		
		Map<String, Object> param = new HashMap<String, Object>();
		param.put("orderId", orderId);
		param.put("companyName", companyName);
		param.put("createDateOn", createDateOn);
		param.put("createDateEnd", createDateEnd);
		param.put("orderStatus", orderStatus);
		List<XOrder> orderList = orderMapper.getOrderListPage(page, param);
		return orderList;
	}
	@Override
	public int closeOrder(String orderId,Integer orderStatus,String endDate) {
		Map<String, Object> param = new HashMap<String, Object>();
		param.put("orderId", orderId);
		param.put("orderStatus", orderStatus);
		param.put("endDate", endDate);
		return orderMapper.closeOrder(param);
	}
	@Override
	public List<Integer> getFinished(String year) {
		List<Map<String, Object>> initList = orderMapper.getFinished(year);
		List<Integer> needsList = new ArrayList<Integer>();
		Date date = new Date();
		int moh = date.getMonth()+1;
		int thisYear = date.getYear()+1900;
		if(thisYear != Integer.parseInt(year)) {
			moh = 12;
		}
		for (int i = 0; i < moh; i++) {
			needsList.add(0);
		}
		for (Map<String, Object> map : initList) {
			String month = map.get("date").toString().substring(5);
			Integer monthInt = Integer.parseInt(month)-1;
			Integer count = Integer.parseInt(map.get("count").toString());
			needsList.set(monthInt, count);
		}
		return needsList;
	}
	@Override
	public List<Integer> getAddedOrderCount(String year) {
		List<Map<String, Object>> initList = orderMapper.getAddedOrderCount(year);
		List<Integer> needsList = new ArrayList<Integer>();
		Date date = new Date();
		int moh = date.getMonth()+1;
		int thisYear = date.getYear()+1900;
		if(thisYear != Integer.parseInt(year)) {
			moh = 12;
		}
		for (int i = 0; i < moh; i++) {
			needsList.add(0);
		}
		for (Map<String, Object> map : initList) {
			String month = map.get("date").toString().substring(5);
			Integer monthInt = Integer.parseInt(month)-1;
			Integer count = Integer.parseInt(map.get("count").toString());
			needsList.set(monthInt, count);
		}
		return needsList;
	}
	
}
