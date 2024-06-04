package org.example.diplloma.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Entity
@Builder
@Table(name = "users")
@AllArgsConstructor
@NoArgsConstructor
public class User {

    @Id
    @Column(nullable = false, unique = true)
    String login;

    @Column(nullable = false)
    String password;

    @OneToMany(cascade = CascadeType.ALL)
    List<File> userFiles;
}
