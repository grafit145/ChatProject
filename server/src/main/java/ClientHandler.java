import java.io.IOException;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Scanner;

public class ClientHandler implements Runnable {
    final Logger logger = Logger.getInstance();
    private Server server;
    private PrintWriter outMessage;
    private Scanner inMessage;
    private Socket clientSocket = null;


    public ClientHandler(Socket socket, Server server) {
        try {
            this.server = server;
            this.clientSocket = socket;
            this.outMessage = new PrintWriter(socket.getOutputStream());
            this.inMessage = new Scanner(socket.getInputStream());
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    @Override
    public void run() {
       while (true) {
            if (inMessage.hasNext()) {
                String clientMessage = inMessage.nextLine();
                if(clientMessage.contains("/exit")){
                    server.sendMessageToAllClients("Пользователь вышел из чата");
                    break;
                }
                System.out.println(clientMessage);
                logger.log(clientMessage);
                server.sendMessageToAllClients(clientMessage);
            }
        }
    }

    public void sendMsg(String msg) {
        try {
            outMessage.println(msg);
            outMessage.flush();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

}
