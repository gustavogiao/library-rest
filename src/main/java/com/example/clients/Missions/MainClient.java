package com.example.clients.Missions;

public class MainClient {
    public static void main(String[] args) {

        MissionClient client = new MissionClient();

        String json = """
        {
            "id": 4343,
            "name": "Missao do caraças",
            "status": "PLANNED",
            "launch_date": "2026-04-01",
            "crewSize": 5
        }
        """;

        client.addMission(json);

        String response = client.getMissions();
        System.out.println(response);
    }
}
