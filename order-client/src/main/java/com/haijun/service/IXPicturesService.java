package com.haijun.service;

import com.haijun.model.XPictures;

import java.util.List;

import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author funton
 * @since 2019-05-15
 */
public interface IXPicturesService extends IService<XPictures> {
	/**
	 * 查询主页展示的所有图片的地址
	 * @return
	 */
	public List<String> loadAllPrc();
}
