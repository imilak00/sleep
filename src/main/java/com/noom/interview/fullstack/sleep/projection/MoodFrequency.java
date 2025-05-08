package com.noom.interview.fullstack.sleep.projection;

import com.noom.interview.fullstack.sleep.model.Mood;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class MoodFrequency {

    private Mood mood;
    private Integer frequency;

    public MoodFrequency(Mood mood, Long frequency) {
        this.mood = mood;
        this.frequency = frequency.intValue();
    }

}