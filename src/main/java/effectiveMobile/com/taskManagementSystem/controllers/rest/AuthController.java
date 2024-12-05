package effectiveMobile.com.taskManagementSystem.controllers.rest;

import effectiveMobile.com.taskManagementSystem.dto.JwtAuthenticationResponse;
import effectiveMobile.com.taskManagementSystem.dto.SignInRequest;
import effectiveMobile.com.taskManagementSystem.dto.SignUpRequest;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Authentication endpoint interface
 */
@RequestMapping("/api/rest/auth")
@Tag(name = "Authentication")
public interface AuthController {

    @Operation(summary = "New user registration")
    @PostMapping("/sign-up")
    JwtAuthenticationResponse signUp(@RequestBody @Valid SignUpRequest request);

    @Operation(summary = "User authentication")
    @PostMapping("/sign-in")
    JwtAuthenticationResponse signIn(@RequestBody @Valid SignInRequest request);
}