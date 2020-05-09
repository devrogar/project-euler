package com.devrogar.projecteuler;

import java.util.HashMap;
import java.util.Map;

/**
 * 
 * https://projecteuler.net/problem=12
 * 
 * @author Rohit G
 *
 */
public class P12_Highly_divisible_triangular_number {

	public static void main(String[] args) {

		P12_Highly_divisible_triangular_number p12 = new P12_Highly_divisible_triangular_number();
		//System.out.println(p12.getPrimeFactors(66));
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

	/*
	 * Optimized
	 * 
	 * The total divisors any number can have is all the combinations possible
	 * for the prime factors that number has.
	 */
	private int getDivisorCount(int val) {
		if (val == 1) return 1;
		if (val == 2) return 2;
		Map<Integer,Integer> primeFactors = getPrimeFactors(val);
		int combinations = 1;
		for (Map.Entry<Integer, Integer> entry : primeFactors.entrySet()) {
			combinations *= (entry.getValue() + 1);
		}
		return combinations;
	}

	private Map<Integer, Integer> getPrimeFactors(int val) {
		Map<Integer, Integer> primeFactors = new HashMap<>();
		int n = val;
		while (n % 2 == 0) { 
			primeFactors.putIfAbsent(2, 0);
			primeFactors.merge(2, 1, Integer::sum);
            n /= 2; 
        }
		for (int i = 3; i <= Math.sqrt(n); i += 2) { 
            while (n % i == 0) { 
            	primeFactors.putIfAbsent(i, 0);
    			primeFactors.merge(i, 1, Integer::sum);
                n /= i; 
            } 
        }
		if (n > 2) {
			primeFactors.putIfAbsent(n, 0);
			primeFactors.merge(n, 1, Integer::sum);
		}
		return primeFactors;
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
