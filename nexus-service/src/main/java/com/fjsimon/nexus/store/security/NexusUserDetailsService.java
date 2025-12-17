package com.fjsimon.nexus.store.security;

import com.fjsimon.nexus.store.domain.User;
import com.fjsimon.nexus.store.repo.UserRepository;
import com.fjsimon.nexus.store.service.JwtService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

import static org.springframework.security.core.userdetails.User.withUsername;

@Component
@RequiredArgsConstructor
public class NexusUserDetailsService implements UserDetailsService {

    private final UserRepository userRepository;
    private final JwtService jwtService;

    @Override
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
        User user = userRepository.findByUsername(s).orElseThrow(() ->
                new UsernameNotFoundException(String.format("User with name %s does not exist", s)));

        return withUsername(user.getUsername())
            .password(user.getPassword())
            .authorities(user.getRoles())
            .accountExpired(false)
            .accountLocked(false)
            .credentialsExpired(false)
            .disabled(false)
            .build();
    }

    /**
     * Extract username and roles from a validated jwt string.
     *
     * @param token String
     * @return UserDetails if valid, Empty otherwise
     */
    public Optional<UserDetails> loadUserByJwtToken(String token) {

        if (!jwtService.isAccessTokenValid(token)) {
            return Optional.empty();
        }

        String username = jwtService.getUsernameFromAccess(token);
        List<GrantedAuthority> authorities = jwtService.getRoles(token);

        UserDetails userDetails = withUsername(username)
                .password("{noop}jwt")
                .authorities(authorities)
                .accountExpired(false)
                .accountLocked(false)
                .credentialsExpired(false)
                .disabled(false)
                .build();

        return Optional.of(userDetails);
    }

    /**
     * Extract the username from the JWT then lookup the user in the database.
     *
     * @param accessToken String
     * @return
     */
    public Optional<UserDetails> loadUserByJwtTokenAndDatabase(String accessToken) {
        if (jwtService.isAccessTokenValid(accessToken)) {
            return Optional.of(loadUserByUsername(jwtService.getUsernameFromAccess(accessToken)));
        } else {
            return Optional.empty();
        }
    }
}