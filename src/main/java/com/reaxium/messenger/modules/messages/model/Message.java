package com.reaxium.messenger.modules.messages.model;

import com.reaxium.messenger.infrastructure.model.Auditable;
import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;


@Entity
@Getter
@Setter
@Builder
@Table(name = "messages")
public class Message extends Auditable<String> {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "title", nullable = false)
    private String title;

    @Column(name = "message", nullable = false)
    private String message;

    @Column(name = "data", nullable = false)
    private String data;

    @Column(name = "topic", nullable = false)
    private String topic;

    @Column(name = "device_token", nullable = false)
    private String deviceToken;

    @Column(name = "status", nullable = false)
    private String status;

}
