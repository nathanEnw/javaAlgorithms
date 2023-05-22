package Assignment;

import java.util.Scanner;
import java.io.File;
import java.io.FileNotFoundException;

public class Recursion {

	public static boolean isSlip(String str) {
	    int i = 0;
	    // Continues as long as the string is
	    while (i < str.length()) {
	        // Check if current character is D or E
	    	if (str.charAt(i) == 'D' || str.charAt(i) == 'E') {
	    	    int j = i + 1;
	    	    boolean isPatternMatched = true;
	    	    // Loops through for F's
	    	    while (j < str.length() && str.charAt(j) == 'F') {
	    	        j++;
	    	    }
	    	    // Stops after there are no more
	    	    if (j == str.length()) {
	    	        isPatternMatched = false;
	    	    } else {
	    	        // Checks if next character is the same as current character
	    	        char nextChar = str.charAt(j);
	    	        int k = j + 1;
	    	        // Loops through F or G
	    	        while (k < str.length() && (str.charAt(k) == 'F' || str.charAt(k) == 'G')) {
	    	            k++;
	    	        }
	    	        if (k == str.length() || (str.charAt(k) != str.charAt(i) && !(str.charAt(k) == 'F' && nextChar == 'F'))) {
	    	            isPatternMatched = false;
	    	        }
	    	    }
	    	    // If meets requirements, is true
	    	    if (isPatternMatched) {
	    	        return true;
	    	    } else {
	    	        i = j;
	    	    }
	    	} else {
	    	    i++;
	    	}
	    }
	    // Is false if string doesn't meet requirements
	    return false;
	}

	public static boolean isSlap(String str) {
		// Checks if the string is 2 characters and ends with an H
		    if (str.length() == 2 && str.charAt(1) == 'H') {
		        return true;
		        // Check if starts with A, B and ends with C
		    } else if (str.startsWith("AB") && str.endsWith("C")) {
		    	// Calls isSlap on parts in between
		        String subStr = str.substring(2, str.length() - 1);
		        return isSlap(subStr);
		        // Check if the first 2 characters fulfill slip and end with C
		    } else if (isSlip(str.substring(0, 2)) && str.endsWith("C")) {
		    	// Calls isSlip after first 2 characters before the last character
		        String subStr = str.substring(2, str.length() - 1);
		        return isSlip(subStr);
		        // Not a slap
		    } else {
		        return false;
		    }
		}

	public static boolean isSlop(String str) {
		// Too short to be a slop
	    if (str.length() < 4) {
	        return false;
	    }
	    // Check if the requirements for each method are fulfilled
	    if (isSlap(str.substring(0, 2)) && isSlip(str.substring(2))) {
	        return true; 
	    }
	    // Check if starts with A and ends with C
	    if (str.startsWith("A") && str.endsWith("C")) {
	    	// Gets middle part of string
	        String middle = str.substring(1, str.length() - 1);
	        // Finds B in the "middle"
	        int index = middle.indexOf("B");
	        if (index >= 0) {
	        	// Splits into two substrings
	            String prefix = middle.substring(0, index);
	            String suffix = middle.substring(index + 1);
	            // Check if they form a slap and slip
	            return isSlap(prefix) && isSlip(suffix);
	        }
	        return isSlip(middle);
	    }
	    return false;
	}
	
	public static void main(String[] args) throws FileNotFoundException{
		File inputFile = new File("input.txt");
		System.out.println("SLOPS OUTPUT");
        try (Scanner scanner = new Scanner(inputFile)) {
            while (scanner.hasNextLine()) {
                String str = scanner.nextLine();
                if (isSlop(str)) {
                    System.out.println(str + " YES");
                } else {
                    System.out.println(str + " NO");
                }
            }
        } catch (FileNotFoundException e) {
            System.out.println("Cannot find input file");
        }
        System.out.println("END OF OUTPUT");
    }

}