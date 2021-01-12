package Config.Loaders;

import Config.ConfigurationMap;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;

public interface TableLoader {
    public List<ConfigurationMap> load() throws FileNotFoundException;
    void unload( List<ConfigurationMap> configurationMaps) throws IOException;
}
