package com.supadosworks.synctest.domain;


import javax.persistence.*;
import java.io.Serializable;

/**
 * A DatasetItemStatus.
 */
@Entity
@Table(name = "dataset_item_status")
public class DatasetItemStatus implements Serializable {

    @Id
    @Column(name = "dataset_item_status_id")
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

        DatasetItemStatus datasetItemStatus = (DatasetItemStatus) o;

        if (id != null ? !id.equals(datasetItemStatus.id) : datasetItemStatus.id != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        return (int) (id ^ (id >>> 32));
    }

    @Override
    public String toString() {
        return "DatasetItemStatus{" +
                "id=" + id +
                ", name='" + name + "'" +
                ", description='" + description + "'" +
                '}';
    }
}
