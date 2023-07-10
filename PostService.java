package com.hus23.assignment.socialmediaplatform.service;

import com.hus23.assignment.socialmediaplatform.model.Post;
import com.hus23.assignment.socialmediaplatform.model.User;
import com.hus23.assignment.socialmediaplatform.repository.PostRepository;
import com.hus23.assignment.socialmediaplatform.repository.UserRepository;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;

@Service
@NoArgsConstructor
@Slf4j
@Transactional
public class PostService {
    @Autowired
    PostRepository postRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    UserService userService;

    public String savePost(Post post,String username){
        User user = userService.findUserByUserName(username);
        if(user!=null)
        {
            post.setUser(user);
            postRepository.save(post);
            return "Post Added.";
        }
        return "Invalid Username.";
    }

    public List<Post> findAllPostByUserUsername(Long id) {
        return postRepository.findAllPostByUserUsername(id);
    }



    public String updatePost(Post post, Long id) {
        Post dbPost = postRepository.findById(id);
        if(dbPost==null)
        {
            return "Cannot find this Post.";
        }
        post.setUser(dbPost.getUser());
        post.setId(id);
        postRepository.save(post);
        return "Post Updated.";
    }

    public String deletePost(Long id){
        Post dbPost = postRepository.findById(id);
        if(dbPost == null){
            return "Post does not exist.";
        }
        postRepository.deleteById(id);
        return "Post Deleted.";
    }

    public String deleteAllPost(String username){
        User dbUser= userRepository.findByUsername(username);
        if(dbUser == null){
            return "Username does not exist.";
        }
        List<Post> posts = postRepository.findAll();
        posts.forEach(post -> {
            if(post.getUser().getUsername().equals(username))
            {
                postRepository.deleteById(post.getId());
            }
        });
        return "All Posts Deleted.";
    }

    public List<Post> findAllPostByLocation(String location){
        List<Post> allPosts = postRepository.findAll();
        List<Post> locationPosts = new ArrayList<>();
        allPosts.forEach(post -> {
            if(post.getLocation().equals(location)){
                locationPosts.add(post);
            }
        });
        return locationPosts;
    }
}
