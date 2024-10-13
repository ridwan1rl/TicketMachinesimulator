class TicketPrintingSystem {
    public static void main(String[] args) {
        // Create an instance of TicketMachine
        TicketMachine ticketMachine = new TicketMachine();

        // Create a ThreadGroup for passengers
        ThreadGroup passengerGroup = new ThreadGroup("PassengerGroup");

        // Create four Passenger threads
        Passenger passenger1 = new Passenger(ticketMachine, 3);
        Passenger passenger2 = new Passenger(ticketMachine, 3);
        Passenger passenger3 = new Passenger(ticketMachine, 3);
        Passenger passenger4 = new Passenger(ticketMachine, 3);

        // Assign threads to the passenger group
        Thread threadPassenger1 = new Thread(passengerGroup, passenger1);
        Thread threadPassenger2 = new Thread(passengerGroup, passenger2);
        Thread threadPassenger3 = new Thread(passengerGroup, passenger3);
        Thread threadPassenger4 = new Thread(passengerGroup, passenger4);

        // Start all passenger threads
        threadPassenger1.start();
        threadPassenger2.start();
        threadPassenger3.start();
        threadPassenger4.start();

        // Create and start the TicketPaperTechnician thread
        Thread threadPaperTechnician = new Thread(new TicketPaperTechnician(ticketMachine));
        threadPaperTechnician.start();

        // Create and start the TicketTonerTechnician thread
        Thread threadTonerTechnician = new Thread(new TicketTonerTechnician(ticketMachine));
        threadTonerTechnician.start();


        try {
            threadPassenger1.join();
            threadPassenger2.join();
            threadPassenger3.join();
            threadPassenger4.join();
        } catch (InterruptedException I) {


        }

        System.out.println("All passengers have finished. Program terminating.");

        ticketMachine.printFinalStatus();


    }
}


