import java.util.Random;


class Passenger implements Runnable {
    private static int passengerCount = 1;
    private TicketMachine ticketMachine;
    private int ticketsToPrint;
    private String passengerName;

    public Passenger(TicketMachine ticketMachine, int ticketsToPrint) {
        this.ticketMachine = ticketMachine;
        this.ticketsToPrint = ticketsToPrint;
        this.passengerName = "Passenger " + passengerCount++;
    }

    @Override
    public void run() {
        System.out.println(passengerName + " is printing " + ticketsToPrint + " ticket(s).");

        // Simulate random sleep intervals between printing requests
        Random random = new Random();
        try {
            for (int i = 0; i < ticketsToPrint; i++) {
                //  printing each ticket
                ticketMachine.printTicket(1, passengerName);
                //  random sleep interval
                Thread.sleep(random.nextInt(401) + 100);
            }
        } catch (InterruptedException I) {

        }
    }


}

