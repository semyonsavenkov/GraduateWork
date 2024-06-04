package org.example.diplloma.authorization;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class JwtFileResponse {

    @JsonProperty("filename")
    private String fileName;

    @JsonProperty("size")
    private Long size;

}
