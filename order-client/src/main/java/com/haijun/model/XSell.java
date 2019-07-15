package com.haijun.model;

import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import com.baomidou.mybatisplus.annotation.TableId;
import java.time.LocalDateTime;
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
@TableName("tx_sell")
public class XSell extends Model<XSell> {

    private static final long serialVersionUID = 1L;

    /**
     * 此次出货所属的订单编号
     */
    @TableField("order_id")
    private String orderId;

    /**
     * 出货单号，后台用日期生成并录入的
     */
    @TableId("sell_numbers")
    private String sellNumbers;

    /**
     * 出货经手人
     */
    @TableField("sell_person")
    private String sellPerson;

    /**
     * 出货日期
     */
    @TableField("sell_date")
    private String sellDate;

    /**
     * 本次出货总价格
     */
    @TableField("sell_totalPrice")
    private Double sellTotalprice;
    
    /**
     * 此次出货所属的订单编号
     */
    @TableField("remarks")
    private String remarks;
    

    public String getRemarks() {
		return remarks;
	}

	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}

	public String getOrderId() {
        return orderId;
    }

    public XSell setOrderId(String orderId) {
        this.orderId = orderId;
        return this;
    }
    public String getSellNumbers() {
        return sellNumbers;
    }

    public XSell setSellNumbers(String sellNumbers) {
        this.sellNumbers = sellNumbers;
        return this;
    }
    public String getSellPerson() {
        return sellPerson;
    }

    public XSell setSellPerson(String sellPerson) {
        this.sellPerson = sellPerson;
        return this;
    }
    public Double getSellTotalprice() {
        return sellTotalprice;
    }

    public String getSellDate() {
		return sellDate;
	}

	public void setSellDate(String sellDate) {
		this.sellDate = sellDate;
	}

	public XSell setSellTotalprice(Double sellTotalprice) {
        this.sellTotalprice = sellTotalprice;
        return this;
    }

    @Override
    protected Serializable pkVal() {
        return this.sellNumbers;
    }

	@Override
	public String toString() {
		return "XSell [orderId=" + orderId + ", sellNumbers=" + sellNumbers + ", sellPerson=" + sellPerson
				+ ", sellDate=" + sellDate + ", sellTotalprice=" + sellTotalprice + ", remarks=" + remarks + "]";
	}

  
}
