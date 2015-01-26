package com.oc.model;

import java.util.List;

import com.supadosworks.synctest.domain.Study;

public class StudyHelper {
	private List<StudyOC> dataOc;
	private List<Study> data;

	public List<StudyOC> getDataOc() {
		return dataOc;
	}

	public void setDataOc(List<StudyOC> dataOc) {
		this.dataOc = dataOc;
	}

	public List<Study> getData() {
		return data;
	}

	public void setData(List<Study> data) {
		this.data = data;
	}
}
