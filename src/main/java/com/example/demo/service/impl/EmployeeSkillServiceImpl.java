@Service
public class EmployeeSkillServiceImpl implements EmployeeSkillService {

    private final EmployeeSkillRepository employeeSkillRepository;
    private final EmployeeRepository employeeRepository;
    private final SkillRepository skillRepository;

    public EmployeeSkillServiceImpl(
            EmployeeSkillRepository employeeSkillRepository,
            EmployeeRepository employeeRepository,
            SkillRepository skillRepository
    ) {
        this.employeeSkillRepository = employeeSkillRepository;
        this.employeeRepository = employeeRepository;
        this.skillRepository = skillRepository;
    }

    @Override
    public EmployeeSkill createEmployeeSkill(EmployeeSkill es) {

        if (es.getYearsOfExperience() < 0) {
            throw new IllegalArgumentException("Experience years must be non-negative");
        }

        if (!List.of("Beginner", "Intermediate", "Advanced")
                .contains(es.getProficiencyLevel())) {
            throw new IllegalArgumentException("Invalid proficiency");
        }

        Employee emp = employeeRepository.findById(es.getEmployee().getId())
                .orElseThrow(() -> new IllegalArgumentException("Employee not found"));

        if (!emp.getActive()) {
            throw new IllegalArgumentException("inactive employee");
        }

        Skill skill = skillRepository.findById(es.getSkill().getId())
                .orElseThrow(() -> new IllegalArgumentException("Skill not found"));

        if (!skill.getActive()) {
            throw new IllegalArgumentException("inactive skill");
        }

        es.setActive(true);
        return employeeSkillRepository.save(es);
    }

    @Override
    public List<EmployeeSkill> getSkillsForEmployee(Long employeeId) {
        return employeeSkillRepository.findByEmployeeIdAndActiveTrue(employeeId);
    }

    @Override
    public List<EmployeeSkill> getEmployeesBySkill(Long skillId) {
        return employeeSkillRepository.findBySkillIdAndActiveTrue(skillId);
    }

    @Override
    public void deactivateEmployeeSkill(Long id) {
        EmployeeSkill es = employeeSkillRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("EmployeeSkill not found"));
        es.setActive(false);
        employeeSkillRepository.save(es);
    }
}
