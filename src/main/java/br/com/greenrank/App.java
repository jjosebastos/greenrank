package br.com.greenrank;

import org.glassfish.jersey.server.ResourceConfig;

import javax.ws.rs.ApplicationPath;

@ApplicationPath("/api")
public class App extends ResourceConfig {

    public App() {
        packages("br.com.greenrank");
    }
}