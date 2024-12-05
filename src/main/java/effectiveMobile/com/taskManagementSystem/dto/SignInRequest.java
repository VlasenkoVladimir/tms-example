package effectiveMobile.com.taskManagementSystem.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;

/**
 * Sign in request DTO
 */
@Data
@Schema(description = "Sign in request")
public class SignInRequest {

    @Schema(description = "Username", example = "Jonny")
    @Size(min = 5, max = 50, message = "min 5 to max 50 chars")
    @NotBlank(message = "Username can't be null or blank")
    private String username;

    @Schema(description = "Password", example = "my_1seCRet7_paSsworD")
    @Size(max = 255, message = "Max 255 chars")
    private String password;
}