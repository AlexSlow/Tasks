package ViewConventors;


import javafx.scene.Parent;
import javafx.scene.control.CheckBox;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;

public interface Panable {
     Parent getPane();
     CheckBox getCheckbox();
}
