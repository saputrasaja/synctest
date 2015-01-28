package com.oc.model;

import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;

public class StudyEventOC extends AbstractOcModel {
	private Long id;

	private String name;

	private String label;

	private String event_status;

	private String status;
	
	private Date date_start;

	private Date date_end;
	
    private Integer owner_id;
    
    public StudyEventOC() {
	}

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

	public String getLabel() {
		return label;
	}

	public void setLabel(String label) {
		this.label = label;
	}

	public String getEvent_status() {
		return event_status;
	}

	public void setEvent_status(String event_status) {
		this.event_status = event_status;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public Date getDate_start() {
		return date_start;
	}

	public void setDate_start(Date date_start) {
		this.date_start = date_start;
	}

	public Date getDate_end() {
		return date_end;
	}

	public void setDate_end(Date date_end) {
		this.date_end = date_end;
	}

	public Integer getOwner_id() {
		return owner_id;
	}

	public void setOwner_id(Integer owner_id) {
		this.owner_id = owner_id;
	}
	
	public void generate(ResultSet rs) throws SQLException {
		setId(rs.getLong(1));
		setName(rs.getString(2));
		setLabel(rs.getString(3));
		setEvent_status(rs.getString(4));
		setStatus(rs.getString(5));
		setDate_start(rs.getDate(6));
		setDate_end(rs.getDate(7));
		setOwner_id(rs.getInt(8));
	}
	
	public static StudyEventOC getDataset(ResultSet rs) throws SQLException {
		StudyEventOC r = new StudyEventOC();
		r.generate(rs);
		return r;
	}

	@Override
	public String getQueryInsert() {
		StringBuilder sb = new StringBuilder();
		
		return sb.toString();
	}
    
    
}
