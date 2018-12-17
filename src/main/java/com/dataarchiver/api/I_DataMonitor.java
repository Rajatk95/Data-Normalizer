package com.dataarchiver.api;

import java.io.IOException;
import java.util.concurrent.ArrayBlockingQueue;

public interface I_DataMonitor {
    public void initializeMonitor() throws Exception;
    public void monitor() throws InterruptedException;
//    public void onDataArrival(final Object createdObject);
}
