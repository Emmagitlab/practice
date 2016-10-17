Given n pairs of parentheses, write a function to generate all combinations of well-formed parentheses.

For example, given n = 3, a solution set is:

[
  "((()))",
  "(()())",
  "(())()",
  "()(())",
  "()()()"
]


public class Solution {
    public List<String> generateParenthesis(int n) {
        ArrayList<String> res = new ArrayList<String>();
        if(n == 0) return res;
        String str = new String();
        dfs(res, str, n, n);
        return res;
    }
    public void dfs(List<String> res, String str, int left, int right){
        if(right < left) return;
        if(right == 0 && left == 0) res.add(new String (str));
        
        if(left > 0) dfs(res, str+"(", left-1, right);
        if(right > 0) dfs(res, str+")", left, right-1);
    }
}
