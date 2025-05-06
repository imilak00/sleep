package com.noom.interview.fullstack.sleep.controller;

import com.noom.interview.fullstack.sleep.dto.response.UserResponse;
import com.noom.interview.fullstack.sleep.mapper.ObjectMapper;
import com.noom.interview.fullstack.sleep.model.User;
import com.noom.interview.fullstack.sleep.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/users")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    private final ObjectMapper<User, UserResponse> userToUserResponseMapper;

    @GetMapping("/{id}")
    public ResponseEntity<Object> getById(@PathVariable Long id) {
        UserResponse response = userToUserResponseMapper.map(userService.getById(id));

        return ResponseEntity.ok(response);
    }
}