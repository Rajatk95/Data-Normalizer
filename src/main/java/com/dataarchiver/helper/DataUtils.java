package com.dataarchiver.helper;

import com.dataarchiver.Constants;
import com.google.common.io.Files;

import java.io.File;

public class DataUtils {

    public DataUtils() {

    }

    public String validateAndGetType(final String newDataAbssolutePath) throws Exception {

        String contentType = new File(newDataAbssolutePath).isFile() ? Files.getFileExtension(newDataAbssolutePath).toUpperCase() : null;

        if (contentType == null) {
            throw new Exception("Input File :"+newDataAbssolutePath+" doesnot have a valid extension. CSV, AVRO files are only supported.");
        }

        if(contentType.equalsIgnoreCase("")) {
            throw new Exception("Input File :"+newDataAbssolutePath+" doesnot have a valid extension. CSV, AVRO files are only supported.");
        }

        if(!Constants.config.getAcceptedFileTypes().contains(contentType)) {
            throw new Exception("Input File :"+newDataAbssolutePath+" doesnot have a valid extension. CSV, AVRO files are only supported.");
        }

        return contentType;
//        return Optional.ofNullable(contentType).orElse(Constants.acceptedFileTypes.contains(contentType) ? contentType : null);
    }
}
