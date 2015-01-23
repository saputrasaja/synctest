package com.oc.model;

import java.sql.ResultSet;
import java.sql.SQLException;

public class UserTypeOC extends AbstractOcModel {
    private Long id;

    private String user_type;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUser_type() {
        return user_type;
    }

    public void setUser_type(String user_type) {
        this.user_type = user_type;
    }
    
	public void generate(ResultSet rs) throws SQLException {
		setId(rs.getLong(1));
		setUser_type(rs.getString(2));
	}
	
	public static UserTypeOC getDataset(ResultSet rs) throws SQLException{
		UserTypeOC r = new UserTypeOC();
		r.generate(rs);
		return r;
	}

	@Override
	public String getQueryInsert() {
		StringBuilder sb = new StringBuilder();
		return sb.toString();
	}
}
