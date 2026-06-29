package com.example.helloworld.impl;

/**
 * Test class to demonstrate string comparison methods.
 */
public class StringComparisonTest {

	public static void main(String[] args) {
		System.out.println("=== String Comparison Tests ===\n");

		// Test 1: Lexicographic comparison
		System.out.println("Test 1: Lexicographic Comparison");
		String str1 = "apple";
		String str2 = "banana";
		int result = HelloWorldServiceImpl.compareStrings(str1, str2);
		System.out.println("  compareStrings(\"" + str1 + "\", \"" + str2 + "\") = " + result);
		System.out.println("  (Result: " + (result < 0 ? "str1 < str2" : result > 0 ? "str1 > str2" : "str1 == str2") + ")\n");

		// Test 2: Equal strings
		System.out.println("Test 2: Equal Strings");
		str1 = "hello";
		str2 = "hello";
		result = HelloWorldServiceImpl.compareStrings(str1, str2);
		System.out.println("  compareStrings(\"" + str1 + "\", \"" + str2 + "\") = " + result);
		System.out.println("  (Result: " + (result < 0 ? "str1 < str2" : result > 0 ? "str1 > str2" : "str1 == str2") + ")\n");

		// Test 3: Case-insensitive comparison - equal
		System.out.println("Test 3: Case-Insensitive Comparison (Equal)");
		str1 = "Hello";
		str2 = "HELLO";
		boolean caseInsensitiveResult = HelloWorldServiceImpl.compareStringsIgnoreCase(str1, str2);
		System.out.println("  compareStringsIgnoreCase(\"" + str1 + "\", \"" + str2 + "\") = " + caseInsensitiveResult + "\n");

		// Test 4: Case-insensitive comparison - not equal
		System.out.println("Test 4: Case-Insensitive Comparison (Not Equal)");
		str1 = "Java";
		str2 = "Python";
		caseInsensitiveResult = HelloWorldServiceImpl.compareStringsIgnoreCase(str1, str2);
		System.out.println("  compareStringsIgnoreCase(\"" + str1 + "\", \"" + str2 + "\") = " + caseInsensitiveResult + "\n");

		// Test 5: Null handling in lexicographic comparison
		System.out.println("Test 5: Null Handling");
		result = HelloWorldServiceImpl.compareStrings(null, "test");
		System.out.println("  compareStrings(null, \"test\") = " + result + " (null < non-null)\n");

		System.out.println("=== All Tests Complete ===");
	}
}
