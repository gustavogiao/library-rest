package com.example.resources;

import java.util.ArrayList;
import java.util.List;

import com.example.enums.MissionStatus;
import com.example.models.Mission;
import com.google.gson.Gson;

import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.DELETE;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.PUT;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.QueryParam;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

@Path("/missions")
public class MissionResource {

    private static List<Mission> missions = new ArrayList<>();
    private static Gson gson = new Gson();

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public String getMissions(@QueryParam("status") MissionStatus status) {

        if (status == null) {
            return gson.toJson(missions);
        }

        List<Mission> filtered = new ArrayList<>();

        for (Mission m : missions) {
            if (m.status == status) {
                filtered.add(m);
            }
        }

        return gson.toJson(filtered);
    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    public Response addMission(String missionJson) {
        Mission mission = gson.fromJson(missionJson, Mission.class);
        missions.add(mission);
        return Response.status(Response.Status.CREATED).build();
    }

    @GET
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public String getMission(@PathParam("id") int id) {
        for(Mission m : missions){
            if(m.id == id) return gson.toJson(m);
        }
        return "{}";
    }

    @PUT
    @Path("/{id}")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response updateMission(String missionJson) {
        Mission updatedMission = gson.fromJson(missionJson, Mission.class);
        for (int i = 0; i < missions.size(); i++) {
            if(missions.get(i).id == updatedMission.id) {
                missions.set(i, updatedMission);
                return Response.ok().build();
            }
        }
        return Response.status(Response.Status.NOT_FOUND).build();
    }

    @DELETE
    @Path("/{id}")
    public Response deleteMission(@PathParam("id") int id) {
        missions.removeIf(m -> m.id == id);
        return Response.status(Response.Status.NO_CONTENT).build();
    }
}
