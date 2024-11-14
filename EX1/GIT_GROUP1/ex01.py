def base10_to_base16(num):
    # Check if the input is a valid integer
    if not isinstance(num, int):
        raise ValueError("Input must be an integer for base 10.")
    if num == 0:
        return '0'
    hex_digits = '0123456789ABCDEF'
    hex_result = ''
    while num > 0:
        remainder = num % 16
        hex_result = hex_digits[remainder] + hex_result
        num = num // 16
    return hex_result

# Function to convert from base 16 to base 10
def base16_to_base10(hex_num):
    hex_digits = '0123456789ABCDEF'
    decimal_result = 0
    negative = 0
    if hex_num[0] == "-":
        hex_num = hex_num.replace(hex_num[0], "", 1)
        negative = 1

    hex_num = hex_num.upper()  # Ensure consistency
    for i, digit in enumerate(reversed(hex_num)):
        if digit in hex_digits:
            decimal_result += hex_digits.index(digit) * (16 ** i)
        else:
            raise ValueError(f"Invalid character '{digit}' in hexadecimal number")
    if (negative == 1):
        negative = 0
        return decimal_result * (-1)
    return decimal_result

flag = True
while flag == True:
    print("\nChoose an option:")
    print("1. Convert from base 10 to base 16")
    print("2. Convert from base 16 to base 10")
    print("3. exit")
    choice = input("Enter 1 or 2 or 3: ")
    if choice == '1':
        try:
            num = int(input("Enter a base 10 number: "))  # Ensure input is an integer
            if num < 0:
                num = num * (-1)
                print(f"{num} in base 10 is -{base10_to_base16(num)} in base 16")
            else:
                print(f"{num} in base 10 is {base10_to_base16(num)} in base 16")
        except ValueError as e:
            print(f"Invalid input: {e}")

    elif choice == '2':
        hex_num = input("Enter a number in base 16: ")
        try:
            print(f"{hex_num} in base 16 is {base16_to_base10(hex_num)} in base 10")
        except ValueError as e:
            print(e)
    elif choice == '3':
        flag = False
    else:
        print("Invalid choice. Please enter 1 or 2 or 3.")


