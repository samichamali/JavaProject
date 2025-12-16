/*
Author: Sami Chamali
Student ID: GH1033269
Date of Submission: 19/12/2025
*/

import java.util.Objects;
import java.util.Scanner;

public class Main {
    public static void main(String[] args){
        float coinvalue;
        float coinbalance;
        float eurobalance;
        float coins_in_euros;
        String choice1;
        String process = "";
        Scanner read = new Scanner(System.in);

        // Initiating the Crypto Currency Market Simulator Function in a separate thread
        OnlineCurrency.Market Crypto_Market = new OnlineCurrency.Market();
        Crypto_Market.start();

        // Initialising the logger class, creating a name for the txt file and informing the user of the file's presence.
        Logging.Initialise_Logger();

        // Starting Values (customizable)
        PersonalWallet.updateBalance("coin", 0);
        PersonalWallet.updateBalance("euro", 10000);
        Logging.LogBalance();

        System.out.println("Welcome to the Commerce Simulator!");

        // This is a looping interface, the moment you're done with something, you're back to the 3 main options.
        while (true) {
            CodeBeautifier.break_line(50, "=");
            System.out.println("Choose one of the following options:");
            // Display all the program's options to the user
            System.out.println("1. View Coin Value");
            System.out.println("2. View Balance");
            System.out.println("3. Trade");


            CodeBeautifier.break_line(25, "- ");
            System.out.print("Choice: ");
            String choice = read.nextLine();


            if (Objects.equals(choice, "1")) {
                // Grab coin's value in euros from the OnlineCurrency Class and give it to the user
                coinvalue = OnlineCurrency.getCoinValue();
                System.out.println("Coin Value: " + coinvalue + "€");
            }

            else if (Objects.equals(choice, "2")) {
                // Display's the user's euro and coin balance
                coinvalue = OnlineCurrency.getCoinValue();

                coinbalance = PersonalWallet.getBalance("coin");
                eurobalance = PersonalWallet.getBalance("euro");
                // Converting the coin balance to euros, helps the user understand their assets a bit better
                coins_in_euros = coinbalance * coinvalue;

                System.out.println("Euro Balance: "+eurobalance+"€");
                System.out.println("Coin balance: "+coinbalance+" / "+ coins_in_euros +"€");
            }

            else if (Objects.equals(choice, "3")) {
                // Trading Section
                System.out.println("What would you like to do?");
                System.out.println("1- Buy Coin");
                System.out.println("2- Sell Coin");
                System.out.print("Choice: ");
                choice = read.nextLine();
                CodeBeautifier.break_line(25, "- ");

                if (Objects.equals(choice, "1")) {
                    // Asks the user how they want to buy the coin, is the portion you have in mind in euros or in coins?
                    System.out.println("How do you want to buy?");
                    System.out.println("1- In Coins");
                    System.out.println("2- In Euros");
                    System.out.print("Choice: ");
                    choice1 = read.nextLine();
                    CodeBeautifier.break_line(25, "- ");
                    if (choice1.equals("1")) {
                        // The user can input how many coins they want to buy
                        System.out.println("Please Enter the number of coins you would like to buy.");
                        System.out.print("Amount of Coins: ");
                        // Reading a float variable and then calling the Trading class
                        float amount = Float.parseFloat(read.nextLine());
                        process = Trading.Invest("coin", amount);
                    }
                    else if (choice1.equals("2")) {
                        // The user can input how much money they want to buy of this coin
                        System.out.println("Please Enter the amount you would like to invest.");
                        System.out.print("Euros: ");
                        // Reading a float variable and then calling the Trading class
                        float amount = Float.parseFloat(read.nextLine());
                        process = Trading.Invest("euro", amount);
                    }
                    ;
                    if (process.equals("SUCCESS")) {
                        // If the Trading class has successfully invested this amount, then inform the user.
                        System.out.println("Successfully invested!");
                    }
                    else {
                        // If there is an issue, it will be grabbed by the trading class and then given to the user.
                        System.out.println(process);
                    }
                }
                if (Objects.equals(choice, "2")) {
                    // Asks the user how they want to sell the coin, is the portion you have in mind in euros or in coins?
                    System.out.println("How do you want to Sell?");
                    System.out.println("1- In Coins");
                    System.out.println("2- In Euros");
                    System.out.print("Choice: ");
                    choice1 = read.nextLine();
                    CodeBeautifier.break_line(25, "- ");
                    if (choice1.equals("1")) {
                        // The user can input how many coins they want to sell
                        System.out.println("Please Enter the number of coins you would like to sell.");
                        System.out.print("Amount of Coins: ");
                        // Reading a float variable and then calling the Trading class
                        float amount = Float.parseFloat(read.nextLine());
                        process = Trading.Withdraw("coin", amount);
                    }
                    else if (choice1.equals("2")) {
                        // The user can input how much money they want to sell of this coin
                        System.out.println("Please Enter the amount you would like to withdraw.");
                        System.out.print("Euros: ");
                        // Reading a float variable and then calling the Trading class
                        float amount = Float.parseFloat(read.nextLine());
                        process = Trading.Withdraw("euro", amount);
                    }
                    ;
                    if (process.equals("SUCCESS")) {
                        System.out.println("Successfully Withdrawn!");
                    }
                    else {
                        // If there is an issue, it will be grabbed by the trading class and then given to the user.
                        System.out.println(process);
                    }
                }
            }

            // Once done, a breaking line will be printed and then a proceeding confirmation will be asked for.
            CodeBeautifier.break_line(25, "- ");
            System.out.print("Click Enter to continue");
            read.nextLine();
        }
    }
}
