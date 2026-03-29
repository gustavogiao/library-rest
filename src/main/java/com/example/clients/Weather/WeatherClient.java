package com.example.clients.Weather;

import jakarta.ws.rs.client.Client;
import jakarta.ws.rs.client.ClientBuilder;
import jakarta.ws.rs.client.Entity;
import jakarta.ws.rs.client.WebTarget;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

public class WeatherClient {
    
    private final Client client;
    private final WebTarget target;

    public WeatherClient(){
        client = ClientBuilder.newClient();
        target = client.target("http://localhost:8080/weather");
    }

    public void addWeather(String json){
        target.request()
                .post(Entity.entity(json, MediaType.APPLICATION_JSON));
    }

    public String getAll() {
        Response r = target.request().get();
        return r.readEntity(String.class);
    }

     public String getByCity(String city) {
        Response r = target
                .queryParam("city", city)
                .request()
                .get();

        return r.readEntity(String.class);
    }
}
