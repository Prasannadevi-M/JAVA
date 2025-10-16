package oop;
public class EX6 {
	interface BankingService {
		 void deposit(double amount);
		 void withdraw(double amount);
		}
		//Account class implementing interface
		class Account implements BankingService {
		 private double balance;

		 public Account(double initialBalance) {
		     this.balance = initialBalance;
		 }
		 @Override
		 public void deposit(double amount) {
		     if (amount <= 0) {
		         System.out.println("Invalid deposit amount!");
		     } else {
		         balance += amount;
		         System.out.println("Deposited: ₹" + amount + " | Balance: ₹" + balance);
		     }
		 }
		 @Override
		 public void withdraw(double amount) {
		     if (amount <= 0) {
		         System.out.println("Invalid withdrawal amount!");
		     } else if (amount > balance) {
		         System.out.println("Insufficient funds!");
		     } else {
		         balance -= amount;
		         System.out.println("Withdrawn: ₹" + amount + " | Balance: ₹" + balance);
		     }
		 }
		 public double getBalance() {
		     return balance;
		 }
		}
		//Transaction class (for logging)
		class Transaction {
		 public void log(String message) {
		     System.out.println("Transaction recorded: " + message);
		 }
		}
		//Main class
 {
		     // Polymorphism (interface reference → object of Account)
		     BankingService account = new Account(0);
		     Transaction log = new Transaction();
		     // TC1: Deposit ₹1000
		     account.deposit(1000);
		     // TC2: Withdraw ₹500
		     account.withdraw(500);
		     // TC3: Withdraw ₹1500
		     account.withdraw(1500);
		     // TC4: Deposit negative amount
		     account.deposit(-200);
		     // TC5: Log transaction
		     log.log("Deposit ₹1000, Withdraw ₹500");
		 }
		}


