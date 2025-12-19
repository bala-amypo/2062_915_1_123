package com.example.demo.model;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
public class SearchQueryRecord {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long searcherId;
    private String skillsRequested;
    private Integer resultsCount = 0;
    private LocalDateTime searchedAt;

    @PrePersist
    public void onCreate() {
        searchedAt = LocalDateTime.now();
        if (resultsCount == null) resultsCount = 0;
    }

    // getters & setters
}
