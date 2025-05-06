package com.noom.interview.fullstack.sleep.projection;

import com.noom.interview.fullstack.sleep.model.Mood;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class MoodFrequency {

    private Mood mood;
    private Long frequency;

}