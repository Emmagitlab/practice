Given a string, find the length of the longest substring without repeating characters.

Examples:

Given "abcabcbb", the answer is "abc", which the length is 3.

Given "bbbbb", the answer is "b", with the length of 1.

Given "pwwkew", the answer is "wke", with the length of 3. 
Note that the answer must be a substring, "pwke" is a subsequence and not a substring.

public class Solution {
    public int lengthOfLongestSubstring(String s) {
        int[] map = new int[128];
        int begin=0, end=0, len=0;
        while(end < s.length()){
            if(map[s.charAt(end)] == 0){
                len = Math.max(len, end-begin+1);
            }else{
                while(s.charAt(begin++)!=s.charAt(end)) 
                    map[s.charAt(begin-1)]--;
                map[s.charAt(begin-1)]--;
            }
            map[s.charAt(end++)]++;
        }
        return len;
    }
}
