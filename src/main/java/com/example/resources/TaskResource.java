package com.example.resources;

import java.util.ArrayList;
import java.util.List;

import com.example.enums.TaskStatus;
import com.example.models.Task;
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

@Path("/tasks")
public class TaskResource {

    private static List<Task> tasks = new ArrayList<>();
    private static Gson gson = new Gson();

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public String getProducts() {
        return gson.toJson(tasks);
    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    public Response addTask(String taskJson){
        Task task = gson.fromJson(taskJson, Task.class);
        tasks.add(task);
        return Response.status(Response.Status.CREATED).build();
    }

    @GET
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public String getTask(@PathParam("id") int id) {
        for(Task t : tasks){
            if(t.id == id) return gson.toJson(t);
        }
        return "{}";
    }

    @DELETE
    @Path("/{id}")
    public Response deleteTask(@PathParam("id") int id) {
        tasks.removeIf(t -> t.id == id);
        return Response.status(Response.Status.NO_CONTENT).build();
    }

    @PUT
    @Consumes(MediaType.APPLICATION_JSON)
    public Response updateTask(String taskJson) {
        Task updatedTask = gson.fromJson(taskJson, Task.class);
        for (int i = 0; i < tasks.size(); i++) {
            if(tasks.get(i).id == updatedTask.id){
                tasks.set(i, updatedTask);
                return Response.ok().build();
            }
        }
        return Response.status(Response.Status.NOT_FOUND).build();
    }

    @GET
    @Path("/filter")
    @Produces(MediaType.APPLICATION_JSON)
    public String filterByStatus(@QueryParam("status") TaskStatus status) {
        
        List<Task> filtered = new ArrayList<>();

        for(Task t : tasks){
            if(t.status == status) {
                filtered.add(t);
            }
        }
        return gson.toJson(filtered);
    }
}
