package entities;

import javax.persistence.*;
import javax.ws.rs.core.MediaType;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "assignments")
public class Assignment {
    //Family
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @Column(name = "family_name")
    private String familyName;
    @Column(name = "created", updatable = false)
    private LocalDateTime created;
    @Column(name = "contact_info")
    private String contactInfo;

    @ManyToOne
    @JoinColumn(name = "dinner_event_id", referencedColumnName = "id")
    private DinnerEvent dinnerEvent;

    @ManyToMany
    @JoinTable(name = "assignments_users",
            joinColumns = @JoinColumn(name = "assignment_id", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "user_id", referencedColumnName = "id"))
    private List<User> users = new ArrayList<>();

    @PrePersist
    public void onPersist() {
        created = LocalDateTime.now(ZoneId.of("GMT+02:00"));
    }


    public Assignment() {
    }

    public Assignment(String familyName, LocalDateTime created, String contactInfo, List<User> users) {
        this.familyName = familyName;
        this.created = created;
        this.contactInfo = contactInfo;
        this.users = users;
    }

    public void setFamilyName(String familyName) {
        this.familyName = familyName;
    }

    public void setContactInfo(String contactInfo) {
        this.contactInfo = contactInfo;
    }

    public void addDinnerEvent(DinnerEvent dinnerEvent) {
        this.dinnerEvent = dinnerEvent;
        dinnerEvent.addAssignment(this);
    }

    public long getId() {
        return id;
    }

    public String getFamilyName() {
        return familyName;
    }

    public LocalDateTime getCreated() {
        return created;
    }

    public String getContactInfo() {
        return contactInfo;
    }

    public DinnerEvent getDinnerEvent() {
        return dinnerEvent;
    }

    public void removeUser(User user) {
        users.remove(user);
        user.removeAssignment(this);
    }

    @Override
    public String toString() {
        return "Assignment{" +
                "id=" + id +
                ", familyName='" + familyName + '\'' +
                ", created=" + created +
                ", contactInfo='" + contactInfo + '\'' +
                ", dinnerEvent=" + dinnerEvent +
                '}';
    }
}
