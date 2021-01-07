import lombok.Data;

import java.nio.file.Path;

@Data
public class ConfigurationMap {
    private String configName;
    private Path file;
}
