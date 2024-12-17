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

    @Query("SELECT um FROM UserMaster um WHERE lower(um.username) =:username ")
    Optional<UserMaster> findByUsernameEqualsIgnoreCase(String username);

    @Query("SELECT um FROM UserMaster um WHERE um.username=:username AND um.password=:password ")
    Optional<UserMaster> findByUsernameAndPassword(String username, String password);

    @Query("SELECT um FROM UserMaster um WHERE um.username=:username AND um.password=:password ")
    UserMaster getUserByUsernameAndPassword(String username, String password);
}
