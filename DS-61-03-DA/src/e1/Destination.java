package e1;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

final public class Destination implements Searchclause {
    final private String destination;

    public Destination(String destination) {
        if (destination==null) throw new IllegalArgumentException();
        this.destination = destination;
    }

    public String getdestination() {
        return destination;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Destination d2)) return false;
        return destination.equals(d2.getdestination());
    }

    @Override
    public int hashCode() {
        return Objects.hash(destination);
    }

    @Override
    public List<Ticket> search(List<Ticket> ticketList) {
        List<Ticket> auxlist = new ArrayList<>();

        for (Ticket t : ticketList){
            if (t.getDestination().equals(this)){
                auxlist.add(t);
            }
        }

        return auxlist;
    }
}
