# Cottage-Booking-System
A Java console-based cottage booking application developed for my first-year university programming coursework.
# Cottage Booking System

A Java console-based application developed as part of my first-year Introduction to Programming module (4300COMP) at Liverpool John Moores University.

This was my first university programming coursework, where I was tasked with creating a booking system that allows users to search for and reserve holiday cottages based on their requirements.

The project gave me an opportunity to apply what I had learned about Java, object-oriented programming, file handling and input validation.

## Features

- **Search for cottages:** Find available cottages based on type, number of guests, maximum nightly price, sea view and pet preferences.
- **Reserve cottages:** Book an available cottage using an email address.
- **Cancel reservations:** Cancel an existing booking using the cottage number and registered email address.
- **View reservations:** Display cottages that are currently booked.
- **Next-best match:** Suggest an alternative cottage when no exact match is available.
- **Input validation:** Check user inputs and prompt again when invalid options are entered.
- **File storage:** Load cottage information from a text file and save updated reservations when the application closes.

## Technologies Used

- Java
- Object-Oriented Programming (OOP)
- ArrayList
- File I/O (FileReader, FileWriter, Scanner and PrintWriter)
- Eclipse IDE

## How It Works

The application uses a menu with four options:

1. Reserve a cottage
2. Cancel a reservation
3. View cottage reservations
4. Quit

When searching for a cottage, users enter their preferences. The application compares these against the available cottages and displays matching options.

If no exact match is found, the system searches for an alternative based on how many requirements it meets.

Cottage information is stored in `cottages.txt`, which contains 18 sample cottages. Reservations are saved back to the file when the user exits the application through the main menu.

## Running the Project

1. Clone or download the repository.
2. Import the project into Eclipse as an existing Java project.
3. Ensure `cottages.txt` is in the project's root directory.
4. Run `cottageBookingSystem.java`.
5. Follow the instructions displayed in the console.

## What I Learned

As my first university programming coursework, this project helped me develop a better understanding of structuring a Java application beyond basic programming exercises.

I gained experience creating classes and objects, working with ArrayLists, reading and writing data to files, and breaking functionality into separate methods.

It also introduced me to the importance of validating user input and thinking about how someone would interact with an application, rather than just whether the code works.

## Improvements

After receiving feedback on my original submission, I revisited the project to identify areas I could improve.

These included simplifying the Yes/No validation to handle different capitalisations correctly and reviewing how the application presents information to users.

There are still opportunities to improve the booking experience, particularly by making cottage information easier to read, allowing users to decline alternative recommendations and simplifying the cancellation process.

This project represents an early stage of my programming experience and provides a starting point to reflect on how my approach to software development has progressed.
