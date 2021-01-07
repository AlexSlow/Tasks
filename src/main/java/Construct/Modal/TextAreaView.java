package Construct.Modal;

import Construct.Models.ExpressionModels.AbstractExcpression;
import Construct.Models.ExpressionModels.LongTextExpression;
import Construct.Models.ExpressionModels.MapExpression;
import Construct.ViewConventors.Layouts;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

import javax.validation.constraints.NotNull;

public class TextAreaView implements SelectView {
    @NotNull
    private TextField name;
    @NotNull
    private TextArea value;


    public TextAreaView(TextField name) {
        super();
        this.name=name;
        value = new TextArea();
    }

    @Override
    public void ganarate(VBox parent,HBox column) {
        parent.getChildren().clear();
        HBox hBox = Layouts.getHBox(Pos.TOP_CENTER);
        hBox.getChildren().addAll(value);
        parent.getChildren().add(hBox);

        column.getChildren().clear();
        column.getChildren().add(new Label("Значение по умолчанию"));
    }

    @Override
    public AbstractExcpression getExpession() {
        LongTextExpression longTextExpression = new LongTextExpression();
        longTextExpression.setName(name.getText());
        longTextExpression.setValue(value.getText());
        return longTextExpression;
    }

    @Override
    public String toString() {
        return "Текстовая область";
    }
}
