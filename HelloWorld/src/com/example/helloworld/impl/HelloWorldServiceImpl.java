package com.example.helloworld.impl;

import com.example.helloworld.HelloWorldService;

public class HelloWorldServiceImpl implements HelloWorldService {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// Print a friendly greeting. If arguments are provided, join and print them; otherwise print "Hello World".
		if (args == null || args.length == 0) {
			System.out.println("Hello World");
		} else {
			System.out.println("Hello " + String.join(" ", args));
		}
	}

	/**
	 * Compares two strings and returns the comparison result.
	 * 
	 * @param str1 the first string to compare
	 * @param str2 the second string to compare
	 * @return 0 if strings are equal, 1 if str1 > str2 lexicographically, -1 if str1 < str2
	 */
	public static int compareStrings(String str1, String str2) {
		if (str1 == null || str2 == null) {
			if (str1 == str2) {
				return 0; // both null
			}
			return str1 == null ? -1 : 1; // null is considered "less than" non-null
		}
		return str1.compareTo(str2);
	}

	/**
	 * Compares two strings ignoring case differences.
	 * 
	 * @param str1 the first string to compare
	 * @param str2 the second string to compare
	 * @return true if strings are equal (ignoring case), false otherwise
	 */
	public static boolean compareStringsIgnoreCase(String str1, String str2) {
		if (str1 == null || str2 == null) {
			return str1 == str2; // both null or both non-null reference equality
		}
		return str1.equalsIgnoreCase(str2);
	}

}
