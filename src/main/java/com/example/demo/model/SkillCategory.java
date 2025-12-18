package com.example.demo.model;

import jakarta.persistence.*;

@Entity
public class SkillCategory {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true)
    private String categoryName;

    private String description;
    private Boolean active = true;

    // getters and setters
}
