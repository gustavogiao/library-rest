package com.example.clients.Missions;

import jakarta.ws.rs.client.Client;
import jakarta.ws.rs.client.ClientBuilder;
import jakarta.ws.rs.client.Entity;
import jakarta.ws.rs.client.WebTarget;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

public class MissionClient {
    private final Client client;
    private final WebTarget target;

    public MissionClient(){
        client = ClientBuilder.newClient();
        target = client.target("http://localhost:8080/missions");
    }

    public String getMissions() {
        Response r = target.request()
                .accept(MediaType.APPLICATION_JSON)
                .get();
        
        if(r.getStatus() == 200) {
            return r.readEntity(String.class);
        }
        return "Error";
    }

    public void addMission(String json) {
        target.request()
                .post(Entity.entity(json, MediaType.APPLICATION_JSON));
    }

}
