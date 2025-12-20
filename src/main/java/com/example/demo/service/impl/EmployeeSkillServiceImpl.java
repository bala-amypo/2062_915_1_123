package com.example.demo.service.impl;

import com.example.demo.model.Employee;
import com.example.demo.model.EmployeeSkill;
import com.example.demo.repository.EmployeeSkillRepository;
import com.example.demo.service.EmployeeSkillService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EmployeeSkillServiceImpl implements EmployeeSkillService {

    private final EmployeeSkillRepository repo;

    public EmployeeSkillServiceImpl(EmployeeSkillRepository repo) {
        this.repo = repo;
    }

    @Override
    public List<EmployeeSkill> getSkillsByEmployee(Long employeeId) {
        return repo.findByEmployeeIdAndActiveTrue(employeeId);
    }

    @Override
    public List<EmployeeSkill> getEmployeesBySkill(Long skillId) {
        return repo.findBySkillIdAndActiveTrue(skillId);
    }

    @Override
    public List<Employee> searchEmployeesBySkills(List<String> skills, Long userId) {
        return repo.findEmployeesByAllSkillNames(skills, userId);
    }
}
