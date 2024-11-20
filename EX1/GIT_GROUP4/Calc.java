
import java.util.Scanner;
public class Calc {
	static Scanner sc= new Scanner(System.in);
	
	public static void main(String[] args) {


		int choose;
		do {
			System.out.println("Please choose a base:");
			System.out.println("0. End ");
			System.out.println("1. 16--->10");
			System.out.println("2. 10--->16");
			choose=sc.nextInt();
			if(choose<0||choose>2) //Checks if the number is in the requested range
				System.out.println("Invalid menu input");


			if(choose==1)//from base 16 to 10
			{
				int ans=0;
				System.out.println("Please enter number (16):");
				String num; // input
				
				do{
					num = sc.nextLine();
				} while (num.length() == 0);
				
				boolean negative=false;//check if the input negative
				if(num.indexOf("-")==0)//if negative
				{
					negative=true;
					num=num.substring(1);
				}
				
				int chack=num.indexOf(".");//if the input is double
				String left="";//INT part
				String right="";//DOUBLE part
				if(chack!=-1)
				{
					left=num.substring(0,chack);
					right=num.substring(chack+1);
				}
				else//if the input isnt double
					left=num;

				boolean flag=true;//check if input valid

				for(int i=left.length()-1;i>=0;i--)//loop for int part
				{
					char c=left.charAt(0);
					int temp=(int)c;
					if(temp>=48&&temp<=57)//check the number from the ascii table
						temp-=48;
					else if(temp>=65&&temp<=70)
						temp-=55;
					else//if its out of range
					{
						flag=false;
						break;
					}
					ans+=temp*(Math.pow(16, i));
					left=left.substring(1);


				}
				double ansr=0.0;
				if (!right.equals(""))
				{
					String help=right;
					for(int i=1;i<=right.length();i++)//loop for the double part
					{
						char c=help.charAt(0);
						int temp=(int)c;
						if(temp>=48&&temp<=57)
							temp-=48;
						else if(temp>=65&&temp<=70)
							temp-=55;
						else
						{
							flag=false;
							break;
						}
						ansr+=temp*(Math.pow(16, -i));
						help=help.substring(1);


					}
				}

				if(flag==false)//invalid input
				{
					System.out.println("Input error!");
					continue;
				}
				double number=ans+ansr;//add the int part to double part
				if (negative==true)
					number*=-1;
				System.out.println(number);

			}
			if(choose==2)//from base 10 to 16
			{
				System.out.println("Please enter number (10):");
				double num=sc.nextDouble();
				boolean negative=false;
				if(num<0)
				{
					negative=true;
					num*=-1;
				}
				int left=(int)num;
				double right=num-left;
				String ans="";
				while(left>0)
				{
					int temp=left/16;
					double help=(left/16.0)-temp;//help is help var to keep the double for later
					help*=16;
					int con=(int)help;//the answer before transfer into letter if needed
					if(help>9)
					{
						if(con==10)
							ans=ans+"A";
						if(con==11)
							ans=ans+"B";
						if(con==12)
							ans=ans+"C";
						if(con==13)
							ans=ans+"D";
						if(con==14)
							ans=ans+"E";
						if(con==15)
							ans=ans+"F";

					}
					else
					{
						ans+=con;
					}
					left=left/16;
				}
				String rev="";//revers from "bottom to up"
				for(int i=ans.length()-1;i>=0;i--)
				{
					rev+=ans.charAt(i);
				}
				if(rev.length()==0)
				{
					rev+=0;
				}
				if(right>0.0)
				{
					rev+=".";

					for(int i=0;i<3;i++)//the same thing for double part, just 3 numbers after the point
					{

						double temp=right*16;
						int intger=(int)temp;
						right=temp-intger;
						if(intger>9)
						{
							if(intger==10)
								rev=rev+"A";
							if(intger==11)
								rev=rev+"B";
							if(intger==12)
								rev=rev+"C";
							if(intger==13)
								rev=rev+"D";
							if(intger==14)
								rev=rev+"E";
							if(intger==15)
								rev=rev+"F";

						}
						else
						{
							rev+=intger;
						}
					}

				}
				if(negative==true)
				{
					rev="-"+rev;
				}
				System.out.println(rev);

			}
		}while(choose!=0);//end program
		System.out.println("Thank you! :)");


	}

}
