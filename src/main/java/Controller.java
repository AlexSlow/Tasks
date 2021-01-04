import Factory.ConventorFactory;
import Factory.ConventorFactoryImpl;
import Factory.TypeConventor;
import Models.ExpressionModels.*;
import ViewConventors.*;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;
import lombok.Data;
import lombok.SneakyThrows;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Data
public class Controller {
    @FXML
    private VBox mainVbox;

    @FXML
    private Button bt_save;
    @FXML
    private Button bt_add;
    @FXML
    private Button bt_delete;

    private MainController mainController;

    @FXML
    public void initialize() {
        MainController mainController=new MainController();

        ModelImpl model = new ModelImpl();
        View view = new View();
        view.setMainPane(mainVbox);
        mainController.setModel(model);
        mainController.setView(view);


        MapExpression mapExpression = new MapExpression();
        mapExpression.setName("Параметр первый");
        model.add(mapExpression);
        MapExpression mapExpression2 = new MapExpression();
        mapExpression2.setName("Параметр второй");
        model.add(mapExpression2);

        List<String> stringArrayList = new ArrayList();
        stringArrayList.addAll(Arrays.asList("Параметр1", "Параметр2", "Параметр3"));
        SelectExpression selectExpression = new SelectExpression();
        selectExpression.setName("Селект выражение");
        selectExpression.setDataList(stringArrayList);
        selectExpression.setValue("Параметр2");
        model.add(selectExpression);

        LongTextExpression longTextExpression = new LongTextExpression();
        longTextExpression.setName("Примечание");
        longTextExpression.setValue(" введите текст");
        model.add(longTextExpression);
        view.repaint(model.getExcpressions());


        bt_delete.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                model.delete(view.getFireId());
                view.repaint(model.getExcpressions());
            }
        });
        bt_add.setOnAction(new EventHandler<ActionEvent>() {
            @SneakyThrows
            @Override
            public void handle(ActionEvent event) {

                Stage stage = new Stage();
                Parent root = FXMLLoader.load(
                        Controller.class.getResource("modal.fxml"));
                stage.setScene(new Scene(root));
                stage.setTitle("Окно выбора параметра");
                stage.initModality(Modality.WINDOW_MODAL);
                stage.initOwner(
                        ((Node)event.getSource()).getScene().getWindow() );
                stage.show();

            }
        });
    }

}
