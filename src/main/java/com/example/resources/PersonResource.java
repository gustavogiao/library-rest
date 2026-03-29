package com.example.resources;

import java.util.ArrayList;
import java.util.List;

import com.example.models.fitness.Activity;
import com.example.models.fitness.Person;
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

@Path("/person")
public class PersonResource {
    private static final List<Person> people = new ArrayList<>();
    private static final Gson gson = new Gson();

    @GET
    @Path("/{id}/name")
    public String getName(@PathParam("id") int id){
        for(Person p : people){
            if(p.id == id) return p.name;
        }
        return "Not found";
    }

    @GET
    @Path("/{id}/age")
    public String getAge(@PathParam("id") int id){
        for(Person p : people){
            if(p.id == id) return String.valueOf(p.age);
        }
        return "Not found";
    }

    @GET
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public String getPerson(@PathParam("id") int id){
        for(Person p : people){
            if(p.id == id) return gson.toJson(p);
        }
        return "{}";
    }

    @GET
    @Path("/{id}/activity/{idact}")
    @Produces(MediaType.APPLICATION_JSON)
    public String getActivity(@PathParam("id") int id,
                              @PathParam("idact") int idact) {
        for(Person p : people){
            if (p.id == id) {
                for(Activity a : p.activities){
                    if(a.id == idact) return gson.toJson(a);
                }
            }
        }
        return "{}";
    }

    @GET
    @Path("/{id}/activity")
    @Produces(MediaType.APPLICATION_JSON)
    public String getActivityQuery(@PathParam("id") int id,
                                   @QueryParam("idact") int idact) {
        for(Person p : people){
            if(p.id == id){
                for(Activity a : p.activities){
                    if(a.id == idact) return gson.toJson(a);
                }
            }
        }
        return "{}";
    }

    @PUT
    @Path("/{id}/edit")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response updatePerson(@PathParam("id") int id, String json){
        Person updatedPerson = gson.fromJson(json, Person.class);

        for (int i = 0; i < people.size(); i++) {
            if(people.get(i).id == id){
                updatedPerson.id = id;
                people.set(i, updatedPerson);
                return Response.ok().build();
            }
        }
        return Response.status(Response.Status.NOT_FOUND).build();
    }

    @DELETE
    @Path("/{id}/activities")
    public Response deleteActivities(@PathParam("id") int id) {
        for(Person p : people){
            if(p.id == id){
                p.activities.clear();
                return Response.ok().build();
            }
        }
        return Response.status(Response.Status.NOT_FOUND).build();
    }

    @POST
    @Path("/{id}/add")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response addActivity(@PathParam("id") int id, String json) {

        Activity a = gson.fromJson(json, Activity.class);

        for (Person p : people) {
            if (p.id == id) {
                p.activities.add(a);
                return Response.status(Response.Status.CREATED).build();
            }
        }

        return Response.status(Response.Status.NOT_FOUND).build();
    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    public Response addPerson(String json) {
        Person p = gson.fromJson(json, Person.class);
        people.add(p);
        return Response.status(Response.Status.CREATED).build();
    }

}
