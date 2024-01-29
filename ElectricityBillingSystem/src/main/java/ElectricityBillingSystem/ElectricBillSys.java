package ElectricityBillingSystem;


import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Scanner;

interface ElectricInterface
{
	double balanceCalc();
	void display();
}


class InterestPenalty
{
	public int OutsandingAmountCalc(int units)
	{
		int OutstandingAmount = units * 3;
		return OutstandingAmount;
				
	}
	
	public int PenaltyCalc(int Oustand)
	{
		int penalty = Oustand * 20/100;
		return penalty;
	}
}

class ElectricityBillCalc extends InterestPenalty implements ElectricInterface
{
	int consumerId;
	String name;
	int units;
	int outstand;
	//declare global variables
	void setData(int id, String name, int units, int outstand)
	{//declare method for set data
		consumerId=id;
		this.name=name;
		this.units=units;
		this.outstand = outstand;
	//Using "this" keyword for access global variable inside the method as same name
	}
	/*1 - 100 unit - 1.5/= 
	101-200 unit - 3/= 
	201-300 unit - 6/= 
	above 300 - 7/=*/	
	public void display()
	{
		System.out.println();
		System.out.println("******************Electricity Bill******************");
		System.out.println("Consumer ID:    "+consumerId);
		System.out.println("Consumer Name:  "+name);
		System.out.println("Consumed Units: "+units);	
	}
	
	public double balanceCalc()
	{
		double billAmount=0;
			
		
		if(units>0 && units<=100)
		{
			billAmount=units*1.5;
		}
		else if(units>100 && units<=200)
		{
			billAmount=100*1.5+(units-100)*3;
		}
		else if(units>200 && units<=300)
		{
			billAmount=(100*1.5)+200*3+(units-200)*6;
		}
		else if(units>350)
		{
			billAmount=(100*1.5)+200*3+(300-200)*6+(units-350)*7;
		}
		else
		{
			System.out.println("No charges");
		}
		
		return billAmount;
	}
	
	public int OutStandAmount()
	{
		return this.outstand;
	}
}

class ElectricBillSys
{
    public static void main(String args[])
    {
    	System.out.println("Electricity Bill Generator");   	
        ElectricityBillCalc obj=new ElectricityBillCalc();//create object      
        Scanner sc = new Scanner(System.in);       
        System.out.print("Enter the consumer ID: ");
        int consumerId = sc.nextInt();  
        sc.nextLine();
        System.out.print("Enter the consumer name: ");
        String name = sc.nextLine();
        System.out.print("Meter reading from previous month: ");
        int pRead = sc.nextInt();
        System.out.print("Meter reading from current month: ");
        int cRead = sc.nextInt();
        int UnitsConsumed = cRead -pRead;        
        System.out.print("Enter the units not paid in previous month : " );
        int OutstandUnits = sc.nextInt();
        int OutstandAmount = obj.OutsandingAmountCalc(OutstandUnits);
        int Penalty = obj.PenaltyCalc(OutstandAmount);       
        int TotalOutstand = OutstandAmount + Penalty;       
        obj.setData(consumerId,name,UnitsConsumed,TotalOutstand);//call method using object with argument                
        double billPay=obj.balanceCalc();
        int pen = obj.OutStandAmount();
        obj.display();//call method using object
        System.out.println("Bill amount of the present month is Rs "+ billPay);
        System.out.println("Penalty is Rs "+ pen);
        System.out.println("Total amount payable Rs: "+ billPay + pen);       
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
        Calendar c = Calendar.getInstance();
        c.setTime(new Date()); // Using today's date
        c.add(Calendar.DATE, 15); // Adding 15 days
        String output = sdf.format(c.getTime());
        System.out.println("Due Date: "+ output);

        //display amount
    }
    
}

