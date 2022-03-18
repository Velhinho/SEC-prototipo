package securemessages;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.ServerSocket;

public class EchoServer implements Runnable {
    @Override
    public void run() {
        System.out.println("Starting Server");

        try(ServerSocket socket = new ServerSocket(8080)) {
            var client = socket.accept();
            var message = new BufferedReader(new InputStreamReader(client.getInputStream())).readLine();
            System.out.println("ciphered: " + message);
        } catch (Exception e) {
            System.err.println(e.getMessage());
        }
    }
}
