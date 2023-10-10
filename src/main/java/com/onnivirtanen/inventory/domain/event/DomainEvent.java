package com.onnivirtanen.inventory.domain.event;

import com.onnivirtanen.inventory.domain.model.aggregate.Aggregate;
import lombok.Getter;

import java.time.LocalDateTime;
import java.util.UUID;

@Getter
public abstract class DomainEvent<T extends Aggregate> {

    private final UUID eventId;
    private final UUID domainId;
    private final EventType eventType;
    private final T subject;
    private final String message;
    private final LocalDateTime timestamp;

    protected DomainEvent(EventType eventType, T subject, String message) {
        this.domainId = subject.getId();
        this.eventId = UUID.randomUUID();
        this.eventType = eventType;
        this.subject = subject;
        this.message = message;
        this.timestamp = LocalDateTime.now();
    }

    public enum EventType {
        CREATION,
        UPDATE,
        DELETION,
        STATE_CHANGE,
        VALIDATION,
        NOTIFICATION
    }

    @Override
    public String toString() {
        return "DomainEvent{" +
                "eventId=" + eventId +
                ", domainId=" + domainId +
                ", eventType=" + eventType +
                ", subject=" + subject +
                ", message='" + message + '\'' +
                ", timestamp=" + timestamp +
                '}';
    }
}
