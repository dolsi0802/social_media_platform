package com.hus23.assignment.socialmediaplatform.service;

import com.hus23.assignment.socialmediaplatform.model.FollowerFollowing;
import com.hus23.assignment.socialmediaplatform.model.User;
import com.hus23.assignment.socialmediaplatform.repository.UserRepository;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;

@Service
@NoArgsConstructor
@Slf4j
@Transactional
public class UserService {

    @Autowired
    private UserRepository userRepository;


    public String saveUser(User user) {
        if(userRepository.findByUsername(user.getUsername())!=null)
        {
            return "Username already exists.";
        }
        userRepository.save(user);
        return "User created.";
    }
    public User findUserByUserName(String userName) {

        return userRepository.findByUsername(userName);
    }

    public FollowerFollowing getFollowersByUsername(String userName) {
        User user= userRepository.findByUsername(userName);
        FollowerFollowing followerFollowing= FollowerFollowing.builder().build();
        followerFollowing.setFollowers(user.getFollowers());
        followerFollowing.setUsername(userName);
        followerFollowing.setFollowing(user.getFollowing());
        return followerFollowing;
    }

    public List<User> findAllUserByUserName(String userName) {
        List<User> allUsers= userRepository.findAll();
        List<User> requiredUsers = new ArrayList<>();
        allUsers.forEach(user -> {
            if(user.getUsername().contains(userName)){
                requiredUsers.add(user);
            }
        });
        return requiredUsers;
    }

    public List<User> findAllUserByName(String name) {
        List<User> allUsers= userRepository.findAll();
        List<User> requiredUsers = new ArrayList<>();
        allUsers.forEach(user -> {
            if(user.getFirstName().contains(name)){
                requiredUsers.add(user);
            }
        });
        return requiredUsers;
    }

    public List<User> findUserByName(String name) {
        List<User> allUsers= userRepository.findAll();
        List<User> requiredUsers = new ArrayList<>();
        allUsers.forEach(user -> {
            if(user.getFirstName().equals(name)){
                requiredUsers.add(user);
            }
        });
        return requiredUsers;
    }

    public List<User> findUserByLastName(String lastname) {
        List<User> allUsers= userRepository.findAll();
        List<User> requiredUsers = new ArrayList<>();
        allUsers.forEach(user -> {
            if(user.getLastName().equals(lastname)){
                requiredUsers.add(user);
            }
        });
        return requiredUsers;
    }

    public String deleteUser(String userName){
        User dbUser = userRepository.findByUsername(userName);
        if(dbUser==null){
            return "Invalid Username.";
        }
        userRepository.deleteByUsername(userName);
        return "User Deleted.";
    }

    public String updateUser(User user,String userName) {
        User dbUser = userRepository.findByUsername(userName);
        if(dbUser==null)
        {
            return "Cannot find User.";
        }
        user.setId(dbUser.getId());
        userRepository.save(user);
        return "User Updated.";
    }

    public String followUser(String currentUser, String toFollow){
        User follower = userRepository.findByUsername(currentUser);
        User followed = userRepository.findByUsername(toFollow);
        if(follower.equals(null) || followed.equals(null))
            return "Invalid Username.";

        List<String> following = follower.getFollowing();
        following.add(toFollow);
        follower.setFollowing(following);
        updateUser(follower,currentUser);

        List<String> followers = followed.getFollowers();
        followers.add(currentUser);
        followed.setFollowers(followers);
        updateUser(followed,toFollow);

        return "Congratulation now you follow " + toFollow;
    }

    public String unfollowUser(String currentUser, String unFollow){
        User unfollower = userRepository.findByUsername(currentUser);
        User unfollowed = userRepository.findByUsername(unFollow);
        if(unfollower.equals(null) || unfollowed.equals(null))
            return "Invalid Username.";

        List<String> following = unfollower.getFollowing();
        following.remove(unFollow);
        unfollower.setFollowing(following);
        updateUser(unfollower,currentUser);

        List<String> followers = unfollowed.getFollowers();
        followers.remove(currentUser);
        unfollowed.setFollowers(followers);
        updateUser(unfollowed,unFollow);

        return "User Unfollowed.";
    }
}
