import com.dataarchiver.data.CSVDataService;
import com.dataarchiver.data.OutputDataFormat;
import org.junit.Test;

import java.io.File;
import java.util.List;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;


public class CSVDataTest {

    @Test
    public void when_normalizeData_expect_normalizedData() {
        final CSVDataService csvData = readMockCsvFile();

        csvData.normalize();

        final List<OutputDataFormat> data = csvData.getNormalizedData();
        assertThat(data.size()).isEqualTo(3);
        assertThat(data.get(0)).isEqualToComparingFieldByField(MockDataProvider.getMockCSVData());
    }

    private CSVDataService readMockCsvFile() {
        final ClassLoader classLoader = getClass().getClassLoader();
        final File file = new File(classLoader.getResource("csv/test.csv").getFile());
        final CSVDataService csvData = new CSVDataService(file);
        return csvData;
    }
}
