package assignment_3;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Scanner;

public class cottageBookingSystem {
	static Scanner console = new Scanner(System.in);
	private static final String FILE_NAME = "cottages.txt";// Constant as i will use it throughout so its safer and
	// don't have to duplicate

	private static ArrayList<Cottages> cottages = new ArrayList<>();// list loaded from the file

	public static void main(String[] args) throws Exception {

		loadCottage();// first load all cottages before anything

		String choice = "";

		do {// repeating main menu

			System.out.println("\n- - Cottage Booking System - -");

			System.out.println("1 - Reserve Cottage");

			System.out.println("2 - Cancel Cottage");

			System.out.println("3 - View Cottage Reservations");

			System.out.println("Q - Quit");

			System.out.println("Pick:");

			choice = console.next().toUpperCase();

			switch (choice) {

			case "1":
				reserveCottage();
				break;

			case "2":
				cancelCottage();
				break;

			case "3":
				viewCottageReservations();
				break;
			case "Q":
				saveCottages(); // saves data back to file when application exit
				System.out.println("-- GOODBYE --");

				break;

			default:
				System.out.println("--Try Again--");
			}

		} while (!choice.equalsIgnoreCase("Q"));

	}

	private static void loadCottage() throws FileNotFoundException {

		try {
			Scanner read = new Scanner(new FileReader(FILE_NAME));

			while (read.hasNext()) {
				// Reads the file in the right format of "cottageNum cottageType maxOccupancy
				// cottagePrice hasSeaView allowPets eMail"
				int cottageNum = read.nextInt();
				String cottageType = read.next();
				int maxOccupancy = read.nextInt();
				double cottagePrice = read.nextDouble();
				boolean hasSeaView = read.nextBoolean();
				boolean allowPets = read.nextBoolean();
				String eMail = read.next();

				cottages.add(new Cottages(cottageNum, cottageType, maxOccupancy, cottagePrice, hasSeaView, allowPets,
						eMail));// adding a new cottage object to our list based on the guest input
			}

			read.close();

		} catch (Exception objRef) {// handles error for the File reader  
			System.out.println("Error loading cottages: " + objRef.toString());
		}
	}

	private static void saveCottages() throws Exception {
		try {
			PrintWriter print = new PrintWriter(new FileWriter(FILE_NAME));

			for (Cottages cottage : cottages) {
				print.println(cottage.toString());// overwrite the file in the right order
			}

			print.close();

		} catch (Exception objRef) {
			System.out.println("Error saving : " + objRef.toString());
		}
	}

	private static void reserveCottage() {
		// will first look for the exact match if there is none then it will look for
		// the closest based on the users output
		// each output have a while loop to stop the system from running until the
		// required information is put in
		System.out.println("\n-- Reserve Cottage --");

		System.out.println("\n Enter cottage type(Terraced|Detached|Bungalow)");

		String cottageType = console.next();

		while (!cottageType.equalsIgnoreCase("Terraced")
				&& (!cottageType.equalsIgnoreCase("Detached") && !cottageType.equalsIgnoreCase("Bungalow"))) {
			System.out.println("Please enter a valid cottage type: ");
			cottageType = console.next();
		}

		System.out.println("Enter number of guest");

		while (!console.hasNextInt()) {
			System.out.println("Enter a valid number please:");
			console.next();
		}
		int maxOccupancy = console.nextInt();

		System.out.print("Enter maximum price per night £: ");
		while (!console.hasNextDouble()) {
			System.out.println("Enter a valid price please:");
			console.next();
		}
		double price = console.nextDouble();

		System.out.println("Sea view required? (yes||no): ");
		String seaString = console.next();

		while (!seaString.equalsIgnoreCase("yes") && !seaString.equalsIgnoreCase("no")) {
			System.out.println("Invalid input. Please enter yes or no:");
			seaString = console.next();
		}

		boolean hasSeaView = seaString.equalsIgnoreCase("yes");
		// instead of true/false
		// as cleaner and better

		System.out.println("Are pets allowed? (yes/no): ");
		String petsString = console.next();

		while (!petsString.equalsIgnoreCase("yes") && !petsString.equalsIgnoreCase("no")) {
		    System.out.println("Invalid input. Please enter yes or no:");
		    petsString = console.next();
		}

		boolean allowPets = petsString.equalsIgnoreCase("yes");
		ArrayList<Cottages> matches = new ArrayList<Cottages>();// list of the cottages that meet the user requirement

		for (Cottages cottage : cottages) {
			// match the requirement to a free cottage
			if (cottage.getCottagePrice() <= price && cottage.getMaxOccupancy() >= maxOccupancy
					&& cottage.getCottageType().equalsIgnoreCase(cottageType) && cottage.getHasSeaView() == hasSeaView
					&& cottage.getAllowPets() == allowPets && cottage.geteMail().equalsIgnoreCase("free")) {

				matches.add(cottage);

			}
		}
		if (!matches.isEmpty()) {// lets the user book if there is at least one exact match
			System.out.println(matches.size() + " Matching cottages:");

			for (Cottages cottage : matches) {
				System.out.println(cottage);
			}

			System.out.println("Enter the cottage number you want to reserve:");
			int number = console.nextInt();

			System.out.println("Enter email");
			String eMail = console.next();
			while (!eMail.contains("@")) {
				System.out.println("invalid email, must contain '@'");
				eMail = console.next();
			}
			boolean booked = false;
			for (Cottages cottage : matches) {
				if (cottage.getCottageNum() == number) {
					cottage.seteMail(eMail);
					System.out.println("Cottage" + " " + number + " " + "has been reserved for" + " " + eMail);
					booked = true;
					break;
				}

			}
			if (!booked) {
				System.out.println("That cottage number was not on the list");
			}
		}

		if (matches.isEmpty()) {// if there is no exact the system will find the closest one
			System.out.println("No exact matches found. Searching for next best match...");

			int bestIndex = -1;// index of the most suitable cottage from the list for all cottages
			int bestMatch = 0;// keep score of the closest match

			for (int i = 0; i < cottages.size(); i++) {
				Cottages cottage = cottages.get(i);
				if (!cottage.isFree()) {// skips taken cottages
					continue;
				}
				// score out of 5 of how many requirements this cottage meet
				int score = cottage.nextBestMatch(cottageType, maxOccupancy, price, hasSeaView, allowPets);
				// loops till it finds the most suitable
				if (score > bestMatch) {
					bestMatch = score;
					bestIndex = i;
				}
			}
			if (bestIndex == -1) {
				System.out.println("No suitable cottages available at all.");
				return;// if there are no free cottages that match any requirement e.g. fully booked
			}
			Cottages best = cottages.get(bestIndex);

			System.out.println("\nNext best match (matches " + bestMatch + " out of 5 requirements):");
			System.out.println(best);

			System.out.println("\nEnter email");
			String eMailNextBestMatch = console.next();
			while (!eMailNextBestMatch.contains("@")) {
				System.out.println("invalid email :Must contain '@'");
				eMailNextBestMatch = console.next();
			}
			best.seteMail(eMailNextBestMatch);
			System.out.println("Cottage" + " " + " " + "has been reserved for" + " " + eMailNextBestMatch);
		}

	}

	private static void cancelCottage() {

		System.out.println("\n--Cancel Cottage Reservation--");

		System.out.print("Enter the cottage number you want to cancel:");
		int cottageNum = console.nextInt();

		System.out.println("Enter your email");
		String eMail = console.next();

		boolean bookingFound = false;
		for (Cottages cottage : cottages) {
			if (cottage.getCottageNum() == cottageNum && cottage.geteMail().equalsIgnoreCase(eMail)) {// if number and email match it will cancel
				cottage.seteMail("free");// return it to free in the file
				System.out.println("reseravtion for cottage" + " " + cottageNum + " " + "been canceled");

				bookingFound = true;
			}
		}

		if (!bookingFound) {
			System.out.println("Reservation not found under this email");
		}
	}

	private static void viewCottageReservations() {
		System.out.println("\n--View Booked Cottages--");

		boolean isThereAnyBooked = false;
		for (Cottages cottage : cottages) {
			if (!cottage.geteMail().equals("free")) {// shows all reserved cottages so all that are not free
				System.out.println(cottage);
				isThereAnyBooked = true;
			}
		}
		if (!isThereAnyBooked) {
			System.out.println("No cottages are booked");
		}
	}
}
