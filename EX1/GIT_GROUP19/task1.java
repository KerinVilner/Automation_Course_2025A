package automation;

import java.util.Scanner;

public class task1 {
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
				fromTenToHex();  // Pass scanner to the method
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
		while (!checkValid(strValue)) { // as long as wrong 
			System.out.println("Invalid Input, Please try again");
			strValue = sc.next();
		}
		if (isDot(strValue) == 0 ) { //Does function 
			System.out.println("The Decimal Value is --- > " + calculeBeforeDot(strValue));
		}
		if(isDot(strValue) == 1 ) {
			String str1 = strValue.substring(0,(strValue.indexOf('.')) );
			double answer = calculeBeforeDot(str1) + calculateAfterDot(strValue.indexOf('.'),strValue);
			System.out.println("The Decimal Value is --- > " + answer);
		}
	}

	// Algoritem for calculation before dot.
	public static int calculeBeforeDot(String str) { // calculating the sum  before dot
		int sum = 0;
		int j = 0;
		for(int i = str.length() -1; i>=0; i--) {
			int a= (int) Math.pow(16, j); // the power
			sum = sum +a *convert( str.charAt(i)); //the sum of exha in 10  
			j++; // power +1
		}
		return sum;
	}

	// calculating the sum  after dot
	public static double calculateAfterDot(int recivedIndex, String str) { 
		double sumAfterDot = 0; 
		int j = -1; // the power 
		for(int i = recivedIndex + 1 ; i< str.length(); i ++) {
			double a = Math.pow(16, j);
			sumAfterDot = (sumAfterDot + a * convert(str.charAt(i)));
			j--;
		}
		return sumAfterDot;

	}


	// checking how many dots 
	public static int isDot(String str ) { 
		int counter =0;
		int index = 0;
		for(int i = 0; i<str.length(); i++) {
			if(str.charAt(i) == '.') {
				index = str.charAt(i);	
				counter++;		
			}
		}
		saveDotIndex(index); // saving the last dot index 
		return counter;
	}

	// help function for saving the index of the dot 
	public static int saveDotIndex(int index) { 
		return index;
	}

	// 
	public static int convert(char ch) {
		int num;
		if (ch == '0' || ch == '1'||ch == '2'||ch == '3'||ch == '4'||ch == '5'||ch == '6'||ch == '7'||ch == '8'||ch == '9')
			num = (int) ch - 48;
		else
			num = (int) ch - 55;
		return num;

	}

	// function check input for Hexa to Decimal ***valid : 0-9, ., A-F****
	public static boolean checkValid(String str) {
		if (isDot (str) > 1) // check if we have more than 1 dot 
			return false;
		if((isDot(str) == 1) && ((str.charAt(0)== '.' || (str.charAt(str.length() - 1) == '.')))) // if last or first is dot
			return false;
		for(int i = 0; i< str.length(); i ++) {
			int num = (int) str.charAt(i);
			if ((num == 47) || num <46 || (num > 57 && num < 65) || num > 70)
				return false;
		}
		return true;
	}

	// Method to convert from decimal (base 10) to hexadecimal (base 16)
	public static void fromTenToHex() {
		String input="";
		while (true) {
			System.out.print("Please Enter a decimal number (double): ");
			input = sc.nextLine();
			// Check if the input is a valid double number

			if (checkString(input)) {
				if (isDot(input) == 0 ) {
					System.out.println(1);
					String hex = decimalToHex_BeforeDot(input); // Convert to hexadecimal before Dot
					System.out.println("The hexadecimal number is: " + hex);
					break;  // Exit after successful conversion
				}
				if(isDot(input) == 1 ) {				
					String str1 = input.substring(0,(input.indexOf('.')) );					
					String hex1 = decimalToHex_BeforeDot(str1); // Convert to hexadecimal before Dot					
					String str2 = input.substring(input.indexOf('.')+1);					
					String hex2 = decimalToHex_WithPoint(str2);
					System.out.println("THe Hex Value is ---> " + mergeStrings(hex1, hex2));
					break;

				} 
				else {
					System.out.println("That's not a valid double number! Please try again.");
				}
			}
		}
	}

	// checks if string is valid number
	public static boolean checkString(String input) {
		if (isDot (input) > 1) // check if we have more than 1 dot 
			return false;
		if((isDot(input) == 1) && ((input.charAt(0)== '.' || (input.charAt(input.length() - 1) == '.')))) // if last or first is dot
			return false;
		boolean ok = true;
		for(int i = 0; i<input.length(); i++) {
			int num = (int) input.charAt(i);
			if (num < 46 || num > 57  || num == 47) {
				ok = false;
				break;
			}
		}
		return ok;
	}


	// Convert decimal number to hexadecimal string
	public static String decimalToHex_BeforeDot(String str) {
		String hexString = "", reversed = "";
		int number = Integer.parseInt(str);	
		if (number == 0) {
			String str1 = "0";
			return str1;
		}
		while (number > 0) {
			int remainder = (number % 16);
			hexString = hexString + doubleToHexCharacter(remainder);
			number = (int) number / 16;
		}
		for (int i = hexString.length() - 1; i >= 0; i--) {
			reversed += hexString.charAt(i);  // Append each character to the reversed string
		}
		return reversed.toString();
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
		return (strLeft + "." + strRight);
	}

}
