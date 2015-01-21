package com.oc.model;

import java.util.List;

import com.supadosworks.synctest.domain.ExportFormat;

public class ExportFormatHelper {

	private List<ExportFormat> ef;
	private List<ExportFormatOC> efoc;
	
	public ExportFormatHelper() {
	}

	public List<ExportFormat> getEf() {
		return ef;
	}

	public void setEf(List<ExportFormat> ef) {
		this.ef = ef;
	}

	public List<ExportFormatOC> getEfoc() {
		return efoc;
	}

	public void setEfoc(List<ExportFormatOC> efoc) {
		this.efoc = efoc;
	}
	
	
}
