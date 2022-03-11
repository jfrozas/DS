package e2;



import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;



import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class ApartmentAppTest {

        private ApartmentApp app;
        Advertisement a0,a1,a2,a3,a4;

        @BeforeEach
        void setUp() {
                app = new ApartmentApp();
                app.insertApartment("Calle Rosalia de castro 2",100,100,3,1, 4);
                app.insertApartment("Calle Ramon y Cajal 37",200,99,6,2,5,7);
                app.insertApartment("Avenida de Oza 35",5,99,1,5,1,7,100,5,3);
                app.insertApartment("Avenida de Oza 35",5,99,1,5,7,1,5,3,100);
                app.insertApartment("Ronda de Outeiro 44",550,690,2,3,22,6,5);

                a0 = app.getAd(0);
                a1 = app.getAd(1);
                a2 = app.getAd(2);
                a3 = app.getAd(3);
                a4 = app.getAd(4);

        }

        @Test
        void InvalidAdvertisement(){
                 assertThrows(IllegalArgumentException.class, () ->app.insertApartment(null,150,100,3,1,4));        //null location
                 assertThrows(IllegalArgumentException.class, () ->app.insertApartment("Location",0,230,2,1,6));
                 assertThrows(IllegalArgumentException.class, () ->app.insertApartment("Location",-800,230,2,1,6));
                 assertThrows(IllegalArgumentException.class, () ->app.insertApartment("Location",2002,0,2,1,6));
                 assertThrows(IllegalArgumentException.class, () ->app.insertApartment("Location",2002,-3,2,1,6));
                 assertThrows(IllegalArgumentException.class, () ->app.insertApartment("Location",20,230,0,1,6));
                 assertThrows(IllegalArgumentException.class, () ->app.insertApartment("Location",460,230,-2,1,6));

                 // Test wrong number of parking spots
                 assertThrows(IllegalArgumentException.class, () ->app.insertApartment("Location",13560,230,2,0,2,3));
                 assertThrows(IllegalArgumentException.class, () ->app.insertApartment("Location",120,230,2,1,6,3,5));
                 assertThrows(IllegalArgumentException.class, () ->app.insertApartment("Location",120,230,2,6,3,1));
                 assertThrows(IllegalArgumentException.class, () ->app.insertApartment("Location",120,230,2,-2,3,5));

                 // Test any parking spot with negative value
                 assertThrows(IllegalArgumentException.class, () ->app.insertApartment("Location",120,230,2,3,2,5,-7));
                 assertThrows(IllegalArgumentException.class, () ->app.insertApartment("Location",120,230,2,3,2,0,7));

                // Test wrong index in getAd
                assertThrows(IllegalArgumentException.class, () ->app.getAd(100));

                //test equals
                assertEquals(a2,a3); /* Two ads with the same data but different reference
                                        numbers are actually the same apartment*/
        }


        @Test
        void TestOrder_naturalOrder(){
                assertEquals(app.getAd(0),a0); // Rosalia de Castro 2
                assertEquals(app.getAd(1),a1); // Ramon y Cajal 37
                assertEquals(app.getAd(2),a2); // Avenida de Oza 35
                assertEquals(app.getAd(3),a3); // Avenida de Oza 35
                assertEquals(app.getAd(4),a4); // Ronda de Outeiro 44
        }

        @Test
        void TestOrder_basePrice(){

                app.changeOrderingCriteria(new OrderBasePrice());
                app.sortList();


                boolean Ordered=true;
                for (int i=0; i <app.len()-1; i++ ){
                        if (app.getAd(i).getBasePrice()>app.getAd(i+1).getBasePrice())
                                Ordered=false;
                }
                assertTrue(Ordered);
        }

        @Test
        void TestOrder_totalPrice(){
                app.changeOrderingCriteria(new OrderTotalPrice());
                app.sortList();


                boolean Ordered=true;
                for (int i=0; i <app.len()-1; i++ ){
                        if (app.getAd(i).getTotalPrice()>app.getAd(i+1).getTotalPrice())
                                Ordered=false;
                }
                assertTrue(Ordered);


        }

        @Test
        void TestOrder_size(){

                app.changeOrderingCriteria(new OrderSize());
                app.sortList();

                boolean Ordered=true;
                for (int i=0; i <app.len()-1; i++ ){
                        if (app.getAd(i).getSize()>app.getAd(i+1).getSize())
                                Ordered=false;
                }
                assertTrue(Ordered);

        }

        @Test
        void TestOrder_beds(){

                app.changeOrderingCriteria(new OrderBeds());
                app.sortList();

                boolean Ordered=true;
                for (int i=0; i <app.len()-1; i++ ){
                        if (app.getAd(i).getBeds()>app.getAd(i+1).getBeds())
                                Ordered=false;
                }
                assertTrue(Ordered);


        }

        @Test
        void TestOrder_All(){

                assertEquals(app.getAd(0),a0); // Rosalia de Castro 2
                assertEquals(app.getAd(1),a1); // Ramon y Cajal 37
                assertEquals(app.getAd(2),a2); // Avenida de Oza 35
                assertEquals(app.getAd(3),a3); // Avenida de Oza 35
                assertEquals(app.getAd(4),a4); // Ronda de Outeiro 44

                // changed criteria to basePrice
                app.changeOrderingCriteria(new OrderBasePrice());
                app.sortList();
                boolean Ordered=true;
                for (int i=0; i <app.len()-1; i++ ){
                        if (app.getAd(i).getBasePrice()>app.getAd(i+1).getBasePrice())
                                Ordered=false;
                }
                assertTrue(Ordered);

                // changed criteria to size
                app.changeOrderingCriteria(new OrderSize());
                app.sortList();
                Ordered=true;
                for (int i=0; i <app.len()-1; i++ ){
                        if (app.getAd(i).getSize()>app.getAd(i+1).getSize())
                                Ordered=false;
                }
                assertTrue(Ordered);


                // changed criteria to null (reference number)
                app.changeOrderingCriteria(null);
                app.sortList();

                assertEquals(app.getAd(0),a0); // Rosalia de Castro 2
                assertEquals(app.getAd(1),a1); // Ramon y Cajal 37
                assertEquals(app.getAd(2),a2); // Avenida de Oza 35
                assertEquals(app.getAd(3),a3); // Avenida de Oza 35
                assertEquals(app.getAd(4),a4); // Ronda de Outeiro 44




        }

}
