/**
Approach :
Define a mapping of Roman numerals to their integer values.
Traverse each character in the string:
If the current numeral is less than the next one, subtract its value from the total.
Otherwise, add its value to the total.
Return the accumulated total at the end.
 */

class Solution {
    Map<Character, Integer> symbols = Map.of(
            'I', 1, 
            'V', 5, 
            'X', 10, 
            'L', 50, 
            'C', 100, 
            'D', 500, 
            'M', 1000);

    public int romanToInt(String s) {
        int total = 0;

        for(int itr = 0; itr < s.length(); itr++) {
            char curr= ' ', next= ' ';
            curr = s.charAt(itr);
            if((itr + 1 < s.length()) && (symbols.get(curr) < symbols.get(s.charAt(itr + 1)))) {
                total -= symbols.get(curr);
            } else {
                total += symbols.get(curr);
            }
        }

        return total;
    }
}