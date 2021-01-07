package Construct.Modal;

import Construct.Models.ExpressionModels.AbstractExcpression;
import Construct.Models.ExpressionModels.SelectExpression;
import Construct.ViewConventors.Layouts;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.control.*;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;


@Data
public class SelectViewImpl implements SelectView {
    private TextField name;
    private VBox table;
    private BorderPane borderPane;
    private ScrollPane scrollPane;
    private Button delete,add;
    private List<TextField> values;
    private List<CheckBox> checkBoxes;

   public SelectViewImpl(TextField name)
   {
       this.name=name;
       scrollPane=new ScrollPane();
       scrollPane.setFitToHeight(true);
       scrollPane.setFitToWidth(true);
       values=new ArrayList<>();
       checkBoxes=new ArrayList<>();
       table=Layouts.getVBox(Pos.CENTER);
       borderPane=new BorderPane();
       delete=new Button("Удалить");
       add=new Button("Добавить");

       HBox foot= Layouts.getHBox();
       foot.getChildren().addAll(add,delete);

       scrollPane.setContent(table);
       borderPane.setBottom(foot);
       borderPane.setCenter(scrollPane);

       add.setOnAction(new EventHandler<ActionEvent>() {
           @Override
           public void handle(ActionEvent event) {
               HBox hBox=Layouts.getHBox(Pos.TOP_CENTER);
               CheckBox checkBox=new CheckBox();
               checkBoxes.add(checkBox);
               TextField textField=new TextField();
               values.add(textField);
               hBox.getChildren().add(checkBox);
               hBox.getChildren().add(textField);
               table.getChildren().add(hBox);
           }
       });
       delete.setOnAction(new EventHandler<ActionEvent>() {
           private List<HBox> hBoxes;
           @Override
           public void handle(ActionEvent event) {
               hBoxes=new ArrayList<>();
               List<TextField> textFields=new ArrayList<>();
              checkBoxes.forEach(checkBox -> {
                  if (checkBox.isSelected())
                  {
                      HBox parant=(HBox) checkBox.getParent();
                      hBoxes.add(parant);
                      //Опасно?!
                      TextField textField=(TextField) parant.getChildren().get(1);
                      textFields.add(textField);
                  }
              });
              //!!!!!!!!!!!!!!!!!!!!!Тут опасная зависимость!
             ScrollPane scrollPane=(ScrollPane) borderPane.getCenter();
             VBox vBox=(VBox) scrollPane.getContent();
             vBox.getChildren().removeAll(hBoxes);
             values.removeAll(textFields);
           }
       });

   }
    @Override
    public void ganarate(VBox parent,HBox column) {
        parent.getChildren().clear();
        parent.getChildren().add(borderPane);
        column.getChildren().clear();
        column.getChildren().addAll(new Label(" "),new Label("Значения для выбора"));
    }
    @Override
    public AbstractExcpression getExpession() {
        SelectExpression selectExpression=new SelectExpression();
        selectExpression.setName(name.getText());
        List<String > dataList=new ArrayList<>();
        for(TextField textField:values) dataList.add(textField.getText());
        selectExpression.setDataList(dataList);

        CheckBox checkBox=null;
        Integer index=null;
        for(int i=0;i<checkBoxes.size();i++)
        {
            if (checkBoxes.get(i).isSelected()) {index=i; break;}
        }

        if (index!=null) {
          selectExpression.setValue(values.get(index).getText());
        }
        return selectExpression;
    }
    @Override
    public String toString() {
        return "Поле выбора";
    }
}
