/**

Let’s pretend the words are like friends trying to agree on what they have in common at the start. Example: ["flower", "flow", "flight"]

Step 1: Start with the First Word

Take the first word, “flower”, as your “best guess” for the prefix. Now, you’ll check if the other words agree with it.

Step 2: Compare with the Other Words

For each word, check letter by letter:
	•	Compare “flower” with “flow”.
	•	They both start with “fl”, but “flow” stops after that. So, the prefix becomes “fl”.
	•	Compare “fl” with “flight”.
	•	They both start with “fl”, so we keep it as the prefix.

Step 3: Done!

Now there are no more words to check. The longest common prefix is “fl”.

Why does this work?
When we compare two strings and get common prefix (common between `flower` and `flow` is `flow`) and if we encounter a prefix with the next work that is longer than the one we found aka `flow`, we don't care as we need to find the common prefix. For example, if the next word is `flowering`, then obviously the COMMON prefix between `flower` and `flowering` is `flower`. The goal of the problem is to find the common prefix. This means that `flowering`, `flower` and `flow` have `flow` as common. In other words once we find something common between two words, the rest of the comparisons can ONLY produce something smaller than `flow`, any larger match will not yield anything as it would not be common / shared with what we found before. That's why this algo works....
 */


class Solution {
    public String longestCommonPrefix(String[] strs) {

        if(strs == null || strs.length == 0)
            return "";

        String prefix = strs[0];        // intialize one of the strings as prefix

        for(int i = 1; i < strs.length; i++) {
            // Find the occurence of the string prefix in strs[i]. If the occurence is at ANY position EXCEPT 0,
            // we drop the last character from the prefix and start again.
            // Why zero? Since it's a prefix, so it has to be from the 0th index in ALL strings
            while(strs[i].indexOf(prefix) != 0) {
                prefix = prefix.substring(0, prefix.length() - 1);      // Drop the last character

                // Short circuit out if prefix becomes empty. This means that there is atleast one string that does not match
                // with the prefix. SO it cannot be common
                if(prefix.length() == 0)
                    return "";
            }

        }        

        return prefix;
    }
}