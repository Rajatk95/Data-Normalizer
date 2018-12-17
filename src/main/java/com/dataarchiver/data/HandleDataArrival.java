package com.dataarchiver.data;

import com.dataarchiver.Constants;
import com.dataarchiver.api.I_DataService;
import com.dataarchiver.helper.BatchService;
import com.dataarchiver.helper.DataUtils;

import java.io.File;
import java.nio.file.Paths;


public class HandleDataArrival  {

    private static final DataUtils dataUtils = new DataUtils();
    private static final BatchService batch = new BatchService();

    public final void onDataArrival(final Object createdObject) {
        /*String newDataAbssolutePath = Paths.get(Constants.config.getFolderPath()).toUri().getPath().concat(createdObject.toString());*/
        String newDataAbssolutePath = Constants.config.getFolderPath().concat(createdObject.toString());
        String contentType = getContentType(newDataAbssolutePath);
        if (contentType == null) {
            return;
        }
        I_DataService data = DataTypeStrategy.getDataTypeStrategy(Constants.DataType.valueOf(contentType.toUpperCase()), new File(newDataAbssolutePath));
        data.normalize();
        data.toCSV();
        System.out.println("Adding data file "+newDataAbssolutePath+" to batch");
        batch.addToBATCH(data.getNormalizedData());
    }

    private String getContentType(String newDataAbssolutePath) {
        try {
            return dataUtils.validateAndGetType(newDataAbssolutePath);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}
