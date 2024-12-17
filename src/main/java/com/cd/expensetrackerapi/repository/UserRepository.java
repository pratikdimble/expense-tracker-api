package com.cd.expensetrackerapi.repository;

import com.cd.expensetrackerapi.model.CategoryMaster;
import com.cd.expensetrackerapi.model.UserMaster;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface UserRepository extends JpaRepository<UserMaster, Long > {

    Optional<UserMaster> findByUsernameEqualsIgnoreCase(String username);
    Optional<UserMaster> findByUsernameAndPassword(String username, String password);
    UserMaster getUserByUsernameAndPassword(String username, String password);
}
