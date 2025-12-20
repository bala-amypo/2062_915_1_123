package com.example.demo.repository;

import com.example.demo.model.Employee;
import com.example.demo.model.EmployeeSkill;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface EmployeeSkillRepository extends JpaRepository<EmployeeSkill, Long> {

    List<EmployeeSkill> findByEmployee_IdAndActiveTrue(Long employeeId);

    List<EmployeeSkill> findBySkill_IdAndActiveTrue(Long skillId);

    // 🔑 CORRECT JPQL QUERY (NO method-name magic)
    @Query("""
        SELECT es.employee
        FROM EmployeeSkill es
        WHERE es.skill.name IN :skills
        AND es.active = true
        GROUP BY es.employee
        HAVING COUNT(DISTINCT es.skill.name) = :skillCount
    """)
    List<Employee> findEmployeesByAllSkillNames(List<String> skills, long skillCount);
}
