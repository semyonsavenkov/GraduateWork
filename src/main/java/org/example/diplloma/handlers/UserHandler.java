package org.example.diplloma.handlers;

import lombok.AllArgsConstructor;
import org.example.diplloma.entity.User;
import org.example.diplloma.repository.UserRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
public class UserHandler implements CommandLineRunner {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    @Override
    public void run(String... args) {
        userRepository.save(User.builder()
                .login("semyonsavenkov@gmail.com")
                .password(passwordEncoder.encode("111"))
                .build());
        userRepository.save(User.builder()
                .login("ivanivanov@gmail.com")
                .password(passwordEncoder.encode("222"))
                .build());
    }
}
