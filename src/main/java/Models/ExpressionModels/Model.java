package Models.ExpressionModels;

import java.util.List;
import java.util.Optional;

public interface Model {
   List<AbstractExcpression> expressions();
   void clear();
   void add(AbstractExcpression abstractExcpression);
   void delete(List<Integer> ids);
    Optional<AbstractExcpression> getById(Integer id);
   // void repaint();
}
