# Railway Reservation System
Railway Reservation System is a Java application designed to manage ticket booking, cancellation, and status checking for trains with multiple compartments. It allows passengers to book tickets in different classes such as AC, non-AC, and seater, while also managing waitlisted tickets.

## Features
1. Ticket booking: Passengers can book tickets in available compartments of selected trains.
2. Ticket cancellation: Passengers can cancel their booked tickets, freeing up seats in the compartments.
3. Status checking: Users can check the availability status of seats and waitlisted seats in each compartment of all trains.

## Modules
1. Booking: Handles ticket booking functionality.
2. Availability checking: Manages the availability of seats and waitlisted seats in compartments.
3. Cancellation: Handles ticket cancellation functionality.

## Classes and Components
1. Seat: Represents individual seats in a compartment.
2. Compartment: Represents a compartment in a train, with details such as compartment ID, type, remaining seats, and remaining waitlisted seats.
3. Train: Represents a train with multiple compartments (AC, non-AC, seater).
4. Passenger: Represents a passenger with functionalities like ticket booking, cancellation, and status checking.

## Usage
1. Compile the Java files: javac Passenger.java
2. Run the application: java Passenger
3. Follow the on-screen prompts to interact with the Railway Reservation System.
