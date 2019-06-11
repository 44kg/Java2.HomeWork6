import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Scanner;

public class Server {
    public static void main(String[] args) {
        try (ServerSocket serverSocket = new ServerSocket(8189)) {
            System.out.println("Сервер запущен, ожидаем подключения...");
            Socket socket = serverSocket.accept();
            System.out.println("Клиент подключился");
            DataInputStream in = new DataInputStream(socket.getInputStream());
            DataOutputStream out = new DataOutputStream(socket.getOutputStream());
            new Thread(new Runnable() {
                @Override
                public void run() {
                    try {
                        while (true) {
                            String str = in.readUTF();
                            System.out.println("Соббщение от клиента: " + str);
                        }
                    }
                    catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }).start();
            Scanner scanner = new Scanner(System.in);
            while (true) {
                String strOut = scanner.nextLine();
                out.writeUTF(strOut);
            }
        }
        catch (IOException e) {
            e.printStackTrace();
        }
    }
}
