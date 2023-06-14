package facades;

import dtos.AssignmentDTO;
import entities.Assignment;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import utils.EMF_Creator;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class AssignmentFacadeTest {

    private static EntityManagerFactory emf;
    private static AssignmentFacade facade;
    private static Assignment a1;
    private static AssignmentDTO dto;

    @BeforeAll
    public static void setUpClass() {
        emf = EMF_Creator.createEntityManagerFactoryForTest();
        facade = AssignmentFacade.getAssignmentFacade(emf);
    }

    @BeforeEach
    void setUp() {
        EntityManager em = emf.createEntityManager();

        a1 = new Assignment("Perdersen", "test@test.dk");

        try {

            em.getTransaction().begin();
            em.createNamedQuery("Assignment.deleteAllRows").executeUpdate();
            em.persist(a1);
            em.getTransaction().commit();

        } finally {
            em.close();

        }

        dto = new AssignmentDTO(a1);

    }

    @Test
    public void createAssignment() {
        Assignment a2 = new Assignment("Andersen", "test2@test2.dk");
        AssignmentDTO dto2 = new AssignmentDTO(a2);
        AssignmentDTO dto3 = facade.createAssignment(dto2);

        long expected = 2;
        long actual = dto3.getId();

        assertEquals(expected, actual);

    }

}
