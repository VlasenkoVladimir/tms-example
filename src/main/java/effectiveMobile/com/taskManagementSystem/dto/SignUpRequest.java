package effectiveMobile.com.taskManagementSystem.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;
/**
 * Sign up request DTO
 */
@Data
@Schema(description = "Registration request")
public class SignUpRequest {

    @Schema(description = "Username", example = "Jonny")
    @Size(min = 5, max = 50, message = "min 5 to max 50 chars")
    @NotBlank(message = "Username can't be null or blank")
    private String username;

    @Schema(description = "Email address", example = "jondoe@gmail.com")
    @Size(min = 5, max = 255, message = "min 5 to max 50 chars")
    @NotBlank(message = "Email can't be null or blank")
    @Email(message = "Email format will be user@example.com")
    private String email;

    @Schema(description = "Password", example = "my_1secret1_password")
    @Size(max = 255, message = "Max 255 chars")
    private String password;
}