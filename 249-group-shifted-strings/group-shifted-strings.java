/**
    Let's start with the first example: ["abc", "bcd", "acef", "xyz", "az", "ba", "a", "z"]

    Ans:
        [
            ["abc", "bcd", "xyz"], 
            ["az", "ba"], 
            ["acef"], 
            ["a", "z"]
        ]
    Explanation:
	1.	“abc”, “bcd”, and “xyz” belong to the same shifted group because:
        •	“abc” → “bcd” (shift each character by 1)
        •	“bcd” → “xyz” (shift each character by 24, circularly)
	2.	“az” and “ba” are in the same group because of a circular shift.
	3.	“acef” is in its own group.
	4.	“a” and “z” are in the same group because they can shift circularly.

    Key Insight

    For any string, its shift pattern is what determines its group. A shift pattern is defined by the difference between consecutive characters (mod 26 to handle wrapping around the alphabet).

    Example: “bcd”
        1.	Differences between characters:
        •	‘c’ - ‘b’ = 1
        •	‘d’ - ‘c’ = 1
        2.	Shift pattern: [1, 1]

    Example: “xyz”
        1.	Differences between characters:
        •	‘y’ - ‘x’ = 1
        •	‘z’ - ‘y’ = 1
        •	Wrap around: ‘a’ - ‘z’ = 1 (mod 26)
        2.	Shift pattern: [1, 1]

    Since “bcd” and “xyz” have the same shift pattern, they belong to the same group.

    Approach
	1.	Compute the shift pattern for each string.
	2.	Use a hash map to group strings by their shift pattern.
	3.	Return all groups as a list of lists.

    Algorithm
	1.	Shift Pattern Calculation:
        •	For a string  s , calculate the differences between consecutive characters.
        •	Use modulo 26 to handle wrapping (e.g., ‘a’ - ‘z’).
        •	Represent the shift pattern as a comma separated string.
	2.	Group Strings:
        •	Use a hash map where:
        •	Key: Shift pattern (comma separated string).
        •	Value: List of strings with that pattern.
        •	Iterate through the input strings, calculate their pattern, and group them.
	3.	Output:
	    •	Return the values of the hash map as the result.
    
    THe general idea is to get a string based shift pattern which is then used as a key in a map. Imagine a function which given a string gives a shift pattern string as output. If we get this patterns for all strings and add it in the Map with the pattern as the key, we get a grouped version of the ans.

    For the above example:
    1.	For “abc”:
        •	Pattern: [1, 1] → Key: "1,1,"
        •	Add “abc” to group with key "1,1,".
	2.	For “bcd”:
        •	Pattern: [1, 1] → Key: "1,1,"
        •	Add “bcd” to group with key "1,1,".
	3.	For “acef”:
        •	Pattern: [2, 2, 1] → Key: "2,2,1,"
        •	Add “acef” to group with key "2,2,1,".
	4.	For “xyz”:
        •	Pattern: [1, 1] → Key: "1,1,"
        •	Add “xyz” to group with key "1,1,".
	5.	For “az”:
        •	Pattern: [25] → Key: "25,"
        •	Add “az” to group with key "25,".
	6.	For “ba”:
        •	Pattern: [25] → Key: "25,"
        •	Add “ba” to group with key "25,".
	7.	For “a”:
        •	Single character → Key: "0"
        •	Add “a” to group with key "0".
	8.	For “z”:
        •	Single character → Key: "0"
        •	Add “z” to group with key "0".
    
    
    TC: O(L) where L is the strings length and for grouping O(NxL) where N is the length of the array of strings. Overall O(NxL)
 */

class Solution {
    public List<List<String>> groupStrings(String[] strings) {
        List<List<String>> result = new ArrayList<>();
        // Map to group the shift patterns
        Map<String, List<String>> grouping = new HashMap<>();
        
        // Start grouping all the strings based on their shift pattern
        for(String s : strings) {
            String key = getShiftPattern(s);
            grouping.computeIfAbsent(key, v -> new ArrayList()).add(s);
        }

        // Consolidate the answer
        result.addAll(grouping.values());

        return result;
    }

    public String getShiftPattern(String src) {
        // Special case: We cannot get the diff of a single letter
        if (src.length() == 1)
            return "0";

        // Get the diff. 
        /**
            Why do we add 26?
            Without adding 26, the difference between characters could sometimes result in a negative number, which would cause incorrect results. The alphabet is circular: after ‘z’, we go back to ‘a’. When calculating the shift pattern between characters, the difference should always be non-negative (0 through 25, representing all possible shifts).

            What Happens Without Adding 26?

If you don’t add 26, differences that wrap around the alphabet can become negative.

Example: “az”
	•	‘z’ - ‘a’ = -25 (because the ASCII value of ‘z’ is less than ‘a’).
	•	Without correction, you’d record -25, which is not meaningful as a “shift.”

    How Adding 26 Fixes It

By adding 26 to the difference, you ensure the result is always positive before taking modulo 26. This works because modulo is designed to “wrap around” and handle overflow.

Example: “az”
	1.	‘z’ - ‘a’ = -25.
	2.	Add 26: -25 + 26 = 1.
	3.	Take modulo 26: 1 % 26 = 1 (correct result).

Now the shift is accurately recorded as a single step forward, as intended.
In Java ,  a % b  with a negative  a  results in a negative remainder. For example:
	•	In Java:  -25 % 26 = -25  (incorrect for our purposes).
	•	Adding 26 ensures we get the positive equivalent of the remainder.

    Example:
    Input: [“az”, “ba”]
	1.	For “az”:
        •	Difference: ‘z’ - ‘a’ = -25.
        •	Adding 26: -25 + 26 = 1.
        •	Modulo 26: 1 % 26 = 1.
        •	Shift pattern: [1].
	2.	For “ba”:
        •	Difference: ‘b’ - ‘a’ = -1.
        •	Adding 26: -1 + 26 = 25.
        •	Modulo 26: 25 % 26 = 25.
        •	Shift pattern: [25].

Without adding 26, “az” and “ba” would have incorrect or negative patterns, and they wouldn’t be grouped properly.

    Once we do that we obvviously have to do a `% 26` to ensure we are within the 26 alphabets
         */
        StringBuilder sb = new StringBuilder();
        for(int i = 1; i < src.length(); i++) {
            int diff = ((src.charAt(i) - src.charAt(i-1) - 1) + 26) % 26;
            sb.append(diff).append(",");
        }
        return sb.toString();
    }
}