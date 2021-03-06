/*Given a set of distinct integers, nums, return all possible subsets.

Note:
Elements in a subset must be in non-descending order.
The solution set must not contain duplicate subsets.
For example,
If nums = [1,2,3], a solution is:

[
  [3],
  [1],
  [2],
  [1,2,3],
  [1,3],
  [2,3],
  [1,2],
  []
]*/
The mathematical definition of a set ensures the uniqueness of its elements. For a set of cardinality n, the number of its subsets is 2^n. A DFS will traverse every one of them. During each recursion, you can either choose this element or not, resulting in two recursive path.

每个Subset的长度是N数量级 其实总复杂度为N*2^N
　　Time complexity is O(2^n), where n is the cardinality of the set. Space complexity is O(n).

public class Solution {
    public List<List<Integer>> subsets(int[] nums) {
        List<List<Integer>> res = new ArrayList<List<Integer>>();
        if (nums == null || nums.length == 0) {
            return res;
        }
        Arrays.sort(nums);
        dfs(res, new ArrayList<Integer>(), nums, 0);
        return res;
    }
    
    public void dfs(List<List<Integer>> res, List<Integer> list, int[] nums, int pos) {
        res.add(new ArrayList<Integer>(list));
        for (int i = pos; i < nums.length; i++) {
            list.add(nums[i]);
            dfs(res, list, nums, i + 1);
            list.remove(list.size() - 1);
        }
    }
}


public class Solution {
    public List<List<Integer>> subsets(int[] nums) {
        ArrayList<List<Integer>> result = new ArrayList<List<Integer>>();
        if(nums == null || nums.length == 0) return result;
        
        List<Integer> item = new ArrayList<Integer>();
        dfs(nums,result,item,0);
        return result;

    }
    public void dfs(int[] nums, ArrayList<List<Integer>> result, List<Integer> item, int pos){
        
        result.add(new ArrayList<Integer>(item));
        
        for(int i = pos; i < nums.length; i++){
            item.add(nums[i]);
            dfs(nums,result,item,i+1);
            item.remove(item.size()-1);
            
        }
        
    }
}

//method2, 模板DFS， 记得sort array和加{} 到结果
//和面试官讨论Corner case
public class Solution {
    public List<List<Integer>> subsets(int[] S) {
        List<List<Integer>> res = new ArrayList<List<Integer>>();
        ArrayList<Integer> path = new ArrayList<Integer>();
        res.add(path);
        if(S == null || S.length == 0){
            return res;
        }
        Arrays.sort(S);
        dfs(res, path, S, 0);
        return res;
    }
    
    private void dfs(List<List<Integer>> res, ArrayList<Integer> path, int[] S, int pos){
        for(int i = pos; i < S.length; i++){
            //if(i != pos && S[i - 1] == S[i]) continue; subset2
            path.add(S[i]);
            res.add(new ArrayList<Integer>(path));
            dfs(res, path, S, i + 1);
            path.remove(path.size() - 1);
        }
    }
}

