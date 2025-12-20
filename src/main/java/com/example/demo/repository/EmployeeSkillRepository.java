package com.example.demo.repository;

import com.example.demo.model.EmployeeSkill;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface EmployeeSkillRepository extends JpaRepository<EmployeeSkill, Long> {

    // All active skills for an employee
    List<EmployeeSkill> findByEmployee_IdAndActiveTrue(Long employeeId);

    // All active employee-skill mappings for a skill
    List<EmployeeSkill> findBySkill_IdAndActiveTrue(Long skillId);
}
