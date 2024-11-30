package effectiveMobile.com.taskManagementSystem.dto;

import effectiveMobile.com.taskManagementSystem.domain.enums.Priority;
import effectiveMobile.com.taskManagementSystem.domain.enums.Status;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;

import java.util.List;

/**
 * Task DTO
 */
@Data
@Schema(description = "Task")
public class TaskDto {

    @Schema(description = "Id, long value", example = "123L")
    @Size(min =  1, message = "Id can't be less then 1L")
    private Long id;

    @Schema(description = "Title", example = "Example title of task")
    @NotBlank(message = "Title can't be null or blank")
    private String title;

    @Schema(description = "Description", example = "Example description of task")
    @NotBlank(message = "Description can't be null or blank")
    private String description;

    @Schema(description = "Status", example = "Example status of task")
    @NotBlank(message = "Status can't be null or blank")
    private Status status;

    @Schema(description = "Priority", example = "Example priority of task")
    @NotBlank(message = "Priority can't be null or blank")
    private Priority priority;

    @Schema(description = "AuthorId, long value", example = "123L")
    @Size(min =  1, message = "AuthorId can't be less then 1L")
    private Long author;

    @Schema(description = "ExecutorId, long value", example = "123L")
    @Size(min =  1, message = "ExecutorId can't be less then 1L")
    private Long executor;

    @Schema(description = "List of long, comments id")
    private List<Long> comments;
}