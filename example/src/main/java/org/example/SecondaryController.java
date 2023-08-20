package org.example;

import javafx.fxml.FXML;
import lombok.RequiredArgsConstructor;
import org.github.ehayik.toolbelt.viewfx.ViewFxStageRouter;
import org.springframework.stereotype.Controller;

@Controller
@RequiredArgsConstructor
public class SecondaryController {

    private final ViewFxStageRouter stageRouter;

    @FXML
    private void switchToPrimary() {
        stageRouter.navigateByUrl("/primary");
    }
}
