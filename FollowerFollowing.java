package com.hus23.assignment.socialmediaplatform.model;


import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Data
@Getter
@Builder
@Setter
public class FollowerFollowing {

    String username;
    List<String> followers;
    List<String> following;
}
