package com.cd.expensetrackerapi.controller;


import com.cd.expensetrackerapi.model.UserMaster;
import com.cd.expensetrackerapi.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/login")
@CrossOrigin
public class UserController {

    @Autowired
    IUserService userService;

    @PostMapping
    public ResponseEntity<Long> login(@RequestBody UserMaster userMaster)
    {
        Long result= userService.checkAuthentication(userMaster);
        if(result>0)
            return  ResponseEntity.ok(result);
        else
            throw new BadCredentialsException(" Invalid Username or Password  !!");
    }
    @ExceptionHandler(BadCredentialsException.class)
    public String exceptionHandler() {
        return "Credentials Invalid !!";
    }
}
