package Construct;

import Construct.Modal.MapView;
import Construct.Modal.SelectView;
import Construct.Modal.SelectViewImpl;
import Construct.Modal.TextAreaView;
import Construct.Models.ExpressionModels.AbstractExcpression;
import Construct.Models.ExpressionModels.MainController;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import lombok.Data;


@Data
public class ModalController {
    private MainController mainController;
    @FXML
  private Button bt_save;
    @FXML
    private ComboBox<SelectView>  combo_type;
    @FXML
    private VBox mainModalVBox;
    @FXML
    private TextField name_param;
    @FXML
    private HBox HboxColumn;
    @FXML
    public void initialize() {
        MapView mapView=new MapView(name_param);
        TextAreaView textAreaView=new TextAreaView(name_param);
        SelectViewImpl selectView=new SelectViewImpl(name_param);
        ObservableList<SelectView> list=FXCollections.observableArrayList(mapView,textAreaView,selectView);
        combo_type.setItems(list);
        combo_type.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                combo_type.getValue().ganarate(mainModalVBox,HboxColumn);
            }

    });
        bt_save.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                AbstractExcpression abstractExcpression=combo_type.getValue().getExpession();
                mainController.getModel().add(abstractExcpression);
                mainController.getView().add(abstractExcpression);

                Stage stage = (Stage) bt_save.getScene().getWindow();
                stage.close();

            }
        });
}

}
