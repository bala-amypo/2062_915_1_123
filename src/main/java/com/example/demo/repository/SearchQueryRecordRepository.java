package com.example.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.example.demo.model.SearchQueryRecord;

public interface SearchQueryRecordRepository extends JpaRepository<SearchQueryRecord, Long> {
    SearchQueryRecord findBySearchId(Long searchId);
}
