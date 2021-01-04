package ViewConventors;

import Models.ExpressionModels.SelectExpression;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import lombok.Data;

import javax.validation.constraints.NotNull;
@Data
public class SelectConventor implements Panable {
    @NotNull
    private SelectExpression selectExpression;
    private CheckBox checkBox;
    @Override
    public HBox getPane() {
        // return null;

        this.checkBox=new CheckBox();
        checkBox.setId(selectExpression.getId().toString());

        Label label=new Label(this.getSelectExpression().getName());
        ComboBox<String> combo=new ComboBox<>();
        ObservableList list = FXCollections.observableArrayList(selectExpression.getDataList());
        combo.setItems(list);
        combo.setValue(selectExpression.getValue());
        HBox hbox=Layouts.getHBox();
        hbox.setId(selectExpression.getId().toString());
        hbox.getChildren().addAll(checkBox,label,combo);
        return hbox;
    }

    @Override
    public CheckBox getCheckbox() {
        return checkBox;
    }

}
