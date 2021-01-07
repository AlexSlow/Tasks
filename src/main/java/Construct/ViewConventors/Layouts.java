package Construct.ViewConventors;

import javafx.geometry.Pos;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

public class Layouts {
    private Layouts(){};
  public static   HBox getHBox(){
      HBox hBox=new HBox();
      hBox.setAlignment(Pos.CENTER_LEFT);
      hBox.setSpacing(5);
      return hBox;
  }
    public static   HBox getHBox(Pos pos){
        HBox hBox=new HBox();
        hBox.setAlignment(pos);
        hBox.setSpacing(5);


        return hBox;
    }

    public static   VBox getVBox(Pos pos){
        VBox vBox=new VBox();
        vBox.setAlignment(pos);
        vBox.setSpacing(5);
        return vBox;
    }
}
