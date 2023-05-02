package com.reaxium.messenger.modules.push.dto;

import com.reaxium.messenger.modules.messages.model.MessageDTO;
import com.reaxium.messenger.modules.push.model.PushServiceType;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Builder
public class PushMessageDTO implements MessageDTO {

    private String service;
    private String title;
    private String message;
    private String data;
    private String deviceToken;
    private String topic;
    private String status;


    public String getDeviceToken() {
        if ("".equals(deviceToken)) {
            return null;
        }
        return deviceToken;
    }


    public PushServiceType getPushService() {
        return PushServiceType.findByName(getService());
    }
}
