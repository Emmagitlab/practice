Write a function that takes an unsigned integer and returns the number of ’1' bits it has (also known as the Hamming weight).

For example, the 32-bit integer ’11' has binary representation 00000000000000000000000000001011, so the function should return 3.


Operator	Description	Example
>>> (zero fill right shift)	Shift right zero fill operator. 
The left operands value is moved right by the number of bits specified by the right operand and shifted values are filled up with zeros.	
A >>>2 will give 15 which is 0000 1111


public class Solution {
    // you need to treat n as an unsigned value
    public int hammingWeight(int n) {
        int bit = 0;
        while(n !=0){
            bit += n&1;
            n = n>>>1;
        }
        return bit;
    }
}
