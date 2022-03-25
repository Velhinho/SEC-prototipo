package securemessages;

import securemessages.channel.ChannelException;
import securemessages.channel.PlainChannel;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.security.KeyPair;
import java.security.PublicKey;
import java.util.concurrent.Executors;

public class EchoServer {
    private static void serveClient(Socket client) throws RuntimeException {
        try {
            var channel = new PlainChannel(client);
            var serverSide = new ServerSide(channel);
            serverSide.processRequest();
        } catch (RuntimeException | ChannelException e) {
            e.printStackTrace(System.err);
        }
    }

    public static void run(PublicKey clientKey, KeyPair keyPair) {
        System.out.println("Starting Server");
        var executorService = Executors.newCachedThreadPool();

        try(ServerSocket serverSocket = new ServerSocket(8080)) {
            while (true) {
                System.out.println("Waiting for client");
                var client = serverSocket.accept();
                System.out.println("Accepted client");
                executorService.submit(() -> serveClient(client));
            }
        } catch (IOException e) {
            e.printStackTrace(System.err);
        }
    }
}
