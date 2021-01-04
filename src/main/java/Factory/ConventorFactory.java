package Factory;

import Models.ExpressionModels.AbstractExcpression;
import ViewConventors.Panable;

public interface ConventorFactory {
    Panable getConventor(AbstractExcpression abstractExcpression);
}
