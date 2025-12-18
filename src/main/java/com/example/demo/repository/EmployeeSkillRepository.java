package com.example.demo.repository;

import org.springframework.data.jpa.repository.*;
import org.springframework.data.repository.query.Param;
import java.util.List;
import com.example.demo.model.EmployeeSkill;
import com.example.demo.model.Employee;

public interface EmployeeSkillRepository extends JpaRepository<EmployeeSkill, Long> {

    @Query("SELECT e.employee FROM EmployeeSkill e WHERE e.skill.name IN :skills AND e.employee.id = :userId AND e.active = true")
    List<Employee> findEmployeesByAllSkillNames(
            @Param("skills") List<String> skills,
            @Param("userId") Long userId
    );

    List<EmployeeSkill> findByEmployeeIdAndActiveTrue(Long employeeId);
    List<EmployeeSkill> findBySkillIdAndActiveTrue(Long skillId);
}
