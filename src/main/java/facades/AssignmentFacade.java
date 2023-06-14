package facades;

import dtos.AssignmentDTO;
import entities.Assignment;
import entities.User;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import java.util.List;

public class AssignmentFacade {

    private static EntityManagerFactory emf;
    private static AssignmentFacade instance;

    public AssignmentFacade(){
    }

    public static AssignmentFacade getAssignmentFacade(EntityManagerFactory _emf) {
        if (instance == null) {
            emf = _emf;
            instance = new AssignmentFacade();
        }
        return instance;
    }

    public AssignmentDTO createAssignment(AssignmentDTO assignment){
        EntityManager em = emf.createEntityManager();
        Assignment assignement = new Assignment(assignment.getFamilyName(), assignment.getContactInfo());

        try {
            em.getTransaction().begin();
            em.persist(assignement);
            em.getTransaction().commit();
        }
        finally {
            em.close();
        }
         return new AssignmentDTO(assignement);
    }



    public void addUserToAssignment(int assignmentId, List<Integer> userId){

        EntityManager em = emf.createEntityManager();
        Assignment assignment = em.find(Assignment.class, assignmentId);
        for (int id: userId) {
            User user = em.find(User.class, id);
            assignment.addUsers(user);
        }

        try {
            em.getTransaction().begin();
            em.merge(assignment);
            em.getTransaction().commit();
        }
        finally {
            em.close();
        }

    }

    public void removeUserFromAssignment(int assignmentId, int userId){
        EntityManager em = emf.createEntityManager();
        Assignment assignement = em.find(Assignment.class, assignmentId);
        User user = em.find(User.class, userId);
        assignement.removeUser(user);

        try {
            em.getTransaction().begin();
            em.merge(assignement);
            em.getTransaction().commit();
        }
        finally {
            em.close();
        }

    }


}
