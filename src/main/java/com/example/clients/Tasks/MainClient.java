package com.example.clients.Tasks;

public class MainClient {
    public static void main(String[] args) {
        TaskClient client = new TaskClient();

        String json = """
                {
                "id": 5454,
                "title": "Fazer projeto",
                "description": "Implementar API",
                "status": "IN_PROGRESS",
                "dueDate": "2026-04-01"
                }
                """;
        
        client.addTask(json);
        
        String response = client.getTasks();
        System.out.println(response);
    }
}
