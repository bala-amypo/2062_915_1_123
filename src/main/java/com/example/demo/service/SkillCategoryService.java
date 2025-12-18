package com.example.demo.service;

import org.springframework.stereotype.Service;
import java.util.List;
import com.example.demo.repository.SkillCategoryRepository;
import com.example.demo.model.SkillCategory;

@Service
public class SkillCategoryService {

    private final SkillCategoryRepository repo;

    // ✅ EXACT constructor signature required by tests
    public SkillCategoryService(SkillCategoryRepository repo) {
        this.repo = repo;
    }

    public SkillCategory createCategory(SkillCategory category) {
        return repo.save(category);
    }

    public SkillCategory updateCategory(Long id, SkillCategory category) {
        SkillCategory existing = repo.findById(id)
                .orElseThrow(() -> new RuntimeException("Category not found"));
        category.setId(existing.getId());
        return repo.save(category);
    }

    public SkillCategory getCategoryById(Long id) {
        return repo.findById(id)
                .orElseThrow(() -> new RuntimeException("Category not found"));
    }

    public List<SkillCategory> getAllCategories() {
        return repo.findAll();
    }

    public void deactivateCategory(Long id) {
        SkillCategory category = getCategoryById(id);
        category.setActive(false);
        repo.save(category);
    }
}
