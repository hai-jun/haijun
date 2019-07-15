package com.haijun.model;

import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import com.baomidou.mybatisplus.annotation.TableId;
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
@TableName("tx_order")
public class XOrder extends Model<XOrder> {

    private static final long serialVersionUID = 1L;

    /**
     * 订单id，一条订单可包含多条订单明细
     */
    @TableId("order_id")
    private String orderId;

    /**
     * 发单公司
     */
    @TableField("company_name")
    private String companyName;

    /**
     * 订单创建时间
     */
    @TableField("create_date")
    private String createDate;
    
    /**
     * 订单截止事件
     */
    @TableField("term_date")
    private String termDate;
    
    /**
     * 结束日期（可以为空）
     */
    @TableField("end_date")
    private String endDate;

    /**
     * 接单人
     */
    @TableField("person")
    private String person;

    /**
     * 订单状态（默认0：未完成，1：已完成）
     */
    @TableField("order_status")
    private Integer orderStatus;
    
    /**
     * 备注
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

    public XOrder setOrderId(String orderId) {
        this.orderId = orderId;
        return this;
    }
    public String getCompanyName() {
        return companyName;
    }

    public XOrder setCompanyName(String companyName) {
        this.companyName = companyName;
        return this;
    }
    public String getCreateDate() {
        return createDate;
    }

    public XOrder setCreateDate(String createDate) {
        this.createDate = createDate;
        return this;
    }
    public String getEndDate() {
        return endDate;
    }

    public XOrder setEndDate(String endDate) {
        this.endDate = endDate;
        return this;
    }
    public String getPerson() {
        return person;
    }

    public XOrder setPerson(String person) {
        this.person = person;
        return this;
    }
    public Integer getOrderStatus() {
        return orderStatus;
    }

    public XOrder setOrderStatus(Integer orderStatus) {
        this.orderStatus = orderStatus;
        return this;
    }

    @Override
    protected Serializable pkVal() {
        return this.orderId;
    }

	public String getTermDate() {
		return termDate;
	}

	public void setTermDate(String termDate) {
		this.termDate = termDate;
	}

	@Override
	public String toString() {
		return "XOrder [orderId=" + orderId + ", companyName=" + companyName + ", createDate=" + createDate
				+ ", termDate=" + termDate + ", endDate=" + endDate + ", person=" + person + ", orderStatus="
				+ orderStatus + ", remarks=" + remarks + "]";
	}

	
   
}
