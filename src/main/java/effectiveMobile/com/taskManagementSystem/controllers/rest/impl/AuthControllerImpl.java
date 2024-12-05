package effectiveMobile.com.taskManagementSystem.controllers.rest.impl;

import effectiveMobile.com.taskManagementSystem.controllers.rest.AuthController;
import effectiveMobile.com.taskManagementSystem.dto.JwtAuthenticationResponse;
import effectiveMobile.com.taskManagementSystem.dto.SignInRequest;
import effectiveMobile.com.taskManagementSystem.dto.SignUpRequest;
import effectiveMobile.com.taskManagementSystem.services.AuthenticationService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

/**
 * Authentication endpoint  implementation
 */
@Slf4j
@RestController
@Validated
@AllArgsConstructor
public class AuthControllerImpl implements AuthController {

    private final AuthenticationService authenticationService;

    @Override
    public JwtAuthenticationResponse signUp(@RequestBody @Valid SignUpRequest request) {

        log.info("SignUp request: {}", request);
        return authenticationService.signUp(request);
    }

    @Override
    public JwtAuthenticationResponse signIn(@RequestBody @Valid SignInRequest request) {

        log.info("SignIn request: {}", request);
        return authenticationService.signIn(request);
    }
}