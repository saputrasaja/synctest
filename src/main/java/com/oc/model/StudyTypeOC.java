package com.oc.model;

import java.sql.ResultSet;
import java.sql.SQLException;

public class StudyTypeOC  extends AbstractOcModel {
	
	private Long id;

	private String name;

	private String description;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}
	
	public void generate(ResultSet rs) throws SQLException {
		setId(rs.getLong(1));
		setName(rs.getString(2));
		setDescription(rs.getString(3));
	}
	
	public static StudyTypeOC getDataset(ResultSet rs) throws SQLException {
		StudyTypeOC r = new StudyTypeOC();
		r.generate(rs);
		return r;
	}
	
	@Override
	public String getQueryInsert() {
		StringBuilder sb = new StringBuilder();
		
		return sb.toString();
	}
}
