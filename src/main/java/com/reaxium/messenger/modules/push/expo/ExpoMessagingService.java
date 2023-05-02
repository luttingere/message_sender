package com.reaxium.messenger.modules.push.expo;


import com.fasterxml.jackson.core.JsonProcessingException;
import com.reaxium.messenger.modules.push.dto.PushMessageDTO;
import com.reaxium.messenger.modules.push.expo.dto.ExpoApiResponseDTO;
import com.reaxium.messenger.modules.push.expo.dto.ExpoPushNotificationDTO;
import com.reaxium.messenger.util.JsonUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.BodyInserters;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.List;
import java.util.Map;

@Service
@Slf4j
public class ExpoMessagingService
{
    private static final String EXPO_PUSH_API_URL = "https://exp.host/--/api/v2/push/send";

    private final WebClient webClient;

    public ExpoMessagingService() {
        this.webClient = WebClient.builder().baseUrl(EXPO_PUSH_API_URL).build();
    }

    public Mono<ExpoApiResponseDTO> sendPushNotification(PushMessageDTO request) throws JsonProcessingException {
        ExpoPushNotificationDTO dto = ExpoPushNotificationDTO.builder()
                .to(request.getDeviceToken())
                .title(request.getTitle())
                .body(request.getMessage())
                .data(Map.of("data", request.getData()))
                .build();
        log.info(JsonUtil.objectToString(dto));
        return webClient.post()
                .contentType(MediaType.APPLICATION_JSON)
                .body(BodyInserters.fromValue(List.of(dto)))
                .retrieve()
                .bodyToMono(ExpoApiResponseDTO.class);
    }

}
