package dtos;

import entities.Assignment;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

public class AssignmentDTO {
    private long id;
    private String familyName;
    private LocalDateTime created;
    private String contactInfo;

    private DinnerEventDTO dinnerEvent;
    private List<UserDTO> userDTOList;

    public AssignmentDTO() {
    }

    public AssignmentDTO(Assignment assignment) {
        this.id = assignment.getId();
        this.familyName = assignment.getFamilyName();
        this.created = assignment.getCreated();
        this.contactInfo = assignment.getContactInfo();
        if(assignment.getDinnerEvent()!= null) {
            this.dinnerEvent = new DinnerEventDTO(assignment.getDinnerEvent());
        }
        if(assignment.getUsers()!= null) {
            this.userDTOList = assignment.getUsers().stream().map(UserDTO::new).collect(Collectors.toList());

        }

    }

    public static List<AssignmentDTO> getDTOs(List<Assignment> assignments) {
        return assignments.stream().map(a -> new AssignmentDTO(a)).collect(Collectors.toList());
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getFamilyName() {
        return familyName;
    }

    public void setFamilyName(String familyName) {
        this.familyName = familyName;
    }

    public LocalDateTime getCreated() {
        return created;
    }

    public void setCreated(LocalDateTime created) {
        this.created = created;
    }

    public String getContactInfo() {
        return contactInfo;
    }

    public void setContactInfo(String contactInfo) {
        this.contactInfo = contactInfo;
    }

    public DinnerEventDTO getDinnerEvent() {
        return dinnerEvent;
    }

    public void setDinnerEvent(DinnerEventDTO dinnerEvent) {
        this.dinnerEvent = dinnerEvent;
    }

    public List<UserDTO> getUserDTOList() {
        return userDTOList;
    }

    public void setUserDTOList(List<UserDTO> userDTOList) {
        this.userDTOList = userDTOList;
    }

    @Override
    public String toString() {
        return "AssignmentDTO{" +
                "id=" + id +
                ", familyName='" + familyName + '\'' +
                ", created=" + created +
                ", contactInfo='" + contactInfo + '\'' +
                ", dinnerEvent=" + dinnerEvent +
                ", userDTOList=" + userDTOList +
                '}';
    }
}
