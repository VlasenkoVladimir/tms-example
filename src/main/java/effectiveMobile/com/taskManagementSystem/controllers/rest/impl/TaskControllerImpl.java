package effectiveMobile.com.taskManagementSystem.controllers.rest.impl;

import effectiveMobile.com.taskManagementSystem.controllers.rest.GenericRestController;
import effectiveMobile.com.taskManagementSystem.controllers.rest.TaskController;
import effectiveMobile.com.taskManagementSystem.domain.enums.Priority;
import effectiveMobile.com.taskManagementSystem.domain.enums.Status;
import effectiveMobile.com.taskManagementSystem.dto.TaskDto;
import effectiveMobile.com.taskManagementSystem.services.TaskService;
import io.swagger.v3.oas.annotations.Parameter;
import jakarta.validation.constraints.Min;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

import static org.springframework.http.HttpStatus.CREATED;
import static org.springframework.http.HttpStatus.OK;

/**
 * Task endpoint implementation
 */
@Slf4j
@AllArgsConstructor
public class TaskControllerImpl extends GenericRestController implements TaskController {

    private TaskService taskService;


    @Override
    public ResponseEntity<HttpStatus> create(@RequestBody @Validated TaskDto taskDto) {

        log.info("Call TaskControllerImpl endpoint create with TaskDto is: {}", taskDto);

        // TODO Security check

        taskService.create(taskDto);

        return new ResponseEntity<>(CREATED);
    }

    @Override
    public ResponseEntity<Page<TaskDto>> getAllPaginated(@RequestBody @Validated Pageable pageable) {

        log.info("Call TaskControllerImpl endpoint getAllPaginated with Pageable is: {}", pageable);

        // TODO Security check

        return ResponseEntity.status(OK).body(taskService.getAllPaginated(pageable));
    }

    @Override
    public ResponseEntity<List<TaskDto>> getTasksByAuthorId(
            @Parameter(name = "Author id", description = "Author id, minimal long value = 1")
            @RequestParam @Min(1) long authorId) {

        log.info("Call TaskControllerImpl endpoint getTasksByAuthorId with authorId is: {}", authorId);

        // TODO Security check

        return ResponseEntity.status(OK).body(taskService.getTasksByAuthorId(authorId));
    }

    @Override
    public ResponseEntity<Page<TaskDto>> getTasksByAuthorIdPaginated(
            @Parameter(name = "Author id", description = "Author id, minimal long value = 1")
            @RequestParam @Min(1) long authorId,
            @RequestBody @Validated Pageable pageable) {

        log.info("Call TaskControllerImpl endpoint getTasksByAuthorIdPaginated with authorId is: {} and Pageable is: {}", authorId, pageable);

        // TODO Security check

        return ResponseEntity.status(OK).body(taskService.getTasksByAuthorIdPaginated(authorId, pageable));
    }

    @Override
    public ResponseEntity<List<TaskDto>> getTasksByExecutorId(
            @Parameter(name = "Executor id", description = "Executor id, minimal long value = 1")
            @RequestParam @Min(1) long executorId) {

        log.info("Call TaskControllerImpl endpoint getTasksByExecutorId with executorId is: {}", executorId);

        // TODO Security check

        return ResponseEntity.status(OK).body(taskService.getTasksByExecutorId(executorId));
    }

    @Override
    public ResponseEntity<Page<TaskDto>> getTasksByExecutorIdPaginated(
            @Parameter(name = "Executor id", description = "Executor id, minimal long value = 1")
            @RequestParam @Min(1) long executorId,
            @RequestBody @Validated Pageable pageable) {

        log.info("Call TaskControllerImpl endpoint getTasksByExecutorIdPaginated with executorId is: {} and Pageable is: {}", executorId, pageable);

        // TODO Security check

        return ResponseEntity.status(OK).body(taskService.getTasksByExecutorIdPaginated(executorId, pageable));
    }

    @Override
    public ResponseEntity<TaskDto> setExecutor(
            @Parameter(name = "Task id", description = "Task id, minimal long value = 1")
            @RequestParam @Min(1) long taskId,
            @Parameter(name = "Executor id", description = "Executor id, minimal long value = 1")
            @RequestParam @Min(1) long executorId) {

        log.info("Call TaskControllerImpl endpoint setExecutor with taskId is: {} and executorId is: {}", taskId, executorId);

        // TODO Security check

        return ResponseEntity.status(OK).body(taskService.setExecutor(taskId, executorId));
    }

    @Override
    public ResponseEntity<TaskDto> changeStatus(
            @Parameter(name = "Status", description = "Task status")
            @Validated Status newStatus,
            @Parameter(name = "Task id", description = "Task id, minimal long value = 1")
            @RequestParam @Min(1) long taskId) {

        log.info("Call TaskControllerImpl endpoint changeStatus with newStatus is: {} and taskId is: {}", newStatus, taskId);

        // TODO Security check

        return ResponseEntity.status(OK).body(taskService.changeStatus(newStatus, taskId));
    }

    @Override
    public ResponseEntity<TaskDto> changePriority(
            @Parameter(name = "Priority", description = "Task priority")
            @Validated Priority newPriority,
            @Parameter(name = "Task id", description = "Task id, minimal long value = 1")
            @RequestParam @Min(1) long taskId) {

        log.info("Call TaskControllerImpl endpoint changePriority with newPriority is: {} and taskId is: {}", newPriority, taskId);

        // TODO Security check

        return ResponseEntity.status(OK).body(taskService.changePriority(newPriority, taskId));
    }

    @Override
    public ResponseEntity<TaskDto> update(@RequestBody @Validated TaskDto updatedTask) {

        log.info("Call TaskControllerImpl endpoint update with TaskDto is: {}", updatedTask);

        // TODO Security check

        return ResponseEntity.status(OK).body(taskService.update(updatedTask));
    }

    @Override
    public ResponseEntity<HttpStatus> delete(
            @Parameter(name = "Task id, long value", description = "Task number, minimal value = 1")
            @RequestParam @Min(1) long taskId) {

        log.info("Call TaskControllerImpl endpoint delete with taskId is: {}", taskId);

        // TODO Security check

        taskService.delete(taskId);

        return new ResponseEntity<>(OK);
    }
}