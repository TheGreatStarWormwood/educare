package com.cmps272.educare.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.cmps272.educare.entity.User;

public interface UserRepository extends JpaRepository<User, Long> {

}
