import Config.ConfigurationMap;
import Config.Loaders.FileTableLoader;
import Config.Loaders.TableLoader;
import Config.Property.PathProperty;
import Config.Property.PropertyLoader;
import Config.Property.PropertyModal;
import Construct.Constructor;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.JavaFXBuilderFactory;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Modality;
import javafx.stage.Stage;
import lombok.Data;
import lombok.SneakyThrows;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URL;
import java.util.Optional;


@Data
public class PrimaryController {
    @FXML
    private TableView<ConfigurationMap> table;
    @FXML
    private Button bt_create_new_configuration;
    @FXML
    private Button bt_save_changes;
    private ObservableList<ConfigurationMap> configurations;
    private TableLoader tableLoader;
    private Optional<PathProperty> pathProperty;
    @FXML
    private Button open_constuctor;

    @FXML
    private Button bt_delete_conf;

    @FXML
    private MenuItem settingsMenuItem;

    @FXML
    public void initialize() throws IOException {
        //Получить путь сохранения конфигураций и документов
        PropertyLoader propertyLoader=new PropertyLoader();
        pathProperty=Optional.of(propertyLoader.getProperty());

        setCollumns();
        tableLoader = new FileTableLoader("Configuration.txt");
        configurations = FXCollections.observableArrayList(tableLoader.load());
        table.setItems(configurations);

        table.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);
        bt_create_new_configuration.setOnAction(new EventHandler<ActionEvent>() {
            @SneakyThrows
            @Override
            public void handle(ActionEvent event) {
                Stage stage = new Stage();
                FXMLLoader loader = new FXMLLoader();
                loader.setBuilderFactory(new JavaFXBuilderFactory());
                URL location = getClass().getResource("modalCreateConfig.fxml");
                Parent root = loader.load(location.openStream());

                stage.setScene(new Scene(root));
                stage.setTitle("Создание конфигурации");
                stage.initModality(Modality.WINDOW_MODAL);
                stage.initOwner(((Node) event.getSource()).getScene().getWindow());
                CreateConfigController createConfigController = loader.getController();
                createConfigController.setPathProperty(pathProperty);
                createConfigController.setConfigurationMapList(configurations);
                createConfigController.init();
                stage.showAndWait();
                table.refresh();
            }
        });

        bt_save_changes.setOnAction(new EventHandler<ActionEvent>() {
            @SneakyThrows
            @Override
            public void handle(ActionEvent event) {
                tableLoader.unload(configurations);
            }
        });

        bt_delete_conf.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
           ObservableList<ConfigurationMap> configurationMaps= table.getSelectionModel().getSelectedItems();
           configurations.removeAll(configurationMaps);
            }

        });

        settingsMenuItem.setOnAction(new EventHandler<ActionEvent>() {
            @SneakyThrows
            @Override
            public void handle(ActionEvent event) {
                //Открыть модальное окно
                PropertyModal propertyModal=new PropertyModal();
                PropertyLoader propertyLoader=new PropertyLoader();

                propertyModal.init(pathProperty.get());
                propertyModal.open();
                Optional<PathProperty> pathPropertyBuffer=propertyModal.getPathProperty();
                if (pathPropertyBuffer.isPresent())
                {
                    propertyLoader.setProperty(pathPropertyBuffer.get());
                  pathProperty= pathPropertyBuffer;
                }
            }
        });

        open_constuctor.setOnAction(new EventHandler<ActionEvent>() {
            @SneakyThrows
            @Override
            public void handle(ActionEvent event) {

              ConfigurationMap configurationMap= table.getSelectionModel().getSelectedItem();
              if (configurationMap!=null)
              {
                  Constructor constructor=new Constructor(configurationMap);
                  constructor.open("/Constructor.fxml");
                  table.refresh();
              }

            }
        });


    }
    void setCollumns()
    {
        // столбец для вывода имени
        TableColumn<ConfigurationMap, String> nameColumn = new TableColumn<ConfigurationMap, String>("Название конфигурации");
        // определяем фабрику для столбца с привязкой к свойству name
        nameColumn.setCellValueFactory(new PropertyValueFactory<ConfigurationMap, String>("configName"));
        // добавляем столбец
        nameColumn.setPrefWidth(200);
        table.getColumns().add(nameColumn);

        // столбец для вывода имени
        TableColumn<ConfigurationMap, String> fileColumn = new TableColumn<ConfigurationMap, String>("База");
        // определяем фабрику для столбца с привязкой к свойству name
        fileColumn.setCellValueFactory(new PropertyValueFactory<ConfigurationMap, String>("file"));
        // добавляем столбец
        fileColumn.setPrefWidth(200);
        table.getColumns().add(fileColumn);
    }



}
