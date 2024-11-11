class Solution {
    /**
     * The idea is to create a Hashmap that stores each letter in the pattern and its frequency.
     * Once we have a frequency Map, we go through the order string, find that letter in the freq
     * map and then just print the letter 'freq' number of times.
     */
    public String customSortString(String order, String s) {
        //Map to contain the letters
        Map<Character, Integer> freqMap = new HashMap<>();

        //Create the freq Map
        for(int ctr = 0; ctr < s.length(); ctr++) {
            char c = s.charAt(ctr);
            int charFreq = freqMap.getOrDefault(c, 0) + 1;
            freqMap.put(c, charFreq);
        }

        StringBuilder result = new StringBuilder();

        //Iterate the order string and pick all the letters from the freqMap
        for(int ctr = 0; ctr < order.length(); ctr++) {
            char oc = order.charAt(ctr);
            if (freqMap.containsKey(oc)) {
                while(freqMap.get(oc) > 0) {
                    result.append(oc);
                    freqMap.put(oc, freqMap.get(oc) - 1);
                }
            }
        }

        //Append the remaining elements
        for(Map.Entry<Character, Integer> entry: freqMap.entrySet()) {
            char c = entry.getKey();
            int freq = entry.getValue();

            if(freq > 0)
                while(freq > 0) {
                    result.append(c);
                    freq--;
                }
        }

        return result.toString();
    }
}