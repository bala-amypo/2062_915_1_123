package com.example.demo.service;

import com.example.demo.model.Employee;
import com.example.demo.model.EmployeeSkill;

import java.util.List;

public interface EmployeeSkillService {

    List<EmployeeSkill> getSkillsForEmployee(Long employeeId);

    List<EmployeeSkill> getEmployeesBySkill(Long skillId);

    List<Employee> searchEmployeesBySkills(List<String> skillNames, Long userId);
}
