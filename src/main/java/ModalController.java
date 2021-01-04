import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.layout.VBox;
import lombok.Data;



@Data
public class ModalController {
    @FXML
  private Button bt_save;
    @FXML
    private ComboBox<String>  type;
    @FXML
    private VBox mainModalVBox;

    @FXML
    public void initialize() {
    }


}
