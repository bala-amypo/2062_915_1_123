package com.example.demo.controller;

import org.springframework.web.bind.annotation.*;
import io.swagger.v3.oas.annotations.tags.Tag;
import java.util.List;
import com.example.demo.model.EmployeeSkill;
import com.example.demo.service.EmployeeSkillService;

@RestController
@RequestMapping("/api/employee-skills")
@Tag(name = "Employee Skill")
public class EmployeeSkillController {

    private final EmployeeSkillService service;

    public EmployeeSkillController(EmployeeSkillService service) {
        this.service = service;
    }

    @PostMapping
    public EmployeeSkill create(@RequestBody EmployeeSkill mapping) {
        return service.createEmployeeSkill(mapping);
    }

    @GetMapping("/employee/{employeeId}")
    public List<EmployeeSkill> getByEmployee(@PathVariable Long employeeId) {
        return service.getSkillsForEmployee(employeeId);
    }

    @GetMapping("/skill/{skillId}")
    public List<EmployeeSkill> getBySkill(@PathVariable Long skillId) {
        return service.getEmployeesBySkill(skillId);
    }

    @PutMapping("/{id}/deactivate")
    public void deactivate(@PathVariable Long id) {
        service.deactivateEmployeeSkill(id);
    }
}
