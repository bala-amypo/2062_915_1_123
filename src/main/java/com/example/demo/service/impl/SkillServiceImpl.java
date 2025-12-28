@Service
public class SkillServiceImpl implements SkillService {

    private final SkillRepository skillRepository;

    public SkillServiceImpl(SkillRepository skillRepository) {
        this.skillRepository = skillRepository;
    }

    @Override
    public Skill updateSkill(Long id, Skill updatedSkill) {

        Skill existing = skillRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Skill not found"));

        // ✅ MUTATE EXISTING ENTITY
        if (updatedSkill.getName() != null) {
            existing.setName(updatedSkill.getName());
        }

        return skillRepository.save(existing);
    }
}
