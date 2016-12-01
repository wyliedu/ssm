package com.hszs.stb.model.system;

import java.sql.Timestamp;

/**
 * 日志信息
 * @author wylie
 *
 */
public class LogInfo {

	private int xh;
	private int id;
	private String description;  //描述
	private String method;       //调用方法名
	private String requestIp;   //请求ip
	private String exceptionCode;   
	private String exceptionDetail;  //异常详细信息
	private String params;           //人参
	private Integer accountid;
	private String operator;        //操作人
	private int type;               //类型
	private Timestamp createtime;      //创建时间
	private String createtimevalue;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public String getMethod() {
		return method;
	}
	public void setMethod(String method) {
		this.method = method;
	}
	public String getRequestIp() {
		return requestIp;
	}
	public void setRequestIp(String requestIp) {
		this.requestIp = requestIp;
	}
	public String getExceptionCode() {
		return exceptionCode;
	}
	public void setExceptionCode(String exceptionCode) {
		this.exceptionCode = exceptionCode;
	}
	public String getExceptionDetail() {
		return exceptionDetail;
	}
	public void setExceptionDetail(String exceptionDetail) {
		this.exceptionDetail = exceptionDetail;
	}
	public String getParams() {
		return params;
	}
	public void setParams(String params) {
		this.params = params;
	}
	public String getOperator() {
		return operator;
	}
	public void setOperator(String operator) {
		this.operator = operator;
	}
	public int getType() {
		return type;
	}
	public void setType(int type) {
		this.type = type;
	}
	public Timestamp getCreatetime() {
		return createtime;
	}
	public void setCreatetime(Timestamp createtime) {
		this.createtime = createtime;
	}
	public Integer getAccountid() {
		return accountid;
	}
	public void setAccountid(Integer accountid) {
		this.accountid = accountid;
	}
	public int getXh() {
		return xh;
	}
	public void setXh(int xh) {
		this.xh = xh;
	}
	public String getCreatetimevalue() {
		return createtimevalue;
	}
	public void setCreatetimevalue(String createtimevalue) {
		this.createtimevalue = createtimevalue;
	}
}
