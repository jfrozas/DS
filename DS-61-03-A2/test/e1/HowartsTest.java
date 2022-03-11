package e1;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class HowartsTest {

    private School school, emptySchool;

    @BeforeEach
    void setUp() {
        school = new School();
        school.addStudent("Hermione","Granger", 18, 3, Resident.ResidentHouse.Gryffindor);
        school.addGhost("Bloody","Baron",109,1,Resident.ResidentHouse.Slytherin);
        school.addGhost("Fat","Friar",122,3,Resident.ResidentHouse.Hufflepuff);
        school.addGamekeeper("Rubeus","Hagrid",45,2);
        school.addStudent("Draco","Malfoy", 18, 1, Resident.ResidentHouse.Slytherin);
        school.addTeacher("Minerva", "McGonagall",52, 1, Teacher.subjects.Transfiguration);
        school.addTeacher("Pomona", "Sprout",57, 0, Teacher.subjects.Herbology);
        school.addTeacher("Cuthbert", "Binns",203, 10, Teacher.subjects.History);
        school.addTeacher("Severus", "Snape",42, 2, Teacher.subjects.Defence);
        school.addCaretaker("Argus","Filch",60,0);
        school.addCaretaker("Filch's","cat",2,1);

        emptySchool = new School();
    }


    @Test
    void testInvalidMember(){

        assertThrows(IllegalArgumentException.class, () -> school.addStudent(null, "surname", 17,3, Resident.ResidentHouse.Slytherin));
        assertThrows(IllegalArgumentException.class, () -> school.addGhost("name", null, 17,3, Resident.ResidentHouse.Gryffindor));
        assertThrows(IllegalArgumentException.class, () -> school.addStudent("name", "surname", -3,3, Resident.ResidentHouse.Hufflepuff));
        assertThrows(IllegalArgumentException.class, () -> school.addGhost("name", "surname", 17,-18, Resident.ResidentHouse.Ravenclaw));

        // Test to check that more than one teacher of the same subject is not allowed
        assertThrows(IllegalArgumentException.class, () -> school.addTeacher("Remus", "Lupin", 38,3, Teacher.subjects.Defence));


    }

    @Test
    void testRewards() {

        assertEquals("""
                Hermione Granger ( Student of Gryffindor, 3 horcruxes ): 270.0 galleons
                Bloody Baron ( Ghost of Slytherin, 1 horcruxes ): 160.0 galleons
                Fat Friar ( Ghost of Hufflepuff, 3 horcruxes ): 240.0 galleons
                Draco Malfoy ( Student of Slytherin, 1 horcruxes ): 180.0 galleons
                Rubeus Hagrid ( Gamekeeper, 2 horcruxes ): 150.0 galleons
                Minerva McGonagall ( Teacher of Transfiguration, 1 horcruxes ): 50.0 galleons
                Pomona Sprout ( Teacher of Herbology, 0 horcruxes ): 0.0 galleons
                Cuthbert Binns ( Teacher of History, 10 horcruxes ): 500.0 galleons
                Severus Snape ( Teacher of Defence, 2 horcruxes ): 75.0 galleons
                Argus Filch ( Caretaker, 0 horcruxes ): 0.0 galleons
                Filch's cat ( Caretaker, 1 horcruxes ): 65.0 galleons
                The total reward for Hogwarts School is 1690.0 galleons""", school.printRewards());

        assertEquals("There are no members on this school: The total reward for Hogwarts School is 0.0 galleons",emptySchool.printRewards());
    }

    @Test
    void testSalaries() {

        assertEquals("""
                Rubeus Hagrid ( Gamekeeper ): 180 galleons
                Minerva McGonagall ( Teacher of Transfiguration ): 400 galleons
                Pomona Sprout ( Teacher of Herbology ): 250 galleons
                Cuthbert Binns ( Teacher of History ): 200 galleons
                Severus Snape ( Teacher of Defence ): 500 galleons
                Argus Filch ( Caretaker ): 160 galleons
                Filch's cat ( Caretaker ): 160 galleons
                The total payroll for Hogwarts School is 1850 galleons""", school.printSalaries());

        assertEquals("There is no staff on this school: The total payroll for Hogwarts School is 0 galleons",emptySchool.printSalaries());

    }

}

