import com.dataarchiver.data.CSVDataService;
import com.dataarchiver.data.OutputDataFormat;

import java.io.File;

public class MockDataProvider {

    static public OutputDataFormat getMockCSVData() {
        final OutputDataFormat outputDataFormat = new OutputDataFormat();
        outputDataFormat.setCity("Delhi");
        outputDataFormat.setCountry("India");
        outputDataFormat.setName("Blogger0");
        return outputDataFormat;
    }
}
