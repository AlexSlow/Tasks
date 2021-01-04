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
        Parent root = FXMLLoader.load(getClass().getResource("mainForm.fxml"));
        primaryStage.setTitle("Задачи");
        primaryStage.setScene(new Scene(root));
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
