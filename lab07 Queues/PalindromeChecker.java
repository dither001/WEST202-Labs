
/**
 *	@Author Nick Foster, revised from code by Ben Simpson & Nick Foster
 *	@Date	October 2017
 */

import java.util.Scanner;

public class PalindromeChecker {
	public static void main(String[] args) {
		Scanner input = new Scanner(System.in);

		String message = "";
		boolean isPalindrome = true;

		do {
			ListQueue<Character> queue = new ListQueue<Character>();
			ListStack<Character> stack = new ListStack<Character>();
			
			System.out.println("Enter a string:");
			message = input.nextLine();

			for (int i = 0; i < message.length(); i++) {
				stack.push(message.charAt(i));
				queue.add(message.charAt(i));
			}
			
			// gotta reset this before each check
			isPalindrome = true;
			for (int i = 0; i < message.length(); i++) {
				if (stack.pop() != queue.remove()) {
					System.out.println(message + " is NOT a palindrome.");
					isPalindrome = false;
					break;
				}
			}
			
			if (isPalindrome) {
				System.out.println(message + " IS a palindrome.");
			}
		} while (message.length() != 0);

		input.close();
	}

}