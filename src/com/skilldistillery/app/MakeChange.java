package com.skilldistillery.app;

import java.util.Scanner;

public class MakeChange {
	private static final double HUNDRED = 100.0, FIFTY = 50.0, TWENTY = 20.0, TEN = 10.0, FIVE = 5.0, ONE = 1.0,
			QUARTER = 0.25, DIME = 0.1, NICKEL = 0.05, PENNY = 0.01;

	private static Scanner sc;

	private static double hundreds, fifties, twenties, tens, fives, ones, quarters, dimes, nickels, pennies;
	
	private static int transactions = 0;
	
	private static double totalPurchases = 0.0;

	private static double priceOfItem;
	private static double amountTendered;
	private static boolean exit = false;
	
	public static void main(String[] args) {
		sc = new Scanner(System.in);

		System.out.println("Booting up Cash Register ver 1.35\n\n");

		// Loop until we want to exit the cash register
		while (!exit) {
			runCashRegister();
		}
		
		// Cleanup
		cleanup();
	}
	
	private static void runCashRegister() {

		System.out.println("What is the price of the item? '0' to quit");
		priceOfItem = sc.nextFloat();
		sc.nextLine(); // Eat the new line.
		
		// Exit if price is 0
		if (priceOfItem == 0) {
			exit = true;
			return;
		}

		System.out.println("How much are you paying with? ");
		amountTendered = sc.nextFloat();
		sc.nextLine(); // Eat the new line.

		// Get the change required.
		double change = amountTendered - priceOfItem;

		// We have change we need to give them.
		if (change > 0) {
			produceChange(change);
			displayChange();
			
			transactions++;
			totalPurchases += priceOfItem;
		}
		// Exact amount of money given, so no change.
		else if (change == 0) {
			System.out.println("Thanks for shopping with us today!");
			
			transactions++;
			totalPurchases += priceOfItem;
		}
		// We need more change to finish the transaction.
		else {
			System.out.printf("Uhhh, we need $%.2f to complete the transaction.\n\n", (change * -1));
		}
	}

	private static void produceChange(double change) {
		// Calculate $100 bills
		hundreds = Math.floor(change / HUNDRED);
		change -= hundreds * HUNDRED;

		// Calculate $50 bills
		fifties = Math.floor(change / FIFTY);
		change -= fifties * FIFTY;

		// Calculate $20 bills
		twenties = Math.floor(change / TWENTY);
		change -= twenties * TWENTY;

		// Calculate $10 bills
		tens = Math.floor(change / TEN);
		change -= tens * TEN;

		// Calculate $5 bills
		fives = Math.floor(change / FIVE);
		change -= fives * FIVE;

		// Calculate $1 bills
		ones = Math.floor(change / ONE);
		change -= ones * ONE;

		// Calculate quarters
		quarters = Math.floor(change / QUARTER);
		change -= quarters * QUARTER;
		// Fix the .9999 thing.
		change = Math.round(change * 100.0) / 100.0;

		// Calculate dimes
		dimes = Math.floor(change / DIME);
		change -= dimes * DIME;
		// Fix the .9999 thing.
		change = Math.round(change * 100.0) / 100.0;

		// Calculate nickels
		nickels = Math.floor(change / NICKEL);
		change -= nickels * NICKEL;
		// Fix the .9999 thing.
		change = Math.round(change * 100.0) / 100.0;

		// Calculate pennies
		pennies = Math.floor(change / PENNY);
		change -= pennies * PENNY;
		// Fix the .9999 thing.
		change = Math.round(change * 100.0) / 100.0;

	}

	private static void displayChange() {
		System.out.print("\nHere is your change: ");

		String change = "";
		
		// We cast all these to int to remove the silly .0
		
		// --------------- BILLS -----------------
		
		// HUNDREDS
		if (hundreds > 1) {
			change += (int)hundreds + " hundred dollar bills, ";
		}
		else if (hundreds == 1) {
			change += (int)hundreds + " hundred dollar bill, ";
		}
		
		// FIFTIES
		if (fifties > 1) {
			change += (int)fifties + " fifty dollar bills, ";
		}
		else if (fifties == 1) {
			change += (int)fifties + " fifty dollar bill, ";
		}
		
		// TWENTIES
		if (twenties > 1) {
			change += (int)twenties + " twenty dollar bills, ";
		}
		else if (twenties == 1) {
			change += (int)twenties + " twenty dollar bill, ";
		}
		
		// TENS
		if (tens > 1) {
			change += (int)tens + " ten dollar bills, ";
		}
		else if (tens == 1) {
			change += (int)tens + " ten dollar bill, ";
		}
		
		// FIVES
		if (fives > 1) {
			change += (int)fives + " five dollar bills, ";
		}
		else if (fives == 1) {
			change += (int)fives + " five dollar bill, ";
		}
		
		// ONES
		if (ones > 1) {
			change += (int)ones + " one dollar bills, ";
		}
		else if (ones == 1) {
			change += (int)ones + " one dollar bill, ";
		}

		// ------------ CHANGE ----------------
		
		// QUARTERS
		if (quarters > 1) {
			change += (int)quarters + " quarters, ";
		}
		else if (quarters == 1) {
			change += (int)quarters + " quarter, ";
		}
		
		// DIMES
		if (dimes > 1) {
			change += (int)dimes + " dimes, ";
		}
		else if (dimes == 1) {
			change += (int)dimes + " dime, ";
		}
		
		// NICKELS
		if (nickels > 1) {
			change += (int)nickels + " nickels, ";
		}		
		else if (nickels == 1) {
			change += (int)nickels + " nickel, ";
		}
		
		// PENNIES
		if (pennies > 1) {
			change += (int)pennies + " pennies. ";
		}
		else if (pennies == 1) {
			change += (int)pennies + " penny. ";
		}

		// Replace the ',' with a '.' and add some new lines
		change = change.substring(0, change.length() - 2) + ".\n\n";

		System.out.println(change);
	}
	
	private static void shutRegisterDown() {
		// Just some extra stuff for fun
		System.out.println("Shutting down cash register. . .");
		System.out.println("Total transactions today: " + transactions);
		System.out.printf("Total sales: %.2f\n", totalPurchases);
	}

	private static void cleanup() {
		shutRegisterDown();
		
		// Be nice and always clean up your stuff.
		// Close the scanner.
		sc.close();
	}
}
