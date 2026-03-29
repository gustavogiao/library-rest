package com.example.resources;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import com.example.models.Weather;
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

@Path("/weather")
public class WeatherResource {
    
    private static final List<Weather> data = new ArrayList<>();
    private static final Gson gson = new Gson();

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public String getWeather(@QueryParam("city") String city) {
        
        if(city == null){
            return gson.toJson(data);
        }

        List<Weather> filtered = new ArrayList<>();

        for(Weather w : data) {
            if(w.city.equalsIgnoreCase(city)){
                filtered.add(w);
            }
        }

        return gson.toJson(filtered);
    }

    @GET
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public String getWeatherById(@PathParam("id") int id) {
        for(Weather w : data) {
            if(w.id == id) return gson.toJson(w); 
        }
        return "{}";
    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    public Response addWeather(String json){
        Weather w = gson.fromJson(json, Weather.class);
        w.timestamp = LocalDateTime.now().toString();
        data.add(w);
        return Response.status(Response.Status.CREATED).build();
    }

    @PUT
    @Path("/{id}")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response updateWeather(@PathParam("id") int id, String json){
        Weather updated = gson.fromJson(json, Weather.class);
        for (int i = 0; i < data.size(); i++) {
            if(data.get(i).id == id){
                updated.id = id;
                updated.timestamp = LocalDateTime.now().toString();
                data.set(i, updated);
                return Response.ok().build();
            }
        }
        return Response.status(Response.Status.NOT_FOUND).build();
    }

    @DELETE
    @Path("/{id}")
    public Response deleteWeather(@PathParam("id") int id){
        data.removeIf(d -> d.id == id);
        return Response.status(Response.Status.NO_CONTENT).build();
    }

}
