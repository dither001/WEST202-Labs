//Benjamin Simpson, Nick Foster
import java.util.Scanner;

public class IsBinaryPalindrome {
	public static void main(String[] args) {
		
		if(palCheck(toBinary(args[0]))) System.out.println(args[0] + " in binary (" + toBinary(args[0]) + "), is a palindrome.");
		else System.out.println(args[0] + " in binary (" + toBinary(args[0]) + "), is a not palindrome.");
		
	}

	public static String toBinary(String s) {
		String pancake = "";
		int n = Integer.parseInt(s);
		while (n > 0) {
			if (n % 2 == 0)
				pancake += "0";
			else
				pancake += "1";

			n /= 2;
		}

		return pancake;
	}
	
	
	public static boolean palCheck(String s) {
		
		
		ListStack<Character> vampire = new ListStack<Character>();
		boolean isPalindrome = true;
		do {
			
			
			for (int i = 0; i < s.length(); i++) {
				vampire.push(s.charAt(i));
			}
			
			isPalindrome = true;
			
			for(int i = 0; i < s.length(); i++) {
				if(vampire.pop() != s.charAt(i)) {
					isPalindrome = false;
					break;
				}
				
			}
			if(isPalindrome) {
				return true;
			}else {
				return false;
			}
		} while (s.length() != 0);
		
		
		


	}


}
