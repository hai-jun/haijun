package com.haijun.controller;


import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.interceptor.TransactionAspectSupport;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RestController;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.haijun.model.XPictures;
import com.haijun.service.IXPicturesService;
import com.haijun.utils.ResultMsg;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author funton
 * @since 2019-05-15
 */
@RestController
@RequestMapping("/xPictures")
public class XPicturesController {
	@Autowired
	private IXPicturesService picService;
	/**
	 * 上传图片成功后保存图片地址
	 * @param dataJson
	 * @return
	 */
	@Transactional
	@PostMapping("/savePicUrl")
	public ResultMsg savePicUrl(String dataJson) {
		if(StringUtils.isNotBlank(dataJson)) {
			JSONObject jb = JSON.parseObject(dataJson);
			if(StringUtils.isNotBlank(jb.getString("picUrl"))) {
				String picUrl = jb.getString("picUrl");
				picUrl = picUrl.substring(picUrl.indexOf("[")+2, picUrl.lastIndexOf("]")-1);
//				picUrl = picUrl.substring(0, picUrl.lastIndexOf("?"));
				if(picService.save(new XPictures(picUrl))) {
					return new ResultMsg(200, "success");
				}else {
					TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
				}
			}
		}
		return new ResultMsg(500, "error");
	}
	/**
	 * 获取首页展示的图片的地址
	 * @return
	 */
	@PostMapping("/loadAllPrc")
	public ResultMsg loadAllPrc() {
		List<String> picLists = picService.loadAllPrc();
		if(picLists!=null) {
			return new ResultMsg(200, "success" ,picLists);
		}
		return new ResultMsg(500, "获取图片地址失败！");
	}
}
