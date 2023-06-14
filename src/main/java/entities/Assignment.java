package entities;

import javax.persistence.*;
import javax.ws.rs.core.MediaType;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "assignments")
@NamedQuery(name = "Assignment.deleteAllRows", query = "DELETE from Assignment")
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
            joinColumns = @JoinColumn(name = "assignment", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "user", referencedColumnName = "user_name"))
    private List<User> users = new ArrayList<>();

    @PrePersist
    public void onPersist() {
        created = LocalDateTime.now(ZoneId.of("GMT+02:00"));
    }


    public Assignment() {
    }

    public Assignment(String familyName, String contactInfo) {
        this.familyName = familyName;

        this.contactInfo = contactInfo;

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
    public void setDinnerEvent(DinnerEvent dinnerEvent) {
        this.dinnerEvent = dinnerEvent;
    }

    public void removeDinnerEvent(DinnerEvent dinnerEvent) {
        this.dinnerEvent = null;
        dinnerEvent.removeAssignment(this);
    }

    public void removeUser(User user) {
        users.remove(user);
        user.removeAssignment(this);
    }

    public List<User> getUsers() {
        return users;
    }

    public void addUsers(User user) {
        this.users.add(user);
        user.addAssignment(this);
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
