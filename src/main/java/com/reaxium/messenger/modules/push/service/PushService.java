package com.reaxium.messenger.modules.push.service;

import com.reaxium.messenger.modules.messages.service.MessageService;
import com.reaxium.messenger.modules.push.dto.PushMessageDTO;
import com.reaxium.messenger.modules.push.expo.ExpoMessagingService;
import com.reaxium.messenger.modules.push.firebase.FirebaseMessagingService;
import com.reaxium.messenger.modules.push.model.PushServiceType;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@Slf4j
@AllArgsConstructor
public class PushService {

    private final FirebaseMessagingService firebaseMessagingService;
    private final ExpoMessagingService expoMessagingService;
    private final MessageService messageService;

    public void sendPushNotification(final PushMessageDTO pushMessageDTO) throws Exception {
        String pushServiceResponse;
        if (pushMessageDTO.getPushService().equals(PushServiceType.EXPO)) {
            expoMessagingService.sendPushNotification(pushMessageDTO).subscribe(response -> {
                log.info("Expo Push Notification Response: " + response.toString());
                pushMessageDTO.setStatus(response.toString());
                messageService.createMessage(pushMessageDTO);
            });
        } else {
            pushServiceResponse = firebaseMessagingService.sendPushNotification(pushMessageDTO);
            pushMessageDTO.setStatus(pushServiceResponse);
            messageService.createMessage(pushMessageDTO);
        }
    }

}
