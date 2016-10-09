/*The count-and-say sequence is the sequence of integers beginning as follows:
1, 11, 21, 1211, 111221, ...

1 is read off as "one 1" or 11.
11 is read off as "two 1s" or 21.
21 is read off as "one 2, then one 1" or 1211.
Given an integer n, generate the nth sequence.

Note: The sequence of integers will be represented as a string.*/

If you calculate all the string from 1 to n, the total loop num is 1 + 2 + 3 + ... + n = n(n+1)/2 
So, O(n^2)

public class Solution {
    public String countAndSay(int n) {
        String oldString = "1";
        while (n > 1) {
            StringBuilder temp = new StringBuilder();
            char[] oldChars = oldString.toCharArray();
            for (int i = 0; i < oldChars.length; i++) {
                int count = 1;
                while ((i + 1) < oldChars.length && oldChars[i] == oldChars[i + 1]) {
                    count++;
                    i++;
                }
                temp.append(String.valueOf(count) + String.valueOf(oldChars[i]));
            }
            oldString = temp.toString();
            n--;
        }
        return oldString;
    }
}

// time O(n)

public class Solution {
    public String countAndSay(int n) {
        if(n < 1){
            return null;
        }
        String result = "1";
        for(int i = 1; i < n; i++){
           result = countAndSay(result); 
        }
        return result;
    }
    public String countAndSay(String str){
        StringBuilder builder = new StringBuilder();
        int count = 1;
        for(int i = 1;i < str.length();i++){
            if(str.charAt(i)==str.charAt(i-1)){
                count++;
            }else{
                builder.append(count);
                builder.append(str.charAt(i-1));
                count =1;
            }
        }
        builder.append(count);
        builder.append(str.charAt(str.length()-1));
        return builder.toString();
    }
}
