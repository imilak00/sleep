package com.noom.interview.fullstack.sleep.mapper;

import com.noom.interview.fullstack.sleep.dto.response.UserResponse;
import com.noom.interview.fullstack.sleep.model.User;
import org.springframework.stereotype.Component;

@Component
public class UserToUserResponseMapper implements ObjectMapper<User, UserResponse> {

    @Override
    public UserResponse map(User source) {
        UserResponse destination = new UserResponse();
        destination.setId(source.getId());
        destination.setUsername(source.getUsername());
        destination.setEmail(source.getEmail());

        return destination;
    }
}