import java.util.Scanner;

public class GG21 {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		boolean run = true;

		while (run) {
			System.out.println("Welcome, Choose conversion type:\n" +
					"1. Decimal to Hexadecimal\n" +
					"2. Hexadecimal to Decimal\n" +
					"Type 'exit' to quit.");

			String input = sc.next();

			if (input.equalsIgnoreCase("exit")) {
				run = false;
				continue;
			}
			//options to convert between bases or to leave the program
			switch (input) {
			case "1":
				System.out.println("Enter a decimal number:");
				String decimalInput = sc.next();
				
				
				//the conversion from decimal to hexadecimal number 
				try {
					int decimalNumber = Integer.parseInt(decimalInput);
					if (decimalNumber < 0) {
						System.out.println("Hexadecimal: -" + decimalToHex(Math.abs(decimalNumber)));
					} else {
						System.out.println("Hexadecimal: " + decimalToHex(decimalNumber));
					}
				} catch (NumberFormatException e) {
					System.out.println("Invalid decimal number. Please try again.");
				}
				break;

			case "2":
				System.out.println("Enter a hexadecimal number:");
				String hexInput = sc.next();

				// check if the input is valid
				boolean isNegative = hexInput.charAt(0) == '-';
				String hexToCheck = isNegative ? hexInput.substring(1) : hexInput;

				if (!isHexadecimal(hexToCheck)) {
					System.out.println("Invalid hexadecimal number. Please try again.");
					continue;
				}
				//the conversion from hexadecimal to decimal number 
				try {
					int decimalNumber = hexToDecimal(hexToCheck);
					if (isNegative) {
						decimalNumber = -decimalNumber;
					}
					System.out.println("Decimal: " + decimalNumber);
				} catch (NumberFormatException e) {
					System.out.println("Invalid hexadecimal number. Please try again.");
				}
				break;
			//in case the input of the option is invalid
			default:
				System.out.println("Invalid input. Please enter 1 or 2.");
			}
		}

		sc.close();
	}

	// a function to switch from decimal base to hexadecimal base
	public static String decimalToHex(int decimal) {
		if (decimal == 0) {
			return "0";
		}
		StringBuilder hex = new StringBuilder();
		while (decimal != 0) {
			int remainder = decimal % 16;
			hex.insert(0, toHexChar(remainder));
			decimal = decimal / 16;
		}
		return hex.toString();
	}

	// a function to switch from decimal note to hexadecimal note 
	private static char toHexChar(int digit) {
		return (digit >= 0 && digit <= 9) ? (char) (digit + '0') : (char) (digit - 10 + 'A');
	}

	// a function to switch from hexadecimal base to base decimal
	public static int hexToDecimal(String hex) {
		int decimal = 0;
		int length = hex.length();
		for (int i = 0; i < length; i++) {
			char hexChar = hex.charAt(i);
			decimal = decimal * 16 + toDecimalValue(hexChar);
		}
		return decimal;
	}

	// a function to switch from hexadecimal note to decimal note
	private static int toDecimalValue(char hexChar) {
		return (hexChar >= '0' && hexChar <= '9') ? hexChar - '0' : hexChar - 'A' + 10;
	}

	// a function to check if the notes are legal in hexadecimal base 
	private static boolean isHexadecimal(String number) {
		if (number == null || number.isEmpty()) {
			return false;
		}

		for (char c : number.toCharArray()) {
			if (!(Character.isDigit(c) || (c >= 'A' && c <= 'F') || (c >= 'a' && c <= 'f'))) {
				return false;
			}
		}
		return true;
	}

}
