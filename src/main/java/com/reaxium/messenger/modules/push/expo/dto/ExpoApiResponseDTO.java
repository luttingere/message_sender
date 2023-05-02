package com.reaxium.messenger.modules.push.expo.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

import java.util.List;


@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ExpoApiResponseDTO {

    @JsonProperty("data")
    private List<ExpoApiResponseTicketDTO> data;

}
