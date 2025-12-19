package com.example.demo.service.impl;

import com.example.demo.model.*;
import com.example.demo.repository.*;
import com.example.demo.service.SearchQueryService;
import org.springframework.stereotype.Service;
import java.util.*;

@Service
public class SearchQueryServiceImpl implements SearchQueryService {

    private final SearchQueryRecordRepository repo;
    private final EmployeeSkillRepository esRepo;

    public SearchQueryServiceImpl(SearchQueryRecordRepository repo,
                                  EmployeeSkillRepository esRepo) {
        this.repo = repo;
        this.esRepo = esRepo;
    }

    public List<Employee> searchEmployeesBySkills(List<String> skills, Long userId) {
        if (skills == null || skills.isEmpty())
            throw new IllegalArgumentException("must not be empty");

        List<String> normalized = skills.stream()
                .map(s -> s.trim().toLowerCase())
                .distinct()
                .toList();

        List<Employee> result =
                esRepo.findEmployeesByAllSkillNames(normalized, userId + 1);

        SearchQueryRecord r = new SearchQueryRecord();
        r.setSearcherId(userId);
        r.setSkillsRequested(String.join(",", normalized));
        r.setResultsCount(result.size());
        repo.save(r);

        return result;
    }

    public SearchQueryRecord getQueryById(Long id) {
        return repo.findById(id).orElseThrow();
    }

    public List<SearchQueryRecord> getQueriesForUser(Long userId) {
        return repo.findBySearcherId(userId);
    }

    public void saveQuery(SearchQueryRecord record) {
        repo.save(record);
    }
}
