package securemessages;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import java.io.IOException;
import java.util.List;
import java.util.Objects;

public class ServerSide {
    private final Channel channel;

    public Channel getChannel() {
        return channel;
    }

    public ServerSide(Channel channel) {
        this.channel = channel;
    }

    public JsonObject makeResponse(Object response) {
        var gson = new Gson();
        var responseJson = gson.toJson(response);
        return JsonParser.parseString(responseJson).getAsJsonObject();
    }

    public void processRequest() throws IOException {
        var requestJson = channel.receiveMessage();
        var requestType = requestJson.get("requestType").getAsString();
        var gson = new Gson();

        if(Objects.equals(requestType, "checkAccount")) {
            var request = gson.fromJson(requestJson.get("request"), CheckAccountRequest.class);

            var response = List.of();
            var responseJson = makeResponse(response);
            channel.sendMessage(responseJson);

        } else if(Objects.equals(requestType, "audit")) {
            var request = gson.fromJson(requestJson.get("request"), AuditRequest.class);

            var response = List.of();
            var responseJson = makeResponse(response);
            channel.sendMessage(responseJson);

        } else {
            throw new IOException("invalid json message type");
        }
    }
}
