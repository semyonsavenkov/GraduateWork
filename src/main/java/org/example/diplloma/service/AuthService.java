package org.example.diplloma.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.example.diplloma.authorization.TokenProvider;
import org.example.diplloma.dto.AuthorizationRequest;
import org.example.diplloma.repository.AuthRepository;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class AuthService {

    private AuthRepository authorizationRepository;
    private AuthenticationManager authenticationManager;
    private TokenProvider tokenProvider;
    private UserService userService;



    public String login(AuthorizationRequest authorizationRequest) {
        final String username = authorizationRequest.getLogin();
        final String password = authorizationRequest.getPassword();
        Authentication authentication = authenticationManager.authenticate
                (new UsernamePasswordAuthenticationToken(username, password));
        SecurityContextHolder.getContext().setAuthentication(authentication);
        UserDetails userDetails = userService.loadUserByUsername(username);
        String token = tokenProvider.generateToken(userDetails);
        authorizationRepository.addTokenAndUsername(token, username);
        log.info("User {} is authorized", username);
        return token;
    }

    public void logout(String authToken) {
        if (authToken.startsWith("Bearer ")) {
            authToken = authToken.substring(7);
        }
        final String username = authorizationRepository.getUserNameByToken(authToken);
        log.info("User {} logout", username);
        authorizationRepository.removeTokenAndUsernameByToken(authToken);

    }

}
