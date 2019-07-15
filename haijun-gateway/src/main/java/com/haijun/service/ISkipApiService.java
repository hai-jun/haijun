package com.haijun.service;

import com.haijun.model.SkipApi;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author funton
 * @since 2019-05-13
 */
public interface ISkipApiService extends IService<SkipApi> {
	/**
	 * 校验请求是否是不走网关
	 * @param url
	 * @return
	 */
	public SkipApi verification(String url);
}
