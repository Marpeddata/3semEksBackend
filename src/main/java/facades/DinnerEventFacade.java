package facades;

import dtos.DinnerEventDTO;
import entities.Assignment;
import entities.DinnerEvent;
import entities.User;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.TypedQuery;
import java.util.List;

public class DinnerEventFacade {

    private static EntityManagerFactory emf;
    private static DinnerEventFacade instance;

    public DinnerEventFacade(){
    }

    public static DinnerEventFacade getDinnerEventFacade(EntityManagerFactory _emf) {
        if (instance == null) {
            emf = _emf;
            instance = new DinnerEventFacade();
        }
        return instance;
    }

    public List<DinnerEventDTO> getAllDinnerEvents() {
        EntityManager em = emf.createEntityManager();
        TypedQuery<DinnerEvent> query = em.createQuery("SELECT d FROM DinnerEvent d", DinnerEvent.class);
        List<DinnerEvent> dinnerEvents = query.getResultList();
        return DinnerEventDTO.getDTOs(dinnerEvents);

    }

    public DinnerEventDTO createDinnerEvent(DinnerEventDTO dinnerEventDTO) {
        EntityManager em = emf.createEntityManager();
        DinnerEvent dinnerEvent = new DinnerEvent(dinnerEventDTO.getTime(), dinnerEventDTO.getLocation(), dinnerEventDTO.getDish(), dinnerEventDTO.getPricePrPerson());

        try{
            em.getTransaction().begin();
            em.persist(dinnerEvent);
            em.getTransaction().commit();
        } finally {
            em.close();
        }
        return new DinnerEventDTO(dinnerEvent);
    }

    public DinnerEventDTO updateDinnerEvent(int id, DinnerEventDTO dinnerEventDTO) {
        EntityManager em = emf.createEntityManager();
        DinnerEvent dinnerEvent = em.find(DinnerEvent.class, id);
        dinnerEvent.setTime(dinnerEventDTO.getTime());
        dinnerEvent.setLocation(dinnerEventDTO.getLocation());
        dinnerEvent.setDish(dinnerEventDTO.getDish());
        dinnerEvent.setPricePrPerson(dinnerEventDTO.getPricePrPerson());

        try{
            em.getTransaction().begin();
            em.merge(dinnerEvent);
            em.getTransaction().commit();
        } finally {
            em.close();
        }
        return new DinnerEventDTO(dinnerEvent);
    }

    public boolean deleteDinnerEvent(int id) {
        EntityManager em = emf.createEntityManager();
        DinnerEvent dinnerEvent = em.find(DinnerEvent.class, id);

        if(dinnerEvent.getAssignments() != null) {
            for (Assignment assignment : dinnerEvent.getAssignments()) {
                for (User user : assignment.getUsers()) {
                    assignment.removeUser(user);
                }
                assignment.removeDinnerEvent(dinnerEvent);
            }

        }

        try{
            em.getTransaction().begin();
            em.remove(dinnerEvent);
            em.getTransaction().commit();
            return true;
        }catch (Exception e){
            e.printStackTrace();
            return false;
        }

        finally {
            em.close();
        }


    }

}
