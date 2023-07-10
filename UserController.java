package com.hus23.assignment.socialmediaplatform.controller;


import com.hus23.assignment.socialmediaplatform.model.FollowerFollowing;
import com.hus23.assignment.socialmediaplatform.model.User;
import com.hus23.assignment.socialmediaplatform.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.transaction.Transactional;
import java.util.List;

@RestController
@Transactional
@RequestMapping("/")
public class UserController {


    @Autowired
    UserService userService;

    @PostMapping("/user")
    public ResponseEntity<String> createUser(@RequestBody User user){
        return new ResponseEntity<String>(userService.saveUser(user), HttpStatus.valueOf(200));
    }

    @GetMapping("/user/{userName}")
    public ResponseEntity<String> getUserByUsername(@PathVariable String userName){
        User user = userService.findUserByUserName(userName);
        return new ResponseEntity<String>(user.toString(), HttpStatus.valueOf(200));
    }

    @GetMapping("/user/followers/{userName}")
    public ResponseEntity<FollowerFollowing> getFollowersByUsername(@PathVariable String userName){
       FollowerFollowing followerFollowings = userService.getFollowersByUsername(userName);
        return new ResponseEntity<FollowerFollowing>(followerFollowings, HttpStatus.valueOf(200));
    }

    @GetMapping("/user/username={userName}")
    public ResponseEntity<List<User>> getAllUserByUsername(@PathVariable String userName){
        List<User> user = userService.findAllUserByUserName(userName);
        if(user.isEmpty())
        {
            return new ResponseEntity<>(user, HttpStatus.valueOf(204));
        }
        return new ResponseEntity<>(user, HttpStatus.valueOf(200));
    }

    @GetMapping("/user/all/name={name}")
    public ResponseEntity<List<User>> getAllUserByName(@PathVariable String name){
        List<User> user = userService.findAllUserByName(name);
        if(user.isEmpty())
        {
            return new ResponseEntity<>(user, HttpStatus.valueOf(204));
        }
        return new ResponseEntity<>(user, HttpStatus.valueOf(200));
    }

    @GetMapping("/user/name={name}")
    public ResponseEntity<List<User>> getUserByName(@PathVariable String name){
        List<User> user = userService.findUserByName(name);
        if(user.isEmpty())
        {
            return new ResponseEntity<>(user, HttpStatus.valueOf(204));
        }
        return new ResponseEntity<>(user, HttpStatus.valueOf(200));
    }

    @GetMapping("/user/lastname={lastname}")
    public ResponseEntity<List<User>> getUserByLastName(@PathVariable String lastname){
        List<User> user = userService.findUserByLastName(lastname);
        if(user.isEmpty())
        {
            return new ResponseEntity<>(user, HttpStatus.valueOf(204));
        }
        return new ResponseEntity<>(user, HttpStatus.valueOf(200));
    }

    @DeleteMapping("/user/{userName}")
    public ResponseEntity<String> deleteUser(@PathVariable String userName){
        return new ResponseEntity<String>(userService.deleteUser(userName), HttpStatus.valueOf(200));
    }

    @PostMapping("/update/user/{userName}")
    public ResponseEntity<String> updateUser(@RequestBody User user,@PathVariable String userName){
        return new ResponseEntity<String>(userService.updateUser(user,userName), HttpStatus.valueOf(200));
    }

    @PostMapping("user/{currentUser}/follow={toFollow}")
    public ResponseEntity<String> followUser(@PathVariable String currentUser,@PathVariable String toFollow){
        return new ResponseEntity<String>(userService.followUser(currentUser,toFollow), HttpStatus.valueOf(200));
    }

    @PostMapping("user/{currentUser}/unfollow={unFollow}")
    public ResponseEntity<String> unfollowUser(@PathVariable String currentUser,@PathVariable String unFollow){
        return new ResponseEntity<String>(userService.unfollowUser(currentUser,unFollow), HttpStatus.valueOf(200));
    }
}
