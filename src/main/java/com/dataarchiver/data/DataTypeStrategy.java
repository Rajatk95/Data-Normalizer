package com.dataarchiver.data;

import com.dataarchiver.Constants;
import com.dataarchiver.api.I_DataService;

import java.io.File;
import java.util.HashMap;
import java.util.Map;

public class DataTypeStrategy {


    private final static Map<String, I_DataService> dataType = new HashMap<String, I_DataService>() {

    };
    public static I_DataService getDataTypeStrategy(Constants.DataType contentType, File data) {
        switch (contentType) {
            case CSV: return new CSVDataService(data);
            case AVRO: return new AvroDataService(data);
            default: return null;
        }
    }
}
