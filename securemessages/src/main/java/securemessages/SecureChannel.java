package securemessages;

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.security.Key;

public class SecureChannel {
    private final Socket socket;
    private final Key key;

    public Socket getSocket() {
        return socket;
    }

    public Key getKey() {
        return key;
    }

    public SecureChannel(Socket socket, Key key) {
        this.socket = socket;
        this.key = key;
    }

    public void sendMessage(JsonObject jsonObject) throws Exception {
        var writer = new PrintWriter(getSocket().getOutputStream());
        var message = jsonObject.toString();
        var cipheredMessage = StringCipher.cipher(message, getKey());
        writer.println(cipheredMessage);
    }

    public JsonObject receiveMessage() throws Exception {
        var reader = new BufferedReader(new InputStreamReader(getSocket().getInputStream()));
        var cipheredMessage = reader.readLine();
        var message = StringCipher.decipher(cipheredMessage, getKey());
        return JsonParser.parseString(message).getAsJsonObject();
    }
}
