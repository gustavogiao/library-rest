package com.example.resources;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import com.example.models.ItemStock;
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

@Path("/stock")
public class StockResource {

    private static final List<ItemStock> itens = new ArrayList<>();
    private static final Gson gson = new Gson();

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public String getItems(@QueryParam("lowStock") Boolean lowStock) {

        if (lowStock == null || !lowStock) {
            return gson.toJson(itens);
        }

        List<ItemStock> filtered = new ArrayList<>();

        for (ItemStock i : itens) {
            if (i.quantity < 5) {
                filtered.add(i);
            }
        }

        return gson.toJson(filtered);
    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    public Response addItem(String itemJson){
        ItemStock item = gson.fromJson(itemJson, ItemStock.class);
        item.lastUpdated = LocalDateTime.now().toString();
        itens.add(item);
        return Response.status(Response.Status.CREATED).build();
    }

    @GET
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public String getItem(@PathParam("id") int id){
        for(ItemStock i : itens){
            if(i.id == id) return gson.toJson(i);
        }
        return "{}";
    }

    @PUT
    @Consumes(MediaType.APPLICATION_JSON)
    public Response updateItem(String itemJson){
        ItemStock updatedItem = gson.fromJson(itemJson, ItemStock.class);
        for (int i = 0; i < itens.size(); i++) {
            if(itens.get(i).id == updatedItem.id){
                updatedItem.lastUpdated = LocalDateTime.now().toString();
                itens.set(i, updatedItem);
                return Response.ok().build();
            }
        }
        return Response.status(Response.Status.NOT_FOUND).build();
    }

    @DELETE
    @Path("/{id}")
    public Response deleteItem(@PathParam("id") int id) {
        itens.removeIf(i -> i.id == id);
        return Response.status(Response.Status.NO_CONTENT).build();
    }
}
