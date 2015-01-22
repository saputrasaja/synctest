package com.supadosworks.synctest.domain;


import javax.persistence.*;
import java.io.Serializable;

/**
 * A Status.
 */
@Entity
@Table(name = "status")
public class Status implements Serializable {

    @Id
    @Column(name = "status_id")
    private Long id;

    @Column(name = "name")
    private String name;

    @Column(name = "description")
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

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        Status status = (Status) o;

        if (id != null ? !id.equals(status.id) : status.id != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        return (int) (id ^ (id >>> 32));
    }

    @Override
    public String toString() {
        return "Status{" +
                "id=" + id +
                ", name='" + name + "'" +
                ", description='" + description + "'" +
                '}';
    }
}
