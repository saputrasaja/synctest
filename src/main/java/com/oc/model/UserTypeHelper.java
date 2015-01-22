package com.oc.model;

import java.util.List;

import com.supadosworks.synctest.domain.UserType;

public class UserTypeHelper {
	private List<UserTypeOC> dataOc;
	private List<UserType> data;

	public List<UserTypeOC> getDataOc() {
		return dataOc;
	}

	public void setDataOc(List<UserTypeOC> dataOc) {
		this.dataOc = dataOc;
	}

	public List<UserType> getData() {
		return data;
	}

	public void setData(List<UserType> data) {
		this.data = data;
	}
}
