package securemessages;

import java.io.PrintWriter;
import java.net.Socket;

public class EchoClient implements Runnable {
    @Override
    public void run() {
        System.out.println("Starting Client");

        try(var socket = new Socket("localhost", 8080)) {
            var writer = new PrintWriter(socket.getOutputStream(), true);
            var message = "hello";
            var key = AESKeyGenerator.generateKey();
            var ciphered = StringCipher.cipher(message, key);
            writer.println(ciphered);
            System.out.println("deciphered:" + StringCipher.decipher(ciphered, key));
        } catch (Exception e) {
            System.err.println(e.getMessage());
        }
    }
}
