package com.example.clients.eCommerce;

public class MainClient {
    public static void main(String[] args) {
        
        eCommerceClient client = new eCommerceClient();

        String json = """
        {
            "id": 56,
            "title": "Logitech MX Keys S Mini",
            "description": "Teclado maravilhoso, lindo e brutal.",
            "price": 75.06,
            "stock": 1000
        }
        """;

        client.addProduct(json);

        String response = client.getProducts();
        System.out.println(response);
    }
}
