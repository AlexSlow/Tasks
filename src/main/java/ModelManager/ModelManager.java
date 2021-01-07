package ModelManager;

import Construct.Models.MainModel.Model;

import java.util.Optional;

public interface ModelManager {
    Optional<Model> load() throws Exception;
}
