package Factory;

import Models.ExpressionModels.AbstractExcpression;
import Models.ExpressionModels.LongTextExpression;
import Models.ExpressionModels.MapExpression;
import Models.ExpressionModels.SelectExpression;
import ViewConventors.MapConventor;
import ViewConventors.Panable;
import ViewConventors.SelectConventor;
import ViewConventors.TextAreaConventor;
import lombok.extern.log4j.Log4j;
import lombok.extern.slf4j.Slf4j;

import java.util.Map;

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
