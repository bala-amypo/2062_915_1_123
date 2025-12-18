package com.example.demo.service;

import org.springframework.stereotype.Service;
import java.util.List;
import com.example.demo.repository.*;
import com.example.demo.model.*;

@Service
public class EmployeeSkillService {

    private final EmployeeSkillRepository esRepo;
    private final EmployeeRepository eRepo;
    private final SkillRepository sRepo;

    public EmployeeSkillService(EmployeeSkillRepository esRepo,
                                EmployeeRepository eRepo,
                                SkillRepository sRepo) {
        this.esRepo = esRepo;
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

        return esRepo.save(es);
    }

    public List<EmployeeSkill> getSkillsForEmployee(Long empId) {
        return esRepo.findByEmployeeIdAndActiveTrue(empId);
    }

    public List<EmployeeSkill> getEmployeesBySkill(Long skillId) {
        return esRepo.findBySkillIdAndActiveTrue(skillId);
    }

    public void deactivateEmployeeSkill(Long id) {
        EmployeeSkill es = esRepo.findById(id)
                .orElseThrow(() -> new RuntimeException("Mapping not found"));
        es.setActive(false);
        esRepo.save(es);
    }
}
