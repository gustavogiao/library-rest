package com.example.clients.Stock;

public class MainClient {

    public static void main(String[] args) {
        StockClient client = new StockClient();

        String json = """
        {
            "id": 434,
            "name": "Item do Caralho",
            "location": "Caralho",
            "quantity": 24
        }
        """;

        client.addItem(json);

        String response = client.getItems();
        System.out.println(response);
    }


}
