package effectiveMobile.com.taskManagementSystem.controllers.rest.impl;

import effectiveMobile.com.taskManagementSystem.controllers.rest.AuthController;
import effectiveMobile.com.taskManagementSystem.controllers.rest.GenericRestController;
import effectiveMobile.com.taskManagementSystem.dto.JwtAuthenticationResponse;
import effectiveMobile.com.taskManagementSystem.dto.SignInRequest;
import effectiveMobile.com.taskManagementSystem.dto.SignUpRequestDto;
import effectiveMobile.com.taskManagementSystem.services.AuthenticationService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.RequestBody;

/**
 * Authentication endpoint  implementation
 */
@Slf4j
@AllArgsConstructor
public class AuthControllerImpl extends GenericRestController implements AuthController {

    private final AuthenticationService authenticationService;

    @Override
    public JwtAuthenticationResponse signUp(@RequestBody @Valid SignUpRequestDto request) {

        log.info("SignUp request: {}", request);
        return authenticationService.signUp(request);
    }

    @Override
    public JwtAuthenticationResponse signIn(@RequestBody @Valid SignInRequest request) {

        log.info("SignIn request: {}", request);
        return authenticationService.signIn(request);
    }
}