package com.reaxium.messenger.infrastructure.model;
import com.reaxium.messenger.infrastructure.listener.AuditableListener;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;

import java.time.LocalDateTime;

@MappedSuperclass
@EntityListeners(AuditableListener.class)
@Getter
@Setter
public abstract class Auditable<U> {

    @CreatedDate
    @Column(name = "created_at", nullable = false)
    protected LocalDateTime createdAt;

    @CreatedBy
    @Column(name = "created_by", nullable = false)
    protected U createdBy;

    @LastModifiedDate
    @Column(name = "last_modified_at", nullable = false)
    protected LocalDateTime lastModifiedAt;

    @LastModifiedBy
    @Column(name = "last_modified_by", nullable = false)
    protected U lastModifiedBy;

}

