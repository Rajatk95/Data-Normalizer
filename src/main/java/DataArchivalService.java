import com.dataarchiver.Config;
import com.dataarchiver.Constants;
import com.dataarchiver.api.I_DataMonitor;
import com.dataarchiver.monitor.DirectoryMonitor;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.yaml.YAMLFactory;

import java.io.*;

public class DataArchivalService {

    public DataArchivalService() {

    }

    private void preInitializationCheck(String[] args) throws Exception {
        ObjectMapper mapper = new ObjectMapper(new YAMLFactory());
        BufferedReader reader;
        if (args.length == 1) {
            final File file = new File(args[0]);
            reader = new BufferedReader(new FileReader(file));
        } else {
            System.out.println("Reading Default Configuration");
            final ClassLoader classLoader = getClass().getClassLoader();
            InputStream in = classLoader.getResourceAsStream("configuration.yml");
            reader = new BufferedReader(new InputStreamReader(in));
        }

        if (reader.ready()) {
            try {
                Constants.config = mapper.readValue(reader, Config.class);
            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                reader.close();
            }
        } else {
            throw new Exception("Please enter absolute path of configuration file(in double qoutes) as command line parameter.");
        }

    }

    public static void main(String[] args) throws Exception {
        DataArchivalService dataArchivalService = new DataArchivalService();
        dataArchivalService.preInitializationCheck(args);
        I_DataMonitor dataMonitor = new DirectoryMonitor();
        dataMonitor.initializeMonitor();
        dataMonitor.monitor();

    }
}

