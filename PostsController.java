package com.hus23.assignment.socialmediaplatform.controller;

import com.hus23.assignment.socialmediaplatform.model.Post;
import com.hus23.assignment.socialmediaplatform.model.User;
import com.hus23.assignment.socialmediaplatform.repository.PostRepository;
import com.hus23.assignment.socialmediaplatform.service.PostService;
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
public class PostsController {

    @Autowired
    PostService postsService;

    @Autowired
    UserService userService;

    @Autowired
    PostRepository postRepository;

    @PostMapping("/post/{username}")
    public ResponseEntity<String> createPost(@PathVariable String username, @RequestBody Post post){
        return new ResponseEntity<String>(postsService.savePost(post,username), HttpStatus.valueOf(200));
    }

    @GetMapping("/post/{id}")
    public ResponseEntity<String> getPostById(@PathVariable Long id){
        Post post =  postRepository.findById(id);
        if(post.equals(null))
            return new ResponseEntity<String>("Post does not exist.", HttpStatus.valueOf(204));
        return new ResponseEntity<String>(post.toString(), HttpStatus.valueOf(200));
    }

    @PostMapping("/update/post/{id}")
    public ResponseEntity<String> updatePost(@RequestBody Post post, @PathVariable Long id){
        return new ResponseEntity<String>(postsService.updatePost(post,id), HttpStatus.valueOf(200));
    }

    @DeleteMapping("/post/{id}")
    public ResponseEntity<String> deletePost(@PathVariable Long id){
        return new ResponseEntity<String>(postsService.deletePost(id), HttpStatus.valueOf(200));
    }

    @DeleteMapping("/allpost/{username}")
    public ResponseEntity<String> deletePost(@PathVariable String username){
        return new ResponseEntity<String>(postsService.deleteAllPost(username), HttpStatus.valueOf(200));
    }

    @GetMapping("/post/location/{location}")
    public ResponseEntity<List<Post>> getPostById(@PathVariable String location){
        List<Post> posts = postsService.findAllPostByLocation(location);
        if(posts.isEmpty())
            return new ResponseEntity<>(posts, HttpStatus.valueOf(204));
        return new ResponseEntity<>(posts, HttpStatus.valueOf(200));
    }
}
