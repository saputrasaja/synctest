package com.supadosworks.synctest.domain;

import java.io.Serializable;
import java.sql.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * A Dataset.
 */
@Entity
@Table(name = "dataset")
public class Dataset implements Serializable {

    @Id
    @Column(name = "dataset_id")
    private Long id;

    @Column(name = "study_id")
    private Integer study_id;

    @Column(name = "status_id")
    private Integer status_id;

    @Column(name = "name")
    private String name;

    @Column(name = "description")
    private String description;

    @Column(name = "sql_statement")
    private String sql_statement;

    @Column(name = "num_runs")
    private Integer num_runs;

    @Column(name = "date_start", nullable = false)
    private Date date_start;

    @Column(name = "date_end", nullable = false)
    private Date date_end;

    @Column(name = "date_created", nullable = false)
    private Date date_created;

    @Column(name = "date_updated", nullable = false)
    private Date date_updated;

    @Column(name = "date_last_run", nullable = false)
    private Date date_last_run;

    @Column(name = "owner_id")
    private Integer owner_id;

    @Column(name = "approver_id")
    private Integer approver_id;

    @Column(name = "update_id")
    private Integer update_id;

    @Column(name = "show_event_location")
    private Boolean show_event_location;

    @Column(name = "show_event_start")
    private Boolean show_event_start;

    @Column(name = "show_event_end")
    private Boolean show_event_end;

    @Column(name = "show_subject_dob")
    private Boolean show_subject_dob;

    @Column(name = "show_subject_gender")
    private Boolean show_subject_gender;

    @Column(name = "show_event_status")
    private Boolean show_event_status;

    @Column(name = "show_subject_status")
    private Boolean show_subject_status;

    @Column(name = "show_subject_unique_id")
    private Boolean show_subject_unique_id;

    @Column(name = "show_subject_age_at_event")
    private Boolean show_subject_age_at_event;

    @Column(name = "show_crf_status")
    private Boolean show_crf_status;

    @Column(name = "show_crf_version")
    private Boolean show_crf_version;

    @Column(name = "show_crf_int_name")
    private Boolean show_crf_int_name;

    @Column(name = "show_crf_int_date")
    private Boolean show_crf_int_date;

    @Column(name = "show_group_info")
    private Boolean show_group_info;

    @Column(name = "show_disc_info")
    private Boolean show_disc_info;

    @Column(name = "odm_metadataversion_name")
    private String odm_metadataversion_name;

    @Column(name = "odm_metadataversion_oid")
    private String odm_metadataversion_oid;

    @Column(name = "odm_prior_study_oid")
    private String odm_prior_study_oid;

    @Column(name = "odm_prior_metadataversion_oid")
    private String odm_prior_metadataversion_oid;

    @Column(name = "show_secondary_id")
    private Boolean show_secondary_id;

    @Column(name = "dataset_item_status_id")
    private Integer dataset_item_status_id;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getStudy_id() {
        return study_id;
    }

    public void setStudy_id(Integer study_id) {
        this.study_id = study_id;
    }

    public Integer getStatus_id() {
        return status_id;
    }

    public void setStatus_id(Integer status_id) {
        this.status_id = status_id;
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

    public String getSql_statement() {
        return sql_statement;
    }

    public void setSql_statement(String sql_statement) {
        this.sql_statement = sql_statement;
    }

    public Integer getNum_runs() {
        return num_runs;
    }

    public void setNum_runs(Integer num_runs) {
        this.num_runs = num_runs;
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

    public Date getDate_last_run() {
        return date_last_run;
    }

    public void setDate_last_run(Date date_last_run) {
        this.date_last_run = date_last_run;
    }

    public Integer getOwner_id() {
        return owner_id;
    }

    public void setOwner_id(Integer owner_id) {
        this.owner_id = owner_id;
    }

    public Integer getApprover_id() {
        return approver_id;
    }

    public void setApprover_id(Integer approver_id) {
        this.approver_id = approver_id;
    }

    public Integer getUpdate_id() {
        return update_id;
    }

    public void setUpdate_id(Integer update_id) {
        this.update_id = update_id;
    }

    public Boolean getShow_event_location() {
        return show_event_location;
    }

    public void setShow_event_location(Boolean show_event_location) {
        this.show_event_location = show_event_location;
    }

    public Boolean getShow_event_start() {
        return show_event_start;
    }

    public void setShow_event_start(Boolean show_event_start) {
        this.show_event_start = show_event_start;
    }

    public Boolean getShow_event_end() {
        return show_event_end;
    }

    public void setShow_event_end(Boolean show_event_end) {
        this.show_event_end = show_event_end;
    }

    public Boolean getShow_subject_dob() {
        return show_subject_dob;
    }

    public void setShow_subject_dob(Boolean show_subject_dob) {
        this.show_subject_dob = show_subject_dob;
    }

    public Boolean getShow_subject_gender() {
        return show_subject_gender;
    }

    public void setShow_subject_gender(Boolean show_subject_gender) {
        this.show_subject_gender = show_subject_gender;
    }

    public Boolean getShow_event_status() {
        return show_event_status;
    }

    public void setShow_event_status(Boolean show_event_status) {
        this.show_event_status = show_event_status;
    }

    public Boolean getShow_subject_status() {
        return show_subject_status;
    }

    public void setShow_subject_status(Boolean show_subject_status) {
        this.show_subject_status = show_subject_status;
    }

    public Boolean getShow_subject_unique_id() {
        return show_subject_unique_id;
    }

    public void setShow_subject_unique_id(Boolean show_subject_unique_id) {
        this.show_subject_unique_id = show_subject_unique_id;
    }

    public Boolean getShow_subject_age_at_event() {
        return show_subject_age_at_event;
    }

    public void setShow_subject_age_at_event(Boolean show_subject_age_at_event) {
        this.show_subject_age_at_event = show_subject_age_at_event;
    }

    public Boolean getShow_crf_status() {
        return show_crf_status;
    }

    public void setShow_crf_status(Boolean show_crf_status) {
        this.show_crf_status = show_crf_status;
    }

    public Boolean getShow_crf_version() {
        return show_crf_version;
    }

    public void setShow_crf_version(Boolean show_crf_version) {
        this.show_crf_version = show_crf_version;
    }

    public Boolean getShow_crf_int_name() {
        return show_crf_int_name;
    }

    public void setShow_crf_int_name(Boolean show_crf_int_name) {
        this.show_crf_int_name = show_crf_int_name;
    }

    public Boolean getShow_crf_int_date() {
        return show_crf_int_date;
    }

    public void setShow_crf_int_date(Boolean show_crf_int_date) {
        this.show_crf_int_date = show_crf_int_date;
    }

    public Boolean getShow_group_info() {
        return show_group_info;
    }

    public void setShow_group_info(Boolean show_group_info) {
        this.show_group_info = show_group_info;
    }

    public Boolean getShow_disc_info() {
        return show_disc_info;
    }

    public void setShow_disc_info(Boolean show_disc_info) {
        this.show_disc_info = show_disc_info;
    }

    public String getOdm_metadataversion_name() {
        return odm_metadataversion_name;
    }

    public void setOdm_metadataversion_name(String odm_metadataversion_name) {
        this.odm_metadataversion_name = odm_metadataversion_name;
    }

    public String getOdm_metadataversion_oid() {
        return odm_metadataversion_oid;
    }

    public void setOdm_metadataversion_oid(String odm_metadataversion_oid) {
        this.odm_metadataversion_oid = odm_metadataversion_oid;
    }

    public String getOdm_prior_study_oid() {
        return odm_prior_study_oid;
    }

    public void setOdm_prior_study_oid(String odm_prior_study_oid) {
        this.odm_prior_study_oid = odm_prior_study_oid;
    }

    public String getOdm_prior_metadataversion_oid() {
        return odm_prior_metadataversion_oid;
    }

    public void setOdm_prior_metadataversion_oid(String odm_prior_metadataversion_oid) {
        this.odm_prior_metadataversion_oid = odm_prior_metadataversion_oid;
    }

    public Boolean getShow_secondary_id() {
        return show_secondary_id;
    }

    public void setShow_secondary_id(Boolean show_secondary_id) {
        this.show_secondary_id = show_secondary_id;
    }

    public Integer getDataset_item_status_id() {
        return dataset_item_status_id;
    }

    public void setDataset_item_status_id(Integer dataset_item_status_id) {
        this.dataset_item_status_id = dataset_item_status_id;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        Dataset dataset = (Dataset) o;

        if (id != null ? !id.equals(dataset.id) : dataset.id != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        return (int) (id ^ (id >>> 32));
    }

    @Override
    public String toString() {
        return "Dataset{" +
                "id=" + id +
                ", study_id='" + study_id + "'" +
                ", status_id='" + status_id + "'" +
                ", name='" + name + "'" +
                ", description='" + description + "'" +
                ", sql_statement='" + sql_statement + "'" +
                ", num_runs='" + num_runs + "'" +
                ", date_start='" + date_start + "'" +
                ", date_end='" + date_end + "'" +
                ", date_created='" + date_created + "'" +
                ", date_updated='" + date_updated + "'" +
                ", date_last_run='" + date_last_run + "'" +
                ", owner_id='" + owner_id + "'" +
                ", approver_id='" + approver_id + "'" +
                ", update_id='" + update_id + "'" +
                ", show_event_location='" + show_event_location + "'" +
                ", show_event_start='" + show_event_start + "'" +
                ", show_event_end='" + show_event_end + "'" +
                ", show_subject_dob='" + show_subject_dob + "'" +
                ", show_subject_gender='" + show_subject_gender + "'" +
                ", show_event_status='" + show_event_status + "'" +
                ", show_subject_status='" + show_subject_status + "'" +
                ", show_subject_unique_id='" + show_subject_unique_id + "'" +
                ", show_subject_age_at_event='" + show_subject_age_at_event + "'" +
                ", show_crf_status='" + show_crf_status + "'" +
                ", show_crf_version='" + show_crf_version + "'" +
                ", show_crf_int_name='" + show_crf_int_name + "'" +
                ", show_crf_int_date='" + show_crf_int_date + "'" +
                ", show_group_info='" + show_group_info + "'" +
                ", show_disc_info='" + show_disc_info + "'" +
                ", odm_metadataversion_name='" + odm_metadataversion_name + "'" +
                ", odm_metadataversion_oid='" + odm_metadataversion_oid + "'" +
                ", odm_prior_study_oid='" + odm_prior_study_oid + "'" +
                ", odm_prior_metadataversion_oid='" + odm_prior_metadataversion_oid + "'" +
                ", show_secondary_id='" + show_secondary_id + "'" +
                ", dataset_item_status_id='" + dataset_item_status_id + "'" +
                '}';
    }
}
