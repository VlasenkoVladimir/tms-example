package effectiveMobile.com.taskManagementSystem.controllers.rest;

import effectiveMobile.com.taskManagementSystem.domain.enums.Priority;
import effectiveMobile.com.taskManagementSystem.domain.enums.Status;
import effectiveMobile.com.taskManagementSystem.dto.TaskDto;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Min;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;
import static org.springframework.web.bind.annotation.RequestMethod.*;

/**
 * Task endpoint interface
 */
@RequestMapping("/api/rest/tasks")
@Tag(name = "Tasks", description = "Endpoint for tasks")
public interface TaskController {

    @Operation(summary = "Save task to DB")
    @ApiResponses(value = {@ApiResponse(responseCode = "201", description = "Task saved")})
    @RequestMapping(method = POST, consumes = APPLICATION_JSON_VALUE)
    ResponseEntity<HttpStatus> create(
            @RequestBody @Valid TaskDto taskDto);

    @Operation(summary = "Get page of all tasks")
    @ApiResponses(value = {@ApiResponse(responseCode = "200", description = "Page of tasks got")})
    @RequestMapping(method = GET, consumes = APPLICATION_JSON_VALUE, produces = APPLICATION_JSON_VALUE)
    ResponseEntity<Page<TaskDto>> getAllTasksPaginated(
            @RequestBody @Valid Pageable pageable);

    @Operation(summary = "Get all tasks of author")
    @ApiResponses(value = {@ApiResponse(responseCode = "200", description = "List of tasks got")})
    @RequestMapping(method = GET, consumes = APPLICATION_JSON_VALUE, produces = APPLICATION_JSON_VALUE)
    ResponseEntity<List<TaskDto>> getAllTasksByAuthorId(
            @Parameter(name = "Author id", description = "Author id, minimal long value = 1")
            @RequestParam @Min(1) long authorId);

    @Operation(summary = "Get page of author tasks")
    @ApiResponses(value = {@ApiResponse(responseCode = "200", description = "Page of tasks got")})
    @RequestMapping(method = GET, consumes = APPLICATION_JSON_VALUE, produces = APPLICATION_JSON_VALUE)
    ResponseEntity<Page<TaskDto>> getAllTasksByAuthorIdPaginated(
            @Parameter(name = "Author id", description = "Author id, minimal long value = 1")
            @RequestParam @Min(1) long authorId,
            @RequestBody @Valid Pageable pageable);

    @Operation(summary = "Get all tasks of executor")
    @ApiResponses(value = {@ApiResponse(responseCode = "200", description = "List of tasks got")})
    @RequestMapping(method = GET, consumes = APPLICATION_JSON_VALUE, produces = APPLICATION_JSON_VALUE)
    ResponseEntity<List<TaskDto>> getAllTasksByExecutorId(
            @Parameter(name = "Executor id", description = "Executor id, minimal long value = 1")
            @RequestParam @Min(1) long executorId);

    @Operation(summary = "Get page of executor tasks")
    @ApiResponses(value = {@ApiResponse(responseCode = "200", description = "Page of tasks got")})
    @RequestMapping(method = GET, consumes = APPLICATION_JSON_VALUE, produces = APPLICATION_JSON_VALUE)
    ResponseEntity<Page<TaskDto>> getAllTasksByExecutorIdPaginated(
            @Parameter(name = "Executor id", description = "Executor id, minimal long value = 1")
            @RequestParam @Min(1) long executorId,
            @RequestBody @Valid Pageable pageable);

    @Operation(summary = "Set task executor")
    @ApiResponses(value = {@ApiResponse(responseCode = "200", description = "Executor was set")})
    @RequestMapping(method = POST, consumes = APPLICATION_JSON_VALUE, produces = APPLICATION_JSON_VALUE)
    ResponseEntity<TaskDto> setTaskExecutor(
            @Parameter(name = "Task id", description = "Task id, minimal long value = 1")
            @RequestParam @Min(1) long taskId,
            @Parameter(name = "Executor id", description = "Executor id, minimal long value = 1")
            @RequestParam @Min(1) long executorId);

    @Operation(summary = "Set task status")
    @ApiResponses(value = {@ApiResponse(responseCode = "200", description = "Status was set")})
    @RequestMapping(method = POST, consumes = APPLICATION_JSON_VALUE, produces = APPLICATION_JSON_VALUE)
    ResponseEntity<TaskDto> changeTaskStatus(
            @Parameter(name = "Task status", description = "Status: NEW, IN_PROGRESS, WAITING, DONE")
            @RequestParam Status newStatus,
            @Parameter(name = "Task id", description = "Task id, minimal long value = 1")
            @RequestParam @Min(1) long taskId);

    @Operation(summary = "Set task priority")
    @ApiResponses(value = {@ApiResponse(responseCode = "200", description = "Priority was set")})
    @RequestMapping(method = POST, consumes = APPLICATION_JSON_VALUE, produces = APPLICATION_JSON_VALUE)
    ResponseEntity<TaskDto> changeTaskPriority(
            @Parameter(name = "Task Priority", description = "Priority: HIGH, MIDDLE, LOW")
            @RequestParam Priority newPriority,
            @Parameter(name = "Task id", description = "Task id, minimal long value = 1")
            @RequestParam @Min(1) long taskId);

    @Operation(summary = "Update task")
    @ApiResponses(value = {@ApiResponse(responseCode = "201", description = "Task updated")})
    @RequestMapping(method = POST, consumes = APPLICATION_JSON_VALUE)
    ResponseEntity<TaskDto> updateTask(
            @RequestBody @Valid TaskDto updatedTask);

    @Operation(summary = "Delete task by id")
    @ApiResponses(value = {@ApiResponse(responseCode = "200", description = "Task deleted")})
    @RequestMapping(method = DELETE, consumes = APPLICATION_JSON_VALUE)
    ResponseEntity<HttpStatus> deleteTask(
            @Parameter(name = "Task id", description = "Task id, minimal long value = 1")
            @RequestParam @Min(1) long taskId);
}