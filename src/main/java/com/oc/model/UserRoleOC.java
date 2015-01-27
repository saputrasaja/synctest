package com.oc.model;

import java.sql.ResultSet;
import java.sql.SQLException;

import com.supadosworks.synctest.domain.User;

public class UserRoleOC extends AbstractOcModel {
    private Long id;

    private String role_name;

    private Integer parent_id;

    private String role_desc;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getRole_name() {
        return role_name;
    }

    public void setRole_name(String role_name) {
        this.role_name = role_name;
    }

    public Integer getParent_id() {
        return parent_id;
    }

    public void setParent_id(Integer parent_id) {
        this.parent_id = parent_id;
    }

    public String getRole_desc() {
        return role_desc;
    }

    public void setRole_desc(String role_desc) {
        this.role_desc = role_desc;
    }
    
    public void generate(ResultSet rs) throws SQLException {
    	setId(rs.getLong(1));
    	setRole_name(rs.getString(2));
    	setParent_id(rs.getInt(3));
    	setRole_desc(rs.getString(4));
    }
    
    public static UserRoleOC getDataset(ResultSet rs) throws SQLException{
    	UserRoleOC r = new UserRoleOC();
    	r.generate(rs);
    	return r;
    }

	@Override
	public String getQueryInsert() {
		StringBuilder sb = new StringBuilder();
		
		return sb.toString();
	}
}
