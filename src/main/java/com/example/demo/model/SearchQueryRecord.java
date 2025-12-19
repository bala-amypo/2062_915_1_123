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

    private String query;

    private Timestamp searchedAt;

    @PrePersist
    public void onCreate() {
        this.searchedAt = new Timestamp(System.currentTimeMillis());
    }

    // ---------- Getters & Setters ----------

    public Long getId() {
        return id;
    }

    public void setId(Long id) {   // REQUIRED BY TESTS
        this.id = id;
    }

    public String getQuery() {
        return query;
    }

    public void setQuery(String query) {
        this.query = query;
    }

    public Timestamp getSearchedAt() {
        return searchedAt;
    }

    public void setSearchedAt(Timestamp searchedAt) {
        this.searchedAt = searchedAt;
    }
}
