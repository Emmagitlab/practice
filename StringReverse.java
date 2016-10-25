
Write a function that takes a string as input and returns the string reversed.

Example:
Given s = "hello", return "olleh".






public class Solution {
    public static String reverseString(String s) {
         char[] ch = s.toCharArray();
        int start = 0;
        int end = ch.length - 1;
        while (start < end) {
            ch[start] = (char) (ch[start] ^ ch[end]);
            ch[end] = (char) (ch[start] ^ ch[end]);
            ch[start] = (char) (ch[start] ^ ch[end]);
            start++;
            end--;
        }
        return new String(ch);
    }
}


public class Solution {
    public static String reverseString(String s) {
        char[] ch = s.toCharArray();
        int halfLength = s.length() / 2;
        char temp;
        for (int i = 0; i < halfLength; i++) {
            temp = ch[s.length() - 1 - i];
            ch[s.length() - 1 - i] = ch[i];
            ch[i] = temp;
        }
        return new String(ch);
    }
}


public class Solution {
    public String reverseString(String s) {
      StringBuffer reverse = new StringBuffer();
     
      for(int i = s.length()-1; i >= 0; i--){
          reverse.append(s.charAt(i));
      }
     return reverse.toString();
    }
}
