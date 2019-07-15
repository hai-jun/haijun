package com.haijun.mapper;

import com.haijun.model.XStock;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author funton
 * @since 2019-04-15
 */
public interface XStockMapper extends BaseMapper<XStock> {
	/**
	 * 查询库存
	 * @param goodsName
	 * @return
	 */
	public List<XStock> getStock(Page<XStock> page);
	/**
	 * 更新库存
	 * @param goodsId
	 * @param goodsCount
	 * @return
	 */
	public int updataStock(@Param("goodsId")Integer goodsId,@Param("goodsCount")double goodsCount);
	/**
	 * 查询库存，用作下拉框展示
	 * @return
	 */
	public List<Map<String, Object>> selectStock();
	/**
	 * 查询所有的铁线名称
	 * @return
	 */
	@Select("SELECT goods_name FROM tx_stock")
	public List<String> getGoodsTypeList();
}
