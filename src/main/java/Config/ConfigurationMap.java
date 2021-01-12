package Config;

import lombok.Data;

import java.nio.file.Path;
import java.nio.file.Paths;

@Data
public class ConfigurationMap {
    private String configName;
    private Path file;
    public ConfigurationMap(String[] strings)
    {
        if (strings.length==2)
        {
            configName=strings[0];
            file=Paths.get(strings[1]);
        }else
        {
            configName="Ошибка";
        }
    }
    public ConfigurationMap(String name,String file)
    {
        configName=name;
        this.file=Paths.get(file);
    }
    public ConfigurationMap(String configName,Path path)
    {
        this.configName=configName;
        this.file=path;
    }

    @Override
    public String toString()
    {
        return configName+" "+file;
    }
}
