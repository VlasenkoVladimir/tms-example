package effectiveMobile.com.taskManagementSystem.services;

import effectiveMobile.com.taskManagementSystem.domain.Comment;
import effectiveMobile.com.taskManagementSystem.dto.CommentDto;
import effectiveMobile.com.taskManagementSystem.mappers.CommentMapper;
import effectiveMobile.com.taskManagementSystem.repositories.CommentRepository;
import effectiveMobile.com.taskManagementSystem.repositories.TaskRepository;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

import java.util.List;

/**
 * Comment service class
 */
@Slf4j
@Service
@Validated
public class CommentService {

    private final CommentRepository commentRepository;
    private final CommentMapper commentMapper;

    public CommentService(TaskRepository taskRepository, CommentRepository commentRepository) {
        this.commentRepository = commentRepository;
        this.commentMapper = CommentMapper.INSTANCE;
    }

    /**
     * Create new Comment
     * @param commentDto DTO with new Comment data
     * @return commentDto if executed correctly
     */
    public CommentDto create(@Valid final CommentDto commentDto) {

        log.info("Call create with CommentDto is {}", commentDto);

        return commentMapper.toDto(commentRepository.save(commentMapper.toComment(commentDto)));
    }

    /**
     * Get all Comments of Task
     * @param taskId long Task id
     * @return List<CommentDto> with all Comments for Task
     */
    public List<CommentDto> getAllByTaskId(@Valid final long taskId) {

        log.info("Call getAllByTaskId with task id is {}", taskId);

        return commentRepository.getCommentsByTaskId(taskId).stream().map(commentMapper::toDto).toList();
    }

    /**
     * Get all Comments of Task
     * @param taskId long Task id
     * @param pageable Pageable
     * @return Page with all Comments for Task
     */
    public Page<CommentDto> getAllByTaskIdPaginated(@Valid final Long taskId, final Pageable pageable) {

        log.info("Call getAllByTaskIdPaginated with task id is {}", taskId);

        Page<Comment> commentsPaginated = commentRepository.getCommentsByTaskId(taskId, pageable);
        List<CommentDto> result = commentsPaginated.getContent().stream().map(commentMapper::toDto).toList();

        return new PageImpl<>(result, pageable, commentsPaginated.getTotalElements());
    }
}