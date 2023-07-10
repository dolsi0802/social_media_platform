package com.hus23.assignment.socialmediaplatform.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.*;

import javax.persistence.*;
import java.io.Serializable;
import java.sql.Timestamp;

@Entity
@Table(name="posts")
@Data
@Getter
@Setter
public class Post implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String location;

    private String descriptiveText;

    private Timestamp postDateAndTime;

    @ToString.Exclude
    @ManyToOne
    @JoinColumn(name = "user_id")
    @JsonBackReference
    private User user;
}
