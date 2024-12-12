Hexadecimal-Decimal Calculator

Overview
This project is a Java-based calculator that allows users to convert numbers between decimal (base-10) and hexadecimal (base-16) number systems. It features a user-friendly interface with input validation, making it easy for users to switch between these number systems.

Features
- Base 10 to Base 16 Conversion:
Converts decimal numbers (including negatives) to hexadecimal format.
Displays the result as a string of hexadecimal digits (`0-9` and `A-F`).

- Base 16 to Base 10 Conversion:
Validates and converts a hexadecimal number to its decimal equivalent.
Supports uppercase letters for hexadecimal digits (`A-F`).

- Input Validation:
Ensures inputs for conversions are valid and provides meaningful error messages for invalid inputs.

- Interactive Menu:
Offers a simple menu-driven interface for users to select desired operations.

How to Use
Run the Program:
1. Compile and execute the program in a Java-supported environment.
javac Group14HW1.java
java Group14HW1

2. Choose an Option:
The program presents a menu with the following options:
`0`: Exit the program.
`1`: Convert a base-10 integer to base-16.
`2`: Convert a base-16 string to base-10.


3. Follow Prompts:
For base-10 to base-16 conversion, enter a valid integer.
For base-16 to base-10 conversion, input a valid hexadecimal number.

4. View Results:
The program displays the conversion results or error messages for invalid inputs.

Example Execution
- Base 10 to Base 16 Conversion:
Please select an integer:
26
1A

- Base 16 to Base 10 Conversion:
Please enter a hexadecimal number:
1A
The decimal value of the hexadecimal number 1A is: 26

- Invalid Input Handling
Please enter a hexadecimal number:
XYZ
Invalid hexadecimal number. Please enter a valid hexadecimal number (digits 0-9, letters A-F).

File Structure
Group14HW1.java: The main Java program contains all functionalities, including validation, conversion, and menu handling.

Requirements
- Java Development Kit (JDK) 8 or higher
- Command-line interface or an integrated development environment (IDE) like IntelliJ IDEA, Eclipse, or VS Code.

Authors
Team 14

Future Enhancements
- Add support for fractional hexadecimal and decimal conversions.
- Extend functionality to other bases (e.g., base-2, base-8).
- Implement a graphical user interface (GUI) for enhanced usability.

