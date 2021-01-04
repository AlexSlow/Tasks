package ViewConventors;

import Factory.ConventorFactory;
import Factory.ConventorFactoryImpl;
import Models.ExpressionModels.AbstractExcpression;
import Models.ExpressionModels.Model;
import javafx.scene.layout.VBox;
import lombok.Data;
import lombok.extern.log4j.Log4j;

import java.util.List;
@Log4j
@Data
public class View {
    private VBox mainPane;
 //   private Model model;
    private CheckboxManager checkboxManager=new CheckboxManager();

    public void clear()
    {
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
}
