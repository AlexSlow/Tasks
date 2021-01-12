package Construct.Models.ExpressionModels;

import lombok.Data;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
@Data
public class SelectExpression extends AbstractExcpression implements Serializable {
    private static final long serialVersionUID = 4L;
    private List dataList=new ArrayList();
}
