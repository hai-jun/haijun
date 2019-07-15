package com.haijun.model;

import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import java.time.LocalDate;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableField;
import java.io.Serializable;

/**
 * <p>
 * 
 * </p>
 *
 * @author funton
 * @since 2019-05-17
 */
@TableName("tx_sys_logs")
public class XSysLogs extends Model<XSysLogs> {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    @TableField("date")
    private String date;

    @TableField("value")
    private String value;

    public Integer getId() {
        return id;
    }

    public XSysLogs setId(Integer id) {
        this.id = id;
        return this;
    }
    public String getDate() {
        return date;
    }

    public XSysLogs setDate(String date) {
        this.date = date;
        return this;
    }
    public String getValue() {
        return value;
    }

    public XSysLogs setValue(String value) {
        this.value = value;
        return this;
    }

    @Override
    protected Serializable pkVal() {
        return this.id;
    }

    @Override
    public String toString() {
        return "XSysLogs{" +
        "id=" + id +
        ", date=" + date +
        ", value=" + value +
        "}";
    }
}
