package com.linked.clinked.models;

import com.linked.clinked.models.data.User;

import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Integer> {

    // SELECT * FROM User WHERE UserName = username
    User findByUsername(String username);

}
