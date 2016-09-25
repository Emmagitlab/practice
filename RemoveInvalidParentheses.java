/*Remove the minimum number of invalid parentheses in order to make the input string valid. Return all possible results.

Note: The input string may contain letters other than the parentheses ( and ).


Examples:
"()())()" -> ["()()()", "(())()"]
"(a)())()" -> ["(a)()()", "(a())()"]
")(" -> [""]*/


/*The idea is straightforward, with the input string s, we generate all possible states by removing one ( or ), 
check if they are valid, if found valid ones on the current level, put them to the final result list and we are done, 
otherwise, add them to a queue and carry on to the next level.

The good thing of using BFS is that we can guarantee the number of parentheses that need to be removed is minimal, 
also no recursion call is needed in BFS.

Thanks to @peisi, we don't need stack to check valid parentheses.

Time complexity:

In BFS we handle the states level by level, in the worst case, we need to handle all the levels, 
we can analyze the time complexity level by level and add them up to get the final complexity.

On the first level, there's only one string which is the input string s, let's say the length of it is n, 
to check whether it's valid, we need O(n) time. On the second level, we remove one ( or ) from the first level, 
so there are C(n, n-1) new strings, each of them has n-1 characters, and for each string, 
we need to check whether it's valid or not, thus the total time complexity on this level is (n-1) x C(n, n-1). 
Come to the third level, total time complexity is (n-2) x C(n, n-2), so on and so forth...

Finally we have this formula:

T(n) = n x C(n, n) + (n-1) x C(n, n-1) + ... + 1 x C(n, 1) = n x 2^(n-1).

Following is the Java solution:*/


public class Solution {
    public List<String> removeInvalidParentheses(String s) {
      List<String> res = new ArrayList<>();

      // sanity check
      if (s == null) return res;

      Set<String> visited = new HashSet<>();
      Queue<String> queue = new LinkedList<>();

      // initialize
      queue.add(s);
      visited.add(s);

      boolean found = false;

      while (!queue.isEmpty()) {
        s = queue.poll();

        if (isValid(s)) {
          // found an answer, add to the result
          res.add(s);
          found = true;
        }

        if (found) continue;

        // generate all possible states
        for (int i = 0; i < s.length(); i++) {
          // we only try to remove left or right paren
          if (s.charAt(i) != '(' && s.charAt(i) != ')') continue;

          String t = s.substring(0, i) + s.substring(i + 1);

          if (!visited.contains(t)) {
            // for each state, if it's not visited, add it to the queue
            queue.add(t);
            visited.add(t);
          }
        }
      }

      return res;
    }

    // helper function checks if string s contains valid parantheses
    boolean isValid(String s) {
      int count = 0;

      for (int i = 0; i < s.length(); i++) {
        char c = s.charAt(i);
        if (c == '(') count++;
        if (c == ')' && count-- == 0) return false;
      }

      return count == 0;
    }
}


// 只要求一个返回String次数
import java.util.*;
public class Solution{
    
    public String balance(String str){
        if(str == null || str.length() == 0){
            return str;
        }
        
        Stack<Integer> stack = new Stack<Integer>();
        for(int i = 0; i < str.length(); i++){
            char c = str.charAt(i);
            if(stack.isEmpty() || c == '('){
                stack.push(i);
            }else{
                int idx = stack.peek();
                if(str.charAt(idx) == '('){
                    stack.pop();
                }else{
                    stack.push(i);
                }
            }
        }
        StringBuilder sb = new StringBuilder();
        char[] chars = str.toCharArray();
        while(!stack.isEmpty()){
            chars[stack.pop()] = '#';
        }
        for(int i = 0; i < chars.length; i++){
            if(chars[i] != '#'){
                sb.append(chars[i]);
            }
        }
        return sb.toString();
    }
    

    或者
    public static String balance2(String s) {
          Stack<Integer> stk = new Stack<Integer>();
          StringBuilder sb = new StringBuilder(s);
          for (int i = 0; i < s.length(); i++) {
           char c = s.charAt(i);
           if (c == ')') {
            if (stk.isEmpty() || s.charAt(stk.peek()) == ')') {
             stk.push(i);
            } else {
             stk.pop();
            }
           } else {
            stk.push(i);
           }
          }
          while (!stk.isEmpty()) {
           sb.deleteCharAt(stk.pop());
          }
          return sb.toString();
    }

    public String balance2(String str){
        if(str == null || str.length() == 0){
            return str;
        }
        char[] content = str.toCharArray();
        int misMatch = 0;
        for(int i = 0; i < content.length; i++){
            char c = content[i];
            if(c == '('){
                misMatch++;
            }else if(misMatch != 0){
                misMatch--;
            }else{
                content[i] = '#';
            }
        }
        for(int i = content.length - 1; i >= 0 && misMatch > 0; i--){
            if(content[i] == '('){
                misMatch--;
                content[i] = '#';
            }
        }
        StringBuilder sb = new StringBuilder();
        for(char c : content){
            if(c != '#'){
                sb.append(c);
            }
        }
        return sb.toString();
    }
    
    public static void main(String[] args){
        int[] num = {2, 3, 5};
        System.out.println(new Solution().balance(")))(((()()(())"));
    }

只要求次数
int minimumDeleteTimes(string s) {
    int count = 0;
    for(char c : s) {
        if(c == '(') count++;
        else if(c == ')') count–;
    }

    return count > 0 ? count : -count;
}

string turnToValid(string s) {
    stack stk; // store index of '('
    unordered_set set; // index need to delete
    for(int i = 0; i < s.length(); ++i) {
        if(s[i] == '(') stk.push(i);
        else if(s[i] == ')') {
            if(stk.empty()) {
                set.insert(i);
            } else stk.pop();
        }
    }

    while(!stk.empty()) {
        set.insert(stk.top());
        stk.pop();
    }

    string result;
    for(int i = 0; i < s.length(); ++i) {
        if(!set.count(i)) result.push_back(s[i]);
    }

    return result;
}


int  main() {
    string s = "((((a3sq))()))))";
    cout << turnToValid(s) << endl;


    return 0;
}

// DFS
/*DFS solution with optimizations:

Before starting DFS, calculate the total numbers of opening and closing parentheses that need to be removed in the final solution, then these two numbers could be used to speed up the DFS process.
Use while loop to avoid duplicate result in DFS, instead of using HashSet.
Use count variable to validate the parentheses dynamically.*/

public class Solution {
    public List<String> removeInvalidParentheses(String s) {
        int count = 0, openN = 0, closeN = 0;

        // calculate the total numbers of opening and closing parentheses
        // that need to be removed in the final solution
        for (char c : s.toCharArray()) {
            if (c == '(') {
                count++;
            } else if (c == ')') {
                if (count == 0) closeN++;
                else count--;
            }
        }
        openN = count;
        count = 0;

        if (openN == 0 && closeN == 0) return Arrays.asList(s);

        List<String> result = new ArrayList<>();
        StringBuilder sb = new StringBuilder();

        dfs(s.toCharArray(), 0, count, openN, closeN, result, sb);

        return result;
    }

    private void dfs(char[] s, int p, int count, int openN, int closeN, List<String> result, StringBuilder sb) {
        if (count < 0) return; // the parentheses is invalid

        if (p == s.length) {
            if (openN == 0 && closeN == 0) { // the minimum number of invalid parentheses have been removed
                result.add(sb.toString());
            }
            return;
        }

        if (s[p] != '(' && s[p] != ')') {
            sb.append(s[p]);
            dfs(s, p + 1, count, openN, closeN, result, sb);
            sb.deleteCharAt(sb.length() - 1);
        } else if (s[p] == '(') {
            int i = 1;
            while (p + i < s.length && s[p + i] == '(') i++; // use while loop to avoid duplicate result in DFS, instead of using HashSet
            sb.append(s, p, i);
            dfs(s, p + i, count + i, openN, closeN, result, sb);
            sb.delete(sb.length() - i, sb.length());

            if (openN > 0) {
                // remove the current opening parenthesis
                dfs(s, p + 1, count, openN - 1, closeN, result, sb);
            }
        } else {
            int i = 1;
            while (p + i < s.length && s[p + i] == ')') i++; // use while loop to avoid duplicate result in DFS, instead of using HashSet
            sb.append(s, p, i);
            dfs(s, p + i, count - i, openN, closeN, result, sb);
            sb.delete(sb.length() - i, sb.length());

            if (closeN > 0) {
                // remove the current closing parenthesis
                dfs(s, p + 1, count, openN, closeN - 1, result, sb);
            }
        }
    }
}

    
    
    Key Points:

Generate unique answer once and only once, do not rely on Set.
Do not need preprocess.
Runtime 3 ms.
Explanation:
We all know how to check a string of parentheses is valid using a stack. Or even simpler use a counter.
The counter will increase when it is ‘(‘ and decrease when it is ‘)’. Whenever the counter is negative, we have more ‘)’ than ‘(‘ in the prefix.

To make the prefix valid, we need to remove a ‘)’. The problem is: which one? The answer is any one in the prefix. However, if we remove any one, we will generate duplicate results, for example: s = ()), we can remove s[1] or s[2] but the result is the same (). Thus, we restrict ourself to remove the first ) in a series of concecutive )s.

After the removal, the prefix is then valid. We then call the function recursively to solve the rest of the string. However, we need to keep another information: the last removal position. If we do not have this position, we will generate duplicate by removing two ‘)’ in two steps only with a different order.
For this, we keep tracking the last removal position and only remove ‘)’ after that.

Now one may ask. What about ‘(‘? What if s = ‘(()(()’ in which we need remove ‘(‘?
The answer is: do the same from right to left.
However a cleverer idea is: reverse the string and reuse the code!
Here is the final implement in Java.

Java

public List<String> removeInvalidParentheses(String s) {
    List<String> ans = new ArrayList<>();
    remove(s, ans, 0, 0, new char[]{'(', ')'});
    return ans;
}

public void remove(String s, List<String> ans, int last_i, int last_j,  char[] par) {
    for (int stack = 0, i = last_i; i < s.length(); ++i) {
        if (s.charAt(i) == par[0]) stack++;
        if (s.charAt(i) == par[1]) stack--;
        if (stack >= 0) continue;
        for (int j = last_j; j <= i; ++j)
            if (s.charAt(j) == par[1] && (j == last_j || s.charAt(j - 1) != par[1]))
                remove(s.substring(0, j) + s.substring(j + 1, s.length()), ans, i, j, par);
        return;
    }
    String reversed = new StringBuilder(s).reverse().toString();
    if (par[0] == '(') // finished left to right
        remove(reversed, ans, 0, 0, new char[]{')', '('});
    else // finished right to left
        ans.add(reversed);
}
