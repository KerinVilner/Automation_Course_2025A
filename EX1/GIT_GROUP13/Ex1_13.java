import java.util.Scanner;
import java.util.InputMismatchException;

public class Ex1Auto {

	public static void main(String[] args) {
			
		Scanner scanner = new Scanner(System.in);
		try {
			System.out.println("Which base conversion would you like to choose?");
			System.out.println("1. Decimal To Hex");
			System.out.println("2. Hex To Decimal");
			System.out.print("Enter your choice (1 or 2): ");
			int choice = scanner.nextInt();

			switch (choice) {
			case 1:
				System.out.print("Enter a decimal number to convert: ");
				if (!scanner.hasNextInt()) throw new InputMismatchException();
				int decimalNumber = scanner.nextInt();
				if (decimalNumber < 0) {
					System.out.println("Error: Negative numbers are not supported.");
					return;
				}
				String hexResult = decimalToHex(decimalNumber);
				System.out.println("The result in hexadecimal: " + hexResult);
				break;

			case 2:
				System.out.print("Enter a hexadecimal number to convert: ");
				String hexNumber = scanner.next();
				if (hexNumber.trim().isEmpty()) {
					System.out.println("Error: Hexadecimal input cannot be empty.");
					return;
				}
				hexNumber = hexNumber.toUpperCase();
				try {
					int decimalResult = hexToDecimal(hexNumber);
					System.out.println("The result in decimal: " + decimalResult);
				} catch (IllegalArgumentException e) {
					System.out.println("Error: Invalid characters in hexadecimal input.");
				}
				break;

			default:
				System.out.println("Error: Invalid choice. Please enter 1 or 2.");
			}
		} catch (InputMismatchException e) {
			System.out.println("Error: Please enter a valid numeric choice.");
		} finally {
			scanner.close();
		}
	}

	// Function to convert decimal to hexadecimal
	public static String decimalToHex(int decimal) {
		if (decimal == 0) return "0";
		StringBuilder hex = new StringBuilder();
		while (decimal > 0) {
			int remainder = decimal % 16;
			char hexChar = (char) (remainder < 10 ? '0' + remainder : 'A' + (remainder - 10));
			hex.insert(0, hexChar);
			decimal /= 16;
		}
		return hex.toString();
	}

	// Function to convert hexadecimal to decimal
	public static int hexToDecimal(String hex) {
		int decimal = 0;
		for (int i = 0; i < hex.length(); i++) {
			char hexChar = hex.charAt(i);
			int value = (hexChar >= '0' && hexChar <= '9') ? hexChar - '0' :
				(hexChar >= 'A' && hexChar <= 'F') ? hexChar - 'A' + 10 : -1;
			if (value == -1) {
				throw new IllegalArgumentException("Invalid hexadecimal character");
			}
			decimal = decimal * 16 + value;
			if (decimal < 0) { // Overflow check
				throw new ArithmeticException("Overflow: Hexadecimal number too large.");
			}
		}
		return decimal;
	}
}










