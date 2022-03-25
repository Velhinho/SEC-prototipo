package securemessages;

import securemessages.channel.PlainChannel;

import java.net.Socket;
import java.security.KeyPair;
import java.security.PublicKey;

public class EchoClient{
    public static void run(PublicKey serverKey, KeyPair clientKeyPair) {
        System.out.println("Starting Client");

        try(var socket = new Socket("localhost", 8080)) {
            var channel = new PlainChannel(socket);
            var clientSide = new ClientSide(channel);
            var response = clientSide.checkAccount(clientKeyPair.getPublic());
            System.out.println(response);
        } catch (Exception e) {
            e.printStackTrace(System.err);
        }
    }
}
