import java.util.Scanner;
public class Calculator
{
	// Method to convert Decimal to Hexadecimal
	public String decimalToHex(int decimal) 
	{
		return Integer.toHexString(decimal).toUpperCase();
	}
	// Method to convert Hexadecimal to Decimal
	public String hexToDecimal(String hex) 
	{
		try {
			int decimal = Integer.parseInt(hex, 16);
			return String.valueOf(decimal);
		} catch (NumberFormatException e) {
			return "Invalid input - please enter a valid hexadecimal number";
		}
	}

	public static void main(String[] args) 
	{
		Calculator calculator = new Calculator();
		Scanner scanner = new Scanner(System.in);

		System.out.println("Welcome to the Decimal-Hexadecimal Calculator");

		while (true) 
		{
			// Display the menu
			System.out.println("\nChoose an option:");
			System.out.println("1. Convert Decimal to Hexadecimal");
			System.out.println("2. Convert Hexadecimal to Decimal");
			System.out.println("0. Exit");

			System.out.print("Enter option (1, 2, or 0): ");
			int choice;

			// Check if input is an integer
			try {
				choice = Integer.parseInt(scanner.nextLine());
			} catch (NumberFormatException e) {
				System.out.println("Invalid input. Please enter a valid menu option (1, 2, or 0).");
				continue;
			}

			switch (choice) 
			{
			case 1:
				System.out.print("Enter a decimal number: ");
				try {
					int decimal = Integer.parseInt(scanner.nextLine());
					String hexResult = calculator.decimalToHex(decimal);
					System.out.println("The hexadecimal representation of " + decimal + " is: " + hexResult);
				} catch (NumberFormatException e) {
					System.out.println("Invalid decimal number. Please try again.");
				}
				break;

			case 2:
				System.out.print("Enter a hexadecimal number: ");
				String hex = scanner.nextLine();
				String decimalResult = calculator.hexToDecimal(hex);
				if (decimalResult.equals("Invalid input - please enter a valid hexadecimal number")) {
					System.out.println(decimalResult); // Print only the error message if input is invalid
				} else {
					System.out.println("The decimal representation of " + hex + " is: " + decimalResult);
				}
				break;

			case 0:
				System.out.println("Exiting...");
				scanner.close();
				return;

			default:
				System.out.println("Invalid choice. Please select 1, 2, or 0.");
				break;
			}
		}
	}
}









