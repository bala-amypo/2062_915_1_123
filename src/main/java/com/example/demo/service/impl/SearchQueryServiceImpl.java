@Service
public class SearchQueryServiceImpl implements SearchQueryService {

    private final SearchQueryRecordRepository repo;
    private final EmployeeSkillRepository employeeSkillRepository;

    public SearchQueryServiceImpl(
            SearchQueryRecordRepository repo,
            EmployeeSkillRepository employeeSkillRepository
    ) {
        this.repo = repo;
        this.employeeSkillRepository = employeeSkillRepository;
    }

    @Override
    public List<Employee> searchEmployeesBySkills(List<String> skills, Long userId) {

        if (skills == null || skills.isEmpty()) {
            throw new IllegalArgumentException("Skill list must not be empty");
        }

        List<String> normalized = skills.stream()
                .map(s -> s.trim().toLowerCase())
                .distinct()
                .toList();

        List<Employee> result =
                employeeSkillRepository.findEmployeesByAllSkillNames(
                        normalized,
                        normalized.size()
                );

        SearchQueryRecord record = new SearchQueryRecord();
        record.setSearcherId(userId);
        record.setSkillsRequested(String.join(",", normalized));
        record.setResultsCount(result.size());

        repo.save(record);
        return result;
    }
}
