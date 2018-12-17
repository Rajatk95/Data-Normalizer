package com.dataarchiver;

import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.*;

public final class Constants {

    // dir monitor
    public static Config config;

    public enum DataType {CSV, AVRO}

    // data mapping
    public static final Map<String, String> dataMapping = new HashMap<String, String>();

    static {
        dataMapping.put("Name","name");
        dataMapping.put("City","city");
        dataMapping.put("Country","country");
    }

    // batch
    public static final int WRITE_TO_HDFS_INTERVAL = 5;
    public static final int MIN_BATCH_SIZE = 10;

}
