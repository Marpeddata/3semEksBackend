package facades;

import dtos.AssignmentDTO;
import dtos.UserDTO;
import entities.Assignment;
import entities.User;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import utils.EMF_Creator;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class AssignmentFacadeTest {

    private static EntityManagerFactory emf;
    private static AssignmentFacade facade;
    private static UserFacade facade1;
    private static Assignment a1;
    private static AssignmentDTO dto;
    private static User u1;

    @BeforeAll
    public static void setUpClass() {
        emf = EMF_Creator.createEntityManagerFactoryForTest();
        facade = AssignmentFacade.getAssignmentFacade(emf);
        facade1 = UserFacade.getUserFacade(emf);
    }

//    @AfterAll
//    public static void tearDownClass() {
//        EntityManager em = emf.createEntityManager();
//        em.createNativeQuery("TRUNCATE users_assignments").executeUpdate();
//        em.createNativeQuery("TRUNCATE assignments_users").executeUpdate();
//        emf.close();
//    }



    @BeforeEach
    void setUp() {
        EntityManager em = emf.createEntityManager();

        a1 = new Assignment("Perdersen", "test@test.dk");
        u1 = new User("user", "test123");

        try {

            em.getTransaction().begin();

            em.createNamedQuery("Assignment.deleteAllRows").executeUpdate();

            em.persist(u1);
            em.persist(a1);

            em.getTransaction().commit();

        } finally {
            em.close();

        }

        dto = new AssignmentDTO(a1);

    }

//    @Test
//    public void createAssignmentReturnValue() {
//        Assignment a2 = new Assignment("Andersen", "test2@test2.dk");
//        AssignmentDTO dto2 = new AssignmentDTO(a2);
//        AssignmentDTO dto3 = facade.createAssignment(dto2);
//
//        long expected = 3;
//        long actual = dto3.getId();
//
//        assertEquals(expected, actual);
//    }

    @Test
    public void createAssignment() {


        UserDTO user = facade1.getUserByUsernameId("user");
        List<UserDTO> userDTOList = new ArrayList<>();
        userDTOList.add(user);
        System.out.println(user);
        Assignment a3 = new Assignment("Nielsen", "test3@test3.dk");

        AssignmentDTO dto4 = new AssignmentDTO(a3);
        dto4.setUserDTOList(userDTOList);
        AssignmentDTO dto5 = facade.createAssignment(dto4);

        String expected = user.getUsername();
        String actual = dto5.getUserDTOList().get(0).getUsername();

        assertEquals(expected, actual);

    }



}
