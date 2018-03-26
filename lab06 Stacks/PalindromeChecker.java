
/**
 * Benjamin Simpson, Nick Foster, 2017
 */

import java.util.Scanner;

public class PalindromeChecker {
	public static void main(String[] args) {
		Scanner scan = new Scanner(System.in);
		String message = null;
		ListStack<Character> vampire = new ListStack<Character>();
		boolean isPalindrome = true;
		do {
			System.out.println("Enter a string:");

			message = scan.nextLine();
			
			for (int i = 0; i < message.length(); i++) {
				vampire.push(message.charAt(i));
			}
			
			isPalindrome = true;
			
			for(int i = 0; i < message.length(); i++) {
				if(vampire.pop() != message.charAt(i)) {
					System.out.println(message + " is not a palindrome.");
					isPalindrome = false;
					break;
				}
				
			}
			if(isPalindrome) {
				System.out.println(message + " is a palindrome.");
			}
		} while (message.length() != 0);
		
		
		scan.close();


	}

}