package com.example.demo.service;

import org.springframework.stereotype.Service;
import java.util.List;
import com.example.demo.repository.*;
import com.example.demo.model.*;

@Service
public class SearchQueryService {

    private final SearchQueryRecordRepository queryRepo;
    private final EmployeeSkillRepository esRepo;

    public SearchQueryService(SearchQueryRecordRepository queryRepo,
                              EmployeeSkillRepository esRepo) {
        this.queryRepo = queryRepo;
        this.esRepo = esRepo;
    }

    public List<Employee> searchEmployeesBySkills(List<String> skills, Long userId) {
        if (skills == null || skills.isEmpty())
            throw new IllegalArgumentException("must not be empty");

        List<Employee> result =
                esRepo.findEmployeesByAllSkillNames(skills, userId);

        SearchQueryRecord r = new SearchQueryRecord();
        r.setSearchId(userId);
        r.setSkillsRequested(String.join(",", skills));
        r.setResultsCount(result.size());
        queryRepo.save(r);

        return result;
    }

    public SearchQueryRecord getQueryById(Long id) {
        return queryRepo.findBySearchId(id);
    }

    public List<SearchQueryRecord> getQueriesForUser(Long userId) {
        return queryRepo.findAll();
    }
}
