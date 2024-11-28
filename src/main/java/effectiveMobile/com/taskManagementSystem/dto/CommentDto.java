package effectiveMobile.com.taskManagementSystem.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;

@AllArgsConstructor
@Data
@Schema(description = "Comment")
public class CommentDto {

	private Long id;

	@Schema(description = "Comment body can not be Null or Blank", example = "Example of comment")
	@NotBlank(message = "Description can't be null or blank")
	private String description;

	@Schema(description = "Task id, long value", example = "123L")
	@Size(min =  1, message = "TaskId can't be less then 1L")
	private Long task;
}