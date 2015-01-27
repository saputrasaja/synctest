package com.oc.model;

import java.util.List;

import com.supadosworks.synctest.domain.UserRole;

public class UserRoleHelper {
	private List<UserRoleOC> dataOc;
	private List<UserRole> data;

	public List<UserRoleOC> getDataOc() {
		return dataOc;
	}

	public void setDataOc(List<UserRoleOC> dataOc) {
		this.dataOc = dataOc;
	}

	public List<UserRole> getData() {
		return data;
	}

	public void setData(List<UserRole> data) {
		this.data = data;
	}
}
