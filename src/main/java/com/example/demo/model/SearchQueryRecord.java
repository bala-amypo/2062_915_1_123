@Entity
public class SearchQueryRecord {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String query;

    private Timestamp searchedAt;

    @PrePersist
    public void onCreate() {
        this.searchedAt = new Timestamp(System.currentTimeMillis());
    }

    // REQUIRED GETTERS & SETTERS

    public Long getId() {
        return id;
    }

    public void setId(Long id) {        // ✅ THIS FIXES THE ERROR
        this.id = id;
    }

    public Timestamp getSearchedAt() {
        return searchedAt;
    }

    public void setSearchedAt(Timestamp searchedAt) {
        this.searchedAt = searchedAt;
    }
}
