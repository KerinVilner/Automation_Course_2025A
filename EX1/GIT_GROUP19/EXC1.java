package Automation1;
import java.util.Scanner;


public class EXC1 {

	// Menu 
	private static Scanner sc = new Scanner(System.in);
	public static void main(String[] args) {

		boolean ok = true;

		while(ok) {
			System.out.print("Hello!\n1. Press 1 to Convert from Decimal to HexaDecimal.\n2. Press 2 to convert from Hexadecimal to Decimal.\n3. Press 3 to exit\n");
			int answer = sc.nextInt();
			sc.nextLine(); 
			// Check for valid input
			while (answer != 1 && answer != 2 && answer != 3) {
				System.out.print("Wrong! Please try Again: ");
				answer = sc.nextInt();
				sc.nextLine();
			}
			if (answer == 1) {
				fromTenToHex(); 
			} else if (answer == 2) {
				fromHexToTen();
			} else {
				break;
			}
		}
	}

	// Function which convert Hexa to Decimal
	public static void fromHexToTen() {
		System.out.println("Please Enter your Input");
		String strValue = sc.next();
		boolean isNegative = strValue.charAt(0) == '-';
		if (isNegative) {
			strValue = strValue.substring(1); // Remove negative sign if present
		}
		// Validate input
		while (!checkValid(strValue)) {
			System.out.println("Invalid Input, Please try again");
			strValue = sc.next();
		}
		double decimalValue = 0;
		if (isDot(strValue) == 0) { // Check if the number has a dot
			// Convert the integer part directly
			decimalValue = Long.parseLong(strValue, 16);  // Using Java's built-in hex to decimal conversion
		} else {
			String str1 = strValue.substring(0, strValue.indexOf('.'));
			decimalValue = Long.parseLong(str1, 16);  // Integer part
			String str2 = strValue.substring(strValue.indexOf('.') + 1);
			decimalValue += calculateAfterDot(str2);  // Fractional part
		}

		if (isNegative) decimalValue = - decimalValue; // Add negative sign if it was originally negative
		// Output the result as a normal decimal number
		System.out.printf("The Decimal Value is --- > " + decimalValue +"\n"); 
	}

	// Algoritem for calculation before dot.
	public static double calculeBeforeDot(String str) { // calculating the sum  before dot
		double sum = 0;
		int j = 0;
		for (int i = str.length() - 1; i >= 0; i--) {
			double a = Math.pow(16, j); // The power of 16
			sum += a * convert(str.charAt(i)); // Add each digit multiplied by the power of 16
			j++; // Increment power for each digit
		}
		return sum;
	}

	// calculating the sum  after dot
	public static double calculateAfterDot(String str) { 
		double sumAfterDot = 0; 
		double power = -1;
		for (int i = 0; i < str.length(); i++) {
			sumAfterDot += convert(str.charAt(i)) * Math.pow(16, power);
			power--; 
		}
		return sumAfterDot;
	}

	// check how many dots 
	public static int isDot(String str ) { 
		int counter =0;
		int index = 0;
		for(int i = 0; i<str.length(); i++) {
			if(str.charAt(i) == '.') {
				index = str.charAt(i);	
				counter++;		
			}
		}
		return counter;
	}

	
	public static int convert(char ch) {
		int num;
		if (ch >= '0' && ch <= '9') {
			num = ch - '0'; // Convert '0'-'9' to integers 0-9
		} else {
			num = ch - 'A' + 10; // Convert 'A'-'F' to integers 10-15
		}
		return num;
	}

	// function check input for Hexa to Decimal ***valid : 0-9, ., A-F****
	public static boolean checkValid(String str) {
		if (isDot (str) > 1) // check if we have more than 1 dot 
			return false;
		if((isDot(str) == 1) && ((str.charAt(0)== '.' || (str.charAt(str.length() - 1) == '.')))) // if last or first is dot
			return false;
		// Allow negative sign at the beginning
		int start;
		if (str.charAt(0) == '-') {
			start = 1; // if first digit is minus, we start from digit1
		} else {
			start = 0; 
		}
		for (int i = start; i < str.length(); i++) {
			int num = (int) str.charAt(i);
			if ((num == 47) || num < 46 || (num > 57 && num < 65) || num > 70)
				return false;
		}
		return true;
	}

	// Method to convert from decimal (base 10) to hexadecimal (base 16)
	public static void fromTenToHex() {
		String input = "";
		while (true) {
			System.out.print("Please Enter a decimal number (double): ");
			input = sc.nextLine();

			if (checkString(input)) {
				boolean isNegative = input.charAt(0) == '-';
				if (isNegative) {
					input = input.substring(1); // Remove the negative sign for processing
				}

				if (isDot(input) == 0) {
					String hex = decimalToHex_BeforeDot(input); // Convert to hexadecimal before Dot
					if (isNegative) hex = "-" + hex; // Add the negative sign back
					System.out.println("The hexadecimal number is: " + hex);
					break;
				}
				if (isDot(input) == 1) { // dividing to 2 parts
					String str1 = input.substring(0, input.indexOf('.'));
					String hex1 = decimalToHex_BeforeDot(str1);
					String str2 = input.substring(input.indexOf('.') + 1);
					String hex2 = decimalToHex_WithPoint(str2);
					String result = mergeStrings(hex1, hex2);
					if (isNegative) result = "-" + result; // Add the negative sign back
					System.out.println("The hexadecimal number is: " + result);
					break;
				}
			} else {
				System.out.println("That's not a valid double number! Please try again.");
			}
		}
	}

	// checks if string is valid number
	public static boolean checkString(String input) {
		if (isDot (input) > 1) // check if we have more than 1 dot 
			return false;
		if((isDot(input) == 1) && ((input.charAt(0)== '.' || (input.charAt(input.length() - 1) == '.')))) // if last or first is dot
			return false;
		int startIndex = 0; 	// Allow negative sign at the beginning
		if (input.charAt(0) == '-') {
		    startIndex = 1;
		}
		for (int i = startIndex; i < input.length(); i++) {
			int num = (int) input.charAt(i);
			if (num < 46 || num > 57 || num == 47)
				return false;
		}
		return true;
	}


	// Convert decimal number to hexadecimal string
	public static String decimalToHex_BeforeDot(String str) {
		String hexString = "", reversed = "";
		int number = Integer.parseInt(str);	
		if (number == 0) {
			return "0";
		}
		boolean isNegative = number < 0; //Negative number
		if (isNegative) {
			number = Math.abs(number);
		}
		while (number > 0) {
			int remainder = (number % 16);
			hexString = hexString + doubleToHexCharacter(remainder);
			number = (int) number / 16;
		}
		for (int i = hexString.length() - 1; i >= 0; i--) {
			reversed += hexString.charAt(i);  // Append each character to the reversed string
		}
		if (isNegative) { // Add negative sign if the original number was negative
			return "-" + reversed;
		} else {
			return reversed;
		} 
	}


	// Convert decimal number to hex character (0-9, A-F)
	public static String doubleToHexCharacter(int number) {
		if (number >= 0 && number <= 9) {
			return Integer.toString(number);
		} else if (number == 10) {
			return "A";
		} else if (number == 11) {
			return "B";
		} else if (number == 12) {
			return "C";
		} else if (number == 13) {
			return "D";
		} else if (number == 14) {
			return "E";
		} else if (number == 15) {
			return "F";
		}
		return ""; 
	}

	// Function used when there is one point in number
	public static String decimalToHex_WithPoint(String str) {
		String hexString = "", str1 = "0." + str;
		double number1 = Double.parseDouble(str1);		
		for(int i=0; i<5; i++) {                 // until 5 digits after point
			int remainder = (int)(number1 * 16); 
			hexString = hexString + doubleToHexCharacter(remainder);
			number1 = (number1 * 16) - remainder;
		}
		return hexString;
	}

	// Function which Merge two strings when there is point
	public static String mergeStrings (String strLeft, String strRight) {
		return (strLeft + "." +strRight);
	}


}
