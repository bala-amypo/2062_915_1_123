package com.example.demo.model;

import jakarta.persistence.*;
import java.sql.Timestamp;
import java.time.Instant;

@Entity
public class SearchQueryRecord {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long searchId;
    private String skillsRequested;
    private Integer resultsCount;
    private Timestamp searchedAt;

    @PrePersist
    public void onCreate() {
        searchedAt = Timestamp.from(Instant.now());
    }

    // getters and setters
}
