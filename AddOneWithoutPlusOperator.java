


//Given a non-negative number represented as an array of digits, plus one to the number.

//The digits are stored such that the most significant digit is at the head of the list.

public class Solution {
	public void addOne(int num) {
		//System.out.println(addOneHelper(num, 1));
		int carry = 1;
		int sum = 0;
		while (carry != 0) {
			sum = num ^ carry;
			carry = (num & carry) << 1;
			num = sum;
		}
		System.out.println(sum);
		
	}
	// 任意Add
	public int addOneHelper(int a, int b) {
		if (b == 0) {
			return a;
		}
		int sum = a ^ b;
		int carry = (a & b) << 1;
		return addOneHelper(sum, carry);
	}

	public static void main(String[] args) {
		
	}
}



public class Solution {
    public int[] plusOne(int[] digits) {
        int len = digits.length-1;
        while(len >= 0 && digits[len] == 9){
            digits[len] = 0;
            len--;
        }
	// if len == -1, it should be 1 many 0 ,so just set restul[0] =1
        if(len == -1){
            int[] result = new int[digits.length+1];
            result[0] = 1;
            return result;
        } else {
            digits[len] += 1;
            return digits;
        }
    }
}
