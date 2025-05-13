package com.springboot.crud_backend.respository;

import com.springboot.crud_backend.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
}
