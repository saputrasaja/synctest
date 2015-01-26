package com.supadosworks.synctest.domain;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import org.hibernate.annotations.Type;

import javax.persistence.*;
import java.io.Serializable;
import java.sql.Date;

/**
 * A Study.
 */
@Entity
@Table(name = "study")
public class Study implements Serializable {

    @Id
    @Column(name = "study_id")
    private Long id;

    @Column(name = "parent_study_id")
    private Integer parent_study_id;

    @Column(name = "unique_identifier")
    private String unique_identifier;

    @Column(name = "secondary_identifier")
    private String secondary_identifier;

    @Column(name = "name")
    private String name;

    @Column(name = "summary")
    private String summary;

    @Column(name = "date_planned_start", nullable = false)
    private Date date_planned_start;

    @Column(name = "date_planned_end", nullable = false)
    private Date date_planned_end;

    @Column(name = "date_created", nullable = false)
    private Date date_created;

    @Column(name = "date_updated", nullable = false)
    private Date date_updated;

    @Column(name = "owner_id")
    private Integer owner_id;

    @Column(name = "update_id")
    private Integer update_id;

    @Column(name = "type_id")
    private Integer type_id;

    @Column(name = "status_id")
    private Integer status_id;

    @Column(name = "principal_investigator")
    private String principal_investigator;

    @Column(name = "facility_name")
    private String facility_name;

    @Column(name = "facility_city")
    private String facility_city;

    @Column(name = "facility_state")
    private String facility_state;

    @Column(name = "facility_zip")
    private String facility_zip;

    @Column(name = "facility_country")
    private String facility_country;

    @Column(name = "facility_recruitment_status")
    private String facility_recruitment_status;

    @Column(name = "facility_contact_name")
    private String facility_contact_name;

    @Column(name = "facility_contact_degree")
    private String facility_contact_degree;

    @Column(name = "facility_contact_phone")
    private String facility_contact_phone;

    @Column(name = "facility_contact_email")
    private String facility_contact_email;

    @Column(name = "protocol_type")
    private String protocol_type;

    @Column(name = "protocol_description")
    private String protocol_description;

    @Column(name = "protocol_date_verification", nullable = false)
    private Date protocol_date_verification;

    @Column(name = "phase")
    private String phase;

    @Column(name = "expected_total_enrollment")
    private Integer expected_total_enrollment;

    @Column(name = "sponsor")
    private String sponsor;

    @Column(name = "collaborators")
    private String collaborators;

    @Column(name = "medline_identifier")
    private String medline_identifier;

    @Column(name = "url")
    private String url;

    @Column(name = "url_description")
    private String url_description;

    @Column(name = "conditions")
    private String conditions;

    @Column(name = "keywords")
    private String keywords;

    @Column(name = "eligibility")
    private String eligibility;

    @Column(name = "gender")
    private String gender;

    @Column(name = "age_max")
    private String age_max;

    @Column(name = "age_min")
    private String age_min;

    @Column(name = "healthy_volunteer_accepted")
    private boolean healthy_volunteer_accepted;

    @Column(name = "purpose")
    private String purpose;

    @Column(name = "allocation")
    private String allocation;

    @Column(name = "masking")
    private String masking;

    @Column(name = "control")
    private String control;

    @Column(name = "assignment")
    private String assignment;

    @Column(name = "endpoint")
    private String endpoint;

    @Column(name = "interventions")
    private String interventions;

    @Column(name = "duration")
    private String duration;

    @Column(name = "selection")
    private String selection;

    @Column(name = "timing")
    private String timing;

    @Column(name = "official_title")
    private String official_title;

    @Column(name = "results_reference")
    private boolean results_reference;

    @Column(name = "oc_oid")
    private String oc_oid;

    @Column(name = "old_status_id")
    private Integer old_status_id;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getParent_study_id() {
        return parent_study_id;
    }

    public void setParent_study_id(Integer parent_study_id) {
        this.parent_study_id = parent_study_id;
    }

    public String getUnique_identifier() {
        return unique_identifier;
    }

    public void setUnique_identifier(String unique_identifier) {
        this.unique_identifier = unique_identifier;
    }

    public String getSecondary_identifier() {
        return secondary_identifier;
    }

    public void setSecondary_identifier(String secondary_identifier) {
        this.secondary_identifier = secondary_identifier;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSummary() {
        return summary;
    }

    public void setSummary(String summary) {
        this.summary = summary;
    }

    public Date getDate_planned_start() {
        return date_planned_start;
    }

    public void setDate_planned_start(Date date_planned_start) {
        this.date_planned_start = date_planned_start;
    }

    public Date getDate_planned_end() {
        return date_planned_end;
    }

    public void setDate_planned_end(Date date_planned_end) {
        this.date_planned_end = date_planned_end;
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

    public Integer getOwner_id() {
        return owner_id;
    }

    public void setOwner_id(Integer owner_id) {
        this.owner_id = owner_id;
    }

    public Integer getUpdate_id() {
        return update_id;
    }

    public void setUpdate_id(Integer update_id) {
        this.update_id = update_id;
    }

    public Integer getType_id() {
        return type_id;
    }

    public void setType_id(Integer type_id) {
        this.type_id = type_id;
    }

    public Integer getStatus_id() {
        return status_id;
    }

    public void setStatus_id(Integer status_id) {
        this.status_id = status_id;
    }

    public String getPrincipal_investigator() {
        return principal_investigator;
    }

    public void setPrincipal_investigator(String principal_investigator) {
        this.principal_investigator = principal_investigator;
    }

    public String getFacility_name() {
        return facility_name;
    }

    public void setFacility_name(String facility_name) {
        this.facility_name = facility_name;
    }

    public String getFacility_city() {
        return facility_city;
    }

    public void setFacility_city(String facility_city) {
        this.facility_city = facility_city;
    }

    public String getFacility_state() {
        return facility_state;
    }

    public void setFacility_state(String facility_state) {
        this.facility_state = facility_state;
    }

    public String getFacility_zip() {
        return facility_zip;
    }

    public void setFacility_zip(String facility_zip) {
        this.facility_zip = facility_zip;
    }

    public String getFacility_country() {
        return facility_country;
    }

    public void setFacility_country(String facility_country) {
        this.facility_country = facility_country;
    }

    public String getFacility_recruitment_status() {
        return facility_recruitment_status;
    }

    public void setFacility_recruitment_status(String facility_recruitment_status) {
        this.facility_recruitment_status = facility_recruitment_status;
    }

    public String getFacility_contact_name() {
        return facility_contact_name;
    }

    public void setFacility_contact_name(String facility_contact_name) {
        this.facility_contact_name = facility_contact_name;
    }

    public String getFacility_contact_degree() {
        return facility_contact_degree;
    }

    public void setFacility_contact_degree(String facility_contact_degree) {
        this.facility_contact_degree = facility_contact_degree;
    }

    public String getFacility_contact_phone() {
        return facility_contact_phone;
    }

    public void setFacility_contact_phone(String facility_contact_phone) {
        this.facility_contact_phone = facility_contact_phone;
    }

    public String getFacility_contact_email() {
        return facility_contact_email;
    }

    public void setFacility_contact_email(String facility_contact_email) {
        this.facility_contact_email = facility_contact_email;
    }

    public String getProtocol_type() {
        return protocol_type;
    }

    public void setProtocol_type(String protocol_type) {
        this.protocol_type = protocol_type;
    }

    public String getProtocol_description() {
        return protocol_description;
    }

    public void setProtocol_description(String protocol_description) {
        this.protocol_description = protocol_description;
    }

    public Date getProtocol_date_verification() {
        return protocol_date_verification;
    }

    public void setProtocol_date_verification(Date protocol_date_verification) {
        this.protocol_date_verification = protocol_date_verification;
    }

    public String getPhase() {
        return phase;
    }

    public void setPhase(String phase) {
        this.phase = phase;
    }

    public Integer getExpected_total_enrollment() {
        return expected_total_enrollment;
    }

    public void setExpected_total_enrollment(Integer expected_total_enrollment) {
        this.expected_total_enrollment = expected_total_enrollment;
    }

    public String getSponsor() {
        return sponsor;
    }

    public void setSponsor(String sponsor) {
        this.sponsor = sponsor;
    }

    public String getCollaborators() {
        return collaborators;
    }

    public void setCollaborators(String collaborators) {
        this.collaborators = collaborators;
    }

    public String getMedline_identifier() {
        return medline_identifier;
    }

    public void setMedline_identifier(String medline_identifier) {
        this.medline_identifier = medline_identifier;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getUrl_description() {
        return url_description;
    }

    public void setUrl_description(String url_description) {
        this.url_description = url_description;
    }

    public String getConditions() {
        return conditions;
    }

    public void setConditions(String conditions) {
        this.conditions = conditions;
    }

    public String getKeywords() {
        return keywords;
    }

    public void setKeywords(String keywords) {
        this.keywords = keywords;
    }

    public String getEligibility() {
        return eligibility;
    }

    public void setEligibility(String eligibility) {
        this.eligibility = eligibility;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getAge_max() {
        return age_max;
    }

    public void setAge_max(String age_max) {
        this.age_max = age_max;
    }

    public String getAge_min() {
        return age_min;
    }

    public void setAge_min(String age_min) {
        this.age_min = age_min;
    }

    public boolean getHealthy_volunteer_accepted() {
        return healthy_volunteer_accepted;
    }

    public void setHealthy_volunteer_accepted(boolean healthy_volunteer_accepted) {
        this.healthy_volunteer_accepted = healthy_volunteer_accepted;
    }

    public String getPurpose() {
        return purpose;
    }

    public void setPurpose(String purpose) {
        this.purpose = purpose;
    }

    public String getAllocation() {
        return allocation;
    }

    public void setAllocation(String allocation) {
        this.allocation = allocation;
    }

    public String getMasking() {
        return masking;
    }

    public void setMasking(String masking) {
        this.masking = masking;
    }

    public String getControl() {
        return control;
    }

    public void setControl(String control) {
        this.control = control;
    }

    public String getAssignment() {
        return assignment;
    }

    public void setAssignment(String assignment) {
        this.assignment = assignment;
    }

    public String getEndpoint() {
        return endpoint;
    }

    public void setEndpoint(String endpoint) {
        this.endpoint = endpoint;
    }

    public String getInterventions() {
        return interventions;
    }

    public void setInterventions(String interventions) {
        this.interventions = interventions;
    }

    public String getDuration() {
        return duration;
    }

    public void setDuration(String duration) {
        this.duration = duration;
    }

    public String getSelection() {
        return selection;
    }

    public void setSelection(String selection) {
        this.selection = selection;
    }

    public String getTiming() {
        return timing;
    }

    public void setTiming(String timing) {
        this.timing = timing;
    }

    public String getOfficial_title() {
        return official_title;
    }

    public void setOfficial_title(String official_title) {
        this.official_title = official_title;
    }

    public boolean getResults_reference() {
        return results_reference;
    }

    public void setResults_reference(boolean results_reference) {
        this.results_reference = results_reference;
    }

    public String getOc_oid() {
        return oc_oid;
    }

    public void setOc_oid(String oc_oid) {
        this.oc_oid = oc_oid;
    }

    public Integer getOld_status_id() {
        return old_status_id;
    }

    public void setOld_status_id(Integer old_status_id) {
        this.old_status_id = old_status_id;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        Study study = (Study) o;

        if (id != null ? !id.equals(study.id) : study.id != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        return (int) (id ^ (id >>> 32));
    }

    @Override
    public String toString() {
        return "Study{" +
                "id=" + id +
                ", parent_study_id='" + parent_study_id + "'" +
                ", unique_identifier='" + unique_identifier + "'" +
                ", secondary_identifier='" + secondary_identifier + "'" +
                ", name='" + name + "'" +
                ", summary='" + summary + "'" +
                ", date_planned_start='" + date_planned_start + "'" +
                ", date_planned_end='" + date_planned_end + "'" +
                ", date_created='" + date_created + "'" +
                ", date_updated='" + date_updated + "'" +
                ", owner_id='" + owner_id + "'" +
                ", update_id='" + update_id + "'" +
                ", type_id='" + type_id + "'" +
                ", status_id='" + status_id + "'" +
                ", principal_investigator='" + principal_investigator + "'" +
                ", facility_name='" + facility_name + "'" +
                ", facility_city='" + facility_city + "'" +
                ", facility_state='" + facility_state + "'" +
                ", facility_zip='" + facility_zip + "'" +
                ", facility_country='" + facility_country + "'" +
                ", facility_recruitment_status='" + facility_recruitment_status + "'" +
                ", facility_contact_name='" + facility_contact_name + "'" +
                ", facility_contact_degree='" + facility_contact_degree + "'" +
                ", facility_contact_phone='" + facility_contact_phone + "'" +
                ", facility_contact_email='" + facility_contact_email + "'" +
                ", protocol_type='" + protocol_type + "'" +
                ", protocol_description='" + protocol_description + "'" +
                ", protocol_date_verification='" + protocol_date_verification + "'" +
                ", phase='" + phase + "'" +
                ", expected_total_enrollment='" + expected_total_enrollment + "'" +
                ", sponsor='" + sponsor + "'" +
                ", collaborators='" + collaborators + "'" +
                ", medline_identifier='" + medline_identifier + "'" +
                ", url='" + url + "'" +
                ", url_description='" + url_description + "'" +
                ", conditions='" + conditions + "'" +
                ", keywords='" + keywords + "'" +
                ", eligibility='" + eligibility + "'" +
                ", gender='" + gender + "'" +
                ", age_max='" + age_max + "'" +
                ", age_min='" + age_min + "'" +
                ", healthy_volunteer_accepted='" + healthy_volunteer_accepted + "'" +
                ", purpose='" + purpose + "'" +
                ", allocation='" + allocation + "'" +
                ", masking='" + masking + "'" +
                ", control='" + control + "'" +
                ", assignment='" + assignment + "'" +
                ", endpoint='" + endpoint + "'" +
                ", interventions='" + interventions + "'" +
                ", duration='" + duration + "'" +
                ", selection='" + selection + "'" +
                ", timing='" + timing + "'" +
                ", official_title='" + official_title + "'" +
                ", results_reference='" + results_reference + "'" +
                ", oc_oid='" + oc_oid + "'" +
                ", old_status_id='" + old_status_id + "'" +
                '}';
    }
}
