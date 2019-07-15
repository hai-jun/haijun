package com.haijun.model;

import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import com.baomidou.mybatisplus.annotation.TableField;
import java.io.Serializable;

/**
 * <p>
 * 
 * </p>
 *
 * @author funton
 * @since 2019-04-15
 */
@TableName("tx_sell_details")
public class XSellDetails extends Model<XSellDetails> {

    private static final long serialVersionUID = 1L;

    /**
     * 对应订单明细的id
     */
    @TableField("order_details_id")
    private String orderDetailsId;
    /**
     * 出货单号
     */
    @TableField("sell_numbers")
    private String sellNumbers;

    /**
     * 货物编号
     */
    @TableField("goods_id")
    private Integer goodsId;

    /**
     * 货物单价
     */
    @TableField("goods_singlePrice")
    private Double goodsSingleprice;

    /**
     * 货物数目
     */
    @TableField("goods_count")
    private Double goodsCount;

    /**
     * 小计
     */
    @TableField("smail_total")
    private Double smailTotal;

    /**
     * 备注
     */
    @TableField("remarks")
    private String remarks;

    public String getSellNumbers() {
        return sellNumbers;
    }

    public XSellDetails setSellNumbers(String sellNumbers) {
        this.sellNumbers = sellNumbers;
        return this;
    }
    public Integer getGoodsId() {
        return goodsId;
    }

    public XSellDetails setGoodsId(Integer goodsId) {
        this.goodsId = goodsId;
        return this;
    }
    public Double getGoodsSingleprice() {
        return goodsSingleprice;
    }

    public XSellDetails setGoodsSingleprice(Double goodsSingleprice) {
        this.goodsSingleprice = goodsSingleprice;
        return this;
    }
    public Double getGoodsCount() {
        return goodsCount;
    }

    public XSellDetails setGoodsCount(Double goodsCount) {
        this.goodsCount = goodsCount;
        return this;
    }

    @Override
    protected Serializable pkVal() {
        return null;
    }

    public String getOrderDetailsId() {
		return orderDetailsId;
	}

	public void setOrderDetailsId(String orderDetailsId) {
		this.orderDetailsId = orderDetailsId;
	}

	public Double getSmailTotal() {
		return smailTotal;
	}

	public void setSmailTotal(Double smailTotal) {
		this.smailTotal = smailTotal;
	}

	public String getRemarks() {
		return remarks;
	}

	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}

	@Override
	public String toString() {
		return "XSellDetails [orderDetailsId=" + orderDetailsId + ", sellNumbers=" + sellNumbers + ", goodsId="
				+ goodsId + ", goodsSingleprice=" + goodsSingleprice + ", goodsCount=" + goodsCount + ", smailTotal="
				+ smailTotal + ", remarks=" + remarks + "]";
	}


}
