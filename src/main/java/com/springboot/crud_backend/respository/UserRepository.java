package com.springboot.crud_backend.respository;

import com.springboot.crud_backend.model.User;
import jakarta.persistence.NamedStoredProcedureQueries;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.query.Procedure;
import com.springboot.crud_backend.model.User;
import org.springframework.data.repository.query.Param;
import java.util.List;


public interface UserRepository extends JpaRepository<User, Long> {
    @Query(value = "CALL get_age_using_condition(:userAge)", nativeQuery = true)
    List<User> findUsersByAge(@Param("userAge") int age);
}
