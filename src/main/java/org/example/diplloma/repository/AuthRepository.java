package org.example.diplloma.repository;

import org.springframework.stereotype.Repository;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@Repository
public class AuthRepository {

    private final Map<String, String> tokensAndUserNames = new ConcurrentHashMap<>();

    public void addTokenAndUsername(String token, String username) {
        tokensAndUserNames.put(token, username);
    }

    public void removeTokenAndUsernameByToken(String token) {
        tokensAndUserNames.remove(token);
    }

    public String getUserNameByToken(String token) {
        return tokensAndUserNames.get(token);
    }

}