package com.example.clients.Stock;

import jakarta.ws.rs.client.Client;
import jakarta.ws.rs.client.ClientBuilder;
import jakarta.ws.rs.client.Entity;
import jakarta.ws.rs.client.WebTarget;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

public class StockClient {

    private Client client;
    private WebTarget target;

    public StockClient(){
        client = ClientBuilder.newClient();
        target = client.target("http://localhost:8080/stock");
    }

    public String getItems() {
        Response r = target.request()
                .accept(MediaType.APPLICATION_JSON)
                .get();
        
        if(r.getStatus() == 200) {
            return r.readEntity(String.class);
        }
        return "Error";
    }

    public void addItem(String json) {
        target.request()
                .post(Entity.entity(json, MediaType.APPLICATION_JSON));
    }
}
