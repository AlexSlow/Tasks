package Serializators;

import Construct.Models.MainModel.Model;

import java.util.Optional;

public interface Serializator {
    void serialize(Model model);
   Optional<Model> deserialize();
}
