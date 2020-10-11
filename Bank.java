
/*
 *CS1101 Intro to Computer Science
 *Bank Assignment (Loop)
 *Modified and Submitted by:
 Edgar Del Rivero
 Mauricio Mayorga
 Yusof Khalil-Assem
 Jose Figueroa
 */
//Importing Scanner into our program.
import java.util.Scanner;
import java.text.DecimalFormat;

//////////////////////////////// MAIN METHOD //////////////////////////////////
public class Bank {
  public static void main(String[] args) {
    // Declaring Scanner and Variables.
    Scanner scnr = new Scanner(System.in);
    User user = new User();
    int optionSelected = 0;
    clearConsole();
    System.out.println(
        "BANK-BANK\nThank you for Banking with us. \nPlease enter name of the account's holder and total balance.");

    // Obtaning User Information Using Scanner
    System.out.println("\nWelcome back, " + user.getUsername());
    System.out.print("\nIf this is not you, please type 'N'. \nOtherwise please press any key to proceed\n: ");
    String response = scnr.nextLine();
    if (response.equals("N") || response.equals("n")) {
      clearConsole();
      System.out.print("Please enter your username:");
      String newUsername = scnr.nextLine();
      user.setUsername(newUsername);
      System.out.print("Please enter your starting balance:");
      double newBalance = scnr.nextDouble();
      user.setAccountBalance(newBalance);
    }

    // Printing Information From Scanner.
    clearConsole();
    System.out.println(" Account holder name: " + user.getUsername());
    System.out.println(" Account balance: " + usCurrency.format(user.getAccountBalance()));
    System.out.println("Welcome, " + user.getUsername() + ".");

    // Run Options Method.
    selectOptions(user, optionSelected, user.getAccountBalance());

    // Closing Scanner
    scnr.close();
  }

  ////////////////////////////////////// METHODS/////////////////////////////////

  // Selected Option Method.
  public static void selectOptions(User user, int optionSelected, double accountBalance) {
    Scanner scan = new Scanner(System.in);
    // Loop Using "While"
    while (optionSelected != 3) {

      clearConsole();
      System.out.println("Main Menu \nBalance: " + accountBalance);
      System.out.println("1. Withdraw");
      System.out.println("2. Deposit");
      System.out.println("3. Exit");
      optionSelected = scan.nextInt();

      switch (optionSelected) {
        case 1:
          accountBalance = withdrawMoney(user, accountBalance, scan);
          break;

        case 2:
          accountBalance = depositMoney(user, accountBalance, scan);
          break;

        case 3:
          System.out.println("Thank you.");
          System.exit(0);
          break;

        default:
          selectOptions(user, optionSelected, accountBalance);
          break;
      }

    }
  }

  // Withdraw Method.
  public static double withdrawMoney(User user, double accountBalance, Scanner sc) {
    clearConsole();
    System.out.println("How much money would you like to withdraw?");
    double withdrawAmount = sc.nextDouble();
    if (withdrawAmount > 400) {
      System.out.println("Sorry, You are only allowed to withdraw 400 per day");
      return user.getAccountBalance();
    }
    if (withdrawAmount > user.getAccountBalance()) {
      System.out.println("Insufficient funds. Account balance: $ " + usCurrency.format(user.getAccountBalance()));
    } else {
      user.setAccountBalance(user.getAccountBalance() - withdrawAmount);
      System.out
          .println("Account Balance: $" + usCurrency.format(user.getAccountBalance()) + " ($" + withdrawAmount + ")");
    }
    return user.getAccountBalance();
  }

  // Deposit Method
  public static double depositMoney(User user, double accountBalance, Scanner scanr) {
    clearConsole();
    System.out.println("How much money would you like to deposit into you account:");
    double depositAmount = scanr.nextDouble();
    if (depositAmount > 2000) {
      System.out.println("Sorry You are only allowed to deposit 2000 per day");
    } else {
      accountBalance = accountBalance + depositAmount;
      System.out.println("Account Balance: " + "$" + accountBalance);
    }
    return accountBalance;
  }

  public static void clearConsole() {
    // Sources:
    // https://stackoverflow.com/a/10241460
    // https://tldp.org/HOWTO/Bash-Prompt-HOWTO/x361.html
    // \033[H - moves cursor to top left
    // \033[2J - clears screen
    System.out.print("\033[2J");
    System.out.println("\033[H");
    System.out.flush();
  }

  // Sources:
  // https://mkyong.com/java/java-display-double-in-2-decimal-points/
  // https://stackoverflow.com/a/8819889 (for clarification/correction)
  public static DecimalFormat usCurrency = new DecimalFormat("#.00");
}

class User {
  String username = "Valentin Elizalde";
  Double accountBalance = 10000.00;

  public String getUsername() {
    return this.username;
  }

  public void setUsername(String newUsername) {
    this.username = newUsername;
  }

  public double getAccountBalance() {
    return this.accountBalance;
  }

  public void setAccountBalance(double newAccountBalance) {
    this.accountBalance = newAccountBalance;
  }
}
