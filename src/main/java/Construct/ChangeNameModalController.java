package Construct;

import Construct.Models.ExpressionModels.MainController;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import lombok.Data;

@Data
public class ChangeNameModalController {
    @FXML
    private TextField new_name_configuration;
    @FXML private Button bt_save;
    private MainController mainController;

    @FXML
    public void initialize() {
        bt_save.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                mainController.getModel().getConfigurationMap().setConfigName(new_name_configuration.getText());
                mainController.getView().repaintNameConfiguration(new_name_configuration.getText());
                Stage stage = (Stage) bt_save.getScene().getWindow();
                stage.close();
            }
        });
    }


}
