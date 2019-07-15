package com.haijun.mapper;

import com.haijun.model.SkipApi;

import org.apache.ibatis.annotations.Select;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author funton
 * @since 2019-05-13
 */
public interface SkipApiMapper extends BaseMapper<SkipApi> {
	/**
	 * 校验请求是否是不走网关
	 * @param url
	 * @return
	 */
	@Select("SELECT * FROM skip_api WHERE LOCATE(api_url,#{url})>0")
	public SkipApi verification(String url);
}
