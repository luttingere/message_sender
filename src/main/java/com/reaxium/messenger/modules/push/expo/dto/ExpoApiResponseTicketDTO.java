package com.reaxium.messenger.modules.push.expo.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ExpoApiResponseTicketDTO {

    @JsonProperty("id")
    private String ticketId;
    private String status;

}
