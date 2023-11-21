package com.wira.sasangka.standardprojects.entity;

import jakarta.persistence.Id;
import jakarta.persistence.MappedSuperclass;
import jakarta.persistence.PrePersist;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.apache.commons.lang3.RandomStringUtils;

import java.time.Instant;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@MappedSuperclass
public abstract class BaseEntity {
    @Id
    private String id;
    private Instant createdAt;
    private Instant updatedAt;

    @PrePersist
    void generateId() {
        setId(RandomStringUtils.randomAlphanumeric(32));
        setCreatedAt(Instant.now());
        setUpdatedAt(Instant.now());
    }
}
