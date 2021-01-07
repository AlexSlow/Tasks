package Construct.ViewConventors;

import Construct.Models.ExpressionModels.MapExpression;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import lombok.Data;

import javax.validation.constraints.NotNull;


@Data
public class MapConventor implements Panable {
    @NotNull
    private MapExpression mapExpression;
    private CheckBox checkBox;
    private TextField textField;
    @Override
    public HBox getPane() {
       // return null;
        Label label=new Label(this.getMapExpression().getName());
        this.checkBox=new CheckBox();

        checkBox.setId(mapExpression.getId().toString());
        textField=new TextField();
        textField.setText(mapExpression.getValue());
        HBox hbox=Layouts.getHBox();
        hbox.setId(mapExpression.getId().toString());
        hbox.getChildren().addAll(checkBox,label,textField);
        label.setLabelFor(checkBox);
        return hbox;
    }

    @Override
    public CheckBox getCheckbox() {
        return checkBox;
    }

    @Override
    public void loadValue() {
        this.mapExpression.setValue(this.textField.getText());
    }
}
