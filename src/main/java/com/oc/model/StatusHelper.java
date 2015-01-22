package com.oc.model;

import java.util.List;
import com.supadosworks.synctest.domain.Status;

public class StatusHelper {

	private List<StatusOC> sOc;
	private List<Status> s;
	
	public StatusHelper() {
	}

	public List<StatusOC> getsOc() {
		return sOc;
	}

	public void setsOc(List<StatusOC> sOc) {
		this.sOc = sOc;
	}

	public List<Status> getS() {
		return s;
	}

	public void setS(List<Status> s) {
		this.s = s;
	}

}
