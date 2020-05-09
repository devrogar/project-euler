package com.devrogar.projecteuler;

import java.math.BigInteger;
import java.util.HashMap;
import java.util.Map;

/**
 * 
 * https://projecteuler.net/problem=14
 * 
 * @author Rohit G
 *
 */
public class P14_Longest_Collatz_sequence {

	public static void main(String[] args) {
		P14_Longest_Collatz_sequence p14 = new P14_Longest_Collatz_sequence();
		long start = System.currentTimeMillis();
		System.out.println(p14.getLongestChain());
		System.out.print(System.currentTimeMillis() - start + "ms");
	}
	
	Map<BigInteger,Integer> map = new HashMap<>();
	BigInteger bigIntegerZero = new BigInteger("0");
	BigInteger bigIntegerOne = new BigInteger("1");
	BigInteger bigIntegerTwo = new BigInteger("2");
	BigInteger bigIntegerThree = new BigInteger("3");
	
	private int getLongestChain() {
		int max = 0;
		int val = 0;
		for (int i=1;i<1000000;i++) {
			int count = getChainCount(new BigInteger(String.valueOf(i)));
			if (count > max) {
				max = count;
				val = i;
			}
		}
		return val;
	}
	
	private int getChainCount(BigInteger bigStartingNumber) {
		Integer count = map.get(bigStartingNumber);
		if (count == null) {
			if (bigStartingNumber.equals(bigIntegerOne)) {
				count = 1;
			} else if (bigStartingNumber.remainder(bigIntegerTwo).equals(bigIntegerZero)) {
				count = getChainCount(bigStartingNumber.divide(bigIntegerTwo)) + 1;
			} else {
				count = getChainCount(bigStartingNumber.multiply(bigIntegerThree).add(bigIntegerOne));
			}
			map.put(bigStartingNumber, count);
		}
		return count;
	}
	
	
	
}
