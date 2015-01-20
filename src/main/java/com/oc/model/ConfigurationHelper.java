package com.oc.model;

import java.util.List;
import com.oc.model.Configuration;
import com.supadosworks.synctest.domain.OcConfiguration;

public class ConfigurationHelper {

	private List<Configuration> confs;
	private List<OcConfiguration> ocConfs;

	public ConfigurationHelper() {
		// TODO Auto-generated constructor stub
	}

	public List<Configuration> getConfs() {
		return confs;
	}

	public void setConfs(List<Configuration> confs) {
		this.confs = confs;
	}

	public List<OcConfiguration> getOcConfs() {
		return ocConfs;
	}

	public void setOcConfs(List<OcConfiguration> ocConfs) {
		this.ocConfs = ocConfs;
	}

}
