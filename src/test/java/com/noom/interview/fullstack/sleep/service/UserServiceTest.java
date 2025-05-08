package com.noom.interview.fullstack.sleep.service;

import com.noom.interview.fullstack.sleep.model.User;
import com.noom.interview.fullstack.sleep.repository.UserRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import javax.persistence.EntityNotFoundException;
import java.util.Optional;

import static com.noom.interview.fullstack.sleep.util.UserUtil.*;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class UserServiceTest {
    @Mock
    private UserRepository userRepository;
    @InjectMocks
    private UserServiceImpl userService;

    @Test
    public void shouldReturnUserWhenFound() {
        User user = generateUserStub();
        when(userRepository.findById(TEST_ID)).thenReturn(Optional.of(user));

        User result = userService.getById(TEST_ID);

        assertThat(result.getUsername()).isEqualTo(TEST_USERNAME);
    }

    @Test
    public void shouldThrowWhenUserNotFound() {
        when(userRepository.findById(TEST_ID)).thenReturn(Optional.empty());

        assertThrows(EntityNotFoundException.class, () -> userService.getById(TEST_ID));
    }
}