package Construct.ViewConventors;

import javafx.scene.control.CheckBox;
import lombok.Data;
import lombok.extern.log4j.Log4j;

import java.util.ArrayList;
import java.util.List;

/**
 * @Autor AS
 * ПРедназначен для управления чекбоксами, а именно их хранения и получения
 */
@Log4j
@Data
public class CheckboxManager {
    private List<CheckBox> checkBoxList=new ArrayList<>();
    public void clear(){
        checkBoxList.clear();
    }
    public List<Integer>getFireId()
    {
        List<Integer> ids=new ArrayList<>();
        checkBoxList.forEach(checkBox -> {
            if (checkBox.isSelected()) ids.add((Integer.parseInt( checkBox.getId())));
        });
        return ids;
    }
}
