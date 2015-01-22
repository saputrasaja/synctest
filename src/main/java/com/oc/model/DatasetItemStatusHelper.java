package com.oc.model;

import java.util.List;

import com.supadosworks.synctest.domain.DatasetItemStatus;

public class DatasetItemStatusHelper {

	private List<DatasetItemStatusOC> dataOc;
	private List<DatasetItemStatus> data;

	public List<DatasetItemStatusOC> getDataOc() {
		return dataOc;
	}

	public void setDataOc(List<DatasetItemStatusOC> dataOc) {
		this.dataOc = dataOc;
	}

	public List<DatasetItemStatus> getData() {
		return data;
	}

	public void setData(List<DatasetItemStatus> data) {
		this.data = data;
	}
}
