package com.noom.interview.fullstack.sleep.repository;

import com.noom.interview.fullstack.sleep.SleepApplication;
import com.noom.interview.fullstack.sleep.model.User;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.ActiveProfiles;

import java.util.Optional;

import static com.noom.interview.fullstack.sleep.util.UserUtil.TEST_USER_ID;
import static com.noom.interview.fullstack.sleep.util.UserUtil.TEST_USER_USERNAME;
import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
@ActiveProfiles(SleepApplication.UNIT_TEST_PROFILE)
public class UserRepositoryTest {

    @Autowired
    private UserRepository userRepository;

    @Test
    void shouldFindUserById() {
        Optional<User> user = userRepository.findById(TEST_USER_ID);

        assertThat(user).isPresent();
        assertThat(user.get().getUsername()).isEqualTo(TEST_USER_USERNAME);
    }

}
