package com.to_do.project.domain.repositories;

import com.to_do.project.domain.entities.task.Task;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface TaskRepository extends CrudRepository<Task, UUID>, PagingAndSortingRepository<Task, UUID> {
    @Query(value = "SELECT * FROM tasks WHERE user_id = :userId AND (:content IS NULL OR title ILIKE %:content% OR description ILIKE %:content%) AND (CAST(:priority as TEXT) IS NULL OR priority ILIKE %:priority%) " +
            "AND due_date >= COALESCE(:startDate, due_date) AND due_date <= COALESCE(:endDate, due_date) AND (CAST(:done as BOOLEAN) IS NULL OR done = :done) AND (CAST(:isDeleted AS BOOLEAN) IS NULL OR is_deleted = :isDeleted)", nativeQuery = true)
    Optional<List<Task>> findByUserFiltered(UUID userId, String content, String priority, Date startDate, Date endDate, boolean done, boolean isDeleted, Pageable pageable);
}
