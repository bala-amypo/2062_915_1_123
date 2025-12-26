package com.example.demo.service;

import com.example.demo.model.EmployeeSkill;
import com.example.demo.model.Employee;

import java.util.List;

public interface EmployeeSkillService {

    EmployeeSkill createEmployeeSkill(EmployeeSkill employeeSkill);

    List<EmployeeSkill> getSkillsForEmployee(Long employeeId);

    List<EmployeeSkill> getEmployeesBySkill(Long skillId);

    void deactivateEmployeeSkill(Long employeeSkillId);
}
