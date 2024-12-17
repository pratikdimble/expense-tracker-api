package com.cd.expensetrackerapi.service;


import com.cd.expensetrackerapi.model.CategoryMaster;
import com.cd.expensetrackerapi.model.UserMaster;

import java.util.List;

public interface IUserService {
    List<UserMaster> getUsers();

    Long checkAuthentication(UserMaster user);

    UserMaster createUser(UserMaster user);
}
