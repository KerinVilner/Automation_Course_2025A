# Makes sure there is not more than one period,
# and the period is in a correct and valid location.
def validate_decimal_period (num):
    parts = num.split('.')
    if num.count('.') >1 or num[0] == '.' or len(parts[1]) == 0 :
        return False
    return True


def decimal_base_check(num):
    if not str(num) or str(num)[0] == '.' or str(num)[-1] == '.':
        return False
    try:# Try to convert the input to a decimal number
        float(num)
        return True
    except ValueError:
        return False


def hexadecimal_base_check(num) :
    number_tested= str(num).upper()
    # Checks the correctness of the position and amount of the period in the input.
    if '.' in number_tested and not validate_decimal_period(number_tested):
        return False
    if '-' in number_tested[1:] :
        return False

    for n in number_tested: # Check that all characters are digits
          if n not in "-0123456789ABCDEF.":
              return False
    return True


def decimal_to_hexadecimal(num) :
    hex_digits = "0123456789ABCDEF"
    result = ""
    if not decimal_base_check(num):
        return "Error - The number is not decimal."
    is_negative = False
    num =  float(num)
    if num < 0 :
        is_negative= True
        num= -num
    # Separate the integer and decimal parts
    int_part = int(num)
    dec_part= num - int_part

    while int_part > 0 : # Convert the integer part to hexadecimal
        remainder = int_part % 16
        result = hex_digits[remainder] + result
        int_part //=16
    result = result if result else  "0"

    if dec_part > 0: # Convert the decimal part to hexadecimal
        result += "."
        count =0
        while dec_part >0 and count <10:
            dec_part *=16
            digit= int (dec_part)
            result += hex_digits[digit]
            dec_part -= digit
            count += 1

    if is_negative: # Add negative sign if needed
        result = "-" + result
    return result


def hexadecimal_to_decimal(num) :
    result=0
    hex_digits = "0123456789ABCDEF"
    if not hexadecimal_base_check(num) or not num :
        return "Error - The number is not hexadecimal."
    num =str(num)
    is_negative = False
    if num[0] == '-':
        is_negative= True
        num= num[1:]

    if '.' in num: # Separate the integer and decimal parts
        int_part,dec_part= num.split('.')
    else:
        int_part,dec_part= num, ''

    power=0
    for digit in reversed(int_part.upper()): # Convert the integer part to decimal
        result += hex_digits.index(digit) * (16** power)
        power +=1

    power= -1
    for digit in dec_part.upper(): # Convert the decimal part to decimal
        result += hex_digits.index(digit) * (16** power)
        power -=1

    if is_negative: # Add negative sign if needed
        result = -result
    # Format the output with a reasonable number of decimal places
    if dec_part:
        return f"{result:.10f}"
    else:
        return f"{int(result)}"


def main():
    while True:
        # Display the menu to the user
        print("\nChoose a conversion:")
        print("1. Convert Decimal to Hexadecimal")
        print("2. Convert Hexadecimal to Decimal")
        print("3. Exit")

        # Get the user's choice
        choice = input("Enter the number of your choice: ")

        if choice == '1':
            # Convert Decimal to Hexadecimal
            num = input("Enter a decimal number to convert: ")
            print(f"The result in hexadecimal: {decimal_to_hexadecimal(num)}")

        elif choice == '2':
            # Convert Hexadecimal to Decimal
            num = input("Enter a hexadecimal number to convert: ")
            print(f"The result in decimal: {hexadecimal_to_decimal(num)}")

        elif choice == '3':
            # Exit the program
            print("Goodbye!")
            break
        else:
            print("Invalid choice. Please try again.")

if __name__ == "__main__":
    main()