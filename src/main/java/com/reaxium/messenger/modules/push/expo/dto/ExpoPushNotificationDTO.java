package com.reaxium.messenger.modules.push.expo.dto;

import lombok.*;

import java.util.Map;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Builder
public class ExpoPushNotificationDTO {

    private String to;
    private String title;
    private String body;
    private Map<String, String> data;
    private String sound;

}
