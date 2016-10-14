1. 2 sum:
Question:
Input: numbers={2, 7, 11, 15}, target=9
Output: index1=1, index2=2
Answer:
public class Solution {
    public int[] twoSum(int[] numbers, int target) {
        
        HashMap<Integer, Integer> hmap = new HashMap<Integer, Integer>();
        int[] res = {-1,-1};
        
        for(int i=0; i<numbers.length; ++i) {
            int o = target - numbers[i];
            if(hmap.containsKey(o)) {
                int oindex = hmap.get(o);
                if(oindex != i) {
                    res[0] = i+1;
                    res[1] = oindex+1;
                    break;
                }
            }
            if(!hmap.containsKey(numbers[i])) {
                hmap.put(numbers[i], i);
            }
        }

        if(res[0]>res[1]){
            int tmp = res[0];
            res[0]=res[1];
            res[1]=tmp;
        }
        return res;
    }
}

// 3 SUM/4 SUM WITHOUT SORT WITH HASHMAP

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package javaapplication1;

/**
 *
 * @author YangE
 */
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class Solution {

    public static void main(String[] args) {
        Solution sol = new Solution();
        int[] num = {2, 4,2,1, 11, 15};
        int target = 9;
        int target2 = 8;
        List<List<Integer>> result3Sum = sol.threeSum(num, target2);
         for (int i = 0; i < result3Sum.size(); i++) {
            System.out.println(result3Sum.get(i).toString());
        }
        
        
        List<List<Integer>> result4Sum = sol.fourSum(num, target);

        
        for (int i = 0; i < result4Sum.size(); i++) {
            System.out.println(result4Sum.get(i).toString());
        }

    }

    public List<List<Integer>> threeSum(int[] num, int target) {
        int sz = num.length;

        HashMap<Integer, List<Integer>> m = new HashMap<Integer, List<Integer>>();
        List<List<Integer>> ret = new ArrayList<List<Integer>>();
        List<Integer> tri = new ArrayList<Integer>();

        for (int i = 0; i < sz; ++i) {
            int val = num[i];
            if (m.containsKey(val)) {
                List<Integer> l = m.get(val);
                l.add(i);
            } else {
                ArrayList<Integer> l = new ArrayList<Integer>();
                l.add(i);
                m.put(val, l);
            }
        }

        for (int i = 0; i < sz; ++i) {
            for (int j = i + 1; j < sz; ++j) {
                int o = target - num[i] - num[j];
                if (m.containsKey(o)) {
                    List<Integer> l = m.get(o);
                    for (int idx : l) {
                        if (idx > j) {
                            tri.add(num[i]);
                            tri.add(num[j]);
                            tri.add(num[idx]);
                            if (ret.isEmpty() || !tri.equals(ret.get(ret.size() - 1))) {
                                ret.add(tri);
                            }
                        }
                    }
                }
            }
        }
        return ret;
    }

    public List<List<Integer>> fourSum(int[] num, int target) {

        HashMap<Integer, List<Integer>> hmap = new HashMap<Integer, List<Integer>>();
        List<List<Integer>> ret = new ArrayList<List<Integer>>();
        List<Integer> tup = new ArrayList<Integer>();
        int sz = num.length;

        for (int i = 0; i < sz; ++i) {
            for (int j = i + 1; j < sz; ++j) {
                //key: val, value: idx=i*sz+j;
                int val = num[i] + num[j];
                int idx = i * sz + j;                              // pair(i,j) <-> idx= i*sz +j;

                if (hmap.containsKey(val)) {
                    hmap.get(val).add(idx);
                } else {
                    List<Integer> l = new ArrayList<Integer>();
                    l.add(idx);
                    hmap.put(val, l);
                }
            }
        }

        for (int i = 0; i < sz; ++i) {
            for (int j = i + 1; j < sz; ++j) {
                int val = num[i] + num[j];
                int o = target - val;

                if (hmap.containsKey(o)) {
                    List<Integer> l = hmap.get(o);
                    for (int pos : l) {
                        if (j < pos / sz) {                             //!important, to avoid duplicate!     
                            tup.add(num[i]);
                            tup.add(num[j]);
                            tup.add(num[pos / sz]);
                            tup.add(num[pos % sz]);
                            if (ret.isEmpty() || !tup.equals(ret.get(ret.size() - 1))) {
                                ret.add(tup);
                            }
                        }
                    }
                }
            }
        }
        return ret;
    }
}
