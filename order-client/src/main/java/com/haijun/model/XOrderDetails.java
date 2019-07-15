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
@TableName("tx_order_details")
public class XOrderDetails extends Model<XOrderDetails> {

    private static final long serialVersionUID = 1L;
    
    /**
     * 订单详情id
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;
    /**
     * 订单编号
     */
    @TableField("order_id")
    private String orderId;
    /**
     * 货物编号
     */
    @TableField("detail_name")
    private String detailName;
    public String getDetailName() {
		return detailName;
	}

	public void setDetailName(String detailName) {
		this.detailName = detailName;
	}

	/**
     * 货物编号
     */
    @TableField("goods_id")
    private Integer goodsId;

    /**
     * 需要的铁线数目
     */
    @TableField("goods_needsTiexian_count")
    private Double goodsNeedstiexianCount;

    /**
     * 需要的纸卷数目
     */
    @TableField("goods_needsZhijuan_count")
    private Double goodsNeedszhijuanCount;

    /**
     * 已出货的货物数目
     */
    @TableField("goods_selled_count")
    private Double goodsSelledCount;

    /**
     * 货物单位
     */
    @TableField("goods_unit")
    private String goodsUnit;
    
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

	public String getRemarks() {
		return remarks;
	}

	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	public String getOrderId() {
        return orderId;
    }
    

    public XOrderDetails setOrderId(String orderId) {
        this.orderId = orderId;
        return this;
    }
    public Integer getGoodsId() {
        return goodsId;
    }

    public XOrderDetails setGoodsId(Integer goodsId) {
        this.goodsId = goodsId;
        return this;
    }
    public Double getGoodsNeedstiexianCount() {
        return goodsNeedstiexianCount;
    }

    public XOrderDetails setGoodsNeedstiexianCount(Double goodsNeedstiexianCount) {
        this.goodsNeedstiexianCount = goodsNeedstiexianCount;
        return this;
    }
    public Double getGoodsNeedszhijuanCount() {
        return goodsNeedszhijuanCount;
    }

    public XOrderDetails setGoodsNeedszhijuanCount(Double goodsNeedszhijuanCount) {
        this.goodsNeedszhijuanCount = goodsNeedszhijuanCount;
        return this;
    }
    public Double getGoodsSelledCount() {
        return goodsSelledCount;
    }

    public XOrderDetails setGoodsSelledCount(Double goodsSelledCount) {
        this.goodsSelledCount = goodsSelledCount;
        return this;
    }
    public String getGoodsUnit() {
        return goodsUnit;
    }

    public XOrderDetails setGoodsUnit(String goodsUnit) {
        this.goodsUnit = goodsUnit;
        return this;
    }

    @Override
    protected Serializable pkVal() {
        return null;
    }

	@Override
	public String toString() {
		return "XOrderDetails [id=" + id + ", orderId=" + orderId + ", detailName=" + detailName + ", goodsId="
				+ goodsId + ", goodsNeedstiexianCount=" + goodsNeedstiexianCount + ", goodsNeedszhijuanCount="
				+ goodsNeedszhijuanCount + ", goodsSelledCount=" + goodsSelledCount + ", goodsUnit=" + goodsUnit
				+ ", remarks=" + remarks + "]";
	}

    
}
