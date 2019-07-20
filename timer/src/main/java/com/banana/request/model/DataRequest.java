package com.banana.request.model;

import java.util.Date;

public class DataRequest {

	private String id;
	private String account;
	private Date localtime;

	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getAccount() {
		return account;
	}
	public void setAccount(String account) {
		this.account = account;
	}
	public Date getLocaltime() {
		return localtime;
	}
	public void setLocaltime(Date localtime) {
		this.localtime = localtime;
	}

}
