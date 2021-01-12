package Config.Property;

import lombok.Data;

import javax.validation.constraints.NotNull;
import java.io.*;
import java.net.URL;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Date;
import java.util.Properties;

@Data
public class PropertyLoader {
    private final String CONF="config_path";
    private final String DOCS="doc_path";
    private String path="/PathProperties.properties";
    public PathProperty getProperty() throws IOException
    {
        Properties properties=new Properties();

        URL file=this.getClass().getResource(path);

        try (FileReader fileReader=new FileReader(file.getFile())){
            properties.load(fileReader);
            PathProperty pathProperty=new PathProperty();
            pathProperty.setPath_of_congigs(properties.getProperty(getCONF()));
            pathProperty.setPath_of_docs(properties.getProperty(getDOCS()));
            return pathProperty;
        }

    }
    public void setProperty(PathProperty pathProperty) throws  IOException
    {
        URL file=this.getClass().getResource(path);
        try (FileWriter fileWriter=new FileWriter(file.getFile())){
            Properties  properties=new Properties();
            properties.setProperty(CONF,pathProperty.getPath_of_congigs());
            properties.setProperty(DOCS,pathProperty.getPath_of_docs());
            properties.store(fileWriter,new Date().toString());
        }


    }

}
