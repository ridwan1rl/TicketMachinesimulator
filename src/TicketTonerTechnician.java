class TicketTonerTechnician implements Runnable {
    private TicketMachine ticketMachine;

    public TicketTonerTechnician(TicketMachine ticketMachine) {
        this.ticketMachine = ticketMachine;
    }

    @Override
    public void run() {
        while (true) {
            // Check if toner level is below the maximum
            if (ticketMachine.getTonerLevel() < ServiceTicketMachine.MAX_TONER_CAPACITY) {
                // Simulate replacing the toner cartridge
                ticketMachine.refillToner(ServiceTicketMachine.MAX_TONER_CAPACITY - ticketMachine.getTonerLevel());
            }

            // Simulate the technician checking at regular intervals (every 1000 milliseconds)
            try {
                Thread.sleep(1000);
            } catch (InterruptedException I) {

            }
        }
    }
}


