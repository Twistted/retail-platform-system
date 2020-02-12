package com.laimilife.base.vo;

public class Result {
	public String code; // 返回代码，除0外其他都是操作失败
	public String desc; // 返回描述
	public Object data; // 返回数据
	
	public Result(String code, String desc, Object obj) {
		this.code = code;
		this.desc = desc;
		this.data = obj;
	}
	
	public Result(String code, String desc) {
		this.code = code;
		this.desc = desc;
		this.data = null;
	}
	
	public Result() {
		
	}
}
