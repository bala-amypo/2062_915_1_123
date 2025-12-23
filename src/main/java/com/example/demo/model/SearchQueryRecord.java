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

    private Long searcherId;          
    private String skillsRequested;   
    private int resultsCount;         
    private Timestamp searchedAt;

    @PrePersist
    public void onCreate() {
        this.searchedAt = new Timestamp(System.currentTimeMillis());
    }

  
    public Long getId() {
        return id;
    }

    public void setId(Long id) {   // REQUIRED BY TESTS
        this.id = id;
    }

    public Long getSearcherId() {
        return searcherId;
    }

    public void setSearcherId(Long searcherId) {   
        this.searcherId = searcherId;
    }

    public String getSkillsRequested() {
        return skillsRequested;
    }

    public void setSkillsRequested(String skillsRequested) {   
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
