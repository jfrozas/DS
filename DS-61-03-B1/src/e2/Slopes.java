package e2;

public class Slopes
{


    public static int downTheSlope ( char [][] slopeMap , int right , int down ) {
        /*
         * Traverses the slope map making the right and down movements and
         * returns the number of trees found along the way .
         * @param slopeMap A square matrix representing the slope with spaces
         * represented as "." and trees represented as "#".
         * @param right Movement to the right
         * @param down Downward movement.
         * @return Number of trees
         * @throws IllegalArgumentException if the matrix is incorrect because :
         * - It is not square .
         * - It has characters other than "." and "#"
         * - right >= number of columns or right < 1
         * - down >= number of rows of the matrix or down < 1
         */


        int rows;

        /*Checking if the map is valid, if it is not, it throws an IllegalArgumentException*/
        rows=slopeMap.length;
        for (int i=0; i<rows; i++){
            if(slopeMap[i].length!=rows) throw new IllegalArgumentException();
        }
        if(right <1 || down<1 || right>=slopeMap[0].length || down>=slopeMap.length) throw new IllegalArgumentException();

        for (char[] chars : slopeMap) {
            for (int c = 0; c < slopeMap[0].length; c++) {
                if (chars[c] != '.' && chars[c] != '#') {
                    throw new IllegalArgumentException();
                }
            }
        }


        int yPos=0, xPos=0, nrows=rows, ncols=slopeMap[0].length, treecount=0;

        boolean finish=false;

        /*Loop in which each iteration is a movement including down and right movements*/
        while(!finish){

            for(int i=1; i<=right; i++){

                if(xPos==ncols-1) xPos=0;
                else xPos++;

                if(slopeMap[yPos][xPos]=='#') treecount++;
            }

            for (int i=1; i<=down; i++){

                if (yPos==nrows-1) {
                    finish=true;
                    break;
                } else yPos++;

                if(slopeMap[yPos][xPos]=='#') treecount++;

            }
        }
        return treecount;
    }


    public static int jumpTheSlope ( char [][] slopeMap , int right , int down ) {
        /*
         * Traverses the slope map making the right and down movements and
         * returns the number of trees found along the way .
         * Since it " jumps " from the initial position to the final position ,
         * only takes into account the trees on those initial / final positions .
         *
         * Params , return value and thrown expections as in downTheSlope ...
         */

        int rows;

        /*Checking if the map is valid*/
        rows=slopeMap.length;
        for (int i=0; i<rows; i++){
            if(slopeMap[i].length!=rows) throw new IllegalArgumentException();
        }
        if(right <1 || down<1 || right>=slopeMap[0].length || down>=slopeMap.length) throw new IllegalArgumentException();

        for (char[] chars : slopeMap) {
            for (int c = 0; c < slopeMap[0].length; c++) {
                if (chars[c] != '.' && chars[c] != '#') {
                    throw new IllegalArgumentException();
                }
            }
        }




        int yPos=0, xPos=0, nrows=rows, ncols=slopeMap[0].length, treecount=0;

        boolean finish=false;

        /*Loop in which each iteration is a movement including down and right movements*/
        while(!finish){

            for(int i=1; i<=right; i++){

                if(xPos==ncols-1) xPos=0;
                else xPos++;
            }

            for (int i=1; i<=down; i++){

                if (yPos==nrows-1) {
                    finish=true;
                    return treecount;
                } else yPos++;
            }

            if(slopeMap[yPos][xPos]=='#') treecount++;
        }

        return treecount;
    }
}



