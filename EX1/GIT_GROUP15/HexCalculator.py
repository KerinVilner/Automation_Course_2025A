def decimal_to_hexadecimal(decimal):
    try:
        decimal = int(decimal)
        if decimal < 0:  # check if not negative
            raise ValueError("The number must be positive.")
        # Convert to hexadecimal
        return hex(decimal)[2:].upper()
    except ValueError as e:
        return f"Error: {e}"

def hexadecimal_to_decimal(hexadecimal):
    try:
        # Validate that the input is a valid hexadecimal string
        if not all(c in '0123456789ABCDEF' for c in hexadecimal.upper()):
            raise ValueError("The input is not a valid hexadecimal number.")
        # Convert to decimal
        return int(hexadecimal, 16)
    except ValueError as e:
        return f"Error: {e}"

def main():
    while True:
        print("\nWelcome to the number converter!")
        print("Choose an option:")
        print("1. Decimal to Hexadecimal")
        print("2. Hexadecimal to Decimal")
        print("3. Exit")

        choice = input("Enter 1, 2, or 3 to select your conversion: ")

        if choice == '1':
            decimal_number = input("Enter a decimal number: ")
            result = decimal_to_hexadecimal(decimal_number)
            print(f"Decimal {decimal_number} to Hexadecimal: {result}")

        elif choice == '2':
            hex_number = input("Enter a hexadecimal number: ")
            result = hexadecimal_to_decimal(hex_number)
            print(f"Hexadecimal {hex_number} to Decimal: {result}")

        elif choice == '3':
            print("Exiting the program. Goodbye!")
            break

        else:
            print("Invalid choice. Please select 1, 2, or 3.")


if __name__ == "__main__":
    main()
