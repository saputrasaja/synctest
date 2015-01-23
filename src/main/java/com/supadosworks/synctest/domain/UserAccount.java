package com.supadosworks.synctest.domain;

import java.io.Serializable;
import java.sql.Date;
import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * A UserAccount.
 */
@Entity
@Table(name = "user_account")
public class UserAccount implements Serializable {

    @Id
    @Column(name = "user_id")
    private Long id;

    @Column(name = "user_name")
    private String user_name;

    @Column(name = "passwd")
    private String passwd;

    @Column(name = "first_name")
    private String first_name;

    @Column(name = "last_name")
    private String last_name;

    @Column(name = "email")
    private String email;

    @Column(name = "active_study")
    private Integer active_study;

    @Column(name = "institutional_affiliation")
    private String institutional_affiliation;

    @Column(name = "status_id")
    private Integer status_id;

    @Column(name = "owner_id")
    private Integer owner_id;

    @Column(name = "date_created", nullable = false)
    private Date date_created;

    @Column(name = "date_updated", nullable = false)
    private Date date_updated;

    @Column(name = "date_lastvisit", nullable = false)
    private Timestamp date_lastvisit;

    @Column(name = "passwd_timestamp", nullable = false)
    private Date passwd_timestamp;

    @Column(name = "passwd_challenge_question")
    private String passwd_challenge_question;

    @Column(name = "passwd_challenge_answer")
    private String passwd_challenge_answer;

    @Column(name = "phone")
    private String phone;

    @Column(name = "user_type_id")
    private Integer user_type_id;

    @Column(name = "update_id")
    private Integer update_id;

    @Column(name = "enabled")
    private Boolean enabled;

    @Column(name = "account_non_locked")
    private Boolean account_non_locked;

    @Column(name = "lock_counter")
    private Integer lock_counter;

    @Column(name = "run_webservices")
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

    public void setDate_created(Date date_created) {
        this.date_created = date_created;
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

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        UserAccount userAccount = (UserAccount) o;

        if (id != null ? !id.equals(userAccount.id) : userAccount.id != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        return (int) (id ^ (id >>> 32));
    }

    @Override
    public String toString() {
        return "UserAccount{" +
                "id=" + id +
                ", user_name='" + user_name + "'" +
                ", passwd='" + passwd + "'" +
                ", first_name='" + first_name + "'" +
                ", last_name='" + last_name + "'" +
                ", email='" + email + "'" +
                ", active_study='" + active_study + "'" +
                ", institutional_affiliation='" + institutional_affiliation + "'" +
                ", status_id='" + status_id + "'" +
                ", owner_id='" + owner_id + "'" +
                ", date_created='" + date_created + "'" +
                ", date_updated='" + date_updated + "'" +
                ", date_lastvisit='" + date_lastvisit + "'" +
                ", passwd_timestamp='" + passwd_timestamp + "'" +
                ", passwd_challenge_question='" + passwd_challenge_question + "'" +
                ", passwd_challenge_answer='" + passwd_challenge_answer + "'" +
                ", phone='" + phone + "'" +
                ", user_type_id='" + user_type_id + "'" +
                ", update_id='" + update_id + "'" +
                ", enabled='" + enabled + "'" +
                ", account_non_locked='" + account_non_locked + "'" +
                ", lock_counter='" + lock_counter + "'" +
                ", run_webservices='" + run_webservices + "'" +
                '}';
    }
}
