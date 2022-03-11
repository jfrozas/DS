package e1;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

final public class TicketDate implements Searchclause {

    final private LocalDate date;

    public TicketDate(LocalDate date) {
        if (date==null) throw new IllegalArgumentException();
        this.date = date;
    }

    public LocalDate getDate() {
        return date;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof TicketDate that)) return false;
        return date.equals(that.getDate());
    }

    @Override
    public int hashCode() {
        return Objects.hash(date);
    }


    @Override
    public List<Ticket> search(List<Ticket> ticketList) {
        List<Ticket> auxlist = new ArrayList<>();

        for (Ticket t : ticketList){
            if (t.getDate().equals(this)){
                auxlist.add(t);
            }
        }
        return auxlist;
    }
}
