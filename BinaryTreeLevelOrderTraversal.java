/*Given a binary tree, return the level order traversal of its nodes values. (ie, from left to right, level by level).

For example:
Given binary tree {3,9,20,#,#,15,7},
    3
   / \
  9  20
    /  \
   15   7
return its level order traversal as:
[
  [3],
  [9,20],
  [15,7]
]*/


/**
 * Definition for a binary tree node.
 * public class TreeNode {
 *     int val;
 *     TreeNode left;
 *     TreeNode right;
 *     TreeNode(int x) { val = x; }
 * }
 */
public class Solution {
    public List<List<Integer>> levelOrder(TreeNode root) {
        List<List<Integer>> res = new ArrayList<List<Integer>>();
        if (root == null) return res;
        Queue<TreeNode> queue = new LinkedList<TreeNode>();
        queue.offer(root);
        while (!queue.isEmpty()) {
        	int size = queue.size();
        	List<Integer> temp = new ArrayList<Integer>();
        	for (int i = 1; i <= size; i++) {
        		TreeNode node = queue.poll();
        		temp.add(node.val);
        		if (node.left != null) queue.offer(node.left);
        		if (node.right != null) queue.offer(node.right);
        	}
        	res.add(temp);
        }
        return res;
    }
}


public class Solution {
  
  public void levelOrderNaiveApproach(Node root){
    int h = height(root);
    for(int i=1;i<=h;i++){
       printLevels(root,i);
       System.out.println("");
     }
   }
   public void printLevels(Node root, int h){
     if(root==null) return;
     if(h==1) System.out.print(" " + root.data);
     else{
       printLevels(root.left,h-1);
       printLevels(root.right,h-1);
     }   
       }
       public int height(Node root){
     if (root==null) return 0;
     return 1 + Math.max(height(root.left),height(root.right));
   }
   public void levelOrderQueue(Node root){
     Queue q = new LinkedList();
     int levelNodes =0; 
    if(root==null) return;
     q.add(root);
     while(!q.isEmpty()){
       levelNodes = q.size();
       while(levelNodes>0){
        Node n = (Node)q.remove();
        System.out.print(" " + n.data);
        if(n.left!=null) q.add(n.left);
        if(n.right!=null) q.add(n.right);
        levelNodes--;
      }
      System.out.println("");
    }
  }
  public static void main (String[] args) throws java.lang.Exception
  {
    Node root = new Node(5);
    root.left = new Node(10);
    root.right = new Node(15);
    root.left.left = new Node(20);
    root.left.right = new Node(25);
    root.right.left = new Node(30);
    root.right.right = new Node(35);
    
    Solution i  = new Solution();
    System.out.println(" Output by Naive Approach : ");
    i.levelOrderNaiveApproach(root);
    System.out.println(" Output by Better Approach : ");
    i.levelOrderQueue(root);
  }
}
class Node{
  int data;
  Node left;
  Node right;
  public Node(int data){
    this.data = data;
    this.left = null;
    this.right =null;
  }
}
