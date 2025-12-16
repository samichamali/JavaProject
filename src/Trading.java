import java.util.Objects;

public class Trading {
    public static String Invest(String type, float amount){
        // Retrieves the user's current balance and the coin's value
        float CurrentBalance = PersonalWallet.getBalance("euro");
        float CoinBalance = PersonalWallet.getBalance("coin");
        float coinvalue = OnlineCurrency.getCoinValue();

        if (Objects.equals(type, "coin")) {
            // Calculate how much the amount is in euros if the user wanted to input their amount in coins.
            amount *= coinvalue;
        }

        if (amount > CurrentBalance){
            // if the amount of money inputted exceed that of the user's current balance, they will go negative.
            return "NO SUFFICIENT FUNDS";
        }
        // Deduct from the current balance and add to the coin balance, and then log that transaction.
        CurrentBalance -= amount;
        CoinBalance += amount/coinvalue;
        PersonalWallet.updateBalance("euro", CurrentBalance);
        PersonalWallet.updateBalance("coin", CoinBalance);
        Logging.LogBalance();
        return "SUCCESS";
    }
    public static String Withdraw(String type, float amount){
        // Retrieves the user's current balance and the coin's value as well as its conversion to euros.
        float CurrentBalance = PersonalWallet.getBalance("euro");
        float CoinBalance = PersonalWallet.getBalance("coin");
        float coinvalue = OnlineCurrency.getCoinValue();
        float CoinBalanceInEuros = CoinBalance*coinvalue;

        if (Objects.equals(type, "coin")) {
            // Calculate how much the amount is in euros if the user wanted to input their amount in coins.
            amount *= coinvalue;
        }
        if (amount > CoinBalanceInEuros){
            // if the amount of money inputted exceed that of the user's current balance, they will go negative.
            return "YOU DONT HAVE THAT MANY COINS";
        }
        // Add to the current balance and deduct from the coin balance, and then log that transaction.
        CurrentBalance += amount;
        CoinBalance -= amount/coinvalue;
        PersonalWallet.updateBalance("euro", CurrentBalance);
        PersonalWallet.updateBalance("coin", CoinBalance);
        Logging.LogBalance();
        return "SUCCESS";
    }

}
