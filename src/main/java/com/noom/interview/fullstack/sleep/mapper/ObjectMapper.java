package com.noom.interview.fullstack.sleep.mapper;

public interface ObjectMapper<T, U> {
    U map(T source);
}
