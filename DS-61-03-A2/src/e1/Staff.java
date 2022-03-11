package e1;

abstract public class Staff extends Members{

    int baseSalary;

    public Staff(String memberName, String memberSurname, int age, int horrocruxCount) {
        super(memberName, memberSurname, age, horrocruxCount);
    }

    abstract int getSalary();

    String printSalary(){
        return memberName + " " + memberSurname
                + " ( " +  catString() + " ): "
                + getSalary() + " galleons";
    }

}

class Teacher extends Staff{

    enum subjects{History, Transfiguration, Defence, Herbology, Potions}

    subjects subj;

    public Teacher(String memberName, String memberSurname, int age, int horrocruxCount, subjects subj) {
        super(memberName, memberSurname, age, horrocruxCount);
        this.subj = subj;

        baseSalary  = switch (subj){
            case History -> 200;
            case Herbology -> 250;
            case Potions -> 350;
            case Transfiguration -> 400;
            case Defence -> 500;
        };
    }

    float getreward(){

        float reward=50*horcruxCount;

        if (subj==subjects.Defence)
            reward*=0.75;

        return reward;
    }

    int getSalary(){
        return baseSalary;
    }

    String catString(){
        return "Teacher of " + subj;
    }

}

class Gamekeeper extends Staff{

    public Gamekeeper(String memberName, String memberSurname, int age, int horrocruxCount) {
        super(memberName, memberSurname, age, horrocruxCount);
        //super.cat=Category.gamekeeper;
        baseSalary=170;
    }

    float getreward() {
        return 75*horcruxCount;
    }

    int getSalary() {
        return baseSalary+10;
    }

    String catString(){
        return "Gamekeeper";
    }
}

class Caretaker extends Staff{

    public Caretaker(String memberName, String memberSurname, int age, int horrocruxCount) {
        super(memberName, memberSurname, age, horrocruxCount);
        //super.cat=Category.caretaker;
        baseSalary=150;
    }

    float getreward(){
        return 65*horcruxCount;
    }

    int getSalary() {
        return baseSalary+10;
    }

    String catString(){
        return "Caretaker";
    }
}
