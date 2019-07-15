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
 * @since 2019-05-15
 */
@TableName("tx_pictures")
public class XPictures extends Model<XPictures> {

    private static final long serialVersionUID = 1L;

    @TableId(value = "pic_id", type = IdType.AUTO)
    private Integer picId;

    @TableField("pic_url")
    private String picUrl;

    public Integer getPicId() {
        return picId;
    }

    public XPictures setPicId(Integer picId) {
        this.picId = picId;
        return this;
    }
    public String getPicUrl() {
        return picUrl;
    }

    public XPictures setPicUrl(String picUrl) {
        this.picUrl = picUrl;
        return this;
    }

    @Override
    protected Serializable pkVal() {
        return this.picId;
    }

    
    public XPictures() {
		super();
	}

	public XPictures(String picUrl) {
		super();
		this.picUrl = picUrl;
	}

	@Override
    public String toString() {
        return "XPictures{" +
        "picId=" + picId +
        ", picUrl=" + picUrl +
        "}";
    }
}
