package Construct.Models.ExpressionModels;

import Construct.Models.MainModel.Model;
import Construct.Models.MainModel.ModelImpl;
import Construct.ViewConventors.View;
import lombok.Data;

@Data
public class MainController {
   private Model model;
   private View view;
   public void repaint()
   {
       view.repaint(model);
   }
   public void initModel(String name)
   {
   model=new ModelImpl();
   model.setName(name);
   }
}
