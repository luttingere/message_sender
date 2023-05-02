package com.reaxium.messenger.modules.push.queue;

import com.reaxium.messenger.modules.push.service.PushService;
import com.reaxium.messenger.modules.push.dto.PushMessageDTO;
import com.reaxium.messenger.util.JsonUtil;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Component
@Slf4j
@AllArgsConstructor
public class PushQueueReceiver {

    private final PushService pushService;

    public void receiveMessage(String message) {
        try {
            PushMessageDTO pushMessageDTO = JsonUtil.stringToObject(message, PushMessageDTO.class);
            log.info("Push Message Received: " + pushMessageDTO.toString());
            pushService.sendPushNotification(pushMessageDTO);
        } catch (Exception e) {
            log.error("Error sending push notification", e);
        }
    }

}
