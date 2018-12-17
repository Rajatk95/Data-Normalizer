package com.dataarchiver.helper;

import com.dataarchiver.Constants;
import com.dataarchiver.data.OutputDataFormat;
import com.google.common.io.Files;

import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;

public class FileUtils {

    FileUtils() {

    }

    public File toCSVFile(final BatchService batch) {
        StringBuilder allBatchData = new StringBuilder(OutputDataFormat.getHEADER());
        batch.getBATCH().stream().forEach(record -> {
            allBatchData.append(record.toString());
        });

        System.out.println(allBatchData.toString());
        return createTempFileWithData(allBatchData);
    }


    public File createTempFileWithData(final StringBuilder batchData) {
        File dataFile = null;
        try {
//            dataFile = File.createTempFile("DataArchiverFile", "");
            dataFile = new File(Constants.config.getOutputLocation()+Math.random()+".csv");
            dataFile.createNewFile();
            System.out.println("Writing Data to "+dataFile.getAbsolutePath());
            Files.write(batchData, dataFile, StandardCharsets.UTF_8);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return dataFile;
    }
}
