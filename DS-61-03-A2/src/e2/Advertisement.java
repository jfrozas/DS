package e2;

import java.util.*;

public class Advertisement implements Comparable<Advertisement>{

    Integer refNum;

    //prices
    Float basePrice;
    ArrayList<Float> ParkingPrices;

    //Apartment attributes
    float size; //in m^2
    int beds; //number of beds in the apartment
    String location;


    public Advertisement(int refNum, String location, float basePrice, float size, int beds, int parkingSpots, float[] parkingPrices) {
        this.refNum = refNum;
        this.basePrice = basePrice;
        this.size = size;
        this.beds = beds;
        this.location=location;

        if (parkingPrices.length!=parkingSpots) throw new IllegalArgumentException();

        ParkingPrices = new ArrayList<>();
        for (int i=0;i<parkingSpots;i++){
            ParkingPrices.add(parkingPrices[i]);
        }

        if (location==null || basePrice<=0 || size <=0 || beds<=0 || ParkingPrices.stream().anyMatch(i -> i<=0))
            throw new IllegalArgumentException();

        ParkingPrices.sort(Comparator.naturalOrder());
    }

    /*  GETTERS */

    public Integer getRefNum() {
        return refNum;
    }

    public Float getBasePrice() {
        return basePrice;
    }

    public Float getTotalPrice(){
        Float total=getBasePrice();
        for (Float p : ParkingPrices){
            total+=p;
        }
        return total;
    }

    public float getSize() {
        return size;
    }

    public int getBeds() {
        return beds;
    }

    public String getLocation() {
        return location;
    }

    /* OVERRIDE METHODS*/

    @Override
    public int compareTo(Advertisement a) {
        return this.refNum.compareTo(a.refNum);
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Advertisement)) return false;
        Advertisement that = (Advertisement) o;
        return Float.compare(that.basePrice, basePrice) == 0 && Float.compare(that.size, size) == 0 && beds == that.beds && ParkingPrices.equals(that.ParkingPrices);
    }

    @Override
    public int hashCode() {
        return Objects.hash(basePrice, ParkingPrices, size, beds);
    }

    @Override
    public String toString() {
        return "Reference number=" + refNum +
                "\nlocation='" + location + "'" +
                "\nbasePrice=" + basePrice +
                "\nParkingPrices=" + ParkingPrices +
                "\nTotalPrice=" + getTotalPrice() +
                "\nsize=" + size +
                "\nbeds=" + beds + "\n";
    }
}
