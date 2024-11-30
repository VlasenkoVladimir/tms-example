package effectiveMobile.com.taskManagementSystem.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Authentication response DTO
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Schema(description = "response with access token")
public class JwtAuthenticationResponse {

    @Schema(description = "Access token", example = "eyJhbGceOiJIUbUxMiJ9.eqJzdVIiOlJhZG5pbiIsImV4cCI7MTYyNjUwNj...")
    private String token;
}