package com.dataarchiver.monitor;

import com.dataarchiver.Constants;
import com.dataarchiver.api.I_DataService;
import com.dataarchiver.api.I_DataMonitor;
import com.dataarchiver.data.HandleDataArrival;
import com.dataarchiver.helper.BatchService;

import java.io.IOException;
import java.nio.file.*;
import java.util.concurrent.ArrayBlockingQueue;

public class DirectoryMonitor implements I_DataMonitor {

    private WatchService watchService = null;
    private static final HandleDataArrival handleDataArrival = new HandleDataArrival();

    @Override
    public void initializeMonitor() throws Exception {
        this.watchService = FileSystems.getDefault().newWatchService();
        Paths.get(Constants.config.getFolderPath()).register(watchService, StandardWatchEventKinds.ENTRY_CREATE);
    }

    @Override
    public void monitor() throws InterruptedException {
        WatchKey watchKey = null;
        do {
            watchKey = watchService.take();
            for (WatchEvent event : watchKey.pollEvents()) {
                WatchEvent.Kind kind = event.kind();
                if (StandardWatchEventKinds.ENTRY_CREATE.equals(event.kind())) {
                    Thread.sleep(2000);
                    String filename = event.context().toString();
                    handleDataArrival.onDataArrival(event.context());
                    System.out.println("File :" + filename+" added to streaming directory.");
                }
            }
        } while (watchKey.reset());
    }



}
