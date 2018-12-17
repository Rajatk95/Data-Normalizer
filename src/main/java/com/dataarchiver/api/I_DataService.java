package com.dataarchiver.api;

import com.dataarchiver.data.CSVDataService;
import com.dataarchiver.data.OutputDataFormat;

import java.util.List;

public interface I_DataService {

    public String getSchema();

    public List<OutputDataFormat> getNormalizedData();

    public void normalize();

    public CSVDataService toCSV();

}
