package com.isonah.usermanagement.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * @author ielksseyer
 */
@Entity
@Table(name = "users")
public class MystoryUser {

    @Id
    private String username;

    @Column(unique = true, nullable = false)
    private String email;

    public String getUsername() {
        return username;
    }

    public MystoryUser setUsername(String username) {
        this.username = username;
        return this;
    }

    public String getEmail() {
        return email;
    }

    public MystoryUser setEmail(String email) {
        this.email = email;
        return this;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("MystoryUser{");
        sb.append("username='").append(username).append('\'');
        sb.append(", email='").append(email).append('\'');
        sb.append('}');
        return sb.toString();
    }
}

