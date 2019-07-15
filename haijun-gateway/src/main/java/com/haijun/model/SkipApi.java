package com.haijun.model;

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
 * @since 2019-05-13
 */
public class SkipApi extends Model<SkipApi> {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    @TableField("api_url")
    private String apiUrl;

    public Integer getId() {
        return id;
    }

    public SkipApi setId(Integer id) {
        this.id = id;
        return this;
    }
    public String getApiUrl() {
        return apiUrl;
    }

    public SkipApi setApiUrl(String apiUrl) {
        this.apiUrl = apiUrl;
        return this;
    }

    @Override
    protected Serializable pkVal() {
        return this.id;
    }

    @Override
    public String toString() {
        return "SkipApi{" +
        "id=" + id +
        ", apiUrl=" + apiUrl +
        "}";
    }
}
