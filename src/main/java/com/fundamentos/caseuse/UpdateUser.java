package com.fundamentos.caseuse;

import com.fundamentos.entity.User;
import com.fundamentos.service.UserService;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class UpdateUser {

    private UserService userService;

    public UpdateUser(UserService userService) {
        this.userService = userService;
    }

    public Optional<User> update(User newUser, Long id){
        return userService.update(newUser, id);
    }
}
