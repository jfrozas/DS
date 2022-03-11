package e1;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class TicketManagementTest {

    private TicketManager manager;
    Ticket t1,t2,t3,t4,t5;

    @BeforeEach
    void setUp() {

        t1 = new Ticket(new Origin("Madrid"),new Destination("Coruña"), new Price(22.99), new TicketDate(LocalDate.of(2021,12,7)));
        t2 = new Ticket(new Origin("Huelva"),new Destination("Sevilla"), new Price(11.49), new TicketDate(LocalDate.of(2021,12,7)));
        t3 = new Ticket(new Origin("Madrid"),new Destination("Barcelona"), new Price(3.5), new TicketDate(LocalDate.of(2021,12,9)));
        t4 = new Ticket(new Origin("Sevilla"),new Destination("Barcelona"), new Price(1.00), new TicketDate(LocalDate.of(2021,12,8)));
        t5 = new Ticket(new Origin("Madrid"),new Destination("Francia"), new Price(42.99), new TicketDate(LocalDate.of(2021,12,10)));

        List<Ticket> list = new ArrayList<>(Arrays.asList(t1,t2,t3,t4,t5));
        manager = new TicketManager(list);
    }

    @Test
    void testInit(){
        assertThrows(IllegalArgumentException.class, () -> new Origin(null));
        assertThrows(IllegalArgumentException.class, () -> new Destination(null));
        assertThrows(IllegalArgumentException.class, () -> new Price(-3.));
        assertThrows(IllegalArgumentException.class, () -> new TicketDate(null));

        Origin origin = new Origin("Madrid");
        Destination dest = new Destination("Bilbao");
        Price price = new Price(19.99);
        TicketDate ticketdate =  new TicketDate(LocalDate.of(2022,1,1));
        Ticket t = new Ticket(origin,dest, price, ticketdate);

        assertThrows(IllegalArgumentException.class, () -> new Ticket(null, dest, price,ticketdate));
        assertThrows(IllegalArgumentException.class, () -> new Ticket(origin,null, price, new TicketDate(LocalDate.of(2022,1,1))));
        assertThrows(IllegalArgumentException.class, () -> new Ticket(origin,dest, null, ticketdate));
        assertThrows(IllegalArgumentException.class, () -> new Ticket(origin,dest, price, null));


        /*Test of the different classes equals method*/
        assertNotEquals(origin, dest);
        assertNotEquals(dest,price);
        assertNotEquals(price,ticketdate);
        assertNotEquals(price,ticketdate);
        assertNotEquals(ticketdate,origin);
        assertNotEquals(t, origin);
    }

    @Test
    void basicSearch(){

        List<Ticket> result1 = new ArrayList<>(Arrays.asList(t1,t3,t5));
        List<Ticket> result2 = new ArrayList<>(Arrays.asList(t2,t3,t4));
        List<Ticket> result3 = new ArrayList<>(Arrays.asList(t1,t2));
        List<Ticket> result4 = new ArrayList<>(Arrays.asList(t3,t4));
        Collections.sort(result1);
        Collections.sort(result2);
        Collections.sort(result3);
        Collections.sort(result4);

        assertEquals(result1,manager.search(t1.getOrigin()));
        assertEquals(result2,manager.search(t2.getPrice()));
        assertEquals(result3,manager.search(t1.getDate()));
        assertEquals(result4,manager.search(t3.getDestination()));

        assertThrows(IllegalArgumentException.class, () -> manager.search(null));
    }

    @Test
    void OR_AND_Search(){
        Searchclause clause1 =  new ORclause(t1.getOrigin(),t2.getOrigin());
        Searchclause clause2 =  new ORclause(t1.getDestination(),t3.getDestination());
        Searchclause clause3 =  new ORclause(new Price(10.), t5.getDate());
        Searchclause clause4 =  new ANDclause(new Price(10.),t3.getOrigin());


        List<Ticket> result1 = new ArrayList<>(Arrays.asList(t1,t2,t3,t5));
        List<Ticket> result2 = new ArrayList<>(Arrays.asList(t1,t3,t4));//
        List<Ticket> result3 = new ArrayList<>(Arrays.asList(t3,t4,t5));//
        List<Ticket> result4 = new ArrayList<>(List.of(t3));
        Collections.sort(result1);
        Collections.sort(result2);
        Collections.sort(result3);
        Collections.sort(result4);

        assertEquals(result1,manager.search(clause1));
        assertEquals(result2,manager.search(clause2));
        assertEquals(result3,manager.search(clause3));
        assertEquals(result4,manager.search(clause4));
    }


    @Test
    void complexSearch(){
        Searchclause clause1 = new ORclause(t1.getDestination(),t3.getDestination());
        Searchclause clause2 = new ORclause(new Price(10.), t5.getDate());
        Searchclause clausefinal = new ANDclause(clause1,clause2);

        List<Ticket> result = new ArrayList<>(Arrays.asList(t3,t4));
        Collections.sort(result);

        assertEquals(result,manager.search(clausefinal));
    }




}
