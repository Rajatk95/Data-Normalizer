Data Archiver
=============

This utility monitors a given directory for a new CSV file and normalize
it in a given format.

Steps to follow
---------------

1.  Download "DataArchiver-1.0-snapshot.jar" and "configuration.yml"
    located under https://github.com/Rajatk95/Data-Normalizer

2.  Open configuration.yml.

> ![](https://4.bp.blogspot.com/-zUPyUyQvCqI/XBex08tqB2I/AAAAAAAAJv0/GzQE7JFkgAcoxLVuDzy_X6csLN7PPS4qQCLcBGAs/s1600/1.png)

3.  Modify --

    a.  "folderPath" with the directory path you want to monitor for new
        files.

    b.  "outputLocation" with the directory path where you want to save
        the normalized .csv file

        Note: Please add a trailing File Separator at the end of path.

4.  Please make sure that Java 8 is installed on your machine and run
    the utility by following command.

> java -jar DataArchiver-1.0-snapshot.jar configuration.yml
>
> The jar accepts one parameter i.e. the configuration.yml file.

![](https://1.bp.blogspot.com/-cPpZMKLrZ2w/XBex1drk79I/AAAAAAAAJv4/B_YOyVwGIRYLEoFE077TcJlA26cUaIvaQCLcBGAs/s1600/2.png)

-   Once the utility starts, it will create a service which will monitor
    the folderPath (specified in configuration file) for new .csv files.

-   When a .csv file arrives, it reads the file and convert it into the
    specified schema in class "OutputDataFormat.java"

-   Once the file is normalized the records are added to a buffer, and a
    timer task service is created which periodically transfer the data
    into a new CSV file at the output location specified in the
    configuration.

-   Files in Batch is only written when number of records reach above threshold value (specified in configuration file as minBatchSize).

Schema
---------------
The Schema to which should be normalized is defined in class OutputDataFormat.java.

Pending Task
---------------
1.	Implement Normalization module for Avro and Parquet data.
2.	Define Schema in configuration or json file instead of a class.
3.	Test cases for directory monitoring and CSV file creation.
4.	File handling needs to be improved – handle file separator.
5.	Existing bugs needs to be fixed.
6.	Put data to HDFS instead of local file system.
7.	Performance improvement while normalization by implementing a multi-threading in HandleDataArrival.java class
