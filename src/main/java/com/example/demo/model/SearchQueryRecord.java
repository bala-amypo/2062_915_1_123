package com.example.demo.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.PrePersist;

import java.sql.Timestamp;

@Entity
public class SearchQueryRecord {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long searcherId;          // ✅ REQUIRED
    private String skillsRequested;   // ✅ REQUIRED
    private int resultsCount;         // ✅ REQUIRED
    private Timestamp searchedAt;

    @PrePersist
    public void onCreate() {
        this.searchedAt = new Timestamp(System.currentTimeMillis());
    }

    // -------- Getters & Setters --------

    public Long getId() {
        return id;
    }

    public void setId(Long id) {   // REQUIRED BY TESTS
        this.id = id;
    }

    public Long getSearcherId() {
        return searcherId;
    }

    public void setSearcherId(Long searcherId) {   // ✅ FIX
        this.searcherId = searcherId;
    }

    public String getSkillsRequested() {
        return skillsRequested;
    }

    public void setSkillsRequested(String skillsRequested) {   // ✅ FIX
        this.skillsRequested = skillsRequested;
    }

    public int getResultsCount() {
        return resultsCount;
    }

    public void setResultsCount(int resultsCount) {
        this.resultsCount = resultsCount;
    }

    public Timestamp getSearchedAt() {
        return searchedAt;
    }

    public void setSearchedAt(Timestamp searchedAt) {
        this.searchedAt = searchedAt;
    }
}
