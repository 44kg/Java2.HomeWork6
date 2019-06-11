import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.util.Scanner;

public class Client {
    public static void main(String[] args) {
        try {
            Socket socket = new Socket("localhost", 8189);
            DataInputStream in = new DataInputStream(socket.getInputStream());
            DataOutputStream out = new DataOutputStream(socket.getOutputStream());
            new Thread(new Runnable() {
                @Override
                public void run() {
                    try {
                        while (true) {
                            String str = in.readUTF();
                            System.out.println("Сообщение от сервера: " + str);
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
