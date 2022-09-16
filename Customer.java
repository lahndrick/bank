import java.util.Random;

public class Customer
{	
	Random rand = new Random();

	private String fullName;
	private int pin;
	private int balance;
	private int ID;
	private int transactionNumber = 0;
	private int suffix = 10856;
	private int branchNumber = rand.nextInt(900) + 100;
	private String bankAccountNumber;

	public Customer(String fullName)
	{
		if(fullName.isEmpty() == false && fullName != null)
		{
			this.fullName = fullName;
			this.pin = 0000;
			this.balance = 0;
			this.bankAccountNumber = getBankAccountNumber();
		}
	}

	public String getFullName() 
	{
		return fullName;
	}

	public void setFullName(String name) 
	{
		this.fullName = name;
	}

	public int getSuffix() 
	{
		return suffix;
	}

	public void setSuffix(int suffix) 
	{
		this.suffix = suffix;
	}

	public int getBranchNumber() 
	{
		return branchNumber;
	}

	public void setBranchNumber(int branchNumber) 
	{
		this.branchNumber = branchNumber;
	}

	public int getPin() 
	{
		return pin;
	}

	public void setPin(int pin) 
	{
		this.pin = pin;
	}

	public double getBalance() 
	{
		return balance;
	}

	public void setBalance(int balance) 
	{
		this.balance = balance;
	}

	public void deposit(double amount)
	{
		this.transactionNumber++;
		this.balance += amount;
	}

	public void withdraw(double amount)
	{
		if((this.balance - amount) > 0)
		{
			this.transactionNumber++;
			this.balance -= amount;			
		}
	}

	public int getID() 
	{
		return ID;
	}

	public void setID(int iD) 
	{
		ID = iD;
	}


	public String getBankAccountNumber() 
	{
		Object dummyAccount = this.getSuffix() + "-" + this.getBranchNumber() + "-" + this.getID();
		this.bankAccountNumber = (String) dummyAccount;
		return this.bankAccountNumber;
	}

	public int getTransactionNumber() 
	{
		return transactionNumber;
	}

	public String toString()
	{
		return "Customer: " + this.getFullName() + ", "
				+ this.getBankAccountNumber();
	}
}
