package effectiveMobile.com.taskManagementSystem.controllers.rest;


import effectiveMobile.com.taskManagementSystem.dto.CommentDto;
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
import static org.springframework.web.bind.annotation.RequestMethod.GET;
import static org.springframework.web.bind.annotation.RequestMethod.POST;

/**
 * Comment endpoint interface
 */
@RequestMapping(value = "/api/rest/comments")
@Tag(name = "Comments", description = "Endpoint for comments")
public interface CommentController {

    @Operation(summary = "Save comment to DB")
    @ApiResponses(value = {@ApiResponse(responseCode = "201", description = "Comment saved")})
    @RequestMapping(method = POST, consumes = APPLICATION_JSON_VALUE)
    ResponseEntity<HttpStatus> create(
            @RequestBody @Valid CommentDto commentDto);

    @Operation(summary = "Get all comments for task by id")
    @ApiResponses(value = {@ApiResponse(responseCode = "200", description = "Task comments got")})
    @RequestMapping(method = GET, consumes = APPLICATION_JSON_VALUE, produces = APPLICATION_JSON_VALUE)
    ResponseEntity<List<CommentDto>> getAllByTaskId(
            @Parameter(name = "Task id", description = "Task number, minimal long value = 1")
            @RequestParam @Min(1) long taskId);

    @Operation(summary = "Get page of all comments for task by id")
    @ApiResponses(value = {@ApiResponse(responseCode = "200", description = "Page of comments got")})
    @RequestMapping(method = GET, consumes = APPLICATION_JSON_VALUE, produces = APPLICATION_JSON_VALUE)
    ResponseEntity<Page<CommentDto>> getAllByTaskIdPaginated(
            @Parameter(name = "Task id", description = "Task number, minimal long value = 1")
            @RequestParam @Min(1) long taskId,
            @RequestBody @Valid Pageable pageable);
}