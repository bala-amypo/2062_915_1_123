package com.example.demo.controller;

import org.springframework.web.bind.annotation.*;
import io.swagger.v3.oas.annotations.tags.Tag;
import java.util.List;
import com.example.demo.model.Employee;
import com.example.demo.model.SearchQueryRecord;
import com.example.demo.service.SearchQueryService;

@RestController
@RequestMapping("/api/search")
@Tag(name = "Search")
public class SearchQueryController {

    private final SearchQueryService service;

    public SearchQueryController(SearchQueryService service) {
        this.service = service;
    }

    @PostMapping("/employees")
    public List<Employee> search(@RequestBody List<String> skills,
                                 @RequestParam Long userId) {
        return service.searchEmployeesBySkills(skills, userId);
    }

    @GetMapping("/{id}")
    public SearchQueryRecord get(@PathVariable Long id) {
        return service.getQueryById(id);
    }

    @GetMapping("/user/{userId}")
    public List<SearchQueryRecord> getUserQueries(@PathVariable Long userId) {
        return service.getQueriesForUser(userId);
    }
}
