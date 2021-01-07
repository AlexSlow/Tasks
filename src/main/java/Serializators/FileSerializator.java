package Serializators;

import Construct.Models.MainModel.Model;
import javafx.stage.FileChooser;
import lombok.Data;
import lombok.extern.log4j.Log4j;

import java.io.*;
import java.nio.file.Path;
import java.util.Optional;
@Log4j
@Data
public class FileSerializator implements Serializator {
    public FileSerializator()
    {

    }
    public void serialize(Model model)
    {
      Optional<Path> file=saveFile();
      if (file.isPresent())
      {
          try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(file.get().toFile()))) {
              oos.writeObject(model);
          } catch (Exception ex) {
              ex.printStackTrace();
              log.error("Ошибка сериализации в файл");
          }
      }
    }

    @Override
    public Optional<Model> deserialize() {
        Optional<Path> file=openFile();
        if (file.isPresent()) {
            try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(file.get().toFile()))){
               Model model=(Model) ois.readObject();
               return Optional.of(model);
            } catch (Exception ex) {
                ex.printStackTrace();
                log.error("Ошибка дессериализации в файл");
            }
        }
        return Optional.empty();

    }

    private Optional<Path> openFile()
    {
        FileChooser fileChooser = new FileChooser();
        File file = fileChooser.showOpenDialog(null );
        if (file != null) {
           return Optional.of(file.toPath());
        }
        return Optional.empty();
    }

    private Optional<Path> saveFile()
    {
        FileChooser fileChooser = new FileChooser();
        File file = fileChooser.showSaveDialog(null );
        if (file != null) {
            return Optional.of(file.toPath());
        }
        return Optional.empty();
    }


}
