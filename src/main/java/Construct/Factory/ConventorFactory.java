package Construct.Factory;

import Construct.Models.ExpressionModels.AbstractExcpression;
import Construct.ViewConventors.Panable;

public interface ConventorFactory {
    Panable getConventor(AbstractExcpression abstractExcpression);
}
