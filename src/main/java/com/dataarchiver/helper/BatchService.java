package com.dataarchiver.helper;

import com.dataarchiver.Constants;
import com.dataarchiver.data.OutputDataFormat;

import java.io.File;
import java.util.*;

public class BatchService extends TimerTask implements Cloneable{

    private List<OutputDataFormat> BATCH = null;

    public BatchService() {
        System.out.println("Starting Batch Timer Task..");
        initializeBatch();
    }

    private void initializeBatch() {
        BATCH = new ArrayList<OutputDataFormat>();
        Timer timer = new Timer();

        timer.scheduleAtFixedRate(this, 0,  Constants.config.getWriteToHdfsInterval() * 1000);
    }

    @Override
    public void run() {
        System.out.println("Waiting for more data, Current batch size is : "+this.BATCH.size());
        if(writeToHDFS())   reinitializeBatch();
    }

    private void completeTask() {
        try {
            //assuming it takes 2 secs to complete the task
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public List<OutputDataFormat> getBATCH() {
        return Collections.unmodifiableList(BATCH);
    }

    public void reinitializeBatch() {
        this.BATCH = new ArrayList<OutputDataFormat>();
    }

    public void addToBATCH(List<OutputDataFormat> BATCH) {
        this.BATCH.addAll(BATCH);
    }

    public boolean writeToHDFS() {
        if(this.BATCH.size() > Constants.config.getMinBatchSize()) {
            FileUtils fileUtils = new FileUtils();
            try {
                File finalOutput = fileUtils.toCSVFile((BatchService)this.clone());
            } catch (CloneNotSupportedException e) {
                e.printStackTrace();
            }
            return true;
        }
        return false;
    }


}
