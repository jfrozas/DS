package e2;

import java.util.Comparator;
import java.util.Objects;

class OrderBasePrice implements Comparator<Advertisement> {


    @Override
    public int compare(Advertisement a1, Advertisement a2) {
        if (a1.getBasePrice()>a2.getBasePrice()) return 1;
        else if (Objects.equals(a1.getBasePrice(), a2.getBasePrice())) return  0;
        else return -1;
    }
}

class OrderTotalPrice implements Comparator<Advertisement>{

    @Override
    public int compare(Advertisement a1, Advertisement a2) {
        if (a1.getTotalPrice()>a2.getTotalPrice()) return 1;
        else if (Objects.equals(a1.getTotalPrice(), a2.getTotalPrice())) return  0;
        else return -1;
    }

}

class OrderSize implements Comparator<Advertisement>{

    @Override
    public int compare(Advertisement a1, Advertisement a2) {
        if (a1.getSize()>a2.getSize()) return 1;
        else if (Objects.equals(a1.getSize(), a2.getSize())) return  0;
        else return -1;
    }

}

class OrderBeds implements Comparator<Advertisement>{

    @Override
    public int compare(Advertisement a1, Advertisement a2) {
        if (a1.getBeds()>a2.getBeds()) return 1;
        else if (Objects.equals(a1.getBeds(), a2.getBeds())) return  0;
        else return -1;
    }

}
