package e2;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;


public class ApartmentApp {

    ArrayList<Advertisement> adList;

    Comparator<Advertisement> criteria;

    public ApartmentApp() {
        adList = new ArrayList<>();
        criteria=null;
    }

    void insertApartment(String location, float basePrice, float size, int beds, int parkingSpots, float... parkingPrices) {

        adList.add(new Advertisement(adList.size() + 1, location, basePrice, size, beds, parkingSpots, parkingPrices));

    }



    public void changeOrderingCriteria(Comparator<Advertisement> criterion) {
        this.criteria = criterion;
    }

    public void sortList(){ //by actual criteria
        if (criteria==null)
            Collections.sort(adList);
        else{

            adList.sort(criteria);
        }

    }

    public int len(){
        return adList.size();
    }

    public Advertisement getAd(int i){
        if (i >= adList.size())
            throw new IllegalArgumentException();
        return adList.get(i);
    }

}
