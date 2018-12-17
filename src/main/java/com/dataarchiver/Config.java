package com.dataarchiver;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

public class Config {
    public String folderPath;
    public String outputLocation;
    public String host;
    public String port;
    public int writeToHdfsInterval;
    public int minBatchSize;
    public String[] fileTypes;
    public Set<String> acceptedFileTypes;

    public String getFolderPath() {
        return folderPath;
    }

    public void setFolderPath(String folderPath) {
        this.folderPath = folderPath;
    }

    public String getOutputLocation() { return outputLocation; }

    public void setOutputLocation(String outputLocations) { this.outputLocation = outputLocation; }

    public String getHost() {
        return host;
    }

    public void setHost(String host) {
        this.host = host;
    }

    public String getPort() {
        return port;
    }

    public void setPort(String port) {
        this.port = port;
    }

    public int getWriteToHdfsInterval() {
        return writeToHdfsInterval;
    }

    public void setWriteToHdfsInterval(int writeToHdfsInterval) {
        this.writeToHdfsInterval = writeToHdfsInterval;
    }

    public int getMinBatchSize() {
        return minBatchSize;
    }

    public void setMinBatchSize(int minBatchSize) {
        this.minBatchSize = minBatchSize;
    }

    public String[] getFileTypes() {
        return fileTypes;
    }

    public void setFileTypes(String[] fileTypes) {
        this.fileTypes = fileTypes;
        setAcceptedFileTypes(fileTypes);
    }

    public Set<String> getAcceptedFileTypes() {
        return acceptedFileTypes;
    }

    public void setAcceptedFileTypes(final String[] acceptedFileTypes) {
        this.acceptedFileTypes = new HashSet<>(Arrays.asList(acceptedFileTypes));
    }
}

