package effectiveMobile.com.taskManagementSystem.services;

import effectiveMobile.com.taskManagementSystem.domain.User;
import effectiveMobile.com.taskManagementSystem.repositories.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

/**
 * User service
 */
@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository repository;

    /**
     * Save user
     *
     * @return saved user
     */
    public User save(User user) {

        return repository.save(user);
    }

    /**
     * Create user
     *
     * @return created user
     */
    public User create(User user) {

        if (repository.existsByUsername(user.getUsername())) {
            throw new RuntimeException("Username already existed");
        }

        if (repository.existsByEmail(user.getEmail())) {
            throw new RuntimeException("Email already existed");
        }

        return save(user);
    }

    /**
     * Getting User by username
     *
     * @return user
     */
    public User getByUsername(String username) {

        return repository.findByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException("User not found"));

    }

    /**
     * Getting user by username
     *
     * @return user
     */
    public UserDetailsService userDetailsService() {

        return this::getByUsername;
    }

    /**
     * Getting current user
     *
     * @return current user
     */
    public User getCurrentUser() {

        String username = SecurityContextHolder.getContext().getAuthentication().getName();

        return getByUsername(username);
    }
}