class Solution {
    /**
    Logic: Keep two pointers, one on word and other on the abbreviation. When keep matching characters
    for each step. If they don't match, we exit. If they do, we go on until we find an integer 
    in the  abbreviation. Here we either:
     - Check if the first number (or rather character) is not zero. If it is, we exit since it breaks 
     constraints
     - Step through all the characters which represent a number (Character.isDigit) and form the 
     actual number.
    Now, we skip ahead on the word with that number, provided the skip is less than the word itself 
    (else we return false).
    Once we skip ahead, we re-sompare the characters since the pointer in the abbreviation is just 
    after the number and the pointer in the word has skipped the steps equal to the number in the 
    abbreviation and the characters going forward should match if it's a true abbreviation of the
    word
     */
    public boolean validWordAbbreviation(String word, String abbr) {
        //Get our two pointers
        int aptr = 0, wptr = 0;
        int wordLength = word.length(), abbrLength = abbr.length();

        while (wptr < wordLength && aptr < abbrLength) {
            Character wordChar = word.charAt(wptr);
            Character abbrChar = abbr.charAt(aptr);
            // Step ahead while the characters are same
            if( wordChar == abbrChar) {
                aptr++;
                wptr++;
            } else if(Character.isDigit(abbrChar)) {
                //If the digit is zero, it breaks our constraint
                if (abbrChar == '0') {
                    return false;
                } else {
                    // Time to get the skip number from the abbr
                    int skip = 0;
                    while(aptr < abbr.length() && Character.isDigit(abbr.charAt(aptr))) {
                        skip = skip * 10 + Character.getNumericValue(abbr.charAt(aptr));
                        aptr++;
                    }
                    //Now that we have the number, let's skip ahead on the word pointer
                    if (wptr + skip <= wordLength)
                        wptr += skip;
                    else
                        return false;
                }
            } else {
                //Characters dont match, so we exit....
                return false;
            }

        }

        //Ensure that we have reached the end of both word and abbr
        return wptr == wordLength && aptr == abbrLength;
    }
}