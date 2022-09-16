import java.util.ArrayList;
import java.util.Random;

public class backEnd extends Bank
{
	Random rand = new Random();
	private Bank bank;
	private double transactionAmount = 0;

	public backEnd(String title)
	{
		//creating new bank object
		super();
		bank = new Bank();

		//creating customers
		Customer c1 = new Customer("David Beaming");
		Customer c2 = new Customer("Rashmeen Basdf");
		Customer c3 = new Customer("Billy Aoieqwarrok");
		Customer c4 = new Customer("Bob Bilbobaggins");
		Customer c5 = new Customer("Lahndrick Hendricks");


		//adding customers to bank
		bank.addCustomer(c1);
		bank.addCustomer(c2);
		bank.addCustomer(c3);
		bank.addCustomer(c4);
		bank.addCustomer(c5);


		//random 100 deposits per customer
		for(int y = 0;y<100;y++)
		{
			for(int x = 0;x < bank.size();x++)
			{
				transactionAmount = rand.nextDouble(1000);
				bank.getCustomer(x).deposit(transactionAmount);
			}	
		}

		//random 100 withdraws per customer
		for(int y = 0;y<100;y++)
		{
			for(int x = 0;x < bank.size();x++)
			{
				transactionAmount = rand.nextDouble(1000);
				bank.getCustomer(x).withdraw(transactionAmount);
			}	
		}
	}

	public Bank getBank()
	{
		return this.bank;
	}

	public Object convert(backEnd input)
	{
		Object[] converter = new Object[this.getBank().size()];
		ArrayList<Object> converterList = new ArrayList<Object>();

		for(int x = 0;x < this.getBank().size();x++)
		{
			converterList.add(this.getBank().getCustomer(x).toString());
			converter[x] = converterList.get(x);
		}

		return converter;
	}
}
