package com.hus23.assignment.socialmediaplatform.repository;

import com.hus23.assignment.socialmediaplatform.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository("userRepository")
public interface UserRepository extends JpaRepository<User, String> {

    public User findByUsername(String userName);

    public void deleteByUsername(String userName);

    public User findById(Long id);

    public void deleteById(Long id);

}
