import java.util.Scanner;

public class main {
	static Scanner sc = new Scanner(System.in);
	final static char[] Letters = { '0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'A', 'B', 'C', 'D', 'E', 'F' };

	public static void main(String[] args) {
		int num;
		do {
			System.out.println("Hello and welcome to the conversion calculator between bases of group 5!");
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			} 
			System.out.println("0. End Program");
			System.out.println("1. Convert decimal number to hexadecimal number");
			System.out.println("2. Convert hexadecimal number to decimal number");
			num = sc.nextInt();
			sc.nextLine(); // Consume the newline character

			while (num != 0) {
				if (num < 0 || num > 2) {
					System.out.println("Invalid menu input");
					System.out.println("0. End Program");
					System.out.println("1. Convert decimal number to hexadecimal number");
					System.out.println("2. Convert hexadecimal number to decimal number");
					num = sc.nextInt();
					sc.nextLine(); // Consume the newline character

				}
				if (num == 1) {
					System.out.println("Enter number:");
					double decimal = sc.nextDouble();
					sc.nextLine(); // Consume the newline character

					System.out.println("Enter accuracy:");
					int accuracy = sc.nextInt();
					sc.nextLine(); // Consume the newline character
					
					System.out.println("The number in base 16: ");
					
					// negative number
					if (decimal < 0) {
						decimal = decimal * (-1);
						if (decimalTOhexadecimalREST((decimal - (int) decimal) * 16, accuracy) == "")
							System.out.println("-" + decimalTOhexadecimal((int) decimal));
						else
							System.out.println("-" + decimalTOhexadecimal((int) decimal) + "."
									+ decimalTOhexadecimalREST((decimal - (int) decimal) * 16, accuracy));
					}
					// positive number
					else
						if (decimalTOhexadecimalREST((decimal - (int) decimal) * 16, accuracy) == "")
							System.out.println(decimalTOhexadecimal((int) decimal));
						else
							System.out.println(decimalTOhexadecimal((int) decimal) + "."
									+ decimalTOhexadecimalREST((decimal - (int) decimal) * 16, accuracy));

					break;
				}
				// ********************
				if (num == 2) {
					System.out.println("Enter a number:");
					String userNum = sc.nextLine();
					System.out.println("Enter accuracy:");
					int acr= sc.nextInt();
					
					boolean negative = negCheck(userNum); //check if the number is negative
					
					if(negative) {
						userNum = userNum.substring(1);
					}
					
					int pointIndex = userNum.indexOf('.'); // find the point index

					// split the string number to before and after the point
					String integer;
					String decimal;

					if (pointIndex == -1) { // if there isn't a point we get -1
						integer = userNum; // use as an integer
						decimal = "";
					} else {
						integer = userNum.substring(0, pointIndex); // the integer part
						decimal = userNum.substring(pointIndex + 1); // the decimal part
					}

					double outputNum = 0; // the number the user will get

					// integer part
					for (int i = 0; i < integer.length(); i++) {
						char oneChar = integer.charAt(integer.length() - 1 - i);
						int x = fixer(oneChar); // A function that converts a char to an int
						outputNum += x * Math.pow(16, i); // Multiply by 16 to the position power
					}
					// decimal part
					for (int i = 0; i < decimal.length(); i++) {
						char desChar = decimal.charAt(i);
						int y = fixer(desChar);
						outputNum += y * Math.pow(16, -(i + 1));
					}
					System.out.println("The number in base 10: "); //format of acr digits after the point
					String format = String.format("%%.%df", acr);
					if (negative) {
						System.out.println(String.format('-'+format, outputNum)); // prints numbers in non-scientific format -negative
					}
					else {
						System.out.println(String.format(format , outputNum)); // prints numbers in non-scientific format
					}
					

					break;
				}
				// ********************
			}
		} while (num != 0);
		System.out.println("Bye Bye");

	}

	// convert integer number from decimal to hexadecimal
	public static String decimalTOhexadecimal(double decimal) {
		if (decimal < 16)
			return convert((int) decimal);

		return decimalTOhexadecimal((int) decimal / 16) + "" + convert((int) (decimal % 16));
	}

	// convert decimal part from decimal to hexadecimal
	public static String decimalTOhexadecimalREST(double decimal, int accuracy) {
		if (decimal == 0 || accuracy == 0)
			return "";
		return (convert((int) decimal) + "" + decimalTOhexadecimalREST(((decimal - (int) decimal) * 16), accuracy - 1));
	}

	public static String convert(int num) {
		return "" + Letters[num];

	}

	// converts a char to an integer
	public static int fixer(char x) {
		x = Character.toUpperCase(x); // change to capital letters

		if (x >= '0' && x <= '9') { // number between 0-9
			return x - '0'; // base on the ASCII code
		}

		else if (x >= 'A' && x <= 'F') { // number between 10-15
			return x - 'A' + 10; // base on the ASCII code, (x-A+10= 10 to 15)
		}

		else {
			throw new IllegalArgumentException(x + " Is Invaid Inpot!"); // Error of incorrect value entered by the user
		}
	}

	public static boolean negCheck (String str) {
		//boolean negative;
		if (str.indexOf('-')==0) {
			 return true;
		}
		else {
			 return false;
		}
	}
}
