/*

import java.io.*; // wildcard import for brevity in tutorial
import java.net.*;
import java.util.Random;
import java.util.concurrent.*;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.apache.commons.io.FilenameUtils;
import org.apache.commons.io.IOUtils;

public class EventServer {


    private static final int PORT = 9999;
    private static final String DELIMITER = ":";
    private static final long EVENT_PERIOD_SECONDS = 3;
    private static final Random random = new Random();

    public static void main(String[] args) throws IOException, InterruptedException {
//        final Executor SERVER_EXECUTOR = Executors.newSingleThreadExecutor();
        BlockingQueue<File> eventQueue = new ArrayBlockingQueue<>(100);

        Thread thread = new Thread(new SteamingServer(eventQueue));
        thread.start();
//        SERVER_EXECUTOR.execute(new SteamingServer(eventQueue));
        while (true) {
            eventQueue.put(generateEvent());
            Thread.sleep(TimeUnit.SECONDS.toMillis(EVENT_PERIOD_SECONDS));
        }
    }

    private static File generateEvent() {
        final String filePathPrefix = "D:\\Near-Assignment\\Data\\ForStreaming\\";
        String[] fileNames = new String[]{
                "SampleData_0.avro", "SampleData_1.csv",
                "SampleData_10.csv", "SampleData_2.csv",
                "SampleData_3.csv", "SampleData_4.csv",
                "SampleData_5.csv", "SampleData_6.csv",
                "SampleData_7.csv", "SampleData_8.csv",
                "SampleData_9.csv"
        };
        File file = new File(filePathPrefix + fileNames[random.nextInt(1)]);

        System.out.println(file.toPath());
        return file;
    }

    private static class SteamingServer implements Runnable {

        private final BlockingQueue<File> eventQueue;

        public SteamingServer(BlockingQueue<File> eventQueue) {
            this.eventQueue = eventQueue;
        }

        @Override
        public void run() {
            try (ServerSocket serverSocket = new ServerSocket(PORT);
                 Socket clientSocket = serverSocket.accept();
                 OutputStream send = new BufferedOutputStream(clientSocket.getOutputStream());) {
                System.out.println("_____________");
                while (true) {
                    File fileToSend = eventQueue.take();
                    InputStream fileIn = new BufferedInputStream(new FileInputStream(fileToSend));
                    String fileType = FilenameUtils.getExtension(fileToSend.getName());
                    String httpHeader = "";
                    String contentType = fileType.equals("avro") ? "avro/binary" : "";
                    System.out.println("_____"+contentType);
                    httpHeader += "HTTP/1.1 200 OK\n";
                    httpHeader += "Content-type: " + contentType + "; Charset=UTF-8\n";
                    httpHeader += "\n";
                    send.write(httpHeader.getBytes("UTF-8"));
                    send.flush();
                    IOUtils.copy(fileIn, send);
                    send.flush();
                }

            } catch (IOException | InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
*/
