package securemessages;

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

public class Channel {
    private final Socket socket;

    public Channel(Socket socket) {
        this.socket = socket;
    }

    public Socket getSocket() {
        return socket;
    }

    public void sendMessage(JsonObject jsonObject) throws IOException {
        var writer = new PrintWriter(getSocket().getOutputStream());
        writer.println(jsonObject.toString());
    }

    public JsonObject receiveMessage() throws IOException {
        var reader = new BufferedReader(new InputStreamReader(getSocket().getInputStream()));
        var line = reader.readLine();
        return JsonParser.parseString(line).getAsJsonObject();
    }
}
