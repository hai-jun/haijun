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
@TableName("tx_jinhuo")
public class XJinhuo extends Model<XJinhuo> {

    private static final long serialVersionUID = 1L;

    /**
     * 进货单号
     */
    @TableId("jinhuo_number")
    private String jinhuoNumber;

    /**
     * 进货日期
     */
    @TableField("jinhuo_date")
    private String jinhuoDate;

    /**
     * 此进货单总价格
     */
    @TableField("jinhuo_totalPrice")
    private Double jinhuoTotalprice;

    /**
     * 经手人
     */
    @TableField("jinhuo_person")
    private String jinhuoPerson;

    /**
     * 来源公司
     */
    @TableField("jinhuo_company")
    private String jinhuoCompany;
    
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

	public String getJinhuoNumber() {
        return jinhuoNumber;
    }

    public XJinhuo setJinhuoNumber(String jinhuoNumber) {
        this.jinhuoNumber = jinhuoNumber;
        return this;
    }
    public String getJinhuoDate() {
        return jinhuoDate;
    }

    public XJinhuo setJinhuoDate(String jinhuoDate) {
        this.jinhuoDate = jinhuoDate;
        return this;
    }
    public Double getJinhuoTotalprice() {
        return jinhuoTotalprice;
    }

    public XJinhuo setJinhuoTotalprice(Double jinhuoTotalprice) {
        this.jinhuoTotalprice = jinhuoTotalprice;
        return this;
    }
    public String getJinhuoPerson() {
        return jinhuoPerson;
    }

    public XJinhuo setJinhuoPerson(String jinhuoPerson) {
        this.jinhuoPerson = jinhuoPerson;
        return this;
    }
    public String getJinhuoCompany() {
        return jinhuoCompany;
    }

    public XJinhuo setJinhuoCompany(String jinhuoCompany) {
        this.jinhuoCompany = jinhuoCompany;
        return this;
    }

    @Override
    protected Serializable pkVal() {
        return this.jinhuoNumber;
    }

	@Override
	public String toString() {
		return "XJinhuo [jinhuoNumber=" + jinhuoNumber + ", jinhuoDate=" + jinhuoDate + ", jinhuoTotalprice="
				+ jinhuoTotalprice + ", jinhuoPerson=" + jinhuoPerson + ", jinhuoCompany=" + jinhuoCompany
				+ ", remarks=" + remarks + "]";
	}


}
