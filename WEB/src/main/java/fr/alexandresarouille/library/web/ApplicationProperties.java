package fr.alexandresarouille.library.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class ApplicationProperties {

    @Autowired
    private Environment environment;

    public String getRestHostAddress() {
        return  Optional.ofNullable(environment.getProperty("properties.rest.host")).orElse("http://localhost:"+getRestHostPort());
    }

    public int getRestHostPort() {
        return Optional.ofNullable(environment.getProperty("properties.rest.port", Integer.class)).orElse(8080);
    }
}
