package org.github.ehayik.toolbelt.viewfx;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import lombok.RequiredArgsConstructor;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationListener;

@RequiredArgsConstructor
class ViewFxStageInitializer implements ApplicationListener<StageReadyEvent>, ViewFxStageRouter {

    private Scene mainScene;
    private final ViewFxProperties viewFxProperties;
    private final ApplicationContext applicationContext;

    @Override
    public void onApplicationEvent(StageReadyEvent event) {
        var parent = loadFXML(viewFxProperties.getIndexViewUrl());
        mainScene = new Scene(parent, viewFxProperties.getWidth(), viewFxProperties.getHeight());

        var stage = event.getStage();
        stage.setTitle(viewFxProperties.getTitle());
        stage.setScene(mainScene);
        stage.show();
    }

    private Parent loadFXML(String fxml) {
        try {
            var fxmlLoader = new FXMLLoader(ViewFxStageInitializer.class.getResource(fxml + ".fxml"));
            fxmlLoader.setControllerFactory(applicationContext::getBean);
            return fxmlLoader.load();
        } catch (Exception ex) {
            throw new IllegalStateException("It is not possible to set %s as Scene Root.".formatted(fxml), ex);
        }
    }

    @Override
    public void navigateByUrl(String url) {

        if (mainScene == null) {
            throw new IllegalArgumentException("Scene is not ready yet.");
        }

        mainScene.setRoot(loadFXML(url));
    }
}
