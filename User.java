package com.hus23.assignment.socialmediaplatform.model;

import javax.persistence.*;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Data
@Table(name="users")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(unique = true)
    private String username;

    //@Column(name = "first_name")
    private String firstName;

    //@Column(name = "last_name")
    private String lastName;

    //@Column(name = "password")
    private String password;

    //@Column(name = "bio")
    private String bio;

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonManagedReference
    private List<Post> posts;

    @ElementCollection(targetClass = String.class)
    private List<String> followers;
    @ElementCollection(targetClass = String.class)
    private List<String> following;

}
