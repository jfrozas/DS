package e3;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class NetworkTest {

    private Network networkT,networkM;

    @BeforeEach
    void setUp() {
        networkT = new Network(new NetworkTables());
        networkM = new Network(new NetworkMap());

        networkM.addUser("Juan","Football","Wrestling");
        networkM.addUser("Maria","Manga","Anime","League of Legends","WoW","CSgo");
        networkM.addUser("Carlos","Anime","Shopping","Movies");
        networkM.addUser("Enrique","Basketball","League of Legends");
        networkM.addUser("Laura","Football","Anime");

        networkT.addUser("Juan","Football","Wrestling");
        networkT.addUser("Maria","Manga","Anime","League of Legends","WoW","CSgo");
        networkT.addUser("Carlos","Anime","Shopping","Movies");
        networkT.addUser("Enrique","Basketball","League of Legends");
        networkT.addUser("Laura","Football","Anime");

    }


    void Test_insertionAux(Network netManagerType){
        assertEquals("""
                USERNAME: INTERESTS
                ---------------------
                Juan: Football, Wrestling
                Maria: Manga, Anime, League of Legends, WoW, CSgo
                Carlos: Anime, Shopping, Movies
                Enrique: Basketball, League of Legends
                Laura: Football, Anime""",netManagerType.toString());

        assertThrows(IllegalArgumentException.class, () ->netManagerType.addUser(null,"Football","Wrestling"));
        assertThrows(IllegalArgumentException.class, () ->netManagerType.addUser("name",null,"Wrestling"));
        assertThrows(IllegalArgumentException.class, () ->netManagerType.addUser("name"));
    }

    @Test void Test_insertion(){
        Test_insertionAux(networkT);
        Test_insertionAux(networkM);
    }

    void Test_removalAux(Network netManagerType){
        netManagerType.removeUser("Maria");
        assertEquals("""
                USERNAME: INTERESTS
                ---------------------
                Juan: Football, Wrestling
                Carlos: Anime, Shopping, Movies
                Enrique: Basketball, League of Legends
                Laura: Football, Anime""",netManagerType.toString());

        netManagerType.removeUser("Juan");

        assertEquals("""
                USERNAME: INTERESTS
                ---------------------
                Carlos: Anime, Shopping, Movies
                Enrique: Basketball, League of Legends
                Laura: Football, Anime""",netManagerType.toString());

        // WRONG NAME
        assertThrows(IllegalArgumentException.class, () ->netManagerType.removeUser("name"));
        assertThrows(IllegalArgumentException.class, () ->netManagerType.removeUser(null));
    }

    @Test void Test_removal(){
        Test_removalAux(networkT);
        Test_removalAux(networkM);
    }

    void Test_getInterestsAux(Network netManagerType){
        TopicOfInterest[] array1= {new TopicOfInterest("Football"),new TopicOfInterest("Anime")};
        TopicOfInterest[] array2= {new TopicOfInterest("Anime"),new TopicOfInterest("Shopping"),
                new TopicOfInterest("Movies")};

        assertEquals(new ArrayList<>(Arrays.asList(array1)), netManagerType.getUserInterests("Laura"));
        assertEquals(new ArrayList<>(Arrays.asList(array2)), netManagerType.getUserInterests("Carlos"));
        assertThrows(IllegalArgumentException.class, () -> netManagerType.getUserInterests("name"));
        assertThrows(IllegalArgumentException.class, () -> netManagerType.getUserInterests(null));

    }
    @Test void Test_getInterests(){
        Test_getInterestsAux(networkT);
        Test_getInterestsAux(networkM);
    }

    void Test_modifyInterestsAux(Network netManagerType){
        /* ADD INTEREST TEST*/
        TopicOfInterest[] array2_1= {new TopicOfInterest("Anime"),new TopicOfInterest("Shopping"),
                new TopicOfInterest("Movies"), new TopicOfInterest("Manga")};

        netManagerType.addInterest("Carlos","Manga");
        assertEquals(new ArrayList<>(Arrays.asList(array2_1)), netManagerType.getUserInterests("Carlos"));

        assertThrows(IllegalArgumentException.class, () -> netManagerType.addInterest("Carlos",null));
        assertThrows(IllegalArgumentException.class, () -> netManagerType.addInterest(null,"Interest"));
        assertThrows(IllegalArgumentException.class, () -> netManagerType.addInterest("Jose","Interest"));


        /* REMOVE INTEREST TEST*/
        TopicOfInterest[] array2_2= {new TopicOfInterest("Shopping"), new TopicOfInterest("Movies")};
        netManagerType.removeInterest("Carlos","Anime");
        netManagerType.removeInterest("Carlos","Manga");
        assertEquals(new ArrayList<>(Arrays.asList(array2_2)), netManagerType.getUserInterests("Carlos"));

        assertThrows(IllegalArgumentException.class, () -> netManagerType.removeInterest("Carlos",null));
        assertThrows(IllegalArgumentException.class, () -> netManagerType.removeInterest(null,"Interest"));
        assertThrows(IllegalArgumentException.class, () -> netManagerType.removeInterest("Jose","Interest"));
        assertThrows(IllegalArgumentException.class, () -> netManagerType.removeInterest("Fernando","Interest"));

    }

    @Test
    void Test_modifyInterests(){
        Test_modifyInterestsAux(networkM);
        Test_modifyInterestsAux(networkT);
    }

    void Test_compareInterestsAux(Network netManagerType){
        /*TEST USERS INTERESTED IN SAME TOPIC*/
        String[] footballUsers = {"Juan","Laura"}, LoLUsers = {"Maria","Enrique"}, AnimeUsers = {"Maria","Carlos", "Laura"};

        assertEquals(new ArrayList<>(Arrays.asList(footballUsers)), netManagerType.getUsersByInterest("Football"));
        assertEquals(new ArrayList<>(Arrays.asList(LoLUsers)), netManagerType.getUsersByInterest("League of Legends"));
        assertEquals(new ArrayList<>(Arrays.asList(AnimeUsers)), netManagerType.getUsersByInterest("Anime"));

        assertEquals(new ArrayList<String>(),netManagerType.getUsersByInterest("Tennis"));
        assertThrows(IllegalArgumentException.class, () -> netManagerType.getUsersByInterest(null));


        /* TEST COMMON INTERESTS */
        netManagerType.addUser("Marcos","League of Legends", "Anime","Manga");

        TopicOfInterest[] commonMarcosMaria = {new TopicOfInterest("League of Legends"),new TopicOfInterest("Anime"),
                new TopicOfInterest("Manga")}, commonLauraJuan={new TopicOfInterest("Football")};

        assertEquals(new ArrayList<>(Arrays.asList(commonMarcosMaria)), netManagerType.getCommonInterests("Marcos","Maria"));
        assertEquals(new ArrayList<>(Arrays.asList(commonLauraJuan)), netManagerType.getCommonInterests("Laura","Juan"));

        assertThrows(IllegalArgumentException.class, () -> netManagerType.getCommonInterests(null,"Juan"));
        assertThrows(IllegalArgumentException.class, () -> netManagerType.getCommonInterests("Juan",null));
        assertThrows(IllegalArgumentException.class, () -> netManagerType.getCommonInterests("MariCarmen","Juan"));
    }
    @Test
    void Test_compareInterests(){
        Test_compareInterestsAux(networkT);
        Test_compareInterestsAux(networkM);
    }

    void Test_AllInterestsAux(Network netManagerType){

        TopicOfInterest[] topicList={new TopicOfInterest("Football"),new TopicOfInterest("Wrestling"),
                new TopicOfInterest("Manga"), new TopicOfInterest("Anime"),
                new TopicOfInterest("League of Legends"), new TopicOfInterest("WoW"), new TopicOfInterest("CSgo"),
                new TopicOfInterest("Shopping"), new TopicOfInterest("Movies"), new TopicOfInterest("Basketball")};

        ArrayList<TopicOfInterest> topics = new ArrayList<>(Arrays.asList(topicList));

        assertEquals(topics, netManagerType.getAllTopics());

        netManagerType.removeInterest("Maria","WoW");
        topics.remove(new TopicOfInterest("WoW"));

        assertEquals(topics, netManagerType.getAllTopics());

    }
    @Test
    void Test_AllInterests(){
        Test_AllInterestsAux(networkT);
        Test_AllInterestsAux(networkM);
    }



}
