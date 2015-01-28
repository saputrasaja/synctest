package com.oc.model;

import java.util.List;

import com.supadosworks.synctest.domain.StudyEvent;

public class StudyEventHelper {
	private List<StudyEventOC> dataOc;
	private List<StudyEvent> data;

	public List<StudyEventOC> getDataOc() {
		return dataOc;
	}

	public void setDataOc(List<StudyEventOC> dataOc) {
		this.dataOc = dataOc;
	}

	public List<StudyEvent> getData() {
		return data;
	}

	public void setData(List<StudyEvent> data) {
		this.data = data;
	}
}
