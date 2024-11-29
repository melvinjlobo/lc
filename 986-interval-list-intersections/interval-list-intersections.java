/**

1. Initialize two pointers, i = 0 (for firstList) and j = 0 (for secondList), and an empty result list.
While both pointers are within their respective lists:
2. Check if the intervals overlap:
If max(start1, start2) <= min(end1, end2), they overlap.
Compute the intersection as [max(start1, start2), min(end1, end2)].
3. Move the pointer corresponding to the interval with the smaller end time.
Return the result list.

1. Understanding Interval Overlap
For two intervals, [start1, end1] and [start2, end2], there are three possible cases regarding overlap:

No Overlap:

When one interval ends before the other starts:
end1 < start2 or end2 < start1.
In this case, there is no overlap.
Partial Overlap:

When one interval partially overlaps with the other:
For example, [5, 10] and [8, 12] overlap from 8 to 10.
Full Overlap:

When one interval completely covers the other:
For example, [5, 10] and [6, 9] have the intersection [6, 9].
2. Finding the Overlap
To find the overlap of [start1, end1] and [start2, end2]:
The overlapping start is the later of the two starting points:
startMax
=
max(start1, start2)
startMax=max(start1, start2)
The overlapping end is the earlier of the two ending points:
endMin
=
min(end1, end2)
endMin=min(end1, end2)
These two points define the overlap interval [startMax, endMin].
3. Checking for a Valid Overlap
A valid overlap exists only if:
startMax
≤
endMin
startMax≤endMin
If this condition is satisfied, it means the intervals overlap, and you can add [startMax, endMin] to the result list.

Why This Works
The logic leverages the fact that for two intervals to overlap, their start and end points must "meet" in some range. Using max and min ensures you capture the exact overlap if it exists, and the condition startMax <= endMin verifies whether the overlap is valid.

OverLap case example:

5    10
[-----]       (firstList[i])

      8        12
      [-----]  (secondList[j])

Overlap:        8    10
                [--]

The overlap interval [8, 10] is calculated by:
startMax = max(5, 8) = 8
endMin = min(10, 12) = 10

-------------------------------------------------------

What Happens If There's No Overlap?
If the intervals don’t overlap:

The startMax (later start) will be greater than endMin (earlier end).
The condition startMax <= endMin will fail, and no interval will be added to the result.
No Overlap Case Example:

5    10
[-----]       (firstList[i])

              12        15
              [-----]   (secondList[j])

No Overlap.
startMax = max(5, 12) = 12
endMin = min(10, 15) = 10
Since startMax (12) > endMin (10), there’s no overlap.

 */

class Solution {
    public int[][] intervalIntersection(int[][] firstList, int[][] secondList) {
        int firstptr = 0, secondptr = 0;
        List<int[]> result = new ArrayList<>();
       

        while(firstptr < firstList.length && secondptr < secondList.length ) {
            int start1 = firstList[firstptr][0], end1 = firstList[firstptr][1];
            int start2 = secondList[secondptr][0], end2 = secondList[secondptr][1];

            //Get the start max and end min to find the overlap
            int startMax = Math.max(start1, start2);
            int endMin = Math.min(end1, end2);

            if(startMax <= endMin)
                result.add(new int[] { startMax, endMin });

            //Now we need to move pointer, so we move the pointer whose end was 
            //smaller, since there may be another
            //interval that is overlapping with the first one

            if(end1 < end2)
                firstptr++;
            else
                secondptr++;
        }

        return result.toArray(new int[result.size()][]);
    }
}