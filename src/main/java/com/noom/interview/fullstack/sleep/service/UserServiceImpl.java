package com.noom.interview.fullstack.sleep.service;

import com.noom.interview.fullstack.sleep.model.User;
import com.noom.interview.fullstack.sleep.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    @Override
    public User getById(Long id) {
       return userRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("User not found."));
    }

}