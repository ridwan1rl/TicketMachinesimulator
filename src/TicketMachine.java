
class Ticket {

}

// ServiceTicketMachine interface
interface ServiceTicketMachine {
    int MAX_TONER_CAPACITY = 3;
    int MAX_TICKET_CAPACITY = 3;

    // Methods for refilling toner and tickets
    void refillToner(int amount);
    void refillTickets(int amount);

    // Getter methods
    int getTonerLevel();
    int getTicketCount();
}


public class TicketMachine implements ServiceTicketMachine {
    private int tonerLevel;
    private int ticketCount;

    public TicketMachine() {
        // Initialize toner level and ticket count
        this.tonerLevel = MAX_TONER_CAPACITY;
        this.ticketCount = MAX_TICKET_CAPACITY;
    }


    public synchronized void printTicket(int amount, String passengerName) {
        if (amount <= 0) {
            System.out.println("Invalid number of tickets to print.");
            return;
        }

        while (amount > ticketCount || amount > tonerLevel) {
            // If there are insufficient tickets or toner, wait for a refill
            System.out.println("Insufficient tickets or toner for " + passengerName + ". Waiting for refill...");
            try {
                wait(); // Release the lock and wait for a notification

            } catch (InterruptedException I) {

            }
        }


        System.out.println("Printing " + amount + " ticket(s) for " + passengerName + "...");

        tonerLevel -= amount;
        ticketCount -= amount;


    }



    public synchronized void refillToner(int amount) {
        if (amount <= 0) {
            System.out.println("Invalid toner refill amount.");
            return;
        }


        System.out.println("Refilling toner by " + amount + " units.");
        tonerLevel = Math.min(tonerLevel + amount, MAX_TONER_CAPACITY);

        notifyAll();
    }


    public synchronized void refillTickets(int amount) {
        if (amount <= 0) {
            System.out.println("Invalid ticket refill amount.");
            return;
        }


        System.out.println("Refilling tickets by " + amount + " units.");
        ticketCount = Math.min(ticketCount + amount, MAX_TICKET_CAPACITY);

        // Notify waiting passengers that tickets are available
        notifyAll();
    }



    public synchronized void printFinalStatus() {
        System.out.println("Final Status:");
        System.out.println("Toner Level: " + tonerLevel);
        System.out.println("Ticket Count: " + ticketCount);
    }



    public synchronized int getTonerLevel() {
        return tonerLevel;
    }


    public synchronized int getTicketCount() {
        return ticketCount;
    }
}



