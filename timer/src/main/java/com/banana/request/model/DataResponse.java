package com.banana.request.model;

import java.util.ArrayList;

import com.banana.timer.Status;

public class DataResponse {

	public int id;
	public String account;
	public String localtime;
	private ArrayList<Status> statuses;
	public int status;
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getAccount() {
		return account;
	}
	public void setAccount(String account) {
		this.account = account;
	}
	public String getLocaltime() {
		return localtime;
	}
	public void setLocaltime(String localtime) {
		this.localtime = localtime;
	}
	public ArrayList<Status> getStatuses() {
		return statuses;
	}
	public void setStatuses(ArrayList<Status> statuses) {
		this.statuses = statuses;
	}
	public int getStatus() {
		return status;
	}
	public void setStatus(int status) {
		this.status = status;
	}

}
