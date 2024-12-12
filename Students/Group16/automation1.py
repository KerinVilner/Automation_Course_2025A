def decimal_to_hex(decimal_num):
    decimal_digits = "0123456789"
    for char in decimal_num:
        if char not in decimal_digits and char != ".":
            print("Invalid decimal number")
            return
    
    hex_digits = "0123456789ABCDEF"
    # Handle the integer part
    decimal_num=float(decimal_num)
    
    if decimal_num == 0:
        print(f"Decimal: {decimal} -> Hexadecimal: {decimal}")
        return
    
    integer_part =int(decimal_num)
    hex_integer = ""
    while integer_part > 0:
        remainder = integer_part % 16
        hex_integer = hex_digits[remainder] + hex_integer
        integer_part = integer_part // 16

    # Handle the fractional part
    fractional_part =float(decimal_num) - int(decimal_num)
    hex_fractional = ""

    if fractional_part > 0:
        hex_fractional = "."
    while fractional_part > 0:
        fractional_part *= 16
        digit = int(fractional_part)
        hex_fractional += hex_digits[digit]
        fractional_part -= digit
        # To avoid infinite loop for repeating fractions, stop after a reasonable precision
        if len(hex_fractional) > 10:
            break

    hex_num = hex_integer + hex_fractional
    print(f"Decimal: {decimal} -> Hexadecimal: {hex_num}")
    return

def hex_to_decimal(hex_num):
    hex_digits = "0123456789ABCDEF"  # Hexadecimal digits
    hex_num = hex_num.upper()

    for char in hex_num:
        if char not in hex_digits and char != ".":
            print("Invalid hexadecimal number")
            return

    # Split the number into integer and fractional parts
    if '.' in hex_num:
        integer_part, fractional_part = hex_num.split('.')
    else:
        integer_part, fractional_part = hex_num, ""

    # Convert the integer part
    decimal_num = 0
    power = len(integer_part) - 1
    for digit in integer_part:
        value = hex_digits.index(digit)
        decimal_num += value * (16 ** power)
        power -= 1

    # Convert the fractional part
    fractional_value = 0
    power = -1
    for digit in fractional_part:
        value = hex_digits.index(digit)
        fractional_value += value * (16 ** power)
        power -= 1
        if power < -10:
            break

    decimal_num += fractional_value
    print(f"Hexadecimal: {hex} -> Decimal: {decimal_num}")
    return

# main
while True: 
    calculator = input("Enter which calculator do you want to use: 1 for decimal to hex, 2 for hex to decimal, or 0 to exit: ") 
    if calculator == "0": 
        print("Exiting the program. Goodbye!") 
        break 
    elif calculator == "1": 
        decimal = input("Enter a decimal number: ")
        decimal_to_hex(decimal)
    elif calculator == "2": 
        hex = input("Enter a hex number: ")
        hex_to_decimal(hex)
    else: 
        print("Wrong input, please try again.")
    

