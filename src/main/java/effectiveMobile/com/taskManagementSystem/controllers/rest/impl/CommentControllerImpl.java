package effectiveMobile.com.taskManagementSystem.controllers.rest.impl;

import effectiveMobile.com.taskManagementSystem.controllers.rest.CommentController;
import effectiveMobile.com.taskManagementSystem.controllers.rest.GenericRestController;
import effectiveMobile.com.taskManagementSystem.dto.CommentDto;
import effectiveMobile.com.taskManagementSystem.services.CommentService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;

import java.util.List;

import static org.springframework.http.HttpStatus.CREATED;
import static org.springframework.http.HttpStatus.OK;

@Slf4j
@AllArgsConstructor
public class CommentControllerImpl extends GenericRestController implements CommentController {

    private final CommentService commentService;

    @Override
    public ResponseEntity<HttpStatus> create(@Validated CommentDto commentDto) {

        log.info("Call CommentControllerImpl endpoint create with CommentDto is: {}", commentDto);

        // TODO Security check

        commentService.create(commentDto);

        return new ResponseEntity<>(CREATED);
    }

    @Override
    public ResponseEntity<List<CommentDto>> getAllByTaskId(long taskId) {

        log.info("Call CommentControllerImpl endpoint getAllByTaskId with Task id is: {}", taskId);

        // TODO Security check

        return ResponseEntity.status(OK).body(commentService.getAllByTaskId(taskId));
    }

    @Override
    public ResponseEntity<Page<CommentDto>> getAllByTaskIdPaginated(long taskId, Pageable pageable) {

        log.info("Call CommentControllerImpl endpoint getAllByTaskIdPaginated with Task id is: {} and Pageable is: {}", taskId, pageable);

        // TODO Security check

        return ResponseEntity.status(OK).body(commentService.getAllByTaskIdPaginated(taskId, pageable));
    }
}