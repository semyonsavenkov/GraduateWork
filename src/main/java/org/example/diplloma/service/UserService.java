package org.example.diplloma.service;

import lombok.AllArgsConstructor;
import org.example.diplloma.entity.User;
import org.example.diplloma.repository.UserRepository;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@AllArgsConstructor
public class UserService implements UserDetailsService {

    private final UserRepository repository;

    @Override
    public UserDetails loadUserByUsername(String name) throws UsernameNotFoundException {

        List<GrantedAuthority> authorityList = new ArrayList<>();

        User user = repository.findByLogin(name).orElseThrow(() -> new UsernameNotFoundException("User is not found"));

        return org.springframework.security.core.userdetails.User.builder()
                .username(user.getLogin())
                .password(user.getPassword())
                .authorities(authorityList)
                .build();
    }
}
