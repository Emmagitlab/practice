他给的例子：
input: ABAABB
cooldown: 2 (don't repeat characters in this unit of time)
output: AB_A__AB__B




import java.util.HashMap;

public class Solution {
    public String task (String s, int k){
        if(s == null || s.length() == 0) return null;
        int time = 0;
        HashMap<Character, Integer> tracker = new HashMap<>();
        StringBuilder sb = new StringBuilder();
        for(int i = 0; i<s.length();i++){
            char curr = s.charAt(i);
            time++;
            if(tracker.containsKey(curr)){
                int oldTime = tracker.get(curr);
                if(time - oldTime > k){
                    tracker.put(curr, time);
                    sb.append(curr);
                }else {
                    int temp = oldTime + k +1;
                    for(int j = time; j < temp; j++){
                        sb.append("_");
                    }
                    sb.append(curr);
                    tracker.put(curr, temp);
                    time = temp;
                }
                
            }
            else {
                tracker.put(curr, time);
                sb.append(curr);
            
            }

        }
        return sb.toString();
    }



    public static void main(String[] args) {
        Solution test = new Solution();
        System.out.println(test.task("ABAABB", 2));
    }
}
