package com.reaxium.messenger.infrastructure.command;

import com.reaxium.messenger.infrastructure.service.UserService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
@Slf4j
public class FirstAdminUserCreation implements CommandLineRunner {
    
    private final UserService userService;

    @Override
    public void run(String... args) throws Exception {
        log.info("Running startup task: FirstAdminUserCreation");
        userService.createFirstAdminUser();
    }
}
