package com.example.demo.service;

import org.springframework.stereotype.Service;
import java.util.List;
import com.example.demo.repository.SkillRepository;
import com.example.demo.model.Skill;

@Service
public class SkillService {

    private final SkillRepository repo;

    public SkillService(SkillRepository repo) {
        this.repo = repo;
    }

    public Skill createSkill(Skill s) {
        return repo.save(s);
    }

    public Skill updateSkill(Long id, Skill s) {
        s.setId(id);
        return repo.save(s);
    }

    public Skill getSkillById(Long id) {
        return repo.findById(id)
                .orElseThrow(() -> new RuntimeException("Skill not found"));
    }

    public List<Skill> getAllSkills() {
        return repo.findAll();
    }

    public void deactivateSkill(Long id) {
        Skill s = getSkillById(id);
        s.setActive(false);
        repo.save(s);
    }
}
