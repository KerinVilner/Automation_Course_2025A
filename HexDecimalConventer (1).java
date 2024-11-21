import java.util.Scanner;

public class HexDecimalConventer {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String choice;
        System.out.println("Welcome to Hex-Decimal Converter");

        // Main loop
        while (true) {
            System.out.println("0. Quit");
            System.out.println("1. Convert Decimal to Hexadecimal");
            System.out.println("2. Convert Hexadecimal to Decimal");

            // Validate user choice input
            do {
                System.out.print("Enter your choice (0, 1, or 2): ");
                choice = scanner.nextLine();
                if (!choice.equals("0") && !choice.equals("1") && !choice.equals("2")) {
                    System.out.println("Invalid choice. Please enter 0, 1, or 2.");
                }
            } while (!choice.equals("0") && !choice.equals("1") && !choice.equals("2"));

            // Quit if "0"
            if (choice.equals("0")) {
                System.out.println("Program terminated.");
                break;
            } else if (choice.equals("1")) {
                convertDecimalToHex(scanner); // Decimal to Hex conversion
            } else {
                convertHexToDecimal(scanner); // Hexadecimal to Decimal conversion
            }
        }

        scanner.close();
    }

    // Function to convert Decimal to Hexadecimal
    private static void convertDecimalToHex(Scanner scanner) {
        System.out.print("Enter a decimal number (integer or decimal, positive or negative): ");

        // Validates the input
        while (!scanner.hasNextDouble()) {
            System.out.println("Invalid input. Please enter a valid decimal number.");
            scanner.next();
        }

        double decimalNumber = scanner.nextDouble();
        scanner.nextLine(); // Clear newline for future inputs

        String hexResult = decimalToHex(decimalNumber); // Convert to Hex
        System.out.println("Hexadecimal equivalent: " + hexResult);
    }

    // Manual conversion from Decimal to Hexadecimal
    public static String decimalToHex(double decimalNumber) {
        boolean isNegative = decimalNumber < 0;
        decimalNumber = Math.abs(decimalNumber);

        // Convert the integer part to hexadecimal
        int wholePart = (int) decimalNumber;
        String hexWholePart = "";
        while (wholePart > 0) {
            int remainder = wholePart % 16;
            hexWholePart = toHexChar(remainder) + hexWholePart;
            wholePart /= 16;
        }
        if (hexWholePart.isEmpty()) hexWholePart = "0";

        // Convert the fractional part to hexadecimal
        double fractionalPart = decimalNumber - (int) decimalNumber;
        StringBuilder hexFractionalPart = new StringBuilder();
        int precision = 5; // Precision for fractional hex part
        while (fractionalPart > 0 && precision-- > 0) {
            fractionalPart *= 16;
            int digit = (int) fractionalPart;
            hexFractionalPart.append(toHexChar(digit));
            fractionalPart -= digit;
        }

        // Combine integer and fractional hex parts
        String hexResult = hexWholePart;
        if (hexFractionalPart.length() > 0) {
            hexResult += "." + hexFractionalPart;
        }
        if (isNegative) {
            hexResult = "-" + hexResult;
        }

        return hexResult;
    }

    // Convert integer to a single hex character
    private static char toHexChar(int remainder) {
        if (remainder >= 0 && remainder <= 9) {
            return (char) ('0' + remainder);
        } else {
            return (char) ('A' + (remainder - 10));
        }
    }

    // Function to convert Hexadecimal to Decimal
    private static void convertHexToDecimal(Scanner scanner) {
        while (true) {
            System.out.print("Enter a hexadecimal number: ");
            String hexNumber = scanner.next();

            // Validate if input is a valid hexadecimal number with optional fractional part
            if (!isValidHex(hexNumber)) {
                System.out.println("Invalid hexadecimal input. Please enter a valid hexadecimal number (0-9, A-F).");
            } else {
                boolean isNegative = hexNumber.startsWith("-"); // Check if hex is negative
                if (isNegative) {
                    hexNumber = hexNumber.substring(1); // Remove negative sign for parsing
                }

                // Split hex number into integer and fractional parts
                String[] parts = hexNumber.split("\\.");
                double decimalResult = 0;

                // Convert the integer part of hex to decimal
                if (parts.length > 0 && !parts[0].isEmpty()) {
                    decimalResult += hexToDecimal(parts[0], 0, parts[0].length() - 1);
                }

                // Convert the fractional part, if any
                if (parts.length > 1) {
                    String fractionalPart = parts[1];
                    decimalResult += hexToDecimal(fractionalPart, 1, fractionalPart.length());
                }

                if (isNegative) {
                    decimalResult = -decimalResult; // Apply negative if necessary
                }

                System.out.println("Decimal equivalent: " + decimalResult);
                scanner.nextLine(); // Clear the newline left by scanner.next()
                break; // Exit loop after successful conversion
            }
        }
    }

 // Conversion from Hexadecimal to Decimal // changed Req Pool 2 
    private static double hexToDecimal(String hex, int startPower, int endIndex) {
        double result = 0;
        int power = startPower;

        for (char hexChar : hex.toCharArray()) {
            int decimalValue = hexCharToDecimal(hexChar);
            
            if (decimalValue == -1) {
                return -1; // Invalid character
            }

            result += decimalValue * Math.pow(16, power);
            power--; // Decrease power for next digit
        }
        return result;
    }


    // Helper to convert hex character to decimal value
    private static int hexCharToDecimal(char hexChar) {
        if (hexChar >= '0' && hexChar <= '9') {
            return hexChar - '0';
        } else if (hexChar >= 'A' && hexChar <= 'F') {
            return hexChar - 'A' + 10;
        } else if (hexChar >= 'a' && hexChar <= 'f') {
            return hexChar - 'a' + 10;
        } else {
            return -1; // Invalid hex character
        }
    }

    // Validates if input is a valid hexadecimal
    private static boolean isValidHex(String hex) {
        return hex.matches("^-?[0-9A-Fa-f]+(\\.[0-9A-Fa-f]+)?$");
    }
}
