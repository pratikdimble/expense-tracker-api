package com.cd.expensetrackerapi.service;



import com.cd.expensetrackerapi.model.CategoryMaster;
import com.cd.expensetrackerapi.model.UserMaster;
import com.cd.expensetrackerapi.repository.CategoryRepository;
import com.cd.expensetrackerapi.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserServiceImpl implements IUserService {


    @Autowired
    UserRepository userRepository;


    @Override
    public List<UserMaster> getUsers() {
        return userRepository.findAll();
    }

    @Override
    public Long checkAuthentication(UserMaster user) {
        Optional<UserMaster> userExist = userRepository.findByUsernameAndPassword(user.getUsername(), user.getPassword());
        if(userExist.isPresent()) {
            UserMaster userm =  userRepository.getUserByUsernameAndPassword(user.getUsername(), user.getPassword());
            return userm.getUserId();
        }else {
            return 0l;
        }
    }

    @Override
    public UserMaster createUser(UserMaster user) {
        Optional<UserMaster> userExist = userRepository.findByUsernameEqualsIgnoreCase(user.getUsername().toLowerCase());
        if (userExist.isPresent()) {
            System.out.println("User already exists");
        } else
            userRepository.save(user);
        return user;
    }

}
