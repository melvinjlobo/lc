class Solution {

    static class Interval{
        int start;
        int end;

        public Interval(int start, int end) {
            this.start = start;
            this.end = end;
        }

        @Override
        public String toString() {
            return "[" + this.start + "," + this.end + "]";
        }

    }

    public int[][] merge(int[][] intervals) {
        List<Interval> intervalList = new ArrayList<>();
        List<Interval> result = new ArrayList<>();
        

        //Convert from Array to list
        for(int ctr = 0; ctr < intervals.length; ctr++) {
            intervalList.add(new Interval(intervals[ctr][0], intervals[ctr][1]));    
        }

        Collections.sort(intervalList, (i1, i2) -> Integer.compare(i1.start, i2.start));

        for(Interval interval : intervalList) {
             if (result.size() == 0) {
                result.add(interval);
             } else {
                Interval prevInterval = result.get(result.size()  - 1);
                if(interval.start <= prevInterval.end) {
                    prevInterval.end = Math.max(interval.end, prevInterval.end);
                } else {
                    result.add(interval);
                }
             }
        }

        //Convert back to Array
        Iterator<Interval> iter = result.iterator();
        int[][] finalList = new int[result.size()][2];

        int index = 0;
        while(iter.hasNext()) {
            Interval i = iter.next();
            int[] singleInterval = new int[] {i.start, i.end};
            finalList[index] = singleInterval;
            index++;
        }   

        return finalList;
    }
}