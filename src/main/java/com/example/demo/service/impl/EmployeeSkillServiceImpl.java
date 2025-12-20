package com.example.demo.service.impl;

import com.example.demo.model.EmployeeSkill;
import com.example.demo.repository.EmployeeSkillRepository;
import com.example.demo.service.EmployeeSkillService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EmployeeSkillServiceImpl implements EmployeeSkillService {

    private final EmployeeSkillRepository employeeSkillRepository;

    // ✅ SINGLE constructor + final field = Spring will autowire automatically
    public EmployeeSkillServiceImpl(EmployeeSkillRepository employeeSkillRepository) {
        this.employeeSkillRepository = employeeSkillRepository;
    }

    @Override
    public EmployeeSkill createEmployeeSkill(EmployeeSkill employeeSkill) {
        employeeSkill.setActive(true);
        return employeeSkillRepository.save(employeeSkill);
    }

    @Override
    public List<EmployeeSkill> getSkillsForEmployee(Long employeeId) {
        return employeeSkillRepository.findByEmployeeIdAndActiveTrue(employeeId);
    }

    @Override
    public void deactivateEmployeeSkill(Long id) {
        EmployeeSkill skill = employeeSkillRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("EmployeeSkill not found"));
        skill.setActive(false);
        employeeSkillRepository.save(skill);
    }
}
