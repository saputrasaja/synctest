package com.oc.model;

import java.sql.ResultSet;
import java.sql.SQLException;

import com.oc.connection.OcConnect;

public class Configuration {
	private Long id;

	private String key;

	private String value;

	private String description;

	private Integer version;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getKey() {
		return key;
	}

	public void setKey(String key) {
		this.key = key;
	}

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Integer getVersion() {
		return version;
	}

	public void setVersion(Integer version) {
		this.version = version;
	}

	public void generate(ResultSet rs) throws SQLException {
		setId(rs.getLong(1));
		setKey(rs.getString(2));
		setValue(rs.getString(3));
		setDescription(rs.getString(4));
		setVersion(rs.getInt(5));
	}

	public static Configuration getConfig(ResultSet rs) throws SQLException {
		Configuration r = new Configuration();
		r.generate(rs);
		return r;
	}

	public String getQueryInsert() {
		StringBuilder sb = new StringBuilder();
		sb.append("INSERT INTO configuration values(");
		sb.append("nextval('configuration_id_seq'), ");
		sb.append("'" + this.key + "', ");
		sb.append("'" + this.value + "', ");
		sb.append("'" + this.description + "', ");
		sb.append("'" + this.version + "'");
		sb.append(")");
		return sb.toString();
	}

	public void save() {
		try {
			OcConnect oc = OcConnect.getConnection(getQueryInsert());
			OcConnect.close(oc);
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

}
