import java.util.Scanner;
import java.util.Vector;

public class Group14HW1 {

    public static boolean isValidHexadecimalNumber(String hexadecimalNumber) {
        if (hexadecimalNumber.isEmpty()) { // Check if the input is empty
            return false;
        }
        for (int i = 0; i < hexadecimalNumber.length(); i++) {
            char currentChar = hexadecimalNumber.charAt(i);
            if (!((currentChar >= '0' && currentChar <= '9') || (currentChar >= 'A' && currentChar <= 'F' ))) {
                return false;
            }
        }
        return true;
    }

    public static long convertFromHexadecimalToDecimal(String hexadecimalNumber) {
        long decimalNumber = 0;
        int decimalValue;
        String reverseHexadecimal = new String1Builder(hexadecimalNumber).reverse().toString();
        for (int i = 0; i < reverseHexadecimal.length(); i++) {
            char currentChar = reverseHexadecimal.charAt(i);
            if (currentChar >= '0' && currentChar <= '9') {
                decimalValue = currentChar - '0';		// Based on the ASCII value
            } else {
                decimalValue = currentChar - 'A' + 10; // Based on the ASCII value
            }
            decimalNumber += decimalValue * Math.pow(16, i);
        }
        return decimalNumber;
    }

    public static void Returns16Base(int x) { // RECIVES A NUMBER IN BASE 10 AND RETURNS THE NUMBER IN BASE 16
        boolean Negative = false; // TO CHECK IF THE RECIVED NUMBER IS NEGATIVE
        if (x < 0) {
            Negative = true;
            x = x * -1;
        }
        int ans = x;
        int Shaarit = 0;
        Vector<Integer> numbers = new Vector<>(); // SAVE THE NUMBER IN A VECTOR

        while (ans >= 16) { // AS LONG AS THE NUMBER GREATER THAN 16 WE NEED TO DIVIDE HIM
            Shaarit = ans % 16; // TO SAVE THE REMAIN
            numbers.add(0, Shaarit);
            ans = ans / 16; // SAVE THE INTEGAR WITOUT THE REMAIN ROUNDED DOWN
        }
        numbers.add(0, ans); // IF THE NUMBER IS LOWER THAN 16
        if (Negative == true) {
            System.out.print("-");
        }
        for (int i = 0; i <= numbers.size() - 1; i++) { // LOOP TO RUN THROUGH ALL THE VECTOR AND PRINT ACCORDINGLY
            if (numbers.elementAt(i) < 10) {
                System.out.print(numbers.elementAt(i));
            } else if (numbers.elementAt(i) == 10) {
                System.out.print("A");
            } else if (numbers.elementAt(i) == 11) {
                System.out.print("B");
            } else if (numbers.elementAt(i) == 12) {
                System.out.print("C");
            } else if (numbers.elementAt(i) == 13) {
                System.out.print("D");
            } else if (numbers.elementAt(i) == 14) {
                System.out.print("E");
            } else if (numbers.elementAt(i) == 15) {
                System.out.print("F");
            }
        }
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int choice;
        boolean flag = true;

        System.out.println("Welcome to our calculator, please select an option:");
        do {
            System.out.println("0. Exit");
            System.out.println("1. Base 10 to 16");
            System.out.println("2. Base 16 to 10");

            // Check for valid input
            while (!scanner.hasNextInt()) {
                System.out.println("Invalid input, please enter a number (0, 1, or 2).");
                System.out.print("Enter your choice: ");
                scanner.next();  // Clear newline
            }

            choice = scanner.nextInt();
            scanner.nextLine(); // Clear newline

            switch (choice) {
                case 1: {
                    System.out.println("Please select an integer:");
                    
                    // Input validation for base 10 to base 16 conversion
                    while (!scanner.hasNextInt()) {
                        System.out.println("Invalid input! Please enter a valid integer.");
                        scanner.next();  // Clear invalid input
                    }
                    
                    int input = scanner.nextInt();
                    Returns16Base(input);  // Convert and print in base 16
                    System.out.println();
                    break;
                }
                case 2: {
                    System.out.print("Please enter a hexadecimal number: ");
                    String hexadecimalNumber = scanner.nextLine();
                    if (isValidHexadecimalNumber(hexadecimalNumber)) {
                        long decimalNumber = convertFromHexadecimalToDecimal(hexadecimalNumber);
                        System.out.println("The decimal value of the hexadecimal number " + hexadecimalNumber + " is: " + decimalNumber);
                    } else {
                        System.out.println("Invalid hexadecimal number. Please enter a valid hexadecimal number (digits 0-9, letters A-F).");
                    }
                    break;
                }
                case 0:
                    flag = false;
                    break;
            }

        } while (flag);

        System.out.println("Goodbye!");
        scanner.close();
    }
}
