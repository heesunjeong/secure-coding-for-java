package kr.co.bookking.util;

public class NumericOverflowTest {
	public static void main(String[] args) {
		int MAX = Integer.MAX_VALUE;
		System.out.println(MAX); //2147483647
		System.out.println(Integer.toBinaryString(MAX));
		System.out.println(MAX + 1); //-2147483648
		System.out.println(Integer.toBinaryString(MAX + 1));
	}
}
