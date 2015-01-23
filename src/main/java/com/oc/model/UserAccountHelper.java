package com.oc.model;

import java.util.List;

import com.supadosworks.synctest.domain.UserAccount;

public class UserAccountHelper {
	private List<UserAccountOC> dataOc;
	private List<UserAccount> data;

	public List<UserAccountOC> getDataOc() {
		return dataOc;
	}

	public void setDataOc(List<UserAccountOC> dataOc) {
		this.dataOc = dataOc;
	}

	public List<UserAccount> getData() {
		return data;
	}

	public void setData(List<UserAccount> data) {
		this.data = data;
	}
}
