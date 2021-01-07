package Construct.Modal;

import Construct.Models.ExpressionModels.AbstractExcpression;
import Construct.Models.ExpressionModels.MapExpression;
import Construct.ViewConventors.Layouts;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import lombok.Data;

import javax.validation.constraints.NotNull;


@Data
public class MapView implements SelectView {
    @NotNull
    private TextField name;
    @NotNull
    private TextField value;

    public MapView(TextField name) {
        super();
        value=new TextField();
        this.name=name;
    }
    @Override
    public void ganarate(VBox parent,HBox column) {
        parent.getChildren().clear();
        HBox hBox= Layouts.getHBox(Pos.TOP_CENTER);
        hBox.getChildren().addAll(value);
        parent.getChildren().add(hBox);

        column.getChildren().clear();
        column.getChildren().add(new Label("Значение по умолчанию"));

    }

    @Override
    public AbstractExcpression getExpession() {
        MapExpression mapExpression=new MapExpression();
        mapExpression.setName(name.getText());
        mapExpression.setValue(value.getText());
        return mapExpression;
    }

    @Override
    public String toString() {
        return "Текст";
    }
}
