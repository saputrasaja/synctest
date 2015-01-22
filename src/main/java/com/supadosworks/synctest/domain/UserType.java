package com.supadosworks.synctest.domain;


import javax.persistence.*;
import java.io.Serializable;

/**
 * A UserType.
 */
@Entity
@Table(name = "user_type")
public class UserType implements Serializable {

    @Id
    @Column(name = "user_type_id")
    private Long id;

    @Column(name = "user_type")
    private String user_type;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUser_type() {
        return user_type;
    }

    public void setUser_type(String user_type) {
        this.user_type = user_type;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        UserType userType = (UserType) o;

        if (id != null ? !id.equals(userType.id) : userType.id != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        return (int) (id ^ (id >>> 32));
    }

    @Override
    public String toString() {
        return "UserType{" +
                "id=" + id +
                ", user_type='" + user_type + "'" +
                '}';
    }
}
