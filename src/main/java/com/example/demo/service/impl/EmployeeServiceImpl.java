package com.example.demo.service.impl;

import com.example.demo.model.Employee;
import com.example.demo.repository.EmployeeRepository;
import com.example.demo.service.EmployeeService;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class EmployeeServiceImpl implements EmployeeService {

    private final EmployeeRepository repo;

    public EmployeeServiceImpl(EmployeeRepository repo) {
        this.repo = repo;
    }

    public Employee createEmployee(Employee e) {
        return repo.save(e);
    }

    public Employee updateEmployee(Long id, Employee e) {
        Employee existing = repo.findById(id)
                .orElseThrow(() -> new RuntimeException("Employee not found"));
        e.setId(existing.getId());
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
