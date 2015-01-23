package com.oc.model;

import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;

public class UserAccountOC  extends AbstractOcModel {
    private Long id;

    private String user_name;

    private String passwd;

    private String first_name;

    private String last_name;

    private String email;

    private Integer active_study;

    private String institutional_affiliation;

    private Integer status_id;

    private Integer owner_id;

    private Date date_created;

    private Date date_updated;

    private Timestamp date_lastvisit;

    private Date passwd_timestamp;

    private String passwd_challenge_question;

    private String passwd_challenge_answer;

    private String phone;

    private Integer user_type_id;

    private Integer update_id;

    private Boolean enabled;

    private Boolean account_non_locked;

    private Integer lock_counter;

    private Boolean run_webservices;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUser_name() {
        return user_name;
    }

    public void setUser_name(String user_name) {
        this.user_name = user_name;
    }

    public String getPasswd() {
        return passwd;
    }

    public void setPasswd(String passwd) {
        this.passwd = passwd;
    }

    public String getFirst_name() {
        return first_name;
    }

    public void setFirst_name(String first_name) {
        this.first_name = first_name;
    }

    public String getLast_name() {
        return last_name;
    }

    public void setLast_name(String last_name) {
        this.last_name = last_name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Integer getActive_study() {
        return active_study;
    }

    public void setActive_study(Integer active_study) {
        this.active_study = active_study;
    }

    public String getInstitutional_affiliation() {
        return institutional_affiliation;
    }

    public void setInstitutional_affiliation(String institutional_affiliation) {
        this.institutional_affiliation = institutional_affiliation;
    }

    public Integer getStatus_id() {
        return status_id;
    }

    public void setStatus_id(Integer status_id) {
        this.status_id = status_id;
    }

    public Integer getOwner_id() {
        return owner_id;
    }

    public void setOwner_id(Integer owner_id) {
        this.owner_id = owner_id;
    }

    public Date getDate_created() {
        return date_created;
    }

    public void setDate_created(Date date) {
        this.date_created = date;
    }

    public Date getDate_updated() {
        return date_updated;
    }

    public void setDate_updated(Date date_updated) {
        this.date_updated = date_updated;
    }

    public Timestamp getDate_lastvisit() {
        return date_lastvisit;
    }

    public void setDate_lastvisit(Timestamp date_lastvisit) {
        this.date_lastvisit = date_lastvisit;
    }

    public Date getPasswd_timestamp() {
        return passwd_timestamp;
    }

    public void setPasswd_timestamp(Date passwd_timestamp) {
        this.passwd_timestamp = passwd_timestamp;
    }

    public String getPasswd_challenge_question() {
        return passwd_challenge_question;
    }

    public void setPasswd_challenge_question(String passwd_challenge_question) {
        this.passwd_challenge_question = passwd_challenge_question;
    }

    public String getPasswd_challenge_answer() {
        return passwd_challenge_answer;
    }

    public void setPasswd_challenge_answer(String passwd_challenge_answer) {
        this.passwd_challenge_answer = passwd_challenge_answer;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public Integer getUser_type_id() {
        return user_type_id;
    }

    public void setUser_type_id(Integer user_type_id) {
        this.user_type_id = user_type_id;
    }

    public Integer getUpdate_id() {
        return update_id;
    }

    public void setUpdate_id(Integer update_id) {
        this.update_id = update_id;
    }

    public Boolean getEnabled() {
        return enabled;
    }

    public void setEnabled(Boolean enabled) {
        this.enabled = enabled;
    }

    public Boolean getAccount_non_locked() {
        return account_non_locked;
    }

    public void setAccount_non_locked(Boolean account_non_locked) {
        this.account_non_locked = account_non_locked;
    }

    public Integer getLock_counter() {
        return lock_counter;
    }

    public void setLock_counter(Integer lock_counter) {
        this.lock_counter = lock_counter;
    }

    public Boolean getRun_webservices() {
        return run_webservices;
    }

    public void setRun_webservices(Boolean run_webservices) {
        this.run_webservices = run_webservices;
    }
    
	public void generate(ResultSet rs) throws SQLException {
		setId(rs.getLong(1));
		setUser_name(rs.getString(2));
		setPasswd(rs.getString(3));
		setFirst_name(rs.getString(4));
		setLast_name(rs.getString(5));
		setEmail(rs.getString(6));
		setActive_study(rs.getInt(7));
		setInstitutional_affiliation(rs.getString(8));
		setStatus_id(rs.getInt(9));
		setOwner_id(rs.getInt(10));
		setDate_created(rs.getDate(11));
		setDate_updated(rs.getDate(12));
		setDate_lastvisit(rs.getTimestamp(13));
		setPasswd_timestamp(rs.getDate(14));
		setPasswd_challenge_question(rs.getString(15));
		setPasswd_challenge_answer(rs.getString(16));
		setPhone(rs.getString(17));
		setUser_type_id(rs.getInt(18));
		setUpdate_id(rs.getInt(19));
		setEnabled(rs.getBoolean(20));
		setAccount_non_locked(rs.getBoolean(21));
		setLock_counter(rs.getInt(22));
		setRun_webservices(rs.getBoolean(23));
	}
	
	public static UserAccountOC getDataset(ResultSet rs) throws SQLException{
		UserAccountOC r = new UserAccountOC();
		r.generate(rs);
		return r;
	}

	@Override
	public String getQueryInsert() {
		StringBuilder sb = new StringBuilder();
		return null;
	}
}
