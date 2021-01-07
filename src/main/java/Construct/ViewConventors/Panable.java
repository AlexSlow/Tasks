package Construct.ViewConventors;


import javafx.scene.Parent;
import javafx.scene.control.CheckBox;

public interface Panable {
     Parent getPane();
     CheckBox getCheckbox();
     void loadValue();
}
