package com.isonah.usermanagement.model;

import javax.validation.constraints.Email;
import javax.validation.constraints.Size;

/**
 * @author ielksseyer
 */
public class UserDTO {

    @Size(min = 6, max = 25)
    private String username;

    @Email
    private String email;

    public String getUsername() {
        return username;
    }

    public UserDTO setUsername(String username) {
        this.username = username;
        return this;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("UserDTO{");
        sb.append("username='").append(username).append('\'');
        sb.append(", email='").append(email).append('\'');
        sb.append('}');
        return sb.toString();
    }

    public String getEmail() {
        return email;
    }

    public UserDTO setEmail(String email) {
        this.email = email;
        return this;
    }
}
