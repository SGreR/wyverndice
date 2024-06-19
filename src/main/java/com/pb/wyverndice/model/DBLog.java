package com.pb.wyverndice.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@Entity
@NoArgsConstructor
public class DBLog {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Long id;
    private String entityName;
    private Long entityId;
    private String operation;
    private String message;
    private LocalDateTime date;

    public DBLog(String entityName, Long entityId, String operation) {
        this.entityName = entityName;
        this.entityId = entityId;
        this.operation = operation;
        this.date = LocalDateTime.now();
        this.message = "Entity " + entityName + " of ID: " + entityId + " " + operation + " at " + this.date;
    }

    @Override
    public String toString() {
        return message;
    }
}
