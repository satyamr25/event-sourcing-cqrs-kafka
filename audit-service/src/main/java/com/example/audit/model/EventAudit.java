package com.example.audit.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Lob;
import lombok.*;

import java.time.Instant;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class EventAudit {

    @Id
    private String eventId;

    private String eventType;

    @Lob
    private String payload;

    private Instant createdAt;
}
