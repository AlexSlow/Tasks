package Models.ExpressionModels;

import lombok.Data;

import java.io.Serializable;

@Data
public abstract class AbstractExcpression implements Serializable {
    private static Integer IDCOUNTER=0;
    private String name;
    private Integer id=IDCOUNTER++;
    private String value;
}
