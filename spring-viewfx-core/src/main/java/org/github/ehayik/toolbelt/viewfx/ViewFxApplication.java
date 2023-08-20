package org.github.ehayik.toolbelt.viewfx;

import javafx.application.Application;
import javafx.application.HostServices;
import javafx.application.Platform;
import javafx.stage.Stage;
import lombok.SneakyThrows;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.context.ApplicationContextInitializer;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.support.GenericApplicationContext;

import static org.springframework.boot.WebApplicationType.NONE;

public class ViewFxApplication extends Application {

    private ConfigurableApplicationContext applicationContext;

    @Override
    public void init() {
        applicationContext = new SpringApplicationBuilder()
                .web(NONE)
                .sources(loadSpringApplicationClass())
                .initializers(newApplicationContextInitializer(this))
                .run(getParameters().getRaw().toArray(new String[0]));
    }

    @SneakyThrows
    private static Class<?> loadSpringApplicationClass() {
        return Class.forName(System.getProperty("viewfx.application-class-name"));
    }

    private static ApplicationContextInitializer<GenericApplicationContext> newApplicationContextInitializer(
            Application application) {
        return ac -> {
            ac.registerBean(Application.class, () -> application);
            ac.registerBean(Parameters.class, application::getParameters);
            ac.registerBean(HostServices.class, application::getHostServices);
        };
    }

    @Override
    public void start(Stage primaryStage) {
        applicationContext.publishEvent(new StageReadyEvent(primaryStage));
    }

    @Override
    public void stop() {
        applicationContext.close();
        Platform.exit();
    }
}
