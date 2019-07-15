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
 * @since 2019-04-24
 */
@TableName("usertoken")
public class Usertoken extends Model<Usertoken> {

    public Usertoken() {
		super();
	}

	public Usertoken(Integer userId, String userName, String token) {
		super();
		this.userId = userId;
		this.userName = userName;
		this.token = token;
	}

	private static final long serialVersionUID = 1L;

    @TableField("userId")
    private Integer userId;

    @TableField("userName")
    private String userName;

    @TableField("token")
    private String token;

    public Integer getUserId() {
        return userId;
    }

    public Usertoken setUserId(Integer userId) {
        this.userId = userId;
        return this;
    }
    public String getUserName() {
        return userName;
    }

    public Usertoken setUserName(String userName) {
        this.userName = userName;
        return this;
    }
    public String getToken() {
        return token;
    }

    public Usertoken setToken(String token) {
        this.token = token;
        return this;
    }

    @Override
    protected Serializable pkVal() {
        return null;
    }

    @Override
    public String toString() {
        return "Usertoken{" +
        "userId=" + userId +
        ", userName=" + userName +
        ", token=" + token +
        "}";
    }
}
