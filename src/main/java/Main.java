import Construct.Constructor;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import lombok.extern.log4j.Log4j;

@Log4j
public class Main extends Application {
//private static Logger loger= LoggerFactory.getLogger(Main.class);
    @Override
    public void start(Stage primaryStage) throws Exception{
      //log.info("Начало работы программы");
       Parent root = FXMLLoader.load(Main.class.getResource("primaryPage.fxml"));

        primaryStage.setTitle("Конфигурации");
        primaryStage.setScene(new Scene(root));
        primaryStage.show();

      //  Constructor constructor=new Constructor();
        // constructor.open(Main.class.getResource("Constructor.fxml"));

    }

    public static void main(String[] args) {
        launch(args);
    }
}
