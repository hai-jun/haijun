package com.haijun.mapper;

import com.haijun.model.XPictures;

import java.util.List;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author funton
 * @since 2019-05-15
 */
public interface XPicturesMapper extends BaseMapper<XPictures> {
	/**
	 * 查询主页展示的所有图片的地址
	 * @return
	 */
	public List<String> loadAllPrc();
}
