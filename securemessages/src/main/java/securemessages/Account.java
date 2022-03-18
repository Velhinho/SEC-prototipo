package securemessages;

import java.security.KeyPair;

public record Account(KeyPair keyPair, int balance) {
}
