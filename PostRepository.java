package com.hus23.assignment.socialmediaplatform.repository;

import com.hus23.assignment.socialmediaplatform.model.Post;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository("postsRepository")
public interface PostRepository extends JpaRepository<Post, String> {
    public List<Post> findAllPostByUserUsername(Long id);

    public void deleteById(Long id);

    public Post findById(Long id);
}
