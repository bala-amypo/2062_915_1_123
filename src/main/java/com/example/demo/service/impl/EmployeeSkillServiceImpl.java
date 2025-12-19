package com.example.demo.service.impl;

import com.example.demo.model.*;
import com.example.demo.repository.*;
import com.example.demo.service.EmployeeSkillService;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class EmployeeSkillServiceImpl implements EmployeeSkillService {

    private final EmployeeSkillRepository repo;
    private final EmployeeRepository eRepo;
    private final SkillRepository sRepo;

    public EmployeeSkillServiceImpl(EmployeeSkillRepository repo,
                                    EmployeeRepository eRepo,
                                    SkillRepository sRepo) {
        this.repo = repo;
        this.eRepo = eRepo;
        this.sRepo = sRepo;
    }

    public EmployeeSkill createEmployeeSkill(EmployeeSkill es) {
        if (es.getYearsOfExperience() < 0)
            throw new IllegalArgumentException("Experience years");

        List<String> levels = List.of("Beginner","Intermediate","Advanced","Expert");
        if (!levels.contains(es.getProficiencyLevel()))
            throw new IllegalArgumentException("Invalid proficiency");

        if (!es.getEmployee().getActive())
            throw new IllegalArgumentException("inactive employee");

        if (!es.getSkill().getActive())
            throw new IllegalArgumentException("inactive skill");

        return repo.save(es);
    }

    public List<EmployeeSkill> getSkillsForEmployee(Long id) {
        return repo.findByEmployeeIdAndActiveTrue(id);
    }

    public List<EmployeeSkill> getEmployeesBySkill(Long id) {
        return repo.findBySkillIdAndActiveTrue(id);
    }

    public void deactivateEmployeeSkill(Long id) {
        EmployeeSkill es = repo.findById(id)
                .orElseThrow(() -> new RuntimeException("Not found"));
        es.setActive(false);
        repo.save(es);
    }
}
