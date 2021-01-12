package Serializators;

import Config.ConfigurationMap;
import Construct.Models.MainModel.Model;
import Construct.Models.MainModel.ModelImpl;
import lombok.Data;
import lombok.extern.log4j.Log4j;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Optional;
@Data
@Log4j
public class FileProgrammSerializator implements Serializator {
    private ConfigurationMap configurationMap;
    public FileProgrammSerializator(ConfigurationMap configurationMap) {
        this.configurationMap=configurationMap;
    }

    public void serialize(Model model)
    {
        if (configurationMap!=null)
        {
            try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(configurationMap.getFile().toFile()))) {
                oos.writeObject(model);
            } catch (Exception ex) {
                ex.printStackTrace();
                log.error("Ошибка сериализации в файл");
            }
        }
    }
    @Override
    public Optional<Model> deserialize() {
        if (configurationMap!=null) {

            try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(configurationMap.getFile().toFile()))){
                Model model=(Model) ois.readObject();
                return Optional.of(model);
            } catch (Exception ex) {
                ex.printStackTrace();
                log.error("Ошибка дессериализации в файл");
            }
        }
        log.debug("Инициализация модели по умолчанию");
        Model model=new ModelImpl();
      //  model.setConfigurationMap(configurationMap);
        return Optional.of(model);
    }
}
