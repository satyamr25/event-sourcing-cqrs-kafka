package com.example.audit.repository;

import com.example.audit.model.EventAudit;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EventAuditRepository extends JpaRepository<EventAudit, String> {
}
