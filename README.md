Data Archiver
=============

This utility monitors a given directory for a new CSV file and normalize
it in a given format.

Steps to follow
---------------

1.  Download "DataArchiver-1.0-snapshot.jar" and "configuration.yml"
    located under https://github.com/Rajatk95/Data-Normalizer

2.  Open configuration.yml.

> ![](media/image1.png){width="6.375in" height="3.2708333333333335in"}

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

![](media/image2.png){width="6.291666666666667in"
height="2.657638888888889in"}

-   Once the utility starts, it will create a service which will monitor
    the folderPath (specified in configuration file) for new .csv files.

-   When a .csv file arrives, it reads the file and convert it into the
    specified schema in class "OutputDataFormat.java"

-   Once the file is normalized the records are added to a buffer, and a
    timer task service is created which periodically transfer the data
    into a new CSV file at the output location specified in the
    configuration.
