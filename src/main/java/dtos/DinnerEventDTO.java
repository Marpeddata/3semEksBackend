package dtos;

import entities.DinnerEvent;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class DinnerEventDTO {
    private long id;
    private String time;
    private String location;
    private String dish;
    private int pricePrPerson;
    private List<Long> assignmentIds;

    public DinnerEventDTO() {
    }

    public DinnerEventDTO(DinnerEvent dinnerEvent) {
        this.id = dinnerEvent.getId();
        this.time = dinnerEvent.getTime();
        this.location = dinnerEvent.getLocation();
        this.dish = dinnerEvent.getDish();
        this.pricePrPerson = dinnerEvent.getPricePrPerson();
        if(dinnerEvent.getAssignments()!= null) {
            this.assignmentIds = dinnerEvent.getAssignments().stream().map(a -> a.getId()).collect(Collectors.toList());

        }
    }

    public static List<DinnerEventDTO> getDTOs(List<DinnerEvent> dinner){
        return dinner.stream().map(d -> new DinnerEventDTO(d)).collect(Collectors.toList());
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getTime() {
        return time;
    }

    public String getDish() {
        return dish;
    }

    public void setDish(String dish) {
        this.dish = dish;
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

    public int getPricePrPerson() {
        return pricePrPerson;
    }

    public void setPricePrPerson(int pricePrPerson) {
        this.pricePrPerson = pricePrPerson;
    }

    public List<Long> getAssignments() {
        return assignmentIds;
    }

    public void setAssignments(List<Long> assignmentIds) {
        this.assignmentIds = assignmentIds;
    }

    @Override
    public String toString() {
        return "DinnerEventDTO{" +
                "id=" + id +
                ", time='" + time + '\'' +
                ", location='" + location + '\'' +
                ", pricePrPerson=" + pricePrPerson +
                ", Assignments=" + assignmentIds +
                '}';
    }
}
