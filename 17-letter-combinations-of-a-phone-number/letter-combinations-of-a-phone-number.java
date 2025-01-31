/**
    The catch here is to notice that we dont need ALL the characters for the button...just one. Then we move on to the next digit.
    We keep repeating this.. So this becomes a typical backtracking problem.
    Time complexity: O(4^N * N), where N is the length of digits. Note that 4 in this expression is referring to the maximum value length in the hash map, and not to the length of the input.
    SC: O(N)
 */


class Solution {
    public List<String> letterCombinations(String digits) {

        if(digits == null || digits.length() == 0 )
            return new ArrayList<String>();

        Map<Character, String> phoneMap = Map.of('2', "abc",
                                                '3', "def",
                                                '4', "ghi",
                                                '5', "jkl",
                                                '6', "mno",
                                                '7', "pqrs",
                                                '8', "tuv",
                                                '9', "wxyz");
        
        List<String> result = new ArrayList<>();
        backtrack(digits, 0, new StringBuilder(), result, phoneMap);
        return result;

    }

    public void backtrack(String digits, int index, StringBuilder partial, List<String> result, Map<Character, String> phoneMap) {
        if(index == digits.length()) {
            result.add(partial.toString());
            return;
        }

        //Get the character at the index
        char digit = digits.charAt(index);
        if(phoneMap.containsKey(digit)) {
            String letters = phoneMap.get(digit);
            for(Character c: letters.toCharArray()) {
                partial.append(c);                              // Append the letter
                backtrack(digits, index + 1, partial, result, phoneMap);        //We do `index + 1` since we just need the letters equivalent to digits.length
                partial.deleteCharAt(partial.length() - 1);         //Remove the last character
            }
        }
        
    }
}