package securemessages;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.google.gson.reflect.TypeToken;
import io.vavr.collection.List;

import java.io.IOException;
import java.security.PublicKey;

public class ClientSide {
    private final Channel channel;

    public Channel getChannel() {
        return channel;
    }

    public ClientSide(Channel channel) throws IOException {
        this.channel = channel;
    }

    private JsonObject makeRequest(String requestType, Object request) {
        var gson = new Gson();

        var requestJson = new JsonObject();
        requestJson.addProperty("requestType", requestType);
        requestJson.addProperty("request", gson.toJson(request));

        return requestJson;
    }

    List<PendingTransfer> checkAccount(PublicKey publicKey) throws IOException {
        var request = new CheckAccountRequest(publicKey);
        var requestJson = makeRequest("checkAccount", request);
        getChannel().sendMessage(requestJson);

        var gson = new Gson();
        var responseJson = getChannel().receiveMessage();
        return gson.fromJson(responseJson, new TypeToken<List<PendingTransfer>>(){}.getType());
    }

    List<Transfer> audit(PublicKey publicKey) throws IOException {
        var request = new AuditRequest(publicKey);
        var requestJson = makeRequest("audit", request);
        getChannel().sendMessage(requestJson);

        var gson = new Gson();
        var responseJson = getChannel().receiveMessage();
        return gson.fromJson(responseJson, new TypeToken<List<Transfer>>(){}.getType());
    }

}
