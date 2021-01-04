package ViewConventors;

import javafx.geometry.Pos;
import javafx.scene.layout.HBox;

public class Layouts {
    private Layouts(){};
  public static   HBox getHBox(){
      HBox hBox=new HBox();
      hBox.setAlignment(Pos.CENTER_LEFT);
      hBox.setSpacing(5);
      return hBox;
  }
}
