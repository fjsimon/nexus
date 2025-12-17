package com.fjsimon.nexus.store.service;

import com.fjsimon.nexus.store.domain.Role;
import com.fjsimon.nexus.store.domain.User;
import com.fjsimon.nexus.store.exceptions.NotFoundException;
import com.fjsimon.nexus.store.repo.RoleRepository;
import com.fjsimon.nexus.store.repo.UserRepository;

import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserService {

    private static final Logger LOGGER = LoggerFactory.getLogger(UserService.class);

    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final PasswordEncoder passwordEncoder;

    /**
     * Get a user into the application
     *
     * @param username  username
     * @return Optional of User , empty otherwise
     */
    public Optional<User> getUser(String username) {

        LOGGER.info(String.format("getUser : %s", username));

        Optional<User> user = userRepository.findByUsername(username);
        return user;
    }

    /**
     * Create a new user in the database.
     *
     * @param username username
     * @param password password
     * @param firstName first name
     * @param lastName last name
     * @return Optional of user, empty if the user already exists.
     */
    public Optional<User> signup(String username, String password, String firstName, String lastName) {
        LOGGER.info("New user attempting to sign up");

        Optional<User> user = Optional.empty();
        if (!userRepository.findByUsername(username).isPresent()) {
            Optional<Role> optionalRole = roleRepository.findByRoleName("ROLE_ADMIN");
            Role role = optionalRole.orElseThrow(() -> new NotFoundException("Role name ROLE_ADMIN not found"));
            user = Optional.of(userRepository.save(new User(username, passwordEncoder.encode(password), role, firstName, lastName)));
        }
        return user;
    }

    public List<User> getAll() {
        return userRepository.findAll();
    }
}