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
    public void deactivateEmployeeSkill(Long employeeSkillId) {
        EmployeeSkill skill = employeeSkillRepository
                .findById(employeeSkillId)
                .orElseThrow(() -> new RuntimeException("EmployeeSkill not found"));

        skill.setActive(false);
        employeeSkillRepository.save(skill);
    }

    @Override
    public List<EmployeeSkill> getSkillsForEmployee(Long employeeId) {
        return employeeSkillRepository.findByEmployeeIdAndActiveTrue(employeeId);
    }

    @Override
    public List<EmployeeSkill> getEmployeesBySkill(Long skillId) {
        return employeeSkillRepository.findBySkillIdAndActiveTrue(skillId);
    }

    @Override
    public List<Employee> searchEmployeesBySkills(List<String> skillNames, Long userId) {
        return employeeSkillRepository.findEmployeesByAllSkillNames(
                skillNames,
                skillNames.size()
        );
    }
}
