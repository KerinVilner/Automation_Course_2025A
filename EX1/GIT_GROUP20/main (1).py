# Dicts for conversion
values_dict = {"0":0,"1":1,"2":2,"3":3,"4":4,"5":5,"6":6,"7":7,"8":8,"9":9,"A":10,"B":11,"C":12,"D":13,"E":14,"F":15}
strings_dict = {0:"0",1:"1",2:"2",3:"3",4:"4",5:"5",6:"6",7:"7",8:"8",9:"9",10:"A",11:"B",12:"C",13:"D",14:"E",15:"F"}

def StringToInt(string_list): ## Takes a base 16 char and turns ut into an int
    values_dict = {"0":0,"1":1,"2":2,"3":3,"4":4,"5":5,"6":6,"7":7,"8":8,"9":9,"A":10,"B":11,"C":12,"D":13,"E":14,"F":15}
    output = []
    for i in range(len(string_list)):
        if string_list[i] in list(values_dict.keys()):
            output.append(values_dict[string_list[i]])
    return output[::-1]

        
while True: # Begin Loop
    choice = input("Please choose base 10 to 16 (1) or base 16 to 10 (2) or exit (3): ") 
    ## Variables for different edge cases
    total = 0 
    total16 = ""
    total16Decimal = ""
    negativeFlag = False
    if choice == "1": # Base 10 to 16
       num = input("Please enter your base 10 num: ") ## Error Handling
       list_num = list(num)
       if list_num[0] == "-": # Checking if input is negative
            negativeFlag = True
            list_num.pop(0)
       try: # Checking for validity 
           if "." in list_num: # For non natural numbers
               num = int("".join(list_num[0:list_num.index(".")]))
               list_num.insert(0,"0")
               decimal = float("".join(list_num[list_num.index("."):len(list_num)]))
               while abs(num) > 16:
                   total16 = total16 + strings_dict[num%16]
                   num = int(num/16)
               total16 = total16 + strings_dict[num%16]
               total16 = "."+total16
               while abs(decimal) * 16 != int(abs(decimal)*16):
                   total16Decimal = total16Decimal + strings_dict[int(abs(decimal)*16)]
                   decimal = decimal*16 - int(decimal*16)
               total16Decimal = total16Decimal + strings_dict[decimal*16 - int(decimal*16)]
               if negativeFlag:
                   print("Your base 16 number is: -" + total16[::-1] + total16Decimal)
               else: 
                   print("Your base 16 number is: " + total16[::-1] + total16Decimal)
           else:
               num = int("".join(list_num))
               while abs(num) > 16:
                   total16 = total16 + strings_dict[num%16]
                   num = int(num/16)
               total16 = total16 + strings_dict[num%16]
               if negativeFlag:
                   print("Your base 16 number is: -" + total16[::-1])
               else: 
                   print("Your base 16 number is: " + total16[::-1])
       except ValueError:
           print("Invalid")
    elif choice == "2": ## Base 16 to 10
        validFlag = True
        num = input("Please enter your base 16 num: ") 
        list_num = list(num.upper())
        if list_num[0] == "-": # Checking if input is negative
            negativeFlag = True
            list_num.pop(0)
        for i in range(len(list_num)):
            if list_num[i] not in list(values_dict.keys()):
                if(list_num[i] != "."):
                    validFlag = False
        if(validFlag):
            if list_num[0] == "-": # Checking for negative number
                negativeFlag = True
                list_num.pop(0)
            if "." in list_num: # Checking for non natural number
                numbers = StringToInt(list_num[0:list_num.index(".")]) # String to int defined in the begining of the file
                decimal = StringToInt(list_num[list_num.index(".")+1:len(list_num)])[::-1]
                for i in range(len(numbers)):
                    total = total + numbers[i]*(16**(i))
                for i in range(len(decimal)):
                    total = total + decimal[i]*(16**(-1-i))
            else:
                final = StringToInt(list_num)
                for i in range(len(final)):
                    total = total + final[i]*(16**(i))
            if negativeFlag:
                total = total*-1
            print("The Base 10 number is: "+ str(total))
        else:
            print("Invalid input, value not in base 16")
    elif choice == "3": ## Stopping the program
        print("bye bye")
        break
    else: ## Invalid inputs
         print("Invalid")