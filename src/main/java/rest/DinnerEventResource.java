package rest;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import dtos.DinnerEventDTO;
import facades.DinnerEventFacade;
import facades.UserFacade;
import utils.EMF_Creator;

import javax.persistence.EntityManagerFactory;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("dinner")
public class DinnerEventResource {

    private static final EntityManagerFactory EMF = EMF_Creator.createEntityManagerFactory();

    private static final DinnerEventFacade FACADE =  DinnerEventFacade.getDinnerEventFacade(EMF);
    private static final Gson GSON = new GsonBuilder().setPrettyPrinting().create();

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public String getAllDinnerEvents() {
        return GSON.toJson(FACADE.getAllDinnerEvents());
    }

    @POST
    @Path("add")
    @Produces(MediaType.APPLICATION_JSON)
    public Response addDinnerEvent(String json) {
        try {
            DinnerEventDTO dinnerEvent = GSON.fromJson(json, DinnerEventDTO.class);
            FACADE.createDinnerEvent(dinnerEvent);
            return Response.ok().entity(dinnerEvent).build();
        } catch (Exception e) {
            return Response.serverError().entity(e.getMessage()).build();
        }
    }

    @POST
    @Path("update/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response updateDinnerEvent(@PathParam("id") int id, String json) {
        try {
            DinnerEventDTO dinnerEvent = GSON.fromJson(json, DinnerEventDTO.class);
            FACADE.updateDinnerEvent(id, dinnerEvent);
            return Response.ok().entity(dinnerEvent).build();
        } catch (Exception e) {
            return Response.serverError().entity(e.getMessage()).build();
        }
    }

    @POST
    @Path("delete/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response deleteDinnerEvent(@PathParam("id") int id) {
        try {
            FACADE.deleteDinnerEvent(id);
            return Response.ok().build();
        } catch (Exception e) {
            return Response.serverError().entity(e.getMessage()).build();
        }
    }


}
