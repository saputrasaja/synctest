package com.oc.model;

import java.util.List;

import com.supadosworks.synctest.domain.Dataset;

public class DatasetHelper {
	private List<DatasetOC> dataOc;
	private List<Dataset> data;

	public List<DatasetOC> getDataOc() {
		return dataOc;
	}

	public void setDataOc(List<DatasetOC> dataOc) {
		this.dataOc = dataOc;
	}

	public List<Dataset> getData() {
		return data;
	}

	public void setData(List<Dataset> data) {
		this.data = data;
	}
}
