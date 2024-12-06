package effectiveMobile.com.taskManagementSystem.controllers.rest.impl;

import effectiveMobile.com.taskManagementSystem.controllers.rest.TaskController;
import effectiveMobile.com.taskManagementSystem.domain.User;
import effectiveMobile.com.taskManagementSystem.domain.enums.Priority;
import effectiveMobile.com.taskManagementSystem.domain.enums.Status;
import effectiveMobile.com.taskManagementSystem.dto.TaskDto;
import effectiveMobile.com.taskManagementSystem.services.TaskService;
import effectiveMobile.com.taskManagementSystem.services.UserService;
import io.swagger.v3.oas.annotations.Parameter;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Min;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

import static org.springframework.http.HttpStatus.*;

/**
 * Task endpoint implementation
 */
@Slf4j
@RestController
@Validated
@AllArgsConstructor
public class TaskControllerImpl implements TaskController {

    private TaskService taskService;
    private final UserService userService;


    @Override
    public ResponseEntity<HttpStatus> create(@RequestBody @Valid TaskDto taskDto) {

        log.info("Call TaskControllerImpl endpoint create with TaskDto is: {}", taskDto);

        Authentication auth = SecurityContextHolder.getContext().getAuthentication();

        if (auth.getAuthorities().stream().anyMatch(a -> a.getAuthority().equals("ADMIN"))) {

            taskService.create(taskDto);

            return new ResponseEntity<>(CREATED);
        }

        if (auth.getAuthorities().stream().anyMatch(a -> a.getAuthority().equals("USER"))) {

            User currentUser = userService.getByUsername(auth.getName());

            if (taskDto.getAuthor().equals(currentUser.getId())) {

                taskService.create(taskDto);

                return new ResponseEntity<>(CREATED);
            }
        }

        return new ResponseEntity<>(FORBIDDEN);
    }

    @Override
    public ResponseEntity<Page<TaskDto>> getAllTasksPaginated(@RequestBody @Valid Pageable pageable) {

        log.info("Call TaskControllerImpl endpoint getAllPaginated with Pageable is: {}", pageable);

        Authentication auth = SecurityContextHolder.getContext().getAuthentication();

        if (auth.getAuthorities().stream().anyMatch(a -> a.getAuthority().equals("ADMIN"))) {

            return ResponseEntity.status(OK).body(taskService.getAllPaginated(pageable));
        }

        return new ResponseEntity<>(FORBIDDEN);
    }

    @Override
    public ResponseEntity<List<TaskDto>> getAllTasksByAuthorId(
            @Parameter(name = "Author id", description = "Author id, minimal long value = 1")
            @RequestParam @Min(1) long authorId) {

        log.info("Call TaskControllerImpl endpoint getTasksByAuthorId with authorId is: {}", authorId);

        Authentication auth = SecurityContextHolder.getContext().getAuthentication();

        if (auth.getAuthorities().stream().anyMatch(a -> a.getAuthority().equals("ADMIN"))) {

            return ResponseEntity.status(OK).body(taskService.getTasksByAuthorId(authorId));
        }

        if (auth.getAuthorities().stream().anyMatch(a -> a.getAuthority().equals("USER"))) {

            long currentUserId = userService.getByUsername(auth.getName()).getId();

            if (currentUserId == authorId) {

                return ResponseEntity.status(OK).body(taskService.getTasksByAuthorId(currentUserId));
            }
        }

        return new ResponseEntity<>(FORBIDDEN);
    }

    @Override
    public ResponseEntity<Page<TaskDto>> getAllTasksByAuthorIdPaginated(
            @Parameter(name = "Author id", description = "Author id, minimal long value = 1")
            @RequestParam @Min(1) long authorId,
            @RequestBody @Valid Pageable pageable) {

        log.info("Call TaskControllerImpl endpoint getTasksByAuthorIdPaginated with authorId is: {} and Pageable is: {}", authorId, pageable);

        Authentication auth = SecurityContextHolder.getContext().getAuthentication();

        if (auth.getAuthorities().stream().anyMatch(a -> a.getAuthority().equals("ADMIN"))) {

            return ResponseEntity.status(OK).body(taskService.getTasksByAuthorIdPaginated(authorId, pageable));
        }

        if (auth.getAuthorities().stream().anyMatch(a -> a.getAuthority().equals("USER"))) {

            long currentUserId = userService.getByUsername(auth.getName()).getId();

            if (currentUserId == authorId) {

                return ResponseEntity.status(OK).body(taskService.getTasksByAuthorIdPaginated(currentUserId, pageable));
            }
        }

        return new ResponseEntity<>(FORBIDDEN);
    }

    @Override
    public ResponseEntity<List<TaskDto>> getAllTasksByExecutorId(
            @Parameter(name = "Executor id", description = "Executor id, minimal long value = 1")
            @RequestParam @Min(1) long executorId) {

        log.info("Call TaskControllerImpl endpoint getTasksByExecutorId with executorId is: {}", executorId);

        Authentication auth = SecurityContextHolder.getContext().getAuthentication();

        if (auth.getAuthorities().stream().anyMatch(a -> a.getAuthority().equals("ADMIN"))) {

            return ResponseEntity.status(OK).body(taskService.getTasksByExecutorId(executorId));
        }

        if (auth.getAuthorities().stream().anyMatch(a -> a.getAuthority().equals("USER"))) {

            long currentUserId = userService.getByUsername(auth.getName()).getId();

            if (currentUserId == executorId) {

                return ResponseEntity.status(OK).body(taskService.getTasksByExecutorId(currentUserId));
            }
        }

        return new ResponseEntity<>(FORBIDDEN);
    }

    @Override
    public ResponseEntity<Page<TaskDto>> getAllTasksByExecutorIdPaginated(
            @Parameter(name = "Executor id", description = "Executor id, minimal long value = 1")
            @RequestParam @Min(1) long executorId,
            @RequestBody @Valid Pageable pageable) {

        log.info("Call TaskControllerImpl endpoint getTasksByExecutorIdPaginated with executorId is: {} and Pageable is: {}", executorId, pageable);

        Authentication auth = SecurityContextHolder.getContext().getAuthentication();

        if (auth.getAuthorities().stream().anyMatch(a -> a.getAuthority().equals("ADMIN"))) {

            return ResponseEntity.status(OK).body(taskService.getTasksByExecutorIdPaginated(executorId, pageable));
        }

        if (auth.getAuthorities().stream().anyMatch(a -> a.getAuthority().equals("USER"))) {

            long currentUserId = userService.getByUsername(auth.getName()).getId();

            if (currentUserId == executorId) {

                return ResponseEntity.status(OK).body(taskService.getTasksByExecutorIdPaginated(currentUserId, pageable));
            }
        }

        return new ResponseEntity<>(FORBIDDEN);
    }

    @Override
    public ResponseEntity<TaskDto> setTaskExecutor(
            @Parameter(name = "Task id", description = "Task id, minimal long value = 1")
            @RequestParam @Min(1) long taskId,
            @Parameter(name = "Executor id", description = "Executor id, minimal long value = 1")
            @RequestParam @Min(1) long executorId) {

        log.info("Call TaskControllerImpl endpoint setExecutor with taskId is: {} and executorId is: {}", taskId, executorId);

        Authentication auth = SecurityContextHolder.getContext().getAuthentication();

        if (auth.getAuthorities().stream().anyMatch(a -> a.getAuthority().equals("ADMIN"))) {

            return ResponseEntity.status(OK).body(taskService.setExecutor(taskId, executorId));
        }

        if (auth.getAuthorities().stream().anyMatch(a -> a.getAuthority().equals("USER"))) {

            long currentUserId = userService.getByUsername(auth.getName()).getId();
            long authorId = taskService.getById(taskId).getAuthor();

            if (currentUserId == authorId) {

                return ResponseEntity.status(OK).body(taskService.setExecutor(taskId, executorId));
            }
        }

        return new ResponseEntity<>(FORBIDDEN);
    }

    @Override
    public ResponseEntity<TaskDto> changeTaskStatus(
            @Parameter(name = "Status", description = "Task status")
            @Valid Status newStatus,
            @Parameter(name = "Task id", description = "Task id, minimal long value = 1")
            @RequestParam @Min(1) long taskId) {

        log.info("Call TaskControllerImpl endpoint changeStatus with newStatus is: {} and taskId is: {}", newStatus, taskId);

        Authentication auth = SecurityContextHolder.getContext().getAuthentication();

        if (auth.getAuthorities().stream().anyMatch(a -> a.getAuthority().equals("ADMIN"))) {

            return ResponseEntity.status(OK).body(taskService.changeStatus(newStatus, taskId));
        }

        if (auth.getAuthorities().stream().anyMatch(a -> a.getAuthority().equals("USER"))) {

            long currentUserId = userService.getByUsername(auth.getName()).getId();
            TaskDto task = taskService.getById(taskId);
            long authorId = task.getAuthor();
            long executorId = task.getExecutor();

            if (currentUserId == authorId || currentUserId == executorId) {

                return ResponseEntity.status(OK).body(taskService.changeStatus(newStatus, taskId));
            }
        }

        return new ResponseEntity<>(FORBIDDEN);
    }

    @Override
    public ResponseEntity<TaskDto> changeTaskPriority(
            @Parameter(name = "Priority", description = "Task priority")
            @Valid Priority newPriority,
            @Parameter(name = "Task id", description = "Task id, minimal long value = 1")
            @RequestParam @Min(1) long taskId) {

        log.info("Call TaskControllerImpl endpoint changePriority with newPriority is: {} and taskId is: {}", newPriority, taskId);

        Authentication auth = SecurityContextHolder.getContext().getAuthentication();

        if (auth.getAuthorities().stream().anyMatch(a -> a.getAuthority().equals("ADMIN"))) {

            return ResponseEntity.status(OK).body(taskService.changePriority(newPriority, taskId));
        }

        if (auth.getAuthorities().stream().anyMatch(a -> a.getAuthority().equals("USER"))) {

            long currentUserId = userService.getByUsername(auth.getName()).getId();
            long authorId = taskService.getById(taskId).getAuthor();

            if (currentUserId == authorId) {

                return ResponseEntity.status(OK).body(taskService.changePriority(newPriority, taskId));
            }
        }

        return new ResponseEntity<>(FORBIDDEN);
    }

    @Override
    public ResponseEntity<TaskDto> updateTask(@RequestBody @Valid TaskDto updatedTask) {

        log.info("Call TaskControllerImpl endpoint update with TaskDto is: {}", updatedTask);

        Authentication auth = SecurityContextHolder.getContext().getAuthentication();

        if (auth.getAuthorities().stream().anyMatch(a -> a.getAuthority().equals("ADMIN"))) {

            return ResponseEntity.status(OK).body(taskService.update(updatedTask));
        }

        if (auth.getAuthorities().stream().anyMatch(a -> a.getAuthority().equals("USER"))) {

            long currentUserId = userService.getByUsername(auth.getName()).getId();
            long authorId = taskService.getById(updatedTask.getId()).getAuthor();

            if (currentUserId == authorId) {

                return ResponseEntity.status(OK).body(taskService.update(updatedTask));
            }
        }

        return new ResponseEntity<>(FORBIDDEN);
    }

    @Override
    public ResponseEntity<HttpStatus> deleteTask(
            @Parameter(name = "Task id, long value", description = "Task number, minimal value = 1")
            @RequestParam @Min(1) long taskId) {

        log.info("Call TaskControllerImpl endpoint delete with taskId is: {}", taskId);

        Authentication auth = SecurityContextHolder.getContext().getAuthentication();

        if (auth.getAuthorities().stream().anyMatch(a -> a.getAuthority().equals("ADMIN"))) {

            taskService.delete(taskId);

            return new ResponseEntity<>(OK);
        }

        if (auth.getAuthorities().stream().anyMatch(a -> a.getAuthority().equals("USER"))) {

            long currentUserId = userService.getByUsername(auth.getName()).getId();
            long authorId = taskService.getById(taskId).getAuthor();

            if (currentUserId == authorId) {

                taskService.delete(taskId);

                return new ResponseEntity<>(OK);
            }
        }

        return new ResponseEntity<>(FORBIDDEN);
    }
}