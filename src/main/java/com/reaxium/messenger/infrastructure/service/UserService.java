package com.reaxium.messenger.infrastructure.service;

import com.reaxium.messenger.infrastructure.model.User;
import com.reaxium.messenger.infrastructure.repository.UserRepository;
import com.reaxium.messenger.infrastructure.security.PasswordEncoderImpl;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Service
@Slf4j
@AllArgsConstructor
public class UserService {

    private final UserRepository userRepository;
    private final PasswordEncoderImpl passwordEncoder;

    public void createFirstAdminUser() {
        boolean exist = userRepository.findByLogin("admin").isPresent();
        if (exist) {
            log.debug("First admin user already exist");
            return;
        }
        log.debug("Creating first admin user");
        userRepository.save(User.builder()
                        .name("administrator")
                        .login("admin")
                        .system("schwarz")
                        .passwordHash(passwordEncoder.encode("admin"))
                        .environment("ALL")
                        .activated(true)
                .build());
    }


}
