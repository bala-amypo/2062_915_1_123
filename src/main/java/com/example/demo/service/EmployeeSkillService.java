package com.example.demo.service;

import com.example.demo.model.Employee;
import com.example.demo.model.EmployeeSkill;

import java.util.List;

public interface EmployeeSkillService {

    EmployeeSkill createEmployeeSkill(EmployeeSkill employeeSkill);

    List<EmployeeSkill> getSkillsForEmployee(Long employeeId);

    List<EmployeeSkill> getEmployeesBySkill(Long skillId);

    List<Employee> searchEmployeesBySkills(List<String> skills, Long userId);

    void deactivateEmployeeSkill(Long employeeSkillId);
}
