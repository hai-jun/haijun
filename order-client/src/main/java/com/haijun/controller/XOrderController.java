package com.haijun.controller;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import org.apache.commons.codec.digest.DigestUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.interceptor.TransactionAspectSupport;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RestController;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.google.common.collect.Lists;
import com.haijun.model.XOrder;
import com.haijun.model.XOrderDetails;
import com.haijun.model.XUser;
import com.haijun.service.IXOrderDetailsService;
import com.haijun.service.IXOrderService;
import com.haijun.service.IXUserService;
import com.haijun.utils.ResultMsg;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author funton
 * @since 2019-04-15
 */
@RestController
@RequestMapping("/xOrder")
public class XOrderController {
	@Autowired
	private IXOrderService orderService;
	@Autowired
	private IXOrderDetailsService orderDetailsService;
	@Autowired
	private IXUserService userService;
	/**
	 * 分页获取订单列表
	 * @param dataJson
	 * @return
	 */
	@PostMapping("getOrderListPage")
	public ResultMsg getOrderListPage(String dataJson) {
		ResultMsg msg = new ResultMsg(500, "系统异常");
		Integer pageIndex = 1;
		String orderId = null;
		String companyName = "";
		String createDateOn = null;
		String createDateEnd = null;
		Integer orderStatus = null;
		JSONObject json = null;
		if(StringUtils.isBlank(dataJson)) {
			return msg;
		}
		json = JSON.parseObject(dataJson);
		pageIndex = Integer.parseInt(json.getString("page"));
		if(!StringUtils.isBlank(json.getString("orderId"))) {
			orderId = json.getString("orderId");
		}
		if(!StringUtils.isBlank(json.getString("companyName"))) {
			companyName = json.getString("companyName");
		}
		if(!StringUtils.isBlank(json.getString("createDateOn"))) {
			createDateOn = json.getString("createDateOn");
		}
		if(!StringUtils.isBlank(json.getString("createDateEnd"))) {
			createDateEnd = json.getString("createDateEnd");
		}
		if(!StringUtils.isBlank(json.getString("orderStatus"))) {
			orderStatus = Integer.parseInt(json.getString("orderStatus"));
		}
		Page<XOrder> page = new Page<XOrder>();
		page.setSize(10);
		page.setCurrent(pageIndex);
		
		List<XOrder> orderList = orderService.getOrderListPage(page, orderId, companyName, createDateOn,createDateEnd,orderStatus);
		
		Map<String, Object> maps = new HashMap<String, Object>();
		maps.put("total", page.getTotal());
		maps.put("orderList", orderList);
		msg.setCode(200);
		msg.setMsg("success");
		msg.setData(maps);
		return msg;
				
	}
	/**
	 * 在订单详细未完成的情况下关闭订单
	 * @param dataJson
	 * @return
	 */
	@Transactional
	@PostMapping("closeOrder")
	public ResultMsg closeOrder(String dataJson) {
		String orderId = "";
		JSONObject jb = null;
		Integer orderStatus = null;
		
		if(!StringUtils.isBlank(dataJson)) {
			jb = JSON.parseObject(dataJson);
			if(StringUtils.isNotBlank(jb.getString("orderId"))&&jb.getInteger("orderStatus")!=null) {
				orderId = jb.getString("orderId");
				orderStatus = jb.getInteger("orderStatus");
				SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
				String dateStr = df.format(new Date());
				int res = orderService.closeOrder(orderId, orderStatus, dateStr);
				if(res>0) {
					return new ResultMsg(200, "订单关闭成功");
				}
			}
		}
		return new ResultMsg(500, "订单关闭失败");
	}
	/**
	 * 新增订单和订单明细，dataJson格式为
	 * {
	    "orderInfo":{
	        "orderId":"0001",
	        "companyName":"addOrder测试公司",
	        "createDate":"2019-04-22",
	        "person":"admin",
	        "orderStatus":0
	    },
	    "orderDetails":[
	        {
	            "orderId":"0001",
	            "detailName":"23#+6''浅咖啡地卷",
	            "goodsId":4,
	            "goodsNeedstiexianCount":1000,
	            "goodsNeedszhijuanCount":50,
	            "goodsSelledCount":0,
	            "goodsUnit":"kg"
	        }
	    ]
	}
	 * @param dataJson
	 * @return
	 */
	@Transactional
	@PostMapping("addOrder")
	public ResultMsg addOrder(String dataJson) {
		List<XOrderDetails> orderDetails = Lists.newArrayList();
		XOrder order = null;
		JSONObject jb = null;
		if(StringUtils.isNotBlank(dataJson)) {
			jb = JSON.parseObject(dataJson);
			if(jb.getString("orderInfo")!=null) {
				order = JSON.parseObject(jb.getString("orderInfo"), XOrder.class);
				String orderId = UUID.randomUUID().toString().replaceAll("-", "");
				order.setOrderId(orderId);
				if(jb.getString("orderDetails")!=null) {
					orderDetails = JSON.parseArray(jb.getString("orderDetails"), XOrderDetails.class);
					for (XOrderDetails xOrderDetails : orderDetails) {
						xOrderDetails.setOrderId(orderId);
					}
					if(orderDetailsService.saveBatch(orderDetails)) {
						//不知道为什么，前端的el-date-picker的value-format无效，所以只好在这里格式化日期了
						String createDate = order.getCreateDate().substring(0,10);
						order.setCreateDate(createDate);
						if(StringUtils.isNotBlank(order.getTermDate())) {
							String termDate = order.getTermDate().substring(0,10);
							order.setTermDate(termDate);
						}else {
							order.setTermDate(null);
						}
						if(orderService.save(order)) {
							return new ResultMsg(200, "订单和订单明细创建成功");
						}else {
							//如果订单详情保存成功后但是订单保存失败的话，手动回滚
							TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();  
						}
					}
				}
			}
		}
		return  new ResultMsg(500, "订单创建失败");
	}
	/**
	 * 删除订单
	 * @param dataJson
	 * @return
	 */
	@Transactional
	@PostMapping("/removeOrder")
	public ResultMsg removeOrder(String dataJson) {
		if(StringUtils.isNotBlank(dataJson)) {
			JSONObject jb = JSON.parseObject(dataJson);
			if(jb.getInteger("userId") != null&&StringUtils.isNotBlank(jb.getString("password"))) {
				Integer userId = jb.getInteger("userId");
				String password = DigestUtils.sha256Hex(DigestUtils.md5Hex(jb.getString("password")));
				XUser user = userService.getOne(new QueryWrapper<XUser>().eq("id", userId).eq("password", password));
				if(user != null) {
					//验证密码成功，执行删除
					if(StringUtils.isNotBlank(jb.getString("orderId"))) {
						String orderId = jb.getString("orderId");
						//删除订单明细
						if(orderDetailsService.remove(new QueryWrapper<XOrderDetails>().eq("order_id", orderId))) {
							if(orderService.remove(new QueryWrapper<XOrder>().eq("order_id", orderId))) {
								return new ResultMsg(200, "删除完成，此订单已不可恢复");
							}
						}
					}
				}else {
					return new ResultMsg(500, "密码有误，不能删除该订单！");
				}
			}

		}
		return new ResultMsg(500,"系统异常！删除失败");
	}
	
	/**
	 * 出货前首先查询出一条订单信息
	 * @param dataJson
	 * @return
	 */
	@PostMapping("/selectOneOrder")
	public ResultMsg selectOneOrder(String dataJson) {
		if(StringUtils.isNotBlank(dataJson)) {
			JSONObject jb = JSON.parseObject(dataJson);
			if(StringUtils.isNotBlank(jb.getString("orderId"))) {
				String orderId = jb.getString("orderId");
				XOrder orderInfo = orderService.getOne(new QueryWrapper<XOrder>().eq("order_id", orderId));
				List<XOrder> list = new ArrayList<XOrder>();
				list.add(orderInfo);
				return new ResultMsg(200, "success", list);
			}
		}
		return new ResultMsg(500, "查询失败！");
	}
	/**
	 * 订单完成量统计，设计折线图所用
	 * @param dataJson
	 * @return
	 */
	@PostMapping("/getFinishedOrderCount")
	public ResultMsg getFinished(String dataJson) {
		if(StringUtils.isNotBlank(dataJson)) {
			JSONObject jb = JSON.parseObject(dataJson);
			if(StringUtils.isNotBlank(jb.getString("year"))) {
				String year = jb.getString("year").substring(0,4);
				List<Integer> list = orderService.getFinished(year);
				if(list!=null) {
					return new ResultMsg(200, "success", list);
				}
			}
		}
		return new ResultMsg(500, "获取已完成订单数量失败！");
	}
	@PostMapping("/getAddedOrderCount")
	public ResultMsg getAddedOrderCount(String dataJson) {
		if(StringUtils.isNotBlank(dataJson)) {
			JSONObject jb = JSON.parseObject(dataJson);
			if(StringUtils.isNotBlank(jb.getString("year"))) {
				String year = jb.getString("year").substring(0,4);
				List<Integer> list = orderService.getAddedOrderCount(year);
				if(list!=null) {
					return new ResultMsg(200, "success", list);
				}
			}
		}
		return new ResultMsg(500, "获取接单量失败！");
	}
}
