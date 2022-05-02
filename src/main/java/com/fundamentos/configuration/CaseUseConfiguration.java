package com.fundamentos.configuration;

import com.fundamentos.caseuse.GetUser;
import com.fundamentos.caseuse.GetUserImplement;
import com.fundamentos.service.UserService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class CaseUseConfiguration {

    @Bean
    GetUser getUser(UserService userService) {
        return new GetUserImplement(userService);
    }
}
