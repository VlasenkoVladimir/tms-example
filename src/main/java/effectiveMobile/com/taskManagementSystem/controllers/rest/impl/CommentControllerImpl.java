package effectiveMobile.com.taskManagementSystem.controllers.rest.impl;

import effectiveMobile.com.taskManagementSystem.controllers.rest.CommentController;
import effectiveMobile.com.taskManagementSystem.domain.User;
import effectiveMobile.com.taskManagementSystem.dto.CommentDto;
import effectiveMobile.com.taskManagementSystem.dto.TaskDto;
import effectiveMobile.com.taskManagementSystem.services.CommentService;
import effectiveMobile.com.taskManagementSystem.services.TaskService;
import effectiveMobile.com.taskManagementSystem.services.UserService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

import static org.springframework.http.HttpStatus.*;

/**
 * Comment endpoint implementation
 */
@Slf4j
@RestController
@Validated
@AllArgsConstructor
public class CommentControllerImpl implements CommentController {

    private final CommentService commentService;
    private final TaskService taskService;
    private final UserService userService;

    @Override
    public ResponseEntity<HttpStatus> create(@Valid CommentDto commentDto) {

        log.info("Call CommentControllerImpl endpoint create with CommentDto is: {}", commentDto);

        Authentication auth = SecurityContextHolder.getContext().getAuthentication();

        if (auth.getAuthorities().stream().anyMatch(a -> a.getAuthority().equals("ADMIN"))) {

            commentService.create(commentDto);

            return new ResponseEntity<>(CREATED);
        }

        if (auth.getAuthorities().stream().anyMatch(a -> a.getAuthority().equals("USER"))) {

            User currentUser = userService.getByUsername(auth.getName());
            TaskDto task = taskService.getById(commentDto.getTask());

            if (task.getAuthor().equals(currentUser.getId()) || task.getExecutor().equals(currentUser.getId())) {

                commentService.create(commentDto);

                return new ResponseEntity<>(CREATED);
            }
        }

        return new ResponseEntity<>(FORBIDDEN);
    }

    @Override
    public ResponseEntity<List<CommentDto>> getAllByTaskId(@Valid long taskId) {

        log.info("Call CommentControllerImpl endpoint getAllByTaskId with Task id is: {}", taskId);

        Authentication auth = SecurityContextHolder.getContext().getAuthentication();

        if (auth.getAuthorities().stream().anyMatch(a -> a.getAuthority().equals("ADMIN"))) {

            return ResponseEntity.status(OK).body(commentService.getAllByTaskId(taskId));
        }

        if (auth.getAuthorities().stream().anyMatch(a -> a.getAuthority().equals("USER"))) {

            long currentUserId = userService.getByUsername(auth.getName()).getId();
            TaskDto task = taskService.getById(taskId);

            if (task.getAuthor().equals(currentUserId) || task.getExecutor().equals(currentUserId)) {

                return ResponseEntity.status(OK).body(commentService.getAllByTaskId(taskId));
            }
        }

        return new ResponseEntity<>(FORBIDDEN);
    }

    @Override
    public ResponseEntity<Page<CommentDto>> getAllByTaskIdPaginated(@Valid long taskId, Pageable pageable) {

        log.info("Call CommentControllerImpl endpoint getAllByTaskIdPaginated with Task id is: {} and Pageable is: {}", taskId, pageable);

        Authentication auth = SecurityContextHolder.getContext().getAuthentication();

        if (auth.getAuthorities().stream().anyMatch(a -> a.getAuthority().equals("ADMIN"))) {

            return ResponseEntity.status(OK).body(commentService.getAllByTaskIdPaginated(taskId, pageable));
        }

        if (auth.getAuthorities().stream().anyMatch(a -> a.getAuthority().equals("USER"))) {

            long currentUserId = userService.getByUsername(auth.getName()).getId();
            TaskDto task = taskService.getById(taskId);

            if (task.getAuthor().equals(currentUserId) || task.getExecutor().equals(currentUserId)) {

                return ResponseEntity.status(OK).body(commentService.getAllByTaskIdPaginated(taskId, pageable));
            }
        }

        return new ResponseEntity<>(FORBIDDEN);
    }
}