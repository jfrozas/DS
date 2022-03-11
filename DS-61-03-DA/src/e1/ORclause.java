package e1;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class ORclause implements Searchclause{

    Searchclause clause1;
    Searchclause clause2;

    public ORclause(Searchclause clause1, Searchclause clause2) {
        this.clause1 = clause1;
        this.clause2 = clause2;
    }

    private List<Ticket> auxOR(List<Ticket> l1, List<Ticket> l2){
        Set<Ticket> aux = new HashSet<>(l1);
        aux.addAll(l2);
        return new ArrayList<>(aux);
    }

    @Override
    public List<Ticket> search(List<Ticket> ticketList) {
        return auxOR(clause1.search(ticketList),clause2.search(ticketList));
    }
}
