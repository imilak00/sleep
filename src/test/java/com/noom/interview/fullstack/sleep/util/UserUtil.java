package com.noom.interview.fullstack.sleep.util;

import com.noom.interview.fullstack.sleep.model.SleepLog;
import com.noom.interview.fullstack.sleep.model.User;

import java.util.List;

public class UserUtil {

    public static final long TEST_USER_ID = 1L;
    public static final String TEST_USER_USERNAME = "John123";

    public static User generateUserStub() {

        return new User(TEST_USER_ID, TEST_USER_USERNAME, "john@example.com", "pwd123", List.of(new SleepLog()));
    }

}
