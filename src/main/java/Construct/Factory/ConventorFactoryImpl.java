package Construct.Factory;

import Construct.Models.ExpressionModels.AbstractExcpression;
import Construct.Models.ExpressionModels.LongTextExpression;
import Construct.Models.ExpressionModels.MapExpression;
import Construct.Models.ExpressionModels.SelectExpression;
import Construct.ViewConventors.MapConventor;
import Construct.ViewConventors.Panable;
import Construct.ViewConventors.SelectConventor;
import Construct.ViewConventors.TextAreaConventor;
import lombok.extern.log4j.Log4j;

@Log4j
public class ConventorFactoryImpl implements ConventorFactory{
    @Override
    public Panable getConventor(AbstractExcpression abstractExcpression)  {

        if (abstractExcpression instanceof MapExpression)
        {
            MapExpression mapExpression=((MapExpression) abstractExcpression);
                MapConventor mapConventor = new MapConventor();
                mapConventor.setMapExpression(mapExpression);
                return mapConventor;

        } else if(abstractExcpression instanceof LongTextExpression)
        {
            TextAreaConventor textAreaConventor=new TextAreaConventor();
            textAreaConventor.setLongTextExpression(((LongTextExpression) abstractExcpression));
            return textAreaConventor;
        }
        else if (abstractExcpression instanceof SelectExpression){
            SelectExpression selectExpression=(SelectExpression) abstractExcpression;
            SelectConventor selectConventor=new SelectConventor();
            selectConventor.setSelectExpression(selectExpression);
            return selectConventor;
        }
        log.error("Не найден преобразователь!");
        throw new RuntimeException("Не найден преобразователь!");
    }
}
