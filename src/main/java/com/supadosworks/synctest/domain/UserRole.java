package com.supadosworks.synctest.domain;


import javax.persistence.*;
import java.io.Serializable;

/**
 * A UserRole.
 */
@Entity
@Table(name = "user_role")
public class UserRole implements Serializable {

    @Id
    @Column(name = "role_id")
    private Long id;

    @Column(name = "role_name")
    private String role_name;

    @Column(name = "parent_id")
    private Integer parent_id;

    @Column(name = "role_desc")
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

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        UserRole userRole = (UserRole) o;

        if (id != null ? !id.equals(userRole.id) : userRole.id != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        return (int) (id ^ (id >>> 32));
    }

    @Override
    public String toString() {
        return "UserRole{" +
                "id=" + id +
                ", role_name='" + role_name + "'" +
                ", parent_id='" + parent_id + "'" +
                ", role_desc='" + role_desc + "'" +
                '}';
    }
}
