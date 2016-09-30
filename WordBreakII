//Given a string s and a dictionary of words dict, add spaces in s to construct a sentence where each word is a valid dictionary word.

//Return all such possible sentences.

//For example, given
//s = "catsanddog",
//dict = ["cat", "cats", "and", "sand", "dog"].

//A solution is ["cats and dog", "cat sand dog"].

//DP: time: O(n^2*k), space: O(nk), 假设k表示平均每个长度对应解的个数DFS: time: O(2^n), space: O(n)


//DP

public class Solution {
    public List<String> wordBreak(String s, Set<String> wordDict) {
        // 判断是否能够分解
        if (!helper(s, wordDict)) {
            return new ArrayList<String>();
        }
        
        // 记录字符串s.substring(0, i)对应的解
        HashMap<Integer, List<String>> map = new HashMap<Integer, List<String>>();
        map.put(0, new ArrayList<>());
        map.get(0).add("");
        
        for (int i = 1; i <= s.length(); i++) {
            for (int j = 0; j < i; j++) {
                if (map.containsKey(j) && wordDict.contains(s.substring(j, i))) {
                    if (!map.containsKey(i))
                        map.put(i, new ArrayList<>());
                    for (String str : map.get(j)) {
                        map.get(i).add(str + (str.equals("") ? "" : " ") + s.substring(j, i));
                    }
                }
            }
        }
        
        return map.get(s.length());
    }
    
    private boolean helper(String s, Set<String> wordDict) {
        boolean dp[] = new boolean[s.length() + 1];
        dp[0] = true;
        for (int i = 1; i <= s.length(); i++) {
            for (int j = 0; j < i; j++) {
                if (dp[j] && wordDict.contains(s.substring(j, i))) {
                    dp[i] = true;
                }
            }
        }
        return dp[s.length()];
    }
}




//DFS
public class Solution {
    public List<String> wordBreak(String s, Set<String> wordDict) {
        List<String> res = new ArrayList<String>();

        // 用来记录s.substring(i)这个字符串能否分解         
        boolean[] possible = new boolean[s.length() + 1];
        Arrays.fill(possible, true);
        dfs(res, "", s, wordDict,  0, possible);
        return res;
    }
    
    public static void dfs(List<String> res, String cur, String s, Set<String> wordDict, int start, boolean[] possible) {
        if (start == s.length()) {
            res.add(cur);
            return;
        }
        for (int i = start + 1; i <= s.length(); i++) {
            String str = s.substring(start, i);
            if (wordDict.contains(str) && possible[i]) {
                int prevSize = res.size();
                dfs(res, cur + (cur.equals("") ? "" : " ") + str, s, wordDict, i, possible);
                
                // DFS后面部分结果没有变化，说明后面是没有解的    
                if (res.size() == prevSize)
                    possible[i] = false;
            }
        }
    } 
}
