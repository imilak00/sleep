package com.noom.interview.fullstack.sleep.util;

import com.noom.interview.fullstack.sleep.model.SleepLog;
import com.noom.interview.fullstack.sleep.model.User;

import java.util.List;

public class UserUtil {

    public static final long TEST_ID = 1L;
    public static final String TEST_USERNAME = "John123";

    public static User generateUserStub() {

        return new User(TEST_ID, TEST_USERNAME, "john@example.com", "pwd123", List.of(new SleepLog()));
    }

}