package effectiveMobile.com.taskManagementSystem.dto;

import effectiveMobile.com.taskManagementSystem.domain.enums.Priority;
import effectiveMobile.com.taskManagementSystem.domain.enums.Status;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@NoArgsConstructor
@Data
public class TaskDto {

    private Long id;

    @NotBlank(message = "Title can't be null or blank")
    private String title;

    @NotBlank(message = "Description can't be null or blank")
    private String description;

    @NotBlank(message = "Status can't be null or blank")
    private Status status;

    @NotBlank(message = "Priority can't be null or blank")
    private Priority priority;

    @Size(min =  1, message = "AuthorId can't be less then 1L")
    private Long author;

    @Size(min =  1, message = "ExecutorId can't be less then 1L")
    private Long executor;

    private List<Long> comments;
}