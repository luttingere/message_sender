package com.reaxium.messenger.infrastructure.listener;

import com.reaxium.messenger.infrastructure.model.Auditable;
import jakarta.persistence.PrePersist;
import jakarta.persistence.PreUpdate;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Optional;


@Service
public class AuditableListener<U> {

    @PrePersist
    public void onPrePersist(Auditable<U> auditable) {
        Optional<U> createdBy = getCurrentAuditor();
        createdBy.ifPresent(auditable::setCreatedBy);
        auditable.setCreatedAt(LocalDateTime.now());
        auditable.setLastModifiedAt(LocalDateTime.now());
        auditable.setLastModifiedBy(createdBy.orElse(null));
    }

    @PreUpdate
    public void onPreUpdate(Auditable<U> auditable) {
        Optional<U> lastModifiedBy = getCurrentAuditor();
        lastModifiedBy.ifPresent(auditable::setLastModifiedBy);
        auditable.setLastModifiedAt(LocalDateTime.now());
    }

    private Optional<U> getCurrentAuditor() {
        SecurityContext securityContext = SecurityContextHolder.getContext();
        Authentication authentication = securityContext.getAuthentication();
        if (authentication != null) {
            authentication.getDetails();
            Object principal = authentication.getPrincipal();
            if (principal instanceof UserDetails) {
                String username = ((UserDetails) principal).getUsername();
                return Optional.of((U) username);
            }
        }
        return Optional.of((U) "system");
    }
}

