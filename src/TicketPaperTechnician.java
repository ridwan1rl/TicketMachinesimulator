class TicketPaperTechnician implements Runnable {
    private TicketMachine ticketMachine;

    public TicketPaperTechnician(TicketMachine ticketMachine) {
        this.ticketMachine = ticketMachine;
    }

    @Override
    public void run() {
        while (true) {
            // Check if ticket count is below the maximum
            if (ticketMachine.getTicketCount() < ServiceTicketMachine.MAX_TICKET_CAPACITY) {

                ticketMachine.refillTickets(ServiceTicketMachine.MAX_TICKET_CAPACITY - ticketMachine.getTicketCount());
            }

            try {
                Thread.sleep(500);
            } catch (InterruptedException I) {

            }
        }
    }
}



