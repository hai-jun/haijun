package com.haijun.utils;

/**
 * 封装返回的结果
 * @author funton
 *
 */
public class ResultMsg {
	private int code;
	private String msg;
	private Object data;  


	public ResultMsg() {
		super();
	}
	
	public ResultMsg(int code){
		this.code = code;
	}
	
	public ResultMsg(int code, String msg)
	{
		this.code = code;
		this.msg = msg;
	}


	public ResultMsg(int code, String msg, Object data) {  
		this.code = code;  
		this.msg = msg;  
		this.data = data;  
	}  

	public int getCode() {  
		return code;  
	}  
	public void setCode(int code) {  
		this.code = code;  
	}
	public String getMsg() {
		return msg;
	}
	public void setMsg(String msg) {
		this.msg = msg;
	}
	public Object getData() {
		return data;
	}
	public void setData(Object data) {
		this.data = data;
	}  
	
    
    public static ResultMsg createByErrorCodeMessage(int errorCode, String errorMessage){
    	return new ResultMsg(errorCode, errorMessage);
    }

	@Override
	public String toString() {
		return "ResultMsg [code=" + code + ", msg=" + msg + ", data=" + data + "]";
	} 
    


}
