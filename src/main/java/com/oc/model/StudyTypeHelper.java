package com.oc.model;

import java.util.List;

import com.supadosworks.synctest.domain.StudyType;

public class StudyTypeHelper {
	private List<StudyTypeOC> dataOc;
	private List<StudyType> data;

	public List<StudyTypeOC> getDataOc() {
		return dataOc;
	}

	public void setDataOc(List<StudyTypeOC> dataOc) {
		this.dataOc = dataOc;
	}

	public List<StudyType> getData() {
		return data;
	}

	public void setData(List<StudyType> data) {
		this.data = data;
	}
}
