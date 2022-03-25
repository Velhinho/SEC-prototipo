package securemessages;

import java.util.Objects;

public final class Transfer {
    private final Account sender;
    private final Account receiver;
    private final int amount;

    public Transfer(Account sender, Account receiver, int amount) {
        this.sender = sender;
        this.receiver = receiver;
        this.amount = amount;
    }

    public Account sender() {
        return sender;
    }

    public Account receiver() {
        return receiver;
    }

    public int amount() {
        return amount;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == this) return true;
        if (obj == null || obj.getClass() != this.getClass()) return false;
        var that = (Transfer) obj;
        return Objects.equals(this.sender, that.sender) &&
                Objects.equals(this.receiver, that.receiver) &&
                this.amount == that.amount;
    }

    @Override
    public int hashCode() {
        return Objects.hash(sender, receiver, amount);
    }

    @Override
    public String toString() {
        return "Transfer[" +
                "sender=" + sender + ", " +
                "receiver=" + receiver + ", " +
                "amount=" + amount + ']';
    }

}