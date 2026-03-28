package com.example.resources;

import java.util.ArrayList;
import java.util.List;

import com.example.models.Product;
import com.google.gson.Gson;

import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.DELETE;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.PUT;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

@Path("/products")
public class eCommerceResource {

    private static final List<Product> products = new ArrayList<>();
    private static final Gson gson = new Gson();

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public String getProducts() {
        return gson.toJson(products);
    }

    @POST
    @Produces(MediaType.APPLICATION_JSON)
    public Response addProduct(String productJson){
        Product product = gson.fromJson(productJson, Product.class);
        products.add(product);
        return Response.status(Response.Status.CREATED).build();
    }

    @GET
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public String getProduct(@PathParam("id") int id) {
        for(Product p : products){
            if(p.id == id) return gson.toJson(p);
        }
        return "{}";
    }

    @DELETE
    @Path("/{id}")
    public Response deleteProduct(@PathParam("id") int id) {
        products.removeIf(p -> p.id == id);
        return Response.status(Response.Status.NO_CONTENT).build();
    }

    @PUT
    @Consumes(MediaType.APPLICATION_JSON)
    public Response updateProduct(String productJson) {
        Product updatedProduct = gson.fromJson(productJson, Product.class);
        for (int i = 0; i < products.size(); i++) {
            if(products.get(i).id == updatedProduct.id) {
                products.set(i, updatedProduct);
                return Response.ok().build();
            }
        }
        return Response.status(Response.Status.NOT_FOUND).build();
    }
}
