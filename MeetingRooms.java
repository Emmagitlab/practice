/*Given an array of meeting time intervals consisting of start and end times [[s1,e1],[s2,e2],...] (si < ei),
determine if a person could attend all meetings.

For example,
Given [[0, 30],[5, 10],[15, 20]],
return false.*/


/**
 * Definition for an interval.
 * public class Interval {
 *     int start;
 *     int end;
 *     Interval() { start = 0; end = 0; }
 *     Interval(int s, int e) { start = s; end = e; }
 * }
 */
public class Solution {
    public boolean canAttendMeetings(Interval[] intervals) {
        if (intervals == null || intervals.length == 0) return true;
        Arrays.sort(intervals, new Comparator<Interval>() {
            @Override
            public int compare(Interval int1, Interval int2) {
                return int1.start - int2.start;
            }
        });
        for (int i = 0; i < intervals.length - 1; i++) {
            if (intervals[i].end > intervals[i + 1].start) return false;
        }
        return true;
    }
}

//Discuss
public boolean canAttendMeetings(Interval[] intervals) {
  if (intervals == null)
    return false;

  // Sort the intervals by start time
  Arrays.sort(intervals, new Comparator<Interval>() {
    public int compare(Interval a, Interval b) { return a.start - b.start; }
  });

  for (int i = 1; i < intervals.length; i++)
    if (intervals[i].start < intervals[i - 1].end)
      return false;

  return true;
}

//Emma

/**
 * Definition for an interval.
 * public class Interval {
 *     int start;
 *     int end;
 *     Interval() { start = 0; end = 0; }
 *     Interval(int s, int e) { start = s; end = e; }
 * }
 */
public class Solution {
    public boolean canAttendMeetings(Interval[] intervals) {
     if(intervals == null) return true;
     int[] start = new int[intervals.length];
     int[] end = new int[intervals.length];
     for(int i =0; i< intervals.length; i++){
         start[i] = intervals[i].start;
         end[i] = intervals[i].end;
     }
     
     Arrays.sort(start);
     Arrays.sort(end);
     
     for(int i = 1; i < intervals.length; i++){
         if(start[i] < end[i-1]) return false;
     }
     return true;
        
    }
}

