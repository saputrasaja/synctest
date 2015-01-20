package com.supadosworks.synctest.domain;


import javax.persistence.*;
import java.io.Serializable;

/**
 * A OcConfiguration.
 */
@Entity
@Table(name = "configuration")
public class OcConfiguration implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name = "key")
    private String key;

    @Column(name = "value")
    private String value;

    @Column(name = "description")
    private String description;

    @Column(name = "version")
    private Integer version;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Integer getVersion() {
        return version;
    }

    public void setVersion(Integer version) {
        this.version = version;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        OcConfiguration ocConfiguration = (OcConfiguration) o;

        if (id != null ? !id.equals(ocConfiguration.id) : ocConfiguration.id != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        return (int) (id ^ (id >>> 32));
    }

    @Override
    public String toString() {
        return "OcConfiguration{" +
                "id=" + id +
                ", key='" + key + "'" +
                ", value='" + value + "'" +
                ", description='" + description + "'" +
                ", version='" + version + "'" +
                '}';
    }
}
