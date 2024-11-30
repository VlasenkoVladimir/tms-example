package effectiveMobile.com.taskManagementSystem.repositories;

import effectiveMobile.com.taskManagementSystem.domain.Comment;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Comment repository
 */

@Repository
public interface CommentRepository extends JpaRepository<Comment, Long> {

	Page<Comment> getCommentsByTaskId(Long taskId, Pageable pageRequest);

	List<Comment> getCommentsByTaskId(Long taskId);
}