import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.*;
import java.util.concurrent.CopyOnWriteArrayList;

public class Server {
    static File settings = new File("settings.txt");
    private CopyOnWriteArrayList<ClientHandler> clientsList = new CopyOnWriteArrayList<>();

    public int getPortFromSettings(File file) {
        Scanner scanner = null;
        try {
            scanner = new Scanner(file);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        String portSocket = scanner.nextLine();
        return Integer.parseInt(portSocket);
    }

    public Server() {
        Socket clientSocket = null;
        ServerSocket serverSocket = null;
        try {
            serverSocket = new ServerSocket(getPortFromSettings(settings));
            System.out.println("Сервер запущен!");
            while (true) {
                clientSocket = serverSocket.accept();
                ClientHandler clientHandler = new ClientHandler(clientSocket, this);
                clientsList.add(clientHandler);
                new Thread(clientHandler).start();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void sendMessageToAllClients(String msg) {
        for (ClientHandler o : clientsList) {
            o.sendMsg(msg);
        }
    }
}