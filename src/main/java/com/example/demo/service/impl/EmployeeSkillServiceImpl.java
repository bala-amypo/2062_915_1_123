package com.example.demo.service.impl;

import com.example.demo.model.Employee;
import com.example.demo.model.EmployeeSkill;
import com.example.demo.repository.EmployeeSkillRepository;
import com.example.demo.service.EmployeeSkillService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EmployeeSkillServiceImpl implements EmployeeSkillService {

    private final EmployeeSkillRepository employeeSkillRepository;

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
        return employeeSkillRepository.findByEmployee_IdAndActiveTrue(employeeId);
    }

    @Override
    public void deactivateEmployeeSkill(Long id) {
        EmployeeSkill es = employeeSkillRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("EmployeeSkill not found"));
        es.setActive(false);
        employeeSkillRepository.save(es);
    }

    // ✅ THIS WAS MISSING
    @Override
    public List<Employee> searchEmployeesBySkills(List<String> skills, Long requesterId) {
        return employeeSkillRepository.findEmployeesByAllSkillNames(
                skills,
                skills.size()
        );
    }
}
