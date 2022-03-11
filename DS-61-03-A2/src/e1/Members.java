package e1;

abstract public class Members {

    String memberName;
    String memberSurname;
    int age;
    int horcruxCount;

    private void isValidMember(String name, String surname, int age, int horcruxes){
        if (name==null || surname==null || age<=0 || horcruxes<0) throw new IllegalArgumentException();
    }

    public Members(String memberName, String memberSurname, int age, int horrocruxCount) {
        isValidMember(memberName,memberSurname,age,horrocruxCount);
        this.memberName = memberName;
        this.memberSurname = memberSurname;
        this.age = age;
        this.horcruxCount = horrocruxCount;
    }

    abstract float getreward();

    abstract String catString();

    String printRewards(){

        return memberName + " " + memberSurname
                + " ( " +  catString() + ", "+horcruxCount +" horcruxes ): "
                + getreward() + " galleons";
    }
}
