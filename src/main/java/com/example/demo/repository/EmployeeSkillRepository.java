package com.example.demo.repository;

import com.example.demo.model.Employee;
import com.example.demo.model.EmployeeSkill;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface EmployeeSkillRepository extends JpaRepository<EmployeeSkill, Long> {

    List<EmployeeSkill> findByEmployee_IdAndActiveTrue(Long employeeId);

    List<EmployeeSkill> findBySkill_IdAndActiveTrue(Long skillId);

    // ✅ REQUIRED for searchEmployeesBySkills
    @Query("""
        SELECT es.employee
        FROM EmployeeSkill es
        WHERE es.skill.name IN :skills
          AND es.active = true
        GROUP BY es.employee
        HAVING COUNT(DISTINCT es.skill.name) = :skillCount
    """)
    List<Employee> findEmployeesByAllSkillNames(
            @Param("skills") List<String> skills,
            @Param("skillCount") long skillCount
    );
}
