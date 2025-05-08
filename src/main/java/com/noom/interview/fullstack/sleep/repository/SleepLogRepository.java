package com.noom.interview.fullstack.sleep.repository;

import com.noom.interview.fullstack.sleep.model.SleepLog;
import com.noom.interview.fullstack.sleep.model.User;
import com.noom.interview.fullstack.sleep.projection.DateRangeWithAverageDuration;
import com.noom.interview.fullstack.sleep.projection.MoodFrequency;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Repository
public interface SleepLogRepository extends JpaRepository<SleepLog, Long> {

    Optional<SleepLog> findByUserAndLogDate(User user, LocalDate logDate);

    @Query("select new com.noom.interview.fullstack.sleep.projection.DateRangeWithAverageDuration(" +
            "min(sl.logDate)," +
            "max(sl.logDate)," +
            "avg(sl.durationMinutes))" +
            "from sleep_logs sl " +
            "where sl.user.id = :userId " +
            "and sl.logDate between :dateFrom and :dateTo ")
    DateRangeWithAverageDuration getDateRangeWithAverageDuration(@Param("userId") Long userId,
                                             @Param("dateFrom") LocalDate dateFrom,
                                             @Param("dateTo") LocalDate dateTo);

    @Query("select new com.noom.interview.fullstack.sleep.projection.MoodFrequency(" +
            "sl.mood, " +
            "count(sl.id)) " +
            "from sleep_logs sl " +
            "where sl.user.id = :userId " +
            "and sl.logDate between :dateFrom and :dateTo " +
            "group by sl.mood")
    List<MoodFrequency> getMoodFrequencies(@Param("userId") Long userId,
                                           @Param("dateFrom") LocalDate dateFrom,
                                           @Param("dateTo") LocalDate dateTo);

    List<SleepLog> getSleepLogsByUserIdAndLogDateBetween(Long userId, LocalDate dateFrom, LocalDate dateTo);

}