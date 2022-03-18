package securemessages;

public record Transfer(Account sender, Account receiver, int amount) {
}