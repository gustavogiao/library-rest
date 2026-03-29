package com.example.clients.Weather;

public class MainClient {

    public static void main(String[] args) {

        WeatherClient client = new WeatherClient();

        String json = """
        {
          "id": 1,
          "city": "Aveiro",
          "temperature": 22.5,
          "humidity": 60
        }
        """;

        client.addWeather(json);

        System.out.println("All: ");
        System.out.println(client.getAll());

        System.out.println("Filtered By City: ");
        System.out.println(client.getByCity("Aveiro"));
    }
}
