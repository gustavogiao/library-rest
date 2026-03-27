package com.example.servers;

import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.servlet.ServletContextHandler;
import org.eclipse.jetty.servlet.ServletHolder;

public class MainServer {
    public static void main(String[] args) {
        
        ServletContextHandler context = new ServletContextHandler(ServletContextHandler.SESSIONS);
        context.setContextPath("/");

        Server jetty = new Server(8080);
        jetty.setHandler(context);

        ServletHolder jerseyServlet = 
            context.addServlet(org.glassfish.jersey.servlet.ServletContainer.class, "/*");

        jerseyServlet.setInitParameter(
            "jersey.config.server.provider.packages", 
            "com.example.resources");

        try {
            jetty.start();
            jetty.join();
        } catch (Exception e) {
            System.out.println("Error starting server: " + e.getMessage());
        }

    }
}
