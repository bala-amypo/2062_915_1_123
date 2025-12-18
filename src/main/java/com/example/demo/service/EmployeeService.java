package com.example.demo.service;

import org.springframework.stereotype.Service;
import java.util.List;
import com.example.demo.repository.EmployeeRepository;
import com.example.demo.model.Employee;

@Service
public class EmployeeService {

    private final EmployeeRepository repo;

    public EmployeeService(EmployeeRepository repo) {
        this.repo = repo;
    }

    public Employee createEmployee(Employee e) {
        if (repo.existsByEmail(e.getEmail()))
            throw new IllegalArgumentException("Email already exists");
        return repo.save(e);
    }

    public Employee updateEmployee(Long id, Employee e) {
        Employee emp = repo.findById(id)
                .orElseThrow(() -> new RuntimeException("Employee not found"));
        e.setId(emp.getId());
        return repo.save(e);
    }

    public Employee getEmployeeById(Long id) {
        return repo.findById(id)
                .orElseThrow(() -> new RuntimeException("Employee not found"));
    }

    public List<Employee> getAllEmployees() {
        return repo.findAll();
    }

    public void deactivateEmployee(Long id) {
        Employee e = getEmployeeById(id);
        e.setActive(false);
        repo.save(e);
    }
}
