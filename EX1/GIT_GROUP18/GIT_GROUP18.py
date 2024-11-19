# מילון המתרגם תווים למספרים בבסיסים וההפך
hex_map = {
    '0': 0, '1': 1, '2': 2, '3': 3, '4': 4,
    '5': 5, '6': 6, '7': 7, '8': 8, '9': 9,
    'A': 10, 'B': 11, 'C': 12, 'D': 13, 'E': 14, 'F': 15,
    'a': 10, 'b': 11, 'c': 12, 'd': 13, 'e': 14, 'f': 15,
    0: '0', 1: '1', 2: '2', 3: '3', 4: '4',
    5: '5', 6: '6', 7: '7', 8: '8', 9: '9',
    10: 'A', 11: 'B', 12: 'C', 13: 'D', 14: 'E', 15: 'F'
}

# פונקציה להמרה מבסיס 10 לבסיס 16
def decimal_to_hex(decimal_number):

    hex_result = ""
    sign=""
    if decimal_number < 0:
        sign="-"
    decimal_number = abs(decimal_number)

    integer_part = int(decimal_number)
    fractional_part = decimal_number - integer_part

    # המרת החלק השלם
    while integer_part > 0:
        remainder = integer_part % 16
        hex_result = hex_map[remainder] + hex_result
        integer_part //= 16

    # המרת החלק העשרוני
    if fractional_part > 0:
        hex_result += "."
        while fractional_part > 0:
            fractional_part *= 16
            fractional_digit = int(fractional_part)
            hex_result += hex_map[fractional_digit]
            fractional_part -= fractional_digit
            if fractional_part == 0:
                break

    return sign + hex_result

# פונקציה להמרה מבסיס 16 לבסיס 10
def hex_to_decimal_conversion(hex_input):
    hex_input = hex_input.upper()

    # בדיקת שליליות
    is_negative = hex_input.startswith('-')
    if is_negative:
        hex_input = hex_input[1:]

    # חלוקת המספר למספר שלם ועשרוני
    integer_part, NOT_RELEVANT, decimal_part = hex_input.partition(".")

    # המרת החלק השלם
    integer_value = 0
    for i in range(len(integer_part)):
        integer_value += hex_map[integer_part[i]] * (16 ** (len(integer_part) - 1 - i))

    #המרת החלק העשרוני
    decimal_value = 0
    for j in range(len(decimal_part)):
        decimal_value += hex_map[decimal_part[j]] * (16 ** -(j + 1))

    #האיחוד
    final_value = integer_value + decimal_value
    if is_negative:
        final_value = -final_value

    return final_value

# פונקציה לבדיקת תקינות מספר הקדיסצמלי
def is_valid_hexadecimal(user_input):
    valid_chars = "0123456789abcdefABCDEF.-"
    if user_input.count('.') > 1 or user_input.count('-') > 1 or (user_input.startswith('-') and user_input[1:].startswith('-')):
        return False
    for char in user_input:
        if char not in valid_chars:
            return False
    return True

# קבלת קלט המשתמש - לאיזה בסיס הוא רוצה להמיר את המספר
while True:
    try:
        base = int(input("enter base to convert to: 10 or 16 "))
        if base in {10, 16}:
            break
        else:
            print("invalid input please enter 10 or 16")
    except ValueError:
        print("invalid input please enter 10 or 16")

# קבלת קלט המספר מהמתשמש
if base == 16:
    while True:
        try:
            decimal_number = float(input("please enter a decimal number: "))
            hex_result = decimal_to_hex(decimal_number)
            print(f" the hexadecimal output is  {hex_result}")
            break
        except ValueError:
            print("the value entered is not a decimal number")
else:
    while True:
        str_input = input("please enter a hexadecimal number: ")
        if is_valid_hexadecimal(str_input):
            decimal_result = hex_to_decimal_conversion(str_input)
            print(f" the decimal output is: {decimal_result}")
            break
        else:
            print("the number entered is not a valid decimal number`")