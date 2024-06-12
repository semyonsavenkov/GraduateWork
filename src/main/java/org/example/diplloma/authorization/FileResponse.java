package org.example.diplloma.authorization;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class FileResponse {
    @JsonProperty("filename")
    private String fileName;

    @JsonProperty("size")
    private Long size;
}
