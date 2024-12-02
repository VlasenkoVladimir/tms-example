package effectiveMobile.com.taskManagementSystem.services;

import effectiveMobile.com.taskManagementSystem.domain.User;
import effectiveMobile.com.taskManagementSystem.domain.enums.Role;
import effectiveMobile.com.taskManagementSystem.dto.JwtAuthenticationResponse;
import effectiveMobile.com.taskManagementSystem.dto.SignInRequest;
import effectiveMobile.com.taskManagementSystem.dto.SignUpRequestDto;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

/**
 * Authentication service
 */
@Service
@RequiredArgsConstructor
public class AuthenticationService {

    private final UserService userService;
    private final JwtService jwtService;
    private final PasswordEncoder passwordEncoder;
    private final AuthenticationManager authenticationManager;

    /**
     * New user registration
     *
     * @param request user data
     * @return token
     */
    public JwtAuthenticationResponse signUp(SignUpRequestDto request) {

        User user = User.builder()
                .username(request.getUsername())
                .email(request.getEmail())
                .password(passwordEncoder.encode(request.getPassword()))
                .role(Role.ROLE_USER)
                .build();

        userService.create(user);

        String jwt = jwtService.generateToken(user);
        return new JwtAuthenticationResponse(jwt);
    }

    /**
     * User authentication
     *
     * @param request user data
     * @return token
     */
    public JwtAuthenticationResponse signIn(SignInRequest request) {
        authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(
                request.getUsername(),
                request.getPassword()
        ));

        UserDetails user = userService
                .userDetailsService()
                .loadUserByUsername(request.getUsername());

        String jwt = jwtService.generateToken(user);
        return new JwtAuthenticationResponse(jwt);
    }
}