package Config.Property;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.stage.DirectoryChooser;
import javafx.stage.FileChooser;
import javafx.util.Pair;
import lombok.Data;

import java.io.File;
import java.nio.file.Path;
import java.util.Optional;
@Data
public class PropertyModal {
    private Dialog<Pair<String, String>> dialog;
   Optional< PathProperty> pathProperty;
    public void open()
    {
        Optional<Pair<String, String>> property=dialog.showAndWait();
        if (property.isPresent()) {
            pathProperty = Optional.of(new PathProperty());
            pathProperty.get().setPath_of_congigs(property.get().getKey());
            pathProperty.get().setPath_of_docs(property.get().getValue());
        }else{
            pathProperty=Optional.empty();
        }
    }
    public void init(PathProperty pathProperty)
    {
        dialog=new Dialog();
        dialog.setTitle("Настройки каталогов хранения файлов");
        ButtonType saveButton = new ButtonType("Сохранить", ButtonBar.ButtonData.APPLY);

        dialog.getDialogPane().getButtonTypes().addAll( saveButton,ButtonType.CANCEL);
        GridPane gridPane = new GridPane();
        gridPane.setHgap(10);
        gridPane.setVgap(10);
        gridPane.setPadding(new Insets(20, 150, 10, 10));

        TextField path_conf = new TextField();
        path_conf.setText(pathProperty.getPath_of_congigs());
        path_conf.setPromptText("Каталог конфигураций");
        gridPane.add(path_conf, 1, 0);
        Label label=new Label("Каталог конфигураций");
        gridPane.add(label,0,0);
        Button bt_conf=new Button("...");
        bt_conf.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                path_conf.setText(getDirectory());
            }
        });

        gridPane.add(bt_conf,2,0);


        TextField path_doc = new TextField();
        path_doc.setText(pathProperty.getPath_of_docs());
        path_doc.setPromptText("Каталог документов");
        gridPane.add(path_doc, 1, 1);
        Label label_doc=new Label("Каталог документов");
        gridPane.add(label_doc,0,1);

        Button bt_doc=new Button("...");
        bt_doc.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                path_doc.setText(getDirectory());
            }
        });

        gridPane.add(bt_doc,2,1);

        dialog.getDialogPane().setContent(gridPane);

        dialog.setResultConverter(dialogButton -> {

            //Вывод результатов в пару
            if (dialogButton == saveButton) {
                return new Pair<String,String>(path_conf.getText(), path_doc.getText());
            }
            return null;
        });
    }
    private String getDirectory()
    {
        DirectoryChooser directoryChooser = new DirectoryChooser();
        File file = directoryChooser.showDialog(null );
        if (file != null) {
            return file.toString();
        }
        return "";
    }

}
