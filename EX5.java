package oop;
	interface BankingService {
		 void deposit(double amount);
		 void withdraw(double amount);
		}
		class Account implements BankingService {
		 private double balance;
		 public Account(double initialBalance) {
		     this.balance = initialBalance;
		 }
		 public void deposit(double amount) {
		     if (amount <= 0) {
		         System.out.println("Invalid deposit amount!");
		     } else {
		         balance += amount;
		         System.out.println("Deposited: ₹" + amount + " | Balance: ₹" + balance);
		     }
		 }
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
		public class BankingSystem {
		 public static void main(String[] args) {
			 System.out.println("PRASANNADEVI M");
			 System.out.println("2117240070228");
		     BankingService account = new Account(0);
		     account.deposit(1000);  
		 }
		}

