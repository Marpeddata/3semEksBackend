package dtos;

import entities.User;
import entities.User;

import java.util.List;
import java.util.stream.Collectors;

public class UserDTO {
    private String username;
    private String address;
    private String phone;
    private String email;
    private int birthYear;

    private List<Long> AssignmentIds;


    public UserDTO(User u) {
        this.username = u.getUserName();
        this.address = u.getAddress();
        this.phone = u.getPhone();
        this.email = u.getEmail();
        this.birthYear = u.getBirthYear();
        if(u.getAssignmentList()!= null) {
            this.AssignmentIds = u.getAssignmentList().stream().map(p -> p.getId()).collect(Collectors.toList());
        }

    }

    public static List<UserDTO> getDtos(List<User> Users) {
        return Users.stream().map(p -> new UserDTO(p)).collect(Collectors.toList());
    }


    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public int getBirthYear() {
        return birthYear;
    }

    public void setBirthYear(int birthYear) {
        this.birthYear = birthYear;
    }

    public List<Long> getAssignmentIds() {
        return AssignmentIds;
    }

    public void setAssignmentIds(List<Long> assignmentIds) {
        AssignmentIds = assignmentIds;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    @Override
    public String toString() {
        return "UserDTO{" +
                "username='" + username + '\'' +
                ", address='" + address + '\'' +
                ", phone='" + phone + '\'' +
                ", email='" + email + '\'' +
                ", birthYear=" + birthYear +
                ", AssignmentIds=" + AssignmentIds +
                '}';
    }
}
