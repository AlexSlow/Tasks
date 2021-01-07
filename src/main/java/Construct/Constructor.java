package Construct;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import lombok.Data;
import sun.tools.jar.Main;

import java.io.IOException;
import java.net.URL;
import java.nio.file.Path;
@Data
public class Constructor {
    private Path config;

    public void open(URL resourse) throws IOException {
        Stage stage=new Stage();
        Parent root = FXMLLoader.load(resourse);
        stage.setTitle("Задачи");
        stage.setScene(new Scene(root));
        stage.show();
    }

}
