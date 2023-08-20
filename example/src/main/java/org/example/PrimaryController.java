package org.example;

import javafx.fxml.FXML;
import lombok.extern.slf4j.Slf4j;
import org.github.ehayik.toolbelt.viewfx.ViewFxStageRouter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;

@Slf4j
@Controller
public class PrimaryController {

    private final String appMessage;

    private final ViewFxStageRouter stageRouter;

    public PrimaryController(@Value("${app.message}") String appMessage, ViewFxStageRouter stageRouter) {
        this.appMessage = appMessage;
        this.stageRouter = stageRouter;
    }

    @FXML
    private void switchToSecondary() {
        log.info(appMessage);
        stageRouter.navigateByUrl("/secondary");
    }
}
