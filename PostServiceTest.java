package com.hus23.assignment.socialmediaplatform.serviceTest;

import com.hus23.assignment.socialmediaplatform.model.Post;
import com.hus23.assignment.socialmediaplatform.model.User;
import com.hus23.assignment.socialmediaplatform.repository.PostRepository;
import com.hus23.assignment.socialmediaplatform.repository.UserRepository;
import com.hus23.assignment.socialmediaplatform.service.PostService;
import com.hus23.assignment.socialmediaplatform.service.UserService;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

@SpringBootTest
public class PostServiceTest {


    @InjectMocks
    private PostService postService;

    @Mock
    private PostRepository postRepository;

    @Mock
    private UserRepository userRepository;

    @Mock
    private UserService userService;


    @Test
    void savePostSuccessTest(){
        User user = getUser();
        when(userService.findUserByUserName("123")).thenReturn(user);
        when(userRepository.findByUsername("123")).thenReturn(user);
        Post post = getPost();
        String expectedAns = postService.savePost(post,"123");
        assertEquals(expectedAns,"Post Added.");
    }

    @Test
    void savePostFailTest(){
        when(userRepository.findByUsername("123")).thenReturn(null);
        Post post = getPost();
        String expectedAns = postService.savePost(post,"123");
        assertEquals(expectedAns,"Invalid Username.");
    }

    @Test
    void updatePostTest(){
        Post post = getPost();
        when(postRepository.findById(123L)).thenReturn(post);
        String expectedAns = postService.updatePost(post,123L);
        assertEquals(expectedAns,"Post Updated.");
    }

    private User getUser(){
        User user = new User();
        user.setUsername("123");
        user.setId(123L);
        return user;
    }

    private Post getPost(){
        Post post = new Post();
        post.setId(123L);
        return post;
    }

}
