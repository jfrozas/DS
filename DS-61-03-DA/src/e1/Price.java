package e1;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

final public class Price implements Searchclause{
    final private Double price;

    public Price(Double price) {
        if (price<0) throw new IllegalArgumentException();
        this.price = price;
    }

    public Double getPrice() {
        return price;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Price price1)) return false;
        return price.equals(price1.price);
    }

    @Override
    public int hashCode() {
        return Objects.hash(price);
    }


    @Override
    public List<Ticket> search(List<Ticket> ticketList) {
        List<Ticket> auxlist = new ArrayList<>();

        for (Ticket t : ticketList){
            if (t.getPrice().getPrice()<=price){
                auxlist.add(t);
            }
        }

        return auxlist;
    }
}
