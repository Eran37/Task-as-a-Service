package com.example.taas.repos;

import com.example.taas.beans.Task;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.sql.Timestamp;
import java.util.List;

@Repository
public interface TaskRepository extends JpaRepository<Task, Integer> {

    List<Task> findByOrderByWhenAsc();
    List<Task> findByOrderByWhenDesc();
    List<Task> findByWhenBetween(Timestamp startDate, Timestamp endDate);
    List<Task> findByUserId(int userId);

    @Modifying
    @Query(value = "UPDATE `taas2`.`tasks` SET `title` = :name WHERE (`id` = :id);", nativeQuery = true)
    void updateName( @Param("name") String name, @Param("id") int id);

}
