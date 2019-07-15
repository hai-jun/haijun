package com.haijun.model;

import java.io.Serializable;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.extension.activerecord.Model;

/**
 * <p>
 * 
 * </p>
 *
 * @author funton
 * @since 2019-04-15
 */
@TableName("tx_user")
public class XUser extends Model<XUser>{
	private static final long serialVersionUID = 1L;
	
	@TableId(value = "id", type = IdType.AUTO)
	private Integer id;
	
	@TableField("user_name")
	private String userName;
	
	@TableField("password")
	private String password;
	
	@TableField("phone")
	private String phone;
	
	@TableField("discription")
	private String discription;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getDiscription() {
		return discription;
	}

	public void setDiscription(String discription) {
		this.discription = discription;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	
	@Override
    protected Serializable pkVal() {
        return this.id;
    }

	public XUser() {
		super();
	}


	public XUser(String userName, String phone, String discription) {
		super();
		this.userName = userName;
		this.phone = phone;
		this.discription = discription;
	}

	public XUser(String userName, String password, String phone, String discription) {
		super();
		this.userName = userName;
		this.password = password;
		this.phone = phone;
		this.discription = discription;
	}

	@Override
	public String toString() {
		return "XUser [id=" + id + ", userName=" + userName + ", password=" + password + ", phone=" + phone
				+ ", discription=" + discription + "]";
	}
	
}
