package com.reaxium.messenger.modules.push.firebase;

import com.google.firebase.FirebaseApp;
import com.google.firebase.messaging.FirebaseMessaging;
import com.google.firebase.messaging.FirebaseMessagingException;
import com.google.firebase.messaging.Message;
import com.google.firebase.messaging.Notification;
import com.reaxium.messenger.modules.push.dto.PushMessageDTO;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@Slf4j
@AllArgsConstructor
public class FirebaseMessagingService {


    private final FirebaseApp firebaseApp;

    public FirebaseMessaging getFirebaseMessaging() {
        return FirebaseMessaging.getInstance(firebaseApp);
    }

    public String sendPushNotificationToDevice(PushMessageDTO pushMessageDTO) throws FirebaseMessagingException {
        // Get a reference to the Firebase Cloud Messaging service
        FirebaseMessaging messaging = getFirebaseMessaging();

        // Create a notification message
        Notification notification = Notification.builder()
                .setTitle(pushMessageDTO.getTitle())
                .setBody(pushMessageDTO.getMessage())
                .build();

        // Create a message payload
        Message payload = Message.builder()
                .setNotification(notification)
                .putData("data", pushMessageDTO.getData())
                .setToken(pushMessageDTO.getDeviceToken())
                .build();

        // Send the message
        return messaging.send(payload);
    }

    public String sendPushNotification(PushMessageDTO pushMessageDTO) throws FirebaseMessagingException {
        // Get a reference to the Firebase Cloud Messaging service
        FirebaseMessaging messaging = getFirebaseMessaging();

        // Create a notification message
        Notification notification = Notification.builder()
                .setTitle(pushMessageDTO.getTitle())
                .setBody(pushMessageDTO.getMessage())
                .build();

        // Create a message payload
        Message payload = Message.builder()
                .setNotification(notification)
                .setTopic(pushMessageDTO.getTopic())
                .build();

        // Send the message
        return messaging.send(payload);
    }


}
