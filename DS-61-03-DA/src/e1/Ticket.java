package e1;

import java.util.Objects;

final public class Ticket implements Comparable<Ticket>{

    final private Origin origin;
    final private Destination destination;
    final private Price price;
    final private TicketDate date;

    public Ticket(Origin origin, Destination destination, Price price, TicketDate date) {
        if (origin==null || destination==null || price==null || date==null)
            throw new IllegalArgumentException();
        this.origin = origin;
        this.destination = destination;
        this.price = price;
        this.date = date;
    }

    public Origin getOrigin() {
        return origin;
    }

    public Destination getDestination() {
        return destination;
    }

    public Price getPrice() {
        return price;
    }

    public TicketDate getDate() {
        return date;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Ticket ticket)) return false;
        return origin.equals(ticket.origin) && Objects.equals(destination, ticket.destination) && Objects.equals(price, ticket.price) && Objects.equals(date, ticket.date);
    }

    @Override
    public int hashCode() {
        return Objects.hash(origin, destination, price, date);
    }


    @Override
    public int compareTo(Ticket t) {
        return Integer.compare(this.hashCode(), t.hashCode());
    }
}
