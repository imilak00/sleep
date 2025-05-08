package com.noom.interview.fullstack.sleep.repository;

import com.noom.interview.fullstack.sleep.SleepApplication;
import com.noom.interview.fullstack.sleep.model.User;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.ActiveProfiles;

import java.util.Optional;

import static com.noom.interview.fullstack.sleep.util.UserUtil.TEST_ID;
import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
@ActiveProfiles(SleepApplication.UNIT_TEST_PROFILE)
public class UserRepositoryTest {

    @Autowired
    private UserRepository userRepository;

    @Test
    void shouldFindUserById() {
        Optional<User> returnedUser = userRepository.findById(TEST_ID);

        assertThat(returnedUser).isPresent();
        assertThat(returnedUser.get().getUsername()).isEqualTo("alice123");
    }

}
