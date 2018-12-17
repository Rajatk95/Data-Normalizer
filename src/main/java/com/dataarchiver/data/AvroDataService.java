package com.dataarchiver.data;

import com.dataarchiver.api.I_DataService;

import java.io.File;
import java.util.List;

public class AvroDataService implements I_DataService {

    public AvroDataService(final File data) {

    }

    @Override
    public String getSchema() {
        return null;
    }

    @Override
    public List<OutputDataFormat> getNormalizedData() {
        return null;
    }

    @Override
    public void normalize()  {

    }

    @Override
    public CSVDataService toCSV() {
        return null;
    }
}
