package com.haijun.model;

import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;

import java.io.Serializable;

/**
 * <p>
 * 
 * </p>
 *
 * @author funton
 * @since 2019-04-15
 */
@TableName("tx_jinhuo_details")
public class XJinhuoDetails extends Model<XJinhuoDetails> {

    private static final long serialVersionUID = 1L;

    /**
     * 订单详情id
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;
    
    /**
     * 进货单号
     */
    @TableField("jinhuo_numbers")
    private String jinhuoNumbers;

    /**
     * 货物编号
     */
    @TableField("goods_id")
    private Integer goodsId;

    /**
     * 货物数目
     */
    @TableField("goods_count")
    private Double goodsCount;

    /**
     * 由于每个供应商的价位不一致，所以计算支出时以此价位为准
     */
    @TableField("goods_singlePrice")
    private Double goodsSingleprice;

    /**
     * 货物数目
     */
    @TableField("smailtatal")
    private Double smailtatal;

    /**
     * 单位
     */
    @TableField("unit")
    private String unit;
    /**
     * 备注
     */
    @TableField("remarks")
    private String remarks;

    
    public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getJinhuoNumbers() {
        return jinhuoNumbers;
    }

    public XJinhuoDetails setJinhuoNumbers(String jinhuoNumbers) {
        this.jinhuoNumbers = jinhuoNumbers;
        return this;
    }
    public Integer getGoodsId() {
        return goodsId;
    }

    public XJinhuoDetails setGoodsId(Integer goodsId) {
        this.goodsId = goodsId;
        return this;
    }
    public Double getGoodsCount() {
        return goodsCount;
    }

    public XJinhuoDetails setGoodsCount(Double goodsCount) {
        this.goodsCount = goodsCount;
        return this;
    }
    public Double getGoodsSingleprice() {
        return goodsSingleprice;
    }

    public XJinhuoDetails setGoodsSingleprice(Double goodsSingleprice) {
        this.goodsSingleprice = goodsSingleprice;
        return this;
    }

    public String getRemarks() {
		return remarks;
	}

	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}

	public Double getSmailtatal() {
		return smailtatal;
	}

	public void setSmailtatal(Double smailtatal) {
		this.smailtatal = smailtatal;
	}

	public String getUnit() {
		return unit;
	}

	public void setUnit(String unit) {
		this.unit = unit;
	}

	@Override
    protected Serializable pkVal() {
        return null;
    }

	@Override
	public String toString() {
		return "XJinhuoDetails [id=" + id + ", jinhuoNumbers=" + jinhuoNumbers + ", goodsId=" + goodsId
				+ ", goodsCount=" + goodsCount + ", goodsSingleprice=" + goodsSingleprice + ", smailtatal=" + smailtatal
				+ ", unit=" + unit + ", remarks=" + remarks + "]";
	}

	

}
