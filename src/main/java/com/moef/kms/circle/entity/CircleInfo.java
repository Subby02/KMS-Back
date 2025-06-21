package com.moef.kms.circle.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Column;
import jakarta.persistence.OneToMany;
import jakarta.persistence.CascadeType;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import lombok.Data;

@Entity
@Data
public class CircleInfo {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long circleId;

    @Column(nullable = false, length = 100)
    private String name;

    @Column(length = 500)
    private String description;

    @Column(nullable = false, updatable = false, length = 20)
    private String createdBy;

    @Column(nullable = false)
    private LocalDateTime createdAt;

    @OneToMany(mappedBy = "circle", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<CircleMember> members = new ArrayList<>();
}