package org.example.diplloma.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serial;
import java.io.Serializable;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AuthorizationRequest implements Serializable {


    @Serial
    private static final long serialVersionUID = -1854276188080282661L;


    private String login;
    private String password;
}
