package e1;

public class StringCount {


    public static int countWords ( String text ) {
        /*
         * Counts the number of words in a given String .
         * Words are groups of characters separated by one or more spaces .
         *
         * @param text String with the words
         * @return Number of words in the String or zero if it is null
         */


        if(text == null || text.isEmpty()) return 0;


        int wordCount=0;

        boolean isWord = false;
        int stringlength = text.length() - 1;
        char[] characters = text.toCharArray();

        for (int i = 0; i < characters.length; i++) {
            /* loop travels through all the characters in the phrase and checks if it is a whitespace,
             * if the previous characters were a word, the word counter is incremented
             */

            if (!Character.isWhitespace(characters[i]) && i != stringlength) isWord = true;


            else if (Character.isWhitespace(characters[i]) && isWord) {
                wordCount++;
                isWord = false;
            } else if (!Character.isWhitespace(characters[i]) && i == stringlength) wordCount++;

        }

        return wordCount;




    }

    public static int countChar ( String text , char c) {
        /*
         * Counts the number of times the given character appears in the String .
         * Accented characters are considered different characters .
         * @param text String with the characters
         * @param c the character to be found
         * @return Number of times the character appears in the String or zero if null
         */

        if(text == null || text.isEmpty()) return 0;
        char[] characters = text.toCharArray();
        int count=0;

        for (char character : characters) {
            /* In each iteration it is checked if the character is the one
             * we want to count and if it is, increment the total sum
             */

            if (character == c) count++;

        }

    return count;
    }

    public static int countCharIgnoringCase ( String text , char c ) {
        /*
         * Counts the number of times the given character appears in the String .
         * The case is ignored so an ’a’ is equal to an ’A ’.
         * Accented characters are considered different characters .
         * @param text String with the characters
         * @param c the character to be found
         * @return Number of times the character appears in the String or zero if null
         */
        if(text == null || text.isEmpty()) return 0;
        char[] characters = text.toCharArray();
        int count=0;

        for (char character : characters) {
            /*Counts every character excluding the Upper/lower case*/

            if (character == Character.toLowerCase(c) || character == Character.toUpperCase(c)) count++;

        }

        return count;





    }

    public static boolean isPasswordSafe ( String password ) {
        /*
         * Checks if a password is safe according to the following rules :
         * - Has at least 8 characters
         * - Has an uppercase character
         * - Has a lowercase character
         * - Has a digit
         * - Has a special character among these : ’? ’, ’@ ’, ’#’, ’$ ’, ’. ’ and ’,’
         * @param password The password , we assume it is not null .
         * @return true if the password is safe , false otherwise
         */
        if(password.isEmpty()) return false;


        char[] characters = password.toCharArray();
        boolean eight=characters.length>=8, upper=false, lower=false, digit=false, special=false;

        /*the loop travels through all word to check if all necessary conditions are fulfilled*/
        for (char character : characters) {
            if (Character.isLowerCase(character)) lower = true;
            if (Character.isUpperCase(character)) upper = true;
            if (Character.isDigit(character)) digit = true;
            if (character == '?' || character == '@' || character == '#' || character == '.' || character == '$' || character == ',') special = true;
        }


        return eight && upper && lower && digit && special;
    }


}
