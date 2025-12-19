package com.example.demo.service;

import com.example.demo.model.Employee;
import java.util.List;

public interface EmployeeService {
    Employee createEmployee(Employee emp);
    Employee updateEmployee(Long id, Employee emp);
    Employee getEmployeeById(Long id);
    List<Employee> getAllEmployees();
    void deactivateEmployee(Long id);
}
