import java.util.*;
import java.lang.Math;
public class automation1 {
	static Scanner sc = new Scanner(System.in);
	public static void main(String[] args) 
	{

		int choice;
		while(true) {
			System.out.println("0. Exit");
			System.out.println("1. Convert from base 16 to base 10");
			System.out.println("2. Convert from base 10 to base 16");
			choice =  sc.nextInt();

			if(choice==0)
			{
				System.out.println("Goodbye");
				break;
			}
			if(choice==1)

			{
				System.out.println("Insert Number: ");
				String input;
				do{ // get the string from the user
					input = sc.nextLine();
				} while (input.length() == 0);

				String hexNum= input.toUpperCase();//capital letters

				if(!checkString(hexNum,1)) { //check all char valid
					System.out.println("Invalid Input");
					System.out.println();
					continue;					
				}
				hexToDec (hexNum);

			}
			if(choice==2)
			{
				System.out.println("Insert Number: ");
				String input;
				do{ // get the string from the user
					input = sc.nextLine();
				} while (input.length() == 0);
				if(!checkString(input,2)) { //check all char valid
					System.out.println("Invalid Input");
					System.out.println();
					continue;
				}
				int decNum=Integer.parseInt(input);
				decToHex(decNum);

			}
			if  (choice>2|| choice<0){
				System.out.println("The option does not exist, please choose again");
				System.out.println();
			}

		}

	}


	public static boolean checkString(String str,int j) {//check each char is valid

		String check="";
		if(j==1)
			check="0123456789ABCDEF";
		if(j==2)
			check="0123456789";
		for(int i=0;i<str.length();i++)
		{
			if(check.indexOf(str.charAt(i))==-1)
				return false;
		}

		return true;

	}

	public static int value(char c) //convert char to numerical value
	{
		if(c=='A')
			return 10;
		if(c=='B')
			return 11;
		if(c=='C')
			return 12;
		if(c=='D')
			return 13;
		if(c=='E')
			return 14;
		if(c=='F')
			return 15;
		return (int)c-48;//48= ASCII of 0
	}
	public static void hexToDec (String hexNum)
	{

		int decNum=0;
		for(int i=hexNum.length()-1; i>=0;i--)
		{
			decNum+=value(hexNum.charAt(i))*(int)Math.pow(16, hexNum.length()-i-1); //calculation

		}
		System.out.println(decNum);
		System.out.println();
	}

	public static void decToHex(int decNum)
	{
		String hexNum="";
		while(decNum>0)
		{
			int remain= decNum%16;
			char hexdigit =(remain<10)?(char)('0'+remain):(char)('A'+(remain-10));	//calculation			
			hexNum+=hexdigit;
			decNum=decNum/16;
		}
		String reversed="";
		for(int i=hexNum.length()-1;i>=0;i--)
		{
			reversed+=hexNum.charAt(i);
		}
		System.out.println(reversed);
		System.out.println();
	}
}
