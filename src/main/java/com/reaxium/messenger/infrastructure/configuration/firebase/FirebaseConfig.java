package com.reaxium.messenger.infrastructure.configuration.firebase;

import com.google.auth.oauth2.GoogleCredentials;
import com.google.firebase.FirebaseApp;
import com.google.firebase.FirebaseOptions;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import java.io.IOException;
import java.util.Objects;

@Configuration
public class FirebaseConfig {
    @Bean
    public FirebaseApp firebaseApp() throws IOException {
        String pathToCredentials = "firebase/firebase-credentials.json";
        FirebaseOptions options = FirebaseOptions.builder()
                .setCredentials(GoogleCredentials
                        .fromStream(Objects.requireNonNull(getClass().getClassLoader()
                        .getResourceAsStream(pathToCredentials))))
                .build();
        FirebaseApp.initializeApp(options);
        return FirebaseApp.getInstance();
    }
}
