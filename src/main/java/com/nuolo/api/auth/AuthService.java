package com.nuolo.api.auth;

import com.nuolo.api.exception.EmailAlreadyExistsException;
import com.nuolo.api.security.JwtService;
import com.nuolo.api.user.User;
import com.nuolo.api.user.UserRepository;
import com.nuolo.api.user.UserResponse;
import jakarta.inject.Inject;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AuthService {

    @Inject
    UserRepository userRepository;
    @Inject
    PasswordEncoder passwordEncoder;
    @Inject
    JwtService jwtService;
    @Inject
    AuthenticationManager authenticationManager;

    public AuthResponse signup(SignupRequest request) {
        if (userRepository.existsByEmail(request.getEmail())) {
            throw new EmailAlreadyExistsException(request.getEmail());
        }

        User user = User.create(
                java.util.UUID.randomUUID().toString(),
                request.getEmail(),
                request.getName(),
                passwordEncoder.encode(request.getPassword())
        );

        user = userRepository.save(user);

        String token = jwtService.generateToken(user.getEmail());
        return AuthResponse.create(token, toUserResponse(user));
    }

    public AuthResponse login(LoginRequest request) {
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(request.getEmail(), request.getPassword()));

        User user = userRepository.findByEmail(request.getEmail())
                .orElseThrow();

        String token = jwtService.generateToken(user.getEmail());
        return AuthResponse.create(token, toUserResponse(user));
    }

    public UserResponse getCurrentUser(String email) {
        User user = userRepository.findByEmail(email).orElseThrow();
        return toUserResponse(user);
    }

    private UserResponse toUserResponse(User user) {
        return UserResponse.create(user.getUserId(), user.getEmail(), user.getName(), user.getCreatedAt());
    }
}
