package Construct.ViewConventors;

import Construct.Models.ExpressionModels.LongTextExpression;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import lombok.Data;

import javax.validation.constraints.NotNull;


@Data
public class TextAreaConventor implements Panable {

    @NotNull
    private LongTextExpression longTextExpression;
    private CheckBox checkBox;
    private TextArea textArea;
    @Override
    public HBox getPane() {
        HBox hBox=Layouts.getHBox();
        Label label=new Label(this.getLongTextExpression().getName());
        this.checkBox=new CheckBox();
        label.setLabelFor(checkBox);
        checkBox.setId(longTextExpression.getId().toString());
        textArea=new TextArea();
        textArea.setText(longTextExpression.getValue());
        VBox vbox=new VBox();
        vbox.setId(longTextExpression.getId().toString());
        hBox.getChildren().addAll(checkBox,vbox);
        vbox.getChildren().addAll(label,textArea);
        return hBox;
    }

    @Override
    public CheckBox getCheckbox() {
        return checkBox;
    }

    @Override
    public void loadValue() {
        this.longTextExpression.setValue(this.textArea.getText());
    }

}
