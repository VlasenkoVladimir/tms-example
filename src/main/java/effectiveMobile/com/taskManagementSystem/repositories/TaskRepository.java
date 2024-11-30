package effectiveMobile.com.taskManagementSystem.repositories;

import effectiveMobile.com.taskManagementSystem.domain.Task;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Task repository
 */

@Repository
public interface TaskRepository extends JpaRepository<Task, Long> {

	Page<Task> getTasksByAuthorId(Long authorId, Pageable pageRequest);
	List<Task> getTasksByAuthorId(Long authorId);

	Page<Task> getTasksByExecutorId(Long executorId, Pageable pageRequest);
	List<Task> getTasksByExecutorId(Long executorId);
}