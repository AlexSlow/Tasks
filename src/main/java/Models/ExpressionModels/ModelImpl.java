package Models.ExpressionModels;

import Factory.ConventorFactory;
import ViewConventors.View;
import lombok.Data;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
@Data
public class ModelImpl implements Model, Serializable {

    private List<AbstractExcpression> excpressions;
   // private View view;
    public ModelImpl(){
        excpressions=new ArrayList<>();
    }
    @Override
    public List<AbstractExcpression> expressions() {
        return excpressions;
    }

    @Override
    public void clear() {
    excpressions.clear();
    }

    @Override
    public void add(AbstractExcpression excpression) {
    excpressions.add(excpression);
    }

    @Override
    public void delete(List<Integer> ids) {
        for (Integer id:ids)
        {
            Optional<AbstractExcpression> abstractExcpression=getById(id);
            if (abstractExcpression.isPresent())
            {
                excpressions.remove(abstractExcpression.get());
            }

        }
    }

    @Override
    public Optional<AbstractExcpression> getById(Integer id) {
        for(AbstractExcpression excpression:excpressions)
        {
            if (excpression.getId()==id) return Optional.of(excpression);
        }
        return Optional.of(null);
    }

}
