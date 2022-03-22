package securemessages;

import java.nio.charset.StandardCharsets;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.Signature;
import java.util.Base64;

public class StringSignature {
    static String sign(String plainText, PrivateKey privateKey) throws Exception {
        var signature = Signature.getInstance("SHA256withRSA");
        signature.initSign(privateKey);
        signature.update(plainText.getBytes(StandardCharsets.UTF_8));
        var signedBytes = signature.sign();
        return Base64.getEncoder().encodeToString(signedBytes);
    }

    static boolean verify(String plainText, String signedText, PublicKey publicKey) throws Exception {
        var signedBytes = Base64.getDecoder().decode(signedText);
        var signature = Signature.getInstance("SHA256withRSA");
        signature.initVerify(publicKey);
        signature.update(signedBytes);
        return signature.verify(plainText.getBytes(StandardCharsets.UTF_8));
    }
}
