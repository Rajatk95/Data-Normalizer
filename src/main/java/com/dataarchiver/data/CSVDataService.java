package com.dataarchiver.data;

import com.dataarchiver.Constants;
import com.dataarchiver.api.I_DataService;
import com.opencsv.CSVReader;
import com.opencsv.bean.CsvToBean;
import com.opencsv.bean.HeaderColumnNameTranslateMappingStrategy;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public class CSVDataService implements I_DataService {

    private File dataFile = null;
    private List<OutputDataFormat> normalizedData = null;
    private final static CsvToBean<OutputDataFormat> csvToBean = new CsvToBean<OutputDataFormat>();
    private final static HeaderColumnNameTranslateMappingStrategy<OutputDataFormat> strategy = setupDataMappingStrategy();


    public CSVDataService(final File dataFile) {
        this.dataFile = dataFile;
    }

    @Override
    public String getSchema() {
        return null;
    }

    @Override
    public List<OutputDataFormat> getNormalizedData() {
        return Collections.unmodifiableList(this.normalizedData);
    }

    @Override
    public void normalize() {
        final CSVReader csvReader = getCSVReader();
        this.normalizedData = new ArrayList<OutputDataFormat>();
//        List<OutputDataFormat> abc = new ArrayList<>();
        Optional.ofNullable(csvReader).ifPresent(reader -> {
            this.normalizedData.addAll(mapToSchema(reader));
        });
        closeCSVReader(csvReader);
    }

    @Override
    public CSVDataService toCSV() {
        return this;
    }

    private static HeaderColumnNameTranslateMappingStrategy setupDataMappingStrategy() {
        final HeaderColumnNameTranslateMappingStrategy<OutputDataFormat> strategy = new HeaderColumnNameTranslateMappingStrategy<OutputDataFormat>();
        strategy.setType(OutputDataFormat.class);
        strategy.setColumnMapping(Constants.dataMapping);
        CSVDataService.csvToBean.setMappingStrategy(strategy);
        return strategy;
    }

    private CSVReader getCSVReader() {
        try {
            return new CSVReader(new FileReader(this.dataFile));
        } catch (FileNotFoundException e) {
            System.out.println(this.dataFile.getAbsolutePath() + " not found!");
            e.printStackTrace();
        }
        return null;
    }

    /**
     * @param csvReader
     * @return List
     * Map CSV data to Schema Pojo class. Only data fields defined in  Schema are collected.
     */
    private List<OutputDataFormat> mapToSchema(final CSVReader csvReader) {
        CSVDataService.csvToBean.setCsvReader(csvReader);
        final List<OutputDataFormat> list = CSVDataService.csvToBean.parse();
        // removing objects which contains missing data.
        return list.stream().filter(data -> !data.isDataMissing()).collect(Collectors.toList());

    }

    private void closeCSVReader(final CSVReader csvReader) {
        try {
            csvReader.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


}
