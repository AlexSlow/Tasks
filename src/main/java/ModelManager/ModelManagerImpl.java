package ModelManager;

import Construct.Models.MainModel.Model;
import Serializators.Serializator;

import javax.validation.constraints.NotNull;
import java.util.Optional;

public class ModelManagerImpl implements ModelManager {
     private Model model;
     @NotNull
     private  Serializator serializator;
     public ModelManagerImpl(Serializator serializator)
     {
         this.serializator=serializator;
     }
    @Override
    public Optional<Model> load() throws Exception {
         if (serializator==null) throw  new Exception("Не задан сериализатор");
       Optional<Model> model=  serializator.deserialize();
        return model;
    }
}
