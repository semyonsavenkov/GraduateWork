package org.example.diplloma.authorization;

import lombok.AllArgsConstructor;
import org.example.diplloma.entity.User;
import org.example.diplloma.repository.UserRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;


@Component
@AllArgsConstructor
public class StartHandler implements CommandLineRunner {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    @Override
    public void run(String... args) {
        userRepository.save(User.builder()
                .login("user1@netology.ru")
                .password(passwordEncoder.encode("123"))
                .build());
        userRepository.save(User.builder()
                .login("user2@netology.ru")
                .password(passwordEncoder.encode("456"))
                .build());
        userRepository.save(User.builder()
                .login("user3@netology.ru")
                .password(passwordEncoder.encode("789"))
                .build());
    }
}
