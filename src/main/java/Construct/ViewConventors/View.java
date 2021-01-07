package Construct.ViewConventors;

import Construct.Factory.ConventorFactory;
import Construct.Factory.ConventorFactoryImpl;
import Construct.Models.ExpressionModels.AbstractExcpression;
import Construct.Models.MainModel.Model;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import lombok.Data;
import lombok.extern.log4j.Log4j;

import java.util.ArrayList;
import java.util.List;
@Log4j
@Data
public class View {
    private VBox mainPane;
    private List<Panable> conventors=new ArrayList<>();
    private Label name;
 //   private Model model;
    private CheckboxManager checkboxManager=new CheckboxManager();

    public void clear()
    {
        conventors.clear();
        mainPane.getChildren().clear();
        checkboxManager.clear();
    }
    public void  add(List<AbstractExcpression> excpressions)
    {
        excpressions.forEach(e->add(e));
    }
    public void add(AbstractExcpression excpression)
    {
        ConventorFactory factory=new ConventorFactoryImpl();
      Panable panable=  factory.getConventor(excpression);
      conventors.add(panable);

        mainPane.getChildren().add(panable.getPane());
     // log.info("Добавлен чекбокс"+ panable.getCheckbox().getId());
      checkboxManager.getCheckBoxList().add(panable.getCheckbox());
    }
    public List<Integer> getFireId()
    {
       return checkboxManager.getFireId();
    }

    public void repaint(List<AbstractExcpression> excpressions) {
        this.clear();
        this.add(excpressions);
    }

    public void repaint(Model model) {
        this.clear();
       repaint(model.expressions());
       this.name.setText(model.getName());
    }
    public void repaintNameConfiguration(String name)
    {
        this.name.setText(name);
    }

    public void unload()
    {
        conventors.forEach((conventor)->{
            conventor.loadValue();
        });
    }
}
