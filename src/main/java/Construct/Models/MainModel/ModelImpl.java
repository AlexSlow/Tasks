package Construct.Models.MainModel;

import Config.ConfigurationMap;
import Construct.Models.ExpressionModels.AbstractExcpression;
import lombok.Data;

import java.io.Serializable;
import java.util.*;

@Data
public class ModelImpl implements Model, Serializable {
    private static final long serialVersionUID = 4L;
    transient private ConfigurationMap configurationMap;
   // private String name;
    private List<AbstractExcpression> excpressions=new ArrayList<>();

    public ModelImpl(){

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
            if (excpression.getId().equals(id))
            {
                return Optional.of(excpression);
            }
        }
        return Optional.empty();
    }

    @Override
    public Map<String, String> getMap() {
        Map<String,String> map=new HashMap<>();
        map.put("Название конфигурации",configurationMap.getConfigName());
        excpressions.forEach(exp->{
            map.put(exp.getName(),exp.getValue());
        });
        return map;
    }

    @Override
    public String getName() {
      return configurationMap.getConfigName();
    }
}
