package com.info.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.info.entity.User;

public interface UserRepository extends JpaRepository<User, String> {

}
