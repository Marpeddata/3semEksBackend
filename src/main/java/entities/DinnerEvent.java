package entities;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "dinnerEvent")
public class DinnerEvent {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String time;
    private String location;
    private String dish;
    private int pricePrPerson;

    @OneToMany(mappedBy = "dinnerEvent")
    private List<Assignment> assignments = new ArrayList<>();

    public DinnerEvent() {
    }

    public DinnerEvent(String time, String location, String dish, int pricePrPerson) {
        this.time = time;
        this.location = location;
        this.dish = dish;
        this.pricePrPerson = pricePrPerson;
    }

    public long getId() {
        return id;
    }


    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getDish() {
        return dish;
    }

    public void setDish(String dish) {
        this.dish = dish;
    }

    public int getPricePrPerson() {
        return pricePrPerson;
    }

    public void setPricePrPerson(int pricePrPerson) {
        this.pricePrPerson = pricePrPerson;
    }

    public List<Assignment> getAssignments() {
        return assignments;
    }

    public void addAssignment(Assignment assignment) {
        this.assignments.add(assignment);
    }

    @Override
    public String toString() {
        return "DinnerEvent{" +
                "id=" + id +
                ", time='" + time + '\'' +
                ", location='" + location + '\'' +
                ", dish='" + dish + '\'' +
                ", pricePrPerson=" + pricePrPerson +
                ", assignments=" + assignments +
                '}';
    }
}
