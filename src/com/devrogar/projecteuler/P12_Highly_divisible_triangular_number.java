package com.devrogar.projecteuler;

import java.util.HashMap;
import java.util.Map;

/**
 * Extremely slow and naive solution
 * 
 * https://projecteuler.net/problem=12
 * 
 * @author Rohit G
 *
 */
public class P12_Highly_divisible_triangular_number {
	
	public static void main(String[] args) {
		
		P12_Highly_divisible_triangular_number p12 = new P12_Highly_divisible_triangular_number();
		long start = System.currentTimeMillis();
		System.out.println(p12.getFirstTriangleValueWith500Divisors());
		System.out.print(System.currentTimeMillis() - start);
		
	}
	
	Map<Integer,Integer> map = new HashMap<>();

	public int getFirstTriangleValueWith500Divisors() {
		for (int i = 1;true;i++) {
			int val = getTriangleValues(i);
			if (val < 0) {
				break;
			}
			int divisorCount = getDivisorCount(val);
			//System.out.println(String.format("%s -> %s [divisorCount : %s]",i,val,divisorCount));
			if (divisorCount >= 500) {
				return val;
			}
		}
		return 0;
	}

	private int getDivisorCount(int val) {
		if (val == 1) return 1;
		if (val == 2) return 2;
		int divisorCount = 0;
		for (int i = 1;i<=val/2;i++) {
			if (val%i == 0) {
				divisorCount++;
			}
		}
		return divisorCount+1;
	}

	private int getTriangleValues(int n) {
		if (n == 0) return 0;
		Integer triangleValue = map.get(n);
		if (triangleValue == null) {
			triangleValue = n + getTriangleValues(n-1);
			map.put(n, triangleValue);
		}
		return triangleValue;
	}
	
}
