package com.isonah.usermanagement.mapper;

import com.isonah.usermanagement.model.MystoryUser;
import com.isonah.usermanagement.model.UserDTO;

/**
 * @author ielksseyer
 */
public final class UserMapper {

    private UserMapper() {
    }

    public static MystoryUser map(UserDTO userDTO) {
        return new MystoryUser().setUsername(userDTO.getUsername()).setEmail(userDTO.getEmail());
    }

    public static UserDTO map(MystoryUser mystoryUser) {
        return new UserDTO().setUsername(mystoryUser.getUsername()).setEmail(mystoryUser.getEmail());
    }

}
