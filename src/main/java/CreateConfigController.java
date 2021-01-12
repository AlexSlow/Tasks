import Config.ConfigurationMap;
import Config.Property.PathProperty;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.stage.DirectoryChooser;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import lombok.Data;

import java.io.File;
import java.io.FileNotFoundException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.Optional;


@Data
public class CreateConfigController {
    @FXML
    private TextField tb_file;
    @FXML
    private Button bt_file_chooser;
    @FXML
    private TextField nameConfiguration;
    @FXML
    private Button bt_save;
    //private File file;
    private Optional<PathProperty> pathProperty;
    private ObservableList<ConfigurationMap> configurationMapList;

    public void init()
    {
        tb_file.setText(pathProperty.get().getPath_of_congigs());
    }
    @FXML
    public void initialize()  {

        bt_file_chooser.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                DirectoryChooser directoryChooser = new DirectoryChooser();
                File file = directoryChooser.showDialog(null );
                if (file != null) {
                tb_file.setText(file.toString());
            }
        }
    });

        bt_save.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                if (!nameConfiguration.getText().isEmpty())
                {
                    if (tb_file==null) return;
                    Path pathConf= Paths.get(tb_file.getText());
                    pathConf=pathConf.resolve(nameConfiguration.getText());
                    configurationMapList.add(new ConfigurationMap(nameConfiguration.getText(),pathConf));
                }
                Stage stage = (Stage) bt_save.getScene().getWindow();
                stage.close();
            }
        });


}
}
