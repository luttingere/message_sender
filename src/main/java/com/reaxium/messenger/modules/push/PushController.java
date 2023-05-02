package com.reaxium.messenger.modules.push;

import com.reaxium.messenger.infrastructure.dto.ServiceResponseDTO;
import com.reaxium.messenger.modules.push.dto.PushMessageDTO;
import com.reaxium.messenger.modules.push.queue.PushQueuePublisher;
import com.reaxium.messenger.modules.push.service.PushService;
import com.reaxium.messenger.util.JsonUtil;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/push")
@AllArgsConstructor
@Slf4j
public class PushController {

    private final PushService pushService;
    private final PushQueuePublisher pushQueuePublisher;




    @PutMapping("/queue")
    public ResponseEntity<ServiceResponseDTO> queuePushNotification(@RequestBody PushMessageDTO pushMessageDTO) {
        log.info("Queueing Push Notification");
        try {
            pushQueuePublisher.queueMessage(JsonUtil.objectToString(pushMessageDTO));
            return ResponseEntity.ok(new ServiceResponseDTO( "00", "Push notification queued"));
        } catch (Exception e) {
            log.error("Error queueing push notification", e);
            return ResponseEntity.badRequest().body(new ServiceResponseDTO("500", "Error queueing push notification"));
        }
    }

    @PostMapping("/send")
    public ResponseEntity<ServiceResponseDTO> sendPushNotification(@RequestBody PushMessageDTO pushMessageDTO) {
        log.info("Sending Push Notification");
        try {
              pushService.sendPushNotification(pushMessageDTO);
            return ResponseEntity.ok(new ServiceResponseDTO( "00", "Push notification sent"));
        } catch (Exception e) {
            log.error("Error sending push notification", e);
            return ResponseEntity.badRequest().body(new ServiceResponseDTO("500", "Error sending push notification"));
        }
    }

}
