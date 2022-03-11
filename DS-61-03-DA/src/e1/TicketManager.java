package e1;

import java.util.Collections;
import java.util.List;

public class TicketManager {

    private final List<Ticket> ticketList;


    public TicketManager(List<Ticket> ticketList) {
        this.ticketList = ticketList;
    }


    List<Ticket> search(Searchclause clause){
        if (clause==null) throw new IllegalArgumentException();
        List<Ticket> aux = clause.search(ticketList);
        Collections.sort(aux);
        return aux;
    }
}
