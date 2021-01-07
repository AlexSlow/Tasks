package Serializators;

import Construct.Models.MainModel.Model;
import lombok.Data;
import lombok.extern.log4j.Log4j;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.nio.file.Path;
import java.util.Optional;
@Data
@Log4j
public class FileProgrammSerializator implements Serializator {
    private Optional<Path> file;
    public FileProgrammSerializator(Path path) {
        this.file=Optional.of(path);
    }

    public void serialize(Model model)
    {
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
}
