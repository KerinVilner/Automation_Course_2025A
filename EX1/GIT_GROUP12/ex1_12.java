import java.util.Scanner;

public class ex1 {		

	public static void main(String[] args) {
		Scanner sc = new Scanner (System.in);
		String base_type;// user decide which base he chose
		String number; // user's input
		boolean play=true;
		double finalanswer_deci;
		String finalanswer_haxa;
		while(play) {
			System.out.println("Welcome Or! to the best game of your life!!\r\n"
					+ "to play this amazing game Please choose your conversion type:\r\n"
					+ "1.Hexadecimal base --> Decimal base \r\n"
					+ "2.Decimal base --> Hexadecimal base\r\n"
					+ "good luck!" 
					);
			base_type= sc.next();
			while (!base_type.equals("1") && !base_type.equals("2"))  {
				System.out.println("invalid input! try again\r\n"
						+ "1.Hexadecimal base --> Decimal base \r\n"
						+ "2.Decimal base --> Hexadecimal base\r\n");
				base_type= sc.next();
			}
			System.out.println("Please enter your Number");
			number=sc.next();
			boolean is_negative = false;
			String positivePart = "";
			if (number.charAt(0) == '-') { // לבדוק אם המספר שלילי
				is_negative = true; 
				positivePart = number.substring(1); //
			}
			else {
				positivePart = number.substring(0);
			}

			if (base_type.equals("1")) {


				if (positivePart.indexOf(".")==-1) {
					finalanswer_deci =convertHaxatodeci (positivePart, 0, positivePart.length()-1, positivePart.length()-1);
					if (finalanswer_deci!= -1) {
						if (is_negative == true) {
							finalanswer_deci = finalanswer_deci*-1;
							System.out.println(finalanswer_deci);
						}
						else {
							System.out.println(finalanswer_deci);
						}
					}
				}
				else {
					int point = positivePart.indexOf(".");

					double sum1 = (convertHaxatodeci (positivePart, 0, point-1 , point-1));
					double sum2 = (convertHaxatodeci (positivePart, point+1 , positivePart.length()-1 , -1));
					if ((sum1!=-1 && sum2 !=-1)) {
						finalanswer_deci = sum1+sum2;	
						if (is_negative == true) {
							finalanswer_deci = finalanswer_deci*-1;
							System.out.println(finalanswer_deci);
						}
						else {
							System.out.println(finalanswer_deci);
						}
					}
				}
			}
			if (base_type.equals("2")) {
				boolean is_valid=true;
				if (positivePart.indexOf(".")==-1) {
					is_valid = check_input (positivePart);
					if (is_valid) {
						finalanswer_haxa = convertdecitoHaxa(positivePart);
						if (is_negative == true) {
							finalanswer_haxa = "-" + finalanswer_haxa;
							System.out.println(finalanswer_haxa);
						}
						else {
							System.out.println(finalanswer_haxa);
						}
					}
				}
				else {
					int index = positivePart.indexOf(".");
					boolean is_valid2=true;
					is_valid2 = check_input (positivePart.substring(index+1));
					if (is_valid2) {
						if (positivePart.charAt(0)=='0') {
							finalanswer_haxa = '0' + convertdecitoHaxapartial (positivePart.substring(index));
							if (is_negative == true) {
								finalanswer_haxa = "-" + finalanswer_haxa;
								System.out.println(finalanswer_haxa);
							}
							else {
								System.out.println(finalanswer_haxa);
							}						}
						else {						
							finalanswer_haxa = convertdecitoHaxa(positivePart.substring(0, index));
							finalanswer_haxa = finalanswer_haxa + convertdecitoHaxapartial (positivePart.substring(index));
							if (is_negative == true) {
								finalanswer_haxa = "-" + finalanswer_haxa;
								System.out.println(finalanswer_haxa);
							}
							else {
								System.out.println(finalanswer_haxa);
							}
						}
					}
				}
			}

			while(play) {
				is_negative = false;
				System.out.println("Would you like to make another conversion? (you should, it is good!)\r\n"
						+ "1. yes\r\n"
						+ "2. no");
				int userAnswer= sc.nextInt();
				if (userAnswer==2) {
					System.out.println("bye bye");
					play = false;
					break;
				}
				if (userAnswer == 1) {
					break;
				}
				else {
					System.out.println("invalid input! try again");
				}
			}
		}
	}

	public static boolean check_input (String positivePart) {
		for (int i=0; i<positivePart.length();i++) {
			if (positivePart.charAt(i)>='0' && positivePart.charAt(i)<='9') {
			}
			else {
				System.out.println("invalid input! why did you do this :( ");
				return false;
			}
		}
		return true;
	}

	public static String convertdecitoHaxapartial (String positivePart) {
		positivePart = '0'+ positivePart;
		int temp=0;
		double haxapartial = Double.parseDouble(positivePart);
		String deci_answer=".";
		while (haxapartial >0.00000000001) {// this is small enough
			haxapartial = haxapartial *16;
			if (haxapartial<10) {
				temp = (int) (haxapartial);
				deci_answer = deci_answer + (char)(temp + '0'); 
			}
			else {
				temp = (int) (haxapartial);
				deci_answer = deci_answer + (char)(temp + 55); 
			}
			haxapartial = haxapartial-temp;
		}
		return deci_answer;
	}



	public static String convertdecitoHaxa (String positivePart) {
		String deci_answer = "";
		int haxa = Integer.parseInt(positivePart);
		while (haxa >0) {
			int remainder = (int) (haxa % 16);
			if (remainder<10) {
				deci_answer = deci_answer + (char)(remainder + '0');	
			}
			else {
				deci_answer = deci_answer + (char)(remainder+ 55);
			}
			haxa = haxa/16;
		}
		String reversed_ans = new StringBuilder(deci_answer).reverse().toString();
		return(reversed_ans);
	}

	public static double convertHaxatodeci (String positivePart, int begining, int end, int pow) {
		double answer = 0; 
		for (int i = begining; i <= end; i++) {
			if (positivePart.charAt(i)>= '0' && positivePart.charAt(i)<= '9') { 
				answer = answer + (Math.pow(16, pow))*(positivePart.charAt(i)-'0');
				pow--;
			}
			else if (positivePart.charAt(i) >= 'A' && positivePart.charAt(i)<= 'F' ) {
				answer = answer + (Math.pow(16, pow))*(positivePart.charAt(i)- 'A' + 10);
				pow--;

			}
			else {
				System.out.println("Invalid input. Please try again");
				return -1;


			}						
		}
		return answer;
	}
}