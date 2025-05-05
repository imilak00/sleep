package com.noom.interview.fullstack.sleep;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import static org.assertj.core.api.Assertions.assertThat;


@SpringBootTest
@ActiveProfiles(SleepApplication.UNIT_TEST_PROFILE)
public class SleepApplicationTests {

    @Test
    void contextLoads() {
        assertThat(true).isTrue();
    }
}
