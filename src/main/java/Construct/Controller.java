package Construct;

import Config.ConfigurationMap;
import Construct.Models.ExpressionModels.*;
import Construct.Models.MainModel.Model;
import Serializators.FileProgrammSerializator;
import Serializators.FileSerializator;
import Serializators.Serializator;
import Construct.ViewConventors.*;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.JavaFXBuilderFactory;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.MenuItem;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;
import lombok.Data;
import lombok.SneakyThrows;

import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

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

    @FXML
    MenuItem save_in_file;
    @FXML
    private Label name_configuration;
    @FXML
    private MenuItem load_configuraton;
    private MainController mainController;


    private Model model;
    private View view;

    public void initModel()
    {
        //Скорее всего будет передаваться извне
        MainController mainController=new MainController();
        view = new View();
        view.setMainPane(mainVbox);
        view.setName(name_configuration);
        mainController.setView(view);
        mainController.setModel(model);
        mainController.repaint();


        save_in_file.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                Serializator serializator=new FileSerializator();
                serializator.serialize(mainController.getModel());
            }
        });
        load_configuraton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                Serializator serializator=new FileSerializator();
                Optional<Model> model= serializator.deserialize();
                if (model.isPresent())
                    mainController.setModel(model.get());
                mainController.repaint();
            }
        });
        bt_delete.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                mainController.getModel().delete(view.getFireId());
                //view.repaint(mainController.getModel());
                mainController.repaint();
            }
        });
        bt_add.setOnAction(new EventHandler<ActionEvent>() {
            @SneakyThrows
            @Override
            public void handle(ActionEvent event) {

                Stage stage = new Stage();
                FXMLLoader loader=new FXMLLoader();
                loader.setBuilderFactory(new JavaFXBuilderFactory());
                URL location = getClass().getResource("../modal.fxml");
                Parent root = loader.load(location.openStream());

                stage.setScene(new Scene(root));
                stage.setTitle("Окно выбора параметра");
                stage.initModality(Modality.WINDOW_MODAL);
                stage.initOwner(((Node)event.getSource()).getScene().getWindow());
                ModalController modalController=loader.getController();
                modalController.setMainController(mainController);
                stage.showAndWait();

                mainController.getView().unload();

            }
        });

        bt_save.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                //mainController.getView().unload();
                Serializator serializator=new FileProgrammSerializator(mainController.getModel().getConfigurationMap());
                serializator.serialize(mainController.getModel());
            }
        });

        name_configuration.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @SneakyThrows
            @Override
            public void handle(MouseEvent mouseEvent) {
                if(mouseEvent.getButton().equals(MouseButton.PRIMARY)){

                    if(mouseEvent.getClickCount() == 2){
                        Stage stage = new Stage();
                        FXMLLoader loader=new FXMLLoader();
                        loader.setBuilderFactory(new JavaFXBuilderFactory());
                        URL location = getClass().getResource("../modalChangeNameConf.fxml");
                        Parent root = loader.load(location.openStream());

                        stage.setScene(new Scene(root));
                        stage.setTitle("Окно изменения названия конфигурации");
                        stage.initModality(Modality.WINDOW_MODAL);
                        stage.initOwner(((Node)mouseEvent.getSource()).getScene().getWindow());
                        ChangeNameModalController changeNameModalController=loader.getController();
                        changeNameModalController.setMainController(mainController);
                        stage.showAndWait();

                    }
                }
            }
        });
    }
    @FXML
    public void initialize() {
    }

}
