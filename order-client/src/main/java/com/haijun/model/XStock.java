package com.haijun.model;

import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.annotation.IdType;
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
@TableName("tx_stock")
public class XStock extends Model<XStock> {

    private static final long serialVersionUID = 1L;

    /**
     * 货物编号
     */
    @TableId(value = "goods_id", type = IdType.AUTO)
    private Integer goodsId;

    @TableField("goods_name")
    private String goodsName;

    /**
     * 货物单位
     */
    @TableField("goods_unit")
    private String goodsUnit;

    /**
     * 货物数目
     */
    @TableField("goods_count")
    private Double goodsCount;

    /**
     * 货物参考单价（实际上以进货公司给的价格为准）
     */
    @TableField("goods_price")
    private Double goodsPrice;

    /**
     * 标识（1：铁线，2：纸卷）
     */
    @TableField("goods_tips")
    private Integer goodsTips;

    public Integer getGoodsId() {
        return goodsId;
    }

    public XStock setGoodsId(Integer goodsId) {
        this.goodsId = goodsId;
        return this;
    }
    public String getGoodsName() {
        return goodsName;
    }

    public XStock setGoodsName(String goodsName) {
        this.goodsName = goodsName;
        return this;
    }
    public String getGoodsUnit() {
        return goodsUnit;
    }

    public XStock setGoodsUnit(String goodsUnit) {
        this.goodsUnit = goodsUnit;
        return this;
    }
    public Double getGoodsCount() {
        return goodsCount;
    }

    public XStock setGoodsCount(Double goodsCount) {
        this.goodsCount = goodsCount;
        return this;
    }
    public Double getGoodsPrice() {
        return goodsPrice;
    }

    public XStock setGoodsPrice(Double goodsPrice) {
        this.goodsPrice = goodsPrice;
        return this;
    }
    public Integer getGoodsTips() {
        return goodsTips;
    }

    public XStock setGoodsTips(Integer goodsTips) {
        this.goodsTips = goodsTips;
        return this;
    }

    @Override
    protected Serializable pkVal() {
        return this.goodsId;
    }

    @Override
    public String toString() {
        return "XStock{" +
        "goodsId=" + goodsId +
        ", goodsName=" + goodsName +
        ", goodsUnit=" + goodsUnit +
        ", goodsCount=" + goodsCount +
        ", goodsPrice=" + goodsPrice +
        ", goodsTips=" + goodsTips +
        "}";
    }
}
