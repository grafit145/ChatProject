import java.io.*;
import java.util.Date;

public class Logger {
    Date date = new Date();
    private static Logger logger;
    File file = new File("log.txt");

    public static Logger getInstance() {
        if (logger == null) {
            logger = new Logger();
        }
        return logger;
    }

    public void log(String message) {
        try {
            BufferedWriter writer = new BufferedWriter(new FileWriter(file, true));
            writer.append(date.toString() + " " + message);
            writer.newLine();
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
