package e1;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

final public class Origin implements Searchclause{
    final private String origin;

    public Origin(String origin) {
        if (origin==null) throw new IllegalArgumentException();
        this.origin = origin;
    }

    public String getOrigin() {
        return origin;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Origin origin1)) return false;
        return origin.equals(origin1.getOrigin());
    }

    @Override
    public int hashCode() {
        return Objects.hash(origin);
    }

    @Override
    public List<Ticket> search(List<Ticket> ticketList){

        List<Ticket> auxlist = new ArrayList<>();

        for (Ticket t : ticketList){
            if (t.getOrigin().equals(this)){
                auxlist.add(t);
            }
        }
        return auxlist;
    }
}
