package com.gp.homework.pattern.proxy.cglib;

import java.util.Comparator;

public class MyTestJava {

	private static int diff = -100 ;

	private static String testStr = "Liliwei" ;

	public static void main(String[] args) {




		System.out.println(diff > 0 ? 1 : diff == 0 ? 0 : -1);

		System.out.println(testStr.equals("A") ? "right" : testStr.equals("Liliwei") ? "wrong" : "noResult");

		CompareObject compare = new CompareObject() ;
		System.out.println(compare.compare(1, 2));

	}

}

class CompareObject implements Comparator<Integer>,Comparable{

	@Override
	public int compare(Integer o1, Integer o2) {
		return o1-o2 > 0 ? 1 : -1;
	}

	@Override
	public int compareTo(Object o) {
		if(o == null) return 0 ;
		if (this.equals(o)) return 0 ;

		return this.hashCode() - o.hashCode();
	}
}
