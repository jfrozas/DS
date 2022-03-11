package e1;


import java.util.ArrayList;
import java.util.Objects;

public class School {

    ArrayList<Resident> residentList;
    ArrayList<Staff> staffList;

    public School() {
        residentList = new ArrayList<>();
        staffList    = new ArrayList<>();
    }

    void addStudent(String name, String surname, int age, int horcruxes, Resident.ResidentHouse house){
        residentList.add(new Student(name,surname,age,horcruxes,house));
    }

    void addGhost(String name, String surname, int age, int horcruxes, Resident.ResidentHouse house){
        residentList.add(new Ghost(name,surname,age,horcruxes,house));
    }

    void addTeacher(String name, String surname, int age, int horcruxes, Teacher.subjects subj){

        for (Staff s: staffList){
            if (Objects.equals(s.catString(), "Teacher of " + subj))
                throw new IllegalArgumentException();
        }



        staffList.add(new Teacher(name, surname, age, horcruxes, subj));
    }

    void addGamekeeper(String name, String surname, int age, int horcruxes){
        staffList.add(new Gamekeeper(name, surname, age, horcruxes));
    }

    void addCaretaker(String name, String surname, int age, int horcruxes){
        staffList.add(new Caretaker(name, surname, age, horcruxes));
    }

    String printRewards(){

        StringBuilder rewardString = new StringBuilder();
        float totalReward=0;

        for ( Resident r : residentList) {
            rewardString.append(r.printRewards()).append("\n");
            totalReward+=r.getreward();
        }

        for ( Staff s : staffList) {
            rewardString.append(s.printRewards()).append("\n");
            totalReward+=s.getreward();
        }
        if (residentList.isEmpty() && staffList.isEmpty())
            rewardString.append("There are no members on this school: ");
        rewardString.append("The total reward for Hogwarts School is ").append(totalReward).append(" galleons");

        return rewardString.toString();

    }


    String printSalaries(){

        StringBuilder salariesString = new StringBuilder();
        int totalPayroll=0;

        for ( Staff s : staffList) {
            salariesString.append(s.printSalary()).append("\n");
            totalPayroll+=s.getSalary();
        }

        if (staffList.isEmpty())
            salariesString.append("There is no staff on this school: ");
        salariesString.append("The total payroll for Hogwarts School is ").append(totalPayroll).append(" galleons");

        return salariesString.toString();
    }
}
