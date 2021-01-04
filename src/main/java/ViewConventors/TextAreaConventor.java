package ViewConventors;

import Models.ExpressionModels.LongTextExpression;
import Models.ExpressionModels.MapExpression;
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
    @Override
    public HBox getPane() {
        HBox hBox=Layouts.getHBox();
        Label label=new Label(this.getLongTextExpression().getName());
        this.checkBox=new CheckBox();
        checkBox.setId(longTextExpression.getId().toString());
        TextArea textArea=new TextArea();
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

}
