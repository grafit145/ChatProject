import java.io.*;
import java.net.Socket;
import java.util.Scanner;

public class Client {
    static String clientName;
    static File settings = new File("settings.txt");

    public Client ()  {
        final String SERVER_HOST = "localhost";
        final int SERVER_PORT = getPortFromSettings(settings);
        BufferedReader reader = null;
        PrintWriter writer = null;

        try {
            Socket socket = new Socket(SERVER_HOST, SERVER_PORT);
            reader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            writer = new PrintWriter(new OutputStreamWriter(socket.getOutputStream()), true);
        } catch (IOException e) {
            e.printStackTrace();
        }

        Scanner scanner = new Scanner(System.in);

        System.out.println("введите Ваш никнейм:");

        clientName = scanner.nextLine();

        ServerListener serverListener = new ServerListener(reader);

        serverListener.start();

        String message;

        while (true) {
            System.out.println("Введите сообщение");
            message = scanner.nextLine();
            if (message.equals("/exit")) {
                writer.println(message);
                break;
            } else {
                writer.println(clientName + ": " + message);
            }
        }

        serverListener.interrupt();
    }

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
}