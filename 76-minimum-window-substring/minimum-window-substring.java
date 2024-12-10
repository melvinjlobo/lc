/**
    The algorithm works as follows:
    1. Put all characters in pattern `t`, in a frequency Map. This helps to keep the count
    2. For each character found in `s` which is a part of pattern `t`, reduce the frequency in the freqMap
    3. IMP Note as below: Keep reducing the frequency in the freqMap and DO NOT stop at zero. This helps remove redundant characters that are a part of the pattern, but are not actually useful. This is useful later in the code when we shrink windowStart from the left to get rid of redundant letters. This is the only catch in this problem, which otherwise is more of manual bookkeeping
    4. Also note that we increment matched, only when the frequency we reduce for a character in the freqMap drops to zero. We do not decrement `matched` when we get a frequency reducded to negative. This ensures we keep matched with the pattern `t`
    5. Now when we find that `matched == t.length()`, i.e all characters are matched, we run a while loop when the aforementioend statement is still true. In this loop, we will try and decrement the window from the left by incrementing windowStart
        5.a. With each increment, we check if the windowstart has removed a character that is a part of the pattern. if it is , we increment the value in the freqMap (thus reversing the reduction of freq during the match process).
        5.b Inline with point 3., we don't really reduce match unless the frequency in the map is >=0. This is explained below and is required to ignore the redundnat characters that are a part of the pattern, but not useful for our problem.       
 */

class Solution {
    public String minWindow(String s, String t) {
        Map<Character, Integer> freqMap = new HashMap<>();
        int minWindowSize = 0;
        int matched = 0;
        int minLength = Integer.MAX_VALUE;      //Track the min window length
        int subStrStart = 0;            // Index that marks the start of smallest substring. minLength will be the length of this substring
        // Add all the characters in a map with their frequency
        for(Character c: t.toCharArray()) {
            freqMap.put(c, freqMap.getOrDefault(c, 0) + 1);
        }

        int windowStart = 0;
        for(int windowEnd = 0; windowEnd < s.length(); windowEnd++) {

            Character c = s.charAt(windowEnd);

            // Processing done when we find a character that's in the freqMap
            if(freqMap.containsKey(c)) {
                int updatedFreq = freqMap.get(c) - 1;
                // We have considered all frequencies of the character in our string
                //IMP: We don't update this conditionally based on "updatedFreq > 0" because we need to reduce this for redundant
                // characters, i.e. characters that are in string `t`, but appear more times than the freq in `t`. This is because
                // when shrinking the sliding window fro mthe left, we still consider the redundant value
                //E.g. s = wetrtrtdd and t = ttd
                // At a certain point, the window will contain: `wetrtrtd`. Note that we have the char `t` appear 3 times. 
                //So, freqMap has (t, -1), as the original was (t, 2). Now since all our chars are in, we reduce the window
                //We don't react to the first left `t` since we have a - 1
                freqMap.put(c, updatedFreq);    

                // Match iff the freq >= 0 since we don't want to match redundant ones
                if(updatedFreq >= 0) {
                    matched++;
                }
            }
        
            // If all the characters matched, we need to start shrinking the window
            while(matched == t.length()) {
                
                //Update the min window length
                int currentWindowSize = windowEnd - windowStart + 1;
                if(currentWindowSize < minLength) {
                    minLength = currentWindowSize;
                    subStrStart = windowStart;
                }

                Character ws = s.charAt(windowStart);
                windowStart++;

                if(freqMap.containsKey(ws)) {
                     //Reduce the frequency if the char matches the pattern and iff the freq is >= zero
                    if (freqMap.get(ws) >= 0) {
                        matched--;
                    }
                    
                    //Just increase the freq overall
                    freqMap.put(ws, freqMap.get(ws) + 1);
                }
                
            }
        }

        return (minLength == Integer.MAX_VALUE) ? "" : s.substring(subStrStart, subStrStart + minLength);
    }
}