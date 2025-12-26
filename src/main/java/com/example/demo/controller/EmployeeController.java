package com.example.demo.controller;


import org.springframework.web.bind.annotation.*;
import io.swagger.v3.oas.annotations.tags.Tag;
import java.util.List;
import com.example.demo.model.Employee;
import com.example.demo.service.EmployeeService;


@RestController
@RequestMapping("/api/employees")
@Tag(name = "Employee")
public class EmployeeController {

    private final EmployeeService service;

    public EmployeeController(EmployeeService service) {
        this.service = service;
    }

    @PostMapping
    public Employee create(@RequestBody Employee employee) {
        return service.createEmployee(employee);
    }

    @PutMapping("/{id}")
    public Employee update(@PathVariable Long id, @RequestBody Employee employee) {
        return service.updateEmployee(id, employee);
    }

    @GetMapping("/{id}")
    public Employee get(@PathVariable Long id) {
        return service.getEmployeeById(id);
    }

    @GetMapping
    public List<Employee> getAll() {
        return service.getAllEmployees();
    }

    @PutMapping("/{id}/deactivate")
    public void deactivate(@PathVariable Long id) {
        service.deactivateEmployee(id);
    }
}
