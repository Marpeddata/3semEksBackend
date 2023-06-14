package rest;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import dtos.AssignmentDTO;
import entities.Assignment;
import facades.AssignmentFacade;
import facades.DinnerEventFacade;
import org.glassfish.jersey.message.internal.MediaTypes;
import utils.EMF_Creator;

import javax.persistence.EntityManagerFactory;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("assignment")
public class AssignmentResource {

    private static final EntityManagerFactory EMF = EMF_Creator.createEntityManagerFactory();

    private static final AssignmentFacade FACADE =  AssignmentFacade.getAssignmentFacade(EMF);
    private static final Gson GSON = new GsonBuilder().setPrettyPrinting().create();


    @POST
    @Path("/createAssignment")
    @Produces(MediaType.APPLICATION_JSON)
    public Response createAssignment(String json) {
        try {
            AssignmentDTO assignment = GSON.fromJson(json, AssignmentDTO.class);
            FACADE.createAssignment(assignment);
            return Response.ok().build();
        } catch (Exception e) {
            return Response.serverError().build();
        }
    }

    @POST
    @Path("/{assignmentId}/{userId}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response addUserToAssignment(@PathParam("assignmentId") int assignmentId, @PathParam("userId") int userId) {
        try {
            FACADE.removeUserFromAssignment(assignmentId, userId);
            return Response.ok().build();
        } catch (Exception e) {
            return Response.serverError().build();
        }
    }

}
