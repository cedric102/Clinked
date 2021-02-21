package com.linked.clinked.models;

import com.linked.clinked.models.data.Admin;

import org.springframework.data.jpa.repository.JpaRepository;

public interface AdminRepository extends JpaRepository<Admin, Integer> {

    // SELECT * FROM Admin WHERE Username = username
    Admin findByUsername(String username);

}
