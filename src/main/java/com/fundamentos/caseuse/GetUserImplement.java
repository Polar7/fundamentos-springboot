package com.fundamentos.caseuse;

import com.fundamentos.entity.User;
import com.fundamentos.service.UserService;

import java.util.List;

public class GetUserImplement implements GetUser{

    private UserService userService;


    public GetUserImplement(UserService userService) {
        this.userService = userService;
    }

    @Override
    public List<User> getALl() {
        return userService.getAllUsers();
    }
}
