package e1;

public abstract class Resident extends Members{

    enum ResidentHouse {Gryffindor, Hufflepuff, Ravenclaw, Slytherin}


    ResidentHouse resHouse;


    public Resident(String memberName, String memberSurname, int age, int horrocruxCount, ResidentHouse resHouse) {
        super(memberName, memberSurname, age, horrocruxCount);
        this.resHouse = resHouse;
    }

}

class Student extends Resident{

    public Student(String memberName, String memberSurname, int age, int horrocruxCount, ResidentHouse resHouse) {
        super(memberName, memberSurname, age, horrocruxCount, resHouse);
    }

    @Override
    float getreward() {
        float total=90*horcruxCount;

        if (resHouse==ResidentHouse.Slytherin)
            total*=2;

        return total;
    }

    @Override
    String catString() {
        return "Student of " + resHouse;
    }
}

class Ghost extends Resident{

    public Ghost(String memberName, String memberSurname, int age, int horrocruxCount, ResidentHouse resHouse) {
        super(memberName, memberSurname, age, horrocruxCount, resHouse);
    }

    @Override
    float getreward() {
        float total=80*horcruxCount;
        if (resHouse==ResidentHouse.Slytherin)
            total*=2;

        return total;
    }

    @Override
    String catString() {
        return "Ghost of " + resHouse;
    }
}
