

注意corner cases：
自然string comparator。不知道的搜下。就是string 比较的时候考虑里面数字的大小，比如 abc9 < abc123 abc > ab9 
因为char比digit重要。


import java.io.*;
import java.util.*;



public class Solution  {
    public static void main(String[] args) {
        Solution sol = new Solution();
        System.out.println(sol.compare("abc22a", "abc22z"));
        System.out.println(sol.compare("abc23", "abz22"));
        System.out.println(sol.compare("abc", "999"));
        System.out.println(sol.compare("abc22ac43", "abc22ac43"));
    }



    public int compare(String s1, String s2) {
        // TODO Auto-generated method stub
        if (s1 == null && s2 == null) {
            return 0;
        }
        else if (s1 == null && s2 != null) {
            return 1;
        }
        else if (s1 != null && s2 == null) {
            return -1;
        }
        else if (s1.equals(s2)) {
            return 0;
        }
        int a1 = 0, a2 = 0;
        int b1 = 0, b2 = 0;
        //find break point
        boolean isDigit = true;
        while (b1 < s1.length() && !Character.isDigit(s1.charAt(b1)))
            b1++;
        while (b2 < s2.length() && !Character.isDigit(s2.charAt(b2)))
            b2++;
        int cmp = s1.substring(a1, b1).compareTo(s2.substring(a2, b2));
        while (cmp == 0) {
            a1 = b1;
            a2 = b2;
            if (isDigit) {
                while (b1 < s1.length() && Character.isDigit(s1.charAt(b1)))
                    b1++;
                while (b2 < s2.length() && Character.isDigit(s2.charAt(b2)))
                    b2++;
                isDigit = false;
                cmp = Integer.valueOf(s1.substring(a1, b1)) - Integer.valueOf(s2.substring(a2, b2));
            }
            else {
                while (b1 < s1.length() && !Character.isDigit(s1.charAt(b1)))
                    b1++;
                while (b2 < s2.length() && !Character.isDigit(s2.charAt(b2)))
                    b2++;
                isDigit = true;
                cmp = s1.substring(a1, b1).compareTo(s2.substring(a2, b2));
            }
        }
        if (cmp > 0)
            return 1;
        else if (cmp < 0)
            return -1;
        else
            return cmp;
    }
}
