package com.oc.model;

import java.sql.ResultSet;

import com.oc.connection.OcConnect;

public abstract class AbstractOcModel {

	public abstract String getQueryInsert();
	
	public void save() {
		try {
			OcConnect oc = OcConnect.getConnection(getQueryInsert());
			OcConnect.close(oc);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
