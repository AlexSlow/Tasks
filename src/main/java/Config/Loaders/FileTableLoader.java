package Config.Loaders;

import Config.ConfigurationMap;

import javax.validation.constraints.NotNull;
import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class FileTableLoader implements TableLoader {
    @NotNull
    private Path path;

   public FileTableLoader(String file)
   {
       path= Paths.get(file);
      if(Files.notExists(path))
      {
          try {
              Files.createFile(path);
          } catch (IOException e) {
              e.printStackTrace();
          }
      }
   }
    @Override
    public List<ConfigurationMap> load() throws FileNotFoundException {
        List<ConfigurationMap> configurationMaps = new ArrayList<>();
        Scanner scanner = new Scanner(new BufferedInputStream(new FileInputStream(path.toFile())));
        while (scanner.hasNextLine()) {
            String string = scanner.nextLine();
            configurationMaps.add(new ConfigurationMap(string.split(" ")));
        }
        scanner.close();
        return configurationMaps;
    }


    @Override
    public void unload(List<ConfigurationMap> configurationMaps) throws IOException {
        PrintWriter printWriter=new PrintWriter(new FileWriter(path.toFile(),false));
        configurationMaps.forEach(conf->{
            printWriter.println(conf.toString());
        });
        printWriter.flush();
        printWriter.close();
    }
}
