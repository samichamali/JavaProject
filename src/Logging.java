import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Random;

public class Logging {
    static Random randomGenerator = new Random();
    static String FileName;
    static Float Coins;
    static Float Euros;
    static float coinvalue;
    static float coins_in_euros;

    public static void Initialise_Logger() {
        int FileID = randomGenerator.nextInt(999999999);
        FileName = "log" + FileID + ".txt";
        System.out.println("LOGGING TO FILE: " + FileName);
    }
    public static void LogBalance() {
        // 'true' in the FileWriter constructor enables append mode.
        // If the file doesn't exist, FileWriter will create it.
        try (FileWriter fw = new FileWriter(FileName, true);
             PrintWriter pw = new PrintWriter(fw)) {

            // Retrieve coin and euro balances and the coin's value to calculate coin balance in euros, and then append that to the txt log file.
            Coins = PersonalWallet.getBalance("coin");
            Euros = PersonalWallet.getBalance("euro");
            coinvalue = OnlineCurrency.getCoinValue();
            coins_in_euros = Coins * coinvalue;

            pw.println("Coin Balance: " + Coins + " / "+coins_in_euros+"€ | Euro Balance: " + Euros);

        } catch (IOException e) {
            // Handle I/O errors (file permissions issues, directory not found)
            System.err.println("An error occurred while writing to the file: " + e.getMessage());
            e.printStackTrace();
        }
    }
}