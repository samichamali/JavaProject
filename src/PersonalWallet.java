import java.util.Objects;

public class PersonalWallet {
    // Stores the User's balances.
    private static volatile float EuroBalance;
    private static volatile float CoinBalance;

    public static float getBalance(String type) {
        // Returns the type of balance requested.
        if (type == "euro") {
            return EuroBalance;
        }
        else if (type == "coin") {
            return CoinBalance;
        }
        return 0;
    }
    public static void updateBalance(String type, float value) {
        // Updates the type of balance specified with the new value.
        if (Objects.equals(type, "euro")) {
            EuroBalance = value;
        }
        else if (Objects.equals(type, "coin")) {
            CoinBalance = value;
        }

    }
}
