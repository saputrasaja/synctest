package com.supadosworks.synctest.domain;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.supadosworks.synctest.domain.util.CustomDateTimeDeserializer;
import com.supadosworks.synctest.domain.util.CustomDateTimeSerializer;
import org.hibernate.annotations.Type;
import org.joda.time.DateTime;

import javax.persistence.*;
import java.io.Serializable;
import java.sql.Date;

/**
 * A StudyEvent.
 */
@Entity
@Table(name = "T_STUDYEVENT")
public class StudyEvent implements Serializable {

    @Id
    private Long id;

    @Column(name = "name")
    private String name;

    @Column(name = "label")
    private String label;

    @Column(name = "event_status")
    private String event_status;

    @Column(name = "status")
    private String status;

    @Column(name = "date_start", nullable = false)
    private Date date_start;

    @Column(name = "date_end", nullable = false)
    private Date date_end;

    @Column(name = "owner_id")
    private Integer owner_id;

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

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        StudyEvent studyEvent = (StudyEvent) o;

        if (id != null ? !id.equals(studyEvent.id) : studyEvent.id != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        return (int) (id ^ (id >>> 32));
    }

    @Override
    public String toString() {
        return "StudyEvent{" +
                "id=" + id +
                ", name='" + name + "'" +
                ", label='" + label + "'" +
                ", event_status='" + event_status + "'" +
                ", status='" + status + "'" +
                ", date_start='" + date_start + "'" +
                ", date_end='" + date_end + "'" +
                ", owner_id='" + owner_id + "'" +
                '}';
    }
}
