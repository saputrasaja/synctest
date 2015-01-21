package com.supadosworks.synctest.domain;


import javax.persistence.*;
import java.io.Serializable;

/**
 * A ExportFormat.
 */
@Entity
@Table(name = "export_format")
public class ExportFormat implements Serializable {

    @Id
    @Column(name = "export_format_id")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name = "name")
    private String name;

    @Column(name = "description")
    private String description;

    @Column(name = "mime_type")
    private String mime_type;

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

    public String getMime_type() {
        return mime_type;
    }

    public void setMime_type(String mime_type) {
        this.mime_type = mime_type;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        ExportFormat exportFormat = (ExportFormat) o;

        if (id != null ? !id.equals(exportFormat.id) : exportFormat.id != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        return (int) (id ^ (id >>> 32));
    }

    @Override
    public String toString() {
        return "ExportFormat{" +
                "id=" + id +
                ", name='" + name + "'" +
                ", description='" + description + "'" +
                ", mime_type='" + mime_type + "'" +
                '}';
    }
}
