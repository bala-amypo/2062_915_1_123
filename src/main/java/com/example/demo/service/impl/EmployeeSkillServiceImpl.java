package com.example.demo.service.impl;

import com.example.demo.model.Employee;
import com.example.demo.model.EmployeeSkill;
import com.example.demo.model.Skill;
import com.example.demo.repository.EmployeeRepository;
import com.example.demo.repository.EmployeeSkillRepository;
import com.example.demo.repository.SkillRepository;
import com.example.demo.service.EmployeeSkillService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EmployeeSkillServiceImpl implements EmployeeSkillService {

    private final EmployeeSkillRepository employeeSkillRepository;
    private final EmployeeRepository employeeRepository;
    private final SkillRepository skillRepository;

    public EmployeeSkillServiceImpl(EmployeeSkillRepository employeeSkillRepository,
                                    EmployeeRepository employeeRepository,
                                    SkillRepository skillRepository) {
        this.employeeSkillRepository = employeeSkillRepository;
        this.employeeRepository = employeeRepository;
        this.skillRepository = skillRepository;
    }

    @Override
    public EmployeeSkill createEmployeeSkill(EmployeeSkill es) {

        Employee emp = employeeRepository.findById(es.getEmployee().getId())
                .orElseThrow(() -> new IllegalArgumentException("Employee not found"));

        Skill skill = skillRepository.findById(es.getSkill().getId())
                .orElseThrow(() -> new IllegalArgumentException("Skill not found"));

        if (!emp.getActive()) {
            throw new IllegalArgumentException("inactive employee");
        }

        if (!skill.getActive()) {
            throw new IllegalArgumentException("inactive skill");
        }

        if (es.getYearsOfExperience() < 0) {
            throw new IllegalArgumentException("Experience years cannot be negative");
        }

        if (!List.of("Beginner", "Intermediate", "Advanced").contains(es.getProficiencyLevel())) {
            throw new IllegalArgumentException("Invalid proficiency");
        }

        return employeeSkillRepository.save(es);
    }

    @Override
    public void deactivateEmployeeSkill(Long id) {
        EmployeeSkill es = employeeSkillRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("EmployeeSkill not found"));
        es.setActive(false);
        employeeSkillRepository.save(es);
    }

    @Override
    public List<EmployeeSkill> getSkillsForEmployee(Long employeeId) {
        return employeeSkillRepository.findByEmployeeIdAndActiveTrue(employeeId);
    }

    @Override
    public List<EmployeeSkill> getEmployeesBySkill(Long skillId) {
        return employeeSkillRepository.findBySkillIdAndActiveTrue(skillId);
    }
}
