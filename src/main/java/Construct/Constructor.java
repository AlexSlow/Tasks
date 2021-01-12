package Construct;

import Config.ConfigurationMap;
import Construct.Models.MainModel.Model;
import Serializators.FileProgrammSerializator;
import Serializators.Serializator;
import javafx.fxml.FXMLLoader;
import javafx.fxml.JavaFXBuilderFactory;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import lombok.Data;
import lombok.extern.log4j.Log4j;
import sun.tools.jar.Main;

import java.io.IOException;
import java.net.URL;
import java.nio.file.OpenOption;
import java.nio.file.Path;
import java.util.Optional;
@Log4j
@Data
/**
 * Открывает конструктор
 */
public class Constructor {
    private ConfigurationMap configurationMap;

    public Constructor(ConfigurationMap config)
    {
        this.configurationMap=config;
    }
    public void open(String resourse) throws Exception {
        Stage stage=new Stage();
        FXMLLoader loader = new FXMLLoader();
        loader.setBuilderFactory(new JavaFXBuilderFactory());
        //  Parent root = loader.load(resourse.openStream());
        URL location = getClass().getResource(resourse);
        Parent root = loader.load(location.openStream());

        stage.setTitle("Задачи");
        stage.setScene(new Scene(root));

        Serializator serializator= new FileProgrammSerializator(configurationMap);
        Optional<Model> modelOptional= serializator.deserialize();
        modelOptional.get().setConfigurationMap(configurationMap);
        Controller controller = loader.getController();
        controller.setModel(modelOptional.get());
        controller.initModel();
        stage.showAndWait();
    }

}
