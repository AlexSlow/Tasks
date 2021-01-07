package Construct.Models.MainModel;

import Construct.Models.ExpressionModels.AbstractExcpression;

import java.util.List;
import java.util.Map;
import java.util.Optional;

public interface Model {
   List<AbstractExcpression> expressions();
   void clear();
   void add(AbstractExcpression abstractExcpression);
   void delete(List<Integer> ids);
    Optional<AbstractExcpression> getById(Integer id);
    void setName(String name);
    String getName();
    Map<String,String> getMap();
   // void repaint();
}
