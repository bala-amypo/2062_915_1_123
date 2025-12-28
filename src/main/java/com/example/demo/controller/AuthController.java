package com.example.demo.controller;

import com.example.demo.model.Employee;
import com.example.demo.repository.EmployeeRepository;
import com.example.demo.security.JwtTokenProvider;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/auth")
public class AuthController {

    private final EmployeeRepository employeeRepository;
    private final JwtTokenProvider jwtTokenProvider;

    public AuthController(EmployeeRepository employeeRepository,
                          JwtTokenProvider jwtTokenProvider) {
        this.employeeRepository = employeeRepository;
        this.jwtTokenProvider = jwtTokenProvider;
    }

    // 🔐 LOGIN (Swagger-friendly)
    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody Map<String, String> request) {

        String email = request.get("email");

        Employee employee = employeeRepository.findByEmail(email)
                .orElseThrow(() -> new RuntimeException("Invalid email"));

        String token = jwtTokenProvider.generateToken(
                employee.getId(),
                employee.getEmail(),
                "USER"
        );

        Map<String, Object> response = new HashMap<>();
        response.put("token", token);
        response.put("email", employee.getEmail());
        response.put("userId", employee.getId());

        return ResponseEntity.ok(response);
    }

    // (Optional) Register endpoint for completeness
    @PostMapping("/register")
    public ResponseEntity<?> register(@RequestBody Employee employee) {
        employee.setActive(true);
        Employee saved = employeeRepository.save(employee);
        return ResponseEntity.ok(saved);
    }
}
