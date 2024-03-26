// Railway reservation app:
// - ac 
// - non-ac
// - seater

// 60 seats + 10wl

// 1. ticket booking
// 2. ticket cancellation
// 3. status checking

// modules:
// 1.booking
// 2.availability checking
// 3.cancellation
// 4.prepare Chart 

// seat class:
//     variables:
//     unique seat number
//     foreign key passenger id
//     foreign key train number
//     foreign key compartment number
//     isSeatOccupied
    
//     methods:
//     bookSeat()
//     cancelSeat()
//     getSeatStatus()

// // 60 seats, 10 wl
// compartment class
//     unique compartment id 
//     foreign key train number
//     type (ac/non-ac/seater)
//     remaining seats
//     remaining wl seats
//     isCompartmentFull - both seats and wl
    
//     methods:
//     getRemainingAvailableSeats()
//     updateAvailableSeats()

// // n trains - each train with 3 compartments
// train class:
//     unique train number
//     foreign key passenger id
//     ac compartment 
//     non-ac compartment
//     seater compartment
//     isTrainFull
    
    
    
// passenger class:
//     unique passenger id
//     foreign key train number
//     foreign key compartment number
//     foreign key seat number
//     hasTicket
    
//     methods:
//     bookTicket()
//     cancelTicket()
//     checkStatus()
//     prepareChart()

import java.util.*;

class Seat {
    int seatNo;
    boolean isSeatOccupied;
}

class Compartment {
    int compartmentId;
    char compartmentType;
    int remainingSeats;
    int remainingWLSeats;
    boolean isCompartmentFull;
    
    public Compartment(int compartmentId, char compartmentType) {
        this.compartmentId = compartmentId;
        this.compartmentType = compartmentType;
        this.remainingSeats = 60;
        this.remainingWLSeats = 10;
        this.isCompartmentFull = false;
    }
    
}

class Train {
    int trainId;
    Compartment ac;
    Compartment nonAc;
    Compartment seater;
    boolean isTrainFull;
    
    public Train(int trainId) {
        this.trainId = trainId;
        this.isTrainFull = false;
        this.ac  = new Compartment(1,'A');
        this.nonAc  = new Compartment(2,'N');
        this.seater  = new Compartment(3,'S');
    }
    
    public void updateTrainDetails(int remainingSeats, int remainingWLSeats, char compartmentChoice, boolean isCompartmentFull, boolean isTrainFull) {
        switch (compartmentChoice) {
            case 'A':
                this.ac.remainingSeats = remainingSeats;
                this.ac.remainingWLSeats = remainingWLSeats;
                this.ac.isCompartmentFull = isCompartmentFull;
                break;
            case 'N':
                this.nonAc.remainingSeats = remainingSeats;
                this.nonAc.remainingWLSeats = remainingWLSeats;
                this.nonAc.isCompartmentFull = isCompartmentFull;
                break;
            case 'S':
                this.seater.remainingSeats = remainingSeats;
                this.seater.remainingWLSeats = remainingWLSeats;
                this.seater.isCompartmentFull = isCompartmentFull;
                break;
        }
        this.isTrainFull = isTrainFull;
    }
    
    public int bookTrain(char compartmentChoice) {

        int remainingSeats=0, remainingWLSeats=0;
        boolean isTrainFull=this.isTrainFull, isCompartmentFull=false;
        
        if (this.isTrainFull) {
            System.out.println("Sorry! Train is full!");
            return -1;
        }
        
        switch(compartmentChoice) {
            case 'A':
                isCompartmentFull = this.ac.isCompartmentFull;
                remainingSeats = this.ac.remainingSeats;
                remainingWLSeats = this.ac.remainingWLSeats;
                if (this.ac.isCompartmentFull) {
                    System.out.println("Sorry! AC Compartment is full!");
                    return -1;
                }
                if (this.ac.remainingSeats==0) {
                    remainingWLSeats--;
                    System.out.println("Ticket wait listed!");
                }
                else if (this.ac.remainingWLSeats != 0){
                    remainingSeats--;
                    System.out.println("Ticket booked in AC Compartment!");
                }
                else {
                    isCompartmentFull = true;
                }
                break;
            
            case 'N':
                isCompartmentFull = this.nonAc.isCompartmentFull;
                remainingSeats = this.nonAc.remainingSeats;
                remainingWLSeats = this.nonAc.remainingWLSeats;
                if (this.nonAc.isCompartmentFull) {
                    System.out.println("Sorry! non - AC Compartment is full!");
                    return -1;
                }
                if (this.nonAc.remainingSeats==0) {
                    remainingWLSeats = this.nonAc.remainingWLSeats - 1;
                    System.out.println("Ticket wait listed!");
                }
                else if(this.nonAc.remainingWLSeats != 0) {
                    remainingSeats = this.nonAc.remainingSeats - 1;
                    System.out.println("Ticket booked in non - AC Compartment!");
                }
                else {
                    isCompartmentFull = true;
                }
                break;
                
            case 'S':
                isCompartmentFull = this.seater.isCompartmentFull;
                remainingSeats = this.seater.remainingSeats;
                remainingWLSeats = this.seater.remainingWLSeats;
                if (this.seater.isCompartmentFull) {
                    System.out.println("Sorry! Seater Compartment is full!");
                    return -1;
                }
                if (this.seater.remainingSeats==0) {
                    remainingWLSeats = this.seater.remainingWLSeats - 1;
                    System.out.println("Ticket wait listed!");
                }
                else if(this.seater.remainingWLSeats != 0) {
                    remainingSeats = this.seater.remainingSeats - 1;
                    System.out.println("Ticket booked in Seater Compartment!");
                }
                else {
                    isCompartmentFull = true;
                }
                break;
        }
        
        if (this.ac.isCompartmentFull && this.nonAc.isCompartmentFull && this.seater.isCompartmentFull) {
            isTrainFull = true;
        }
        
        // update with new remaining seats
        // update isFull - Compartment and train
        updateTrainDetails(remainingSeats, remainingWLSeats, compartmentChoice, isCompartmentFull, isTrainFull);
        return remainingSeats;
    }
    
    public void cancelTrain(char compartmentChoice, boolean hasTicket) {
        int remainingSeats=0, remainingWLSeats=0;
        boolean isTrainFull=this.isTrainFull, isCompartmentFull=false;
        
        if (hasTicket) {
            isTrainFull = false;
            isCompartmentFull = false;
        }
        
        switch(compartmentChoice) {
            case 'A':
                remainingSeats = this.ac.remainingSeats;
                remainingWLSeats = this.ac.remainingWLSeats;
                
                if (hasTicket) {
                    remainingSeats++;
                }
                else {
                    isCompartmentFull = this.ac.isCompartmentFull;
                    remainingWLSeats++;
                }
                break;
            
            case 'N':
                remainingSeats = this.nonAc.remainingSeats;
                remainingWLSeats = this.nonAc.remainingWLSeats;
                
                if (hasTicket) {
                    remainingSeats++;
                }
                else {
                    isCompartmentFull = this.nonAc.isCompartmentFull;
                    remainingWLSeats++;
                }
                break;
                
            case 'S':
                remainingSeats = this.seater.remainingSeats;
                remainingWLSeats = this.seater.remainingWLSeats;
                
                if (hasTicket) {
                    remainingSeats++;
                }
                else {
                    isCompartmentFull = this.seater.isCompartmentFull;
                    remainingWLSeats++;
                }
                break;
        }
        System.out.println("Ticket cancelled!");
        
        // update with new remaining seats
        // update isFull - Compartment and train
        updateTrainDetails(remainingSeats, remainingWLSeats, compartmentChoice, isCompartmentFull, isTrainFull);
    }
    
}

public class Passenger {
    
    int passengerId;
    boolean hasTicket;
    boolean hasWaitListedTicket;
    int trainId;
    char compartmentChoice;
    int seatNo;
    
    public Passenger(int passengerId) {
        this.passengerId = passengerId;
        this.hasTicket = false;
        this.hasWaitListedTicket = false;
        this.trainId = -1;
        this.compartmentChoice = 'Z';
        this.seatNo = -1;
    }
    
    public static void printTrainDetails(List<Train> trainsList) {
        System.out.println("Train ID        AC Compartment                  Non-AC Compartment          Seater Compartment                  Is Train Full?");
        System.out.println("--------        --------------                  ------------------          ------------------                  --------------");
        
        for (Train train: trainsList) {
            System.out.println(train.trainId + "         Seats-left    WL-Seats-left       Seats-left    WL-Seats-left       Seats-left    WL-Seats-left          "+ train.isTrainFull);
            System.out.println("                "+train.ac.remainingSeats+"           "+train.ac.remainingWLSeats+"               "+train.nonAc.remainingSeats+"           "+train.nonAc.remainingWLSeats+"                       "+train.seater.remainingSeats+"          "+train.seater.remainingWLSeats);
            System.out.println("                            ********************************************");
        }
    }
    
    public void updatePassengerDetails(boolean hasTicket, boolean hasWaitListedTicket, int trainId, char compartmentChoice, int seatNo){
        this.hasTicket = hasTicket;
        this.hasWaitListedTicket = hasWaitListedTicket;
        this.trainId = trainId;
        this.compartmentChoice = compartmentChoice;
        this.seatNo = seatNo;
    }
    public void prepareChart(){
        
    }
    
    public static void main(String[] args) {
        
        int choice, trainChoice, n=5, passengerId = 1, seatNo;
        char compartmentChoice;
        List<Train> trainsList = new ArrayList<Train>();
        List<Passenger> passengersList = new ArrayList<Passenger>();
        Scanner s = new Scanner(System.in);
        
        System.out.println("Welcome to Railway Reservation System!");
        
        // create trains - list of Train objects
        for (int i=0; i<n; i++) {
            Train t = new Train(i);
            trainsList.add(t);
        }
        
        while (true) {
            System.out.println("Enter your choice:\n1.Book ticket\n2.Cancel Ticket\n3.Check Status\n4.Prepare Chart");
            choice = s.nextInt();
            
            switch (choice) {
                // book ticket
                case 1:
                    // create Passengers
                    Passenger p = new Passenger(passengerId);
                    passengersList.add(p);
                    System.out.println("Your unique passenger ID: " + passengerId);
                    System.out.println("Choose a train - Available trains:\n0 1 2 3 4");
                    trainChoice = s.nextInt();
                    
                    if (trainChoice<0 || trainChoice>4) {
                        System.out.println("Invalid train choice!");
                        return;
                        
                    }
                    
                    System.out.println("Booking Train - " + trainChoice);
                    
                    System.out.println("Choose a compartment - Available compartments:\nA: AC\nN: Non-AC\nS: Seater");
                    compartmentChoice = s.next().charAt(0);
                    
                    if (compartmentChoice != 'A' && compartmentChoice != 'N' && compartmentChoice != 'S') {
                        System.out.println("Invalid compartment choice!");
                        return;
                    }
                    
                    seatNo = trainsList.get(trainChoice).bookTrain(compartmentChoice);
                    System.out.println("Your Seat No: " + (seatNo+1));
                    if (seatNo>0) {
                        p.updatePassengerDetails(true, false, trainChoice, compartmentChoice, seatNo+1);
                    }
                    else if (seatNo==0) {
                        p.updatePassengerDetails(false, true, trainChoice, compartmentChoice, seatNo+1);
                    }
                    
                    break;
                    
                case 2:
                    // if passenger exists
                    System.out.println("Enter passenger ID: ");
                    int currentPassengerId = s.nextInt();
                    
                    if (currentPassengerId>=1 && passengerId>=currentPassengerId) {
                        // only if booked
                        Passenger p1 = passengersList.get(currentPassengerId-1);
                        if(p1.hasTicket || p1.hasWaitListedTicket) {
                            trainChoice = p1.trainId;
                            compartmentChoice = p1.compartmentChoice;
                            seatNo = p1.seatNo;
                            System.out.println("Cancelling Train: " + trainChoice + " Compartment(A:AC, N:non-AC, S:Seater): " + compartmentChoice + "  Seat No: " + seatNo);
                            trainsList.get(trainChoice).cancelTrain(compartmentChoice, p1.hasTicket);
                            
                            p1.updatePassengerDetails(false, false, -1, 'Z', -1);
                        }
                        else {
                            System.out.println("Train not booked! You can't cancel!");
                            return;
                        }
                        
                    }
                    else {
                        System.out.println("passenger ID does not exist! You can't cancel!");
                        return;
                    }
                    
                    break;
                
                case 3:
                    System.out.println("checking current Status...");
                    printTrainDetails(trainsList);
                    break;
                    
                case 4:
                    break;
                    
                default:
                    System.out.println("Invalid choice");
                    return;
                    
            }
            passengerId++;
        } 
    }
    
    
}
