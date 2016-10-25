


Given an input string, reverse the string word by word.

For example,
Given s = "the sky is blue",
return "blue is sky the".



public class Solution {
    public String reverseWords(String s) {
        String[] string= s.split(" ");
        if(string.length==0 || string == null) return "";
        StringBuffer result = new StringBuffer();
        for(int i = string.length-1; i >=0; --i){
            if(!string[i].equals("")) result.append(string[i]+" ");
        }
        return result.toString().trim();
    }
}
