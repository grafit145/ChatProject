import java.io.*;

public class ServerListener extends Thread{
    BufferedReader reader;

    public ServerListener(BufferedReader reader){
        this.reader = reader;
    }

    @Override
    public void run() {
        while (!Thread.currentThread().isInterrupted()) {
            String incomingMessage = null;
            try {
                incomingMessage = reader.readLine();
            } catch (IOException e) {
                Thread.currentThread().interrupt();
            }
            if (incomingMessage != null) {
                System.out.println(incomingMessage);
            }
        }
    }
}
