package com.example.clients.Book;

public class MainClient {
    public static void main(String[] args) {

        BookClient client = new BookClient();

        // Criar livro
        String json = """
        {
          "id": 3,
          "title": "REST API",
          "author": "Aluno",
          "year": 2026
        }
        """;

        client.addBook(json);

        // Ver livros
        String response = client.getBooks();
        System.out.println(response);
    }
}
