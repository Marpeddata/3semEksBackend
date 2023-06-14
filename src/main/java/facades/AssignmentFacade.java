package facades;

import dtos.AssignmentDTO;
import dtos.UserDTO;
import entities.Assignment;
import entities.DinnerEvent;
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

    public AssignmentDTO createAssignment(long eventId, AssignmentDTO assignmentdto){
        EntityManager em = emf.createEntityManager();
        DinnerEvent de = em.find(DinnerEvent.class, eventId);
        Assignment assignment = new Assignment(assignmentdto.getFamilyName(), assignmentdto.getContactInfo());
        assignment.setDinnerEvent(de);
        for(UserDTO userdto : assignmentdto.getUserDTOList()){
            User user = em.find(User.class, userdto.getUsername());
            assignment.addUsers(user);
        }

        try {
            em.getTransaction().begin();
            em.persist(assignment);
            em.getTransaction().commit();
        }
        finally {
            em.close();
        }
         return new AssignmentDTO(assignment);
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
