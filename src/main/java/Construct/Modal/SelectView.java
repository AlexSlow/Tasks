package Construct.Modal;

import Construct.Models.ExpressionModels.AbstractExcpression;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

public interface SelectView {
    void ganarate(VBox parent,HBox column);
    AbstractExcpression getExpession();
}
