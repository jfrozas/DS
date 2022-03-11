package e1;

import java.util.ArrayList;
import java.util.List;

public class ANDclause implements Searchclause {

    Searchclause clause1;
    Searchclause clause2;

    public ANDclause(Searchclause clause1, Searchclause clause2) {
        this.clause1 = clause1;
        this.clause2 = clause2;
    }

    private List<Ticket> auxAND(List<Ticket> l1, List<Ticket> l2){
        List<Ticket> l1aux = new ArrayList<>(l1);
        l1aux.retainAll(l2);
        return l1aux;
    }

    @Override
    public List<Ticket> search(List<Ticket> ticketList) {
        return auxAND(clause1.search(ticketList),clause2.search(ticketList));
    }
}
