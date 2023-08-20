package org.example;

import javafx.application.Application;
import org.github.ehayik.toolbelt.viewfx.ViewFxApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class ExampleApplication {

    public static void main(String[] args) {
        System.setProperty("viewfx.application-class-name", ExampleApplication.class.getName());
        Application.launch(ViewFxApplication.class, args);
    }
}
