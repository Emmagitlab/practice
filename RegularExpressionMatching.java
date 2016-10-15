/*Implement regular expression matching with support for '.' and '*'.

'.' Matches any single character.
'*' Matches zero or more of the preceding element.

The matching should cover the entire input string (not partial).

The function prototype should be:
bool isMatch(const char *s, const char *p)

Some examples:
isMatch("aa","a") → false
isMatch("aa","aa") → true
isMatch("aaa","aa") → false
isMatch("aa", "a*") → true
isMatch("aa", ".*") → true
isMatch("ab", ".*") → true
isMatch("aab", "c*a*b") → true*/






// Java DP
public boolean isMatch(String s, String p) {
    /*
        'match' below including .
    f(i,j) means s where s.len=i matches p where p.len=j
    f(i,j) =
        if (p_j-1 != * ) f(i-1, j-1) and s_i-1 matches p_j-1
        if (p_j-1 == * )
            * matches zero times: f(i,j-2)
            or * matches at least one time: f(i-1,j) and s_i-1 matches p_j-2
     */

    if (!p.isEmpty() && p.charAt(0) == '*') {
        return false;   // invalid p
    }

    boolean[][] f = new boolean[s.length() + 1][p.length() + 1];

    // initialize f(0,0)
    f[0][0] = true;

    // f(k,0) and f(0,2k-1) where k>=1 are false by default

    // initialize f(0,2k) where p_2k-1 = * for any k>=1
    for (int j = 1; j < p.length(); j+=2) {
        if (p.charAt(j) == '*') {
            f[0][j+1] = f[0][j-1];
        }
    }

    for (int i = 1; i <= s.length(); i++) {
        for (int j = 1; j <= p.length(); j++) {
            if (p.charAt(j - 1) != '*') {
                f[i][j] = f[i - 1][j - 1] && isCharMatch(s.charAt(i - 1), p.charAt(j - 1));
            } else {
                f[i][j] = f[i][j - 2] || f[i - 1][j] && isCharMatch(s.charAt(i - 1), p.charAt(j - 2));
            }
        }
    }

    return f[s.length()][p.length()];
}

// no * in p
private boolean isCharMatch(char s, char p) {
    return p == '.' || s == p;
}







public class Solution {
    public boolean isMatch(String s, String p) {
        //Java note: s.substring(n) will be "" if n == s.length(), but if n > s.length(), index oob error
        
        int i = 0, j = 0;
        //you don't have to construct a state machine for this problem
 
        if (s.length() == 0) {
            return checkEmpty(p);
        }
 
        if (p.length() == 0) {
            return false;
        }
 
        char c1 = s.charAt(0);
        char d1 = p.charAt(0), d2 = '0'; //any init value except '*'for d2 will do
 
        if (p.length()>1){
            d2 = p.charAt(1);
        }
 
        if (d2 == '*') {
            if (compare(c1, d1)) {
                //fork here: 1. consume the character, and use the same pattern again.
                //2. keep the character, and skip 'd1*' pattern
                 
                //Here is also an opportunity to use DP, but the idea is the same
                return isMatch(s.substring(1), p) || isMatch(s, p.substring(2));
            }
            else {
                return isMatch(s, p.substring(2));
            }
        }
        else {
            if (compare(c1, d1)) {
                return isMatch(s.substring(1), p.substring(1));
            }
            else {
                return false;
            }
        }
    }
    
    public boolean compare(char c1, char d1){
        return d1 == '.' || c1 == d1;
    }
 
    public boolean checkEmpty(String p) {
        if (p.length()%2 != 0) {
            return false;  
        }
 
        for (int i = 1; i < p.length(); i+=2) {
            if (p.charAt(i) != '*') {
                return false;
            }
        }
        return true;
    }
}


// my
public class Solution {
    public boolean isMatch(String s, String p) {
        int m = s.length(), n = p.length();
		boolean[] res = new boolean[n + 1];
		res[0] = true;

		int i, j;
		for (j = 2; j <= n; j++)
			res[j] = res[j - 2] && p.charAt(j - 1) == '*';

		char pc, sc, tc;
		boolean pre, cur; // pre=res[i - 1][j - 1], cur=res[i-1][j]

		for (i = 1; i <= m; i++) {
			pre = res[0];
			res[0] = false;
			sc = s.charAt(i - 1);

			for (j = 1; j <= n; j++) {
				cur = res[j];
				pc = p.charAt(j - 1);
				if (pc != '*')
					res[j] = pre && (sc == pc || pc == '.');
				else {
					// pc == '*' then it has a preceding char, i.e. j>1
					tc = p.charAt(j - 2);
					res[j] = res[j - 2] || (res[j] && (sc == tc || tc == '.'));
				}
				pre = cur;
			}
		}

		return res[n];
        
    }
}
