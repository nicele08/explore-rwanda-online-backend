package com.rwanda.online.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.rwanda.online.model.User;

@Repository
public interface UserRepository extends JpaRepository<User, Long>{

}
