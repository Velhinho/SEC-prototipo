package securemessages;

import securemessages.crypto.RSAKeyGenerator;

import java.util.concurrent.Executors;

public class Main {
    public static void main(String[] args) {
        var executorService = Executors.newCachedThreadPool();
        try {
            var clientKeyPair = RSAKeyGenerator.generateKeyPair();
            var serverKeyPair = RSAKeyGenerator.generateKeyPair();
            executorService.submit(() -> EchoServer.run(clientKeyPair.getPublic(), serverKeyPair));
            EchoClient.run(serverKeyPair.getPublic(), clientKeyPair);
        } catch (Exception e) {
            e.printStackTrace(System.err);
        }
    }
}
