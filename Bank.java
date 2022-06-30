import java.util.ArrayList;

public class Bank
{
	private int accountNumber = 100;
	private ArrayList<Customer> customers;

	public Bank()
	{
		customers = new ArrayList<Customer>();
	}
	
	public void addCustomer(Customer customer)
	{
		customer.setID(accountNumber);
		accountNumber++;
		this.customers.add(customer);
	}
	
	public void removeCustomer(Customer customer)
	{
		this.customers.remove(customer);
	}
	
	public String toString()
	{
		for(int x = 0; x < customers.size();x++)
		{
			System.out.println(customers.get(x));
		}
		return "";
	}
	
	public Customer getCustomer(int x)
	{
		return customers.get(x);
	}
	
	public Customer max()
	{
		Customer maxCustomer = null;
		double max = 0;
		
		for(int x = 0;x < customers.size();x++)
		{
			if(customers.get(x).getBalance() > max)
			{
				max = customers.get(x).getBalance();
				maxCustomer = customers.get(x);
			}
		}
		
		return maxCustomer;
	}
	
	public Customer min()
	{
		Customer minCustomer = null;
		double min = this.average();
		
		for(int x = 0;x < customers.size();x++)
		{
			if(customers.get(x).getBalance() <=  min)
			{
				min = customers.get(x).getBalance();
				minCustomer = customers.get(x);
			}
		}
		
		return minCustomer;
	}

	public int totalAmount()
	{
		int total = 0;
		
		for(int x = 0; x < customers.size();x++)
		{
			total += customers.get(x).getBalance();
		}
		
		return total;
	}
	
	public int average()
	{
		return this.totalAmount() / customers.size();
	}
	
	public int size()
	{
		return this.customers.size();
	}
}
