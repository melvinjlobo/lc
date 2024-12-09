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

        System.out.println("Map - " + freqMap.toString());


        int windowStart = 0;
        for(int windowEnd = 0; windowEnd < s.length(); windowEnd++) {
            System.out.println("windowEnd - " + windowEnd);

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
                    System.out.println("matched - " + matched);
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
                System.out.println("Window Start is - " + windowStart);

                if(freqMap.containsKey(ws)) {
                    if (freqMap.get(ws) >= 0) {
                        matched--;
                        System.out.println("matched reduced - " + matched);
                    }
                    
                    freqMap.put(ws, freqMap.get(ws) + 1);
                }
                
            }
        }

        return (minLength == Integer.MAX_VALUE) ? "" : s.substring(subStrStart, subStrStart + minLength);
    }
}