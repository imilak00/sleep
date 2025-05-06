package com.noom.interview.fullstack.sleep.repository;

import com.noom.interview.fullstack.sleep.model.SleepLog;
import com.noom.interview.fullstack.sleep.model.User;
import com.noom.interview.fullstack.sleep.projection.MoodFrequency;
import com.noom.interview.fullstack.sleep.projection.SleepLogAverages;
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

    @Query(value = "select " +
            "min(sl.log_date) as date_from," +
            "max(sl.log_date) as date_to," +
            "ceil(avg(sl.duration_minutes)) as average_total_time," +
            "date_trunc('minute', avg(sl.start_time::time))::time," +
            "date_trunc('minute', avg(sl.end_time::time))::time " +
            "from sleep_logs sl " +
            "where sl.user_id = :userId " +
            "and sl.log_date between :dateFrom and :dateTo ",
            nativeQuery = true)
    SleepLogAverages getAverages(@Param("userId") Long userId,
                                 @Param("dateFrom") LocalDate dateFrom,
                                 @Param("dateTo") LocalDate dateTo);

    @Query("select new com.noom.interview.fullstack.sleep.projection.MoodFrequency(sl.mood, count(sl.id)) " +
            "from sleep_logs sl where sl.user.id = :userId and sl.logDate between :dateFrom and :dateTo " +
            "group by sl.mood")
    List<MoodFrequency> getMoodFrequencies(@Param("userId") Long userId,
                                           @Param("dateFrom") LocalDate dateFrom,
                                           @Param("dateTo") LocalDate dateTo);

}