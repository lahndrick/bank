import java.util.ArrayList;
import java.util.Random;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;

/************************************************************************
*			Bank CLASS STARTS HERE
************************************************************************/

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
 /************************************************************************
 *			Customer CLASS STARTS HERE
 ************************************************************************/

class Customer
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

/************************************************************************
*			backEnd CLASS STARTS HERE
************************************************************************/

class backEnd extends Bank
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

/************************************************************************
*			frontEnd (the GUI) CLASS STARTS HERE
************************************************************************/

@SuppressWarnings("serial")
class frontEnd extends JFrame implements ActionListener
{
	private backEnd backEnd = new backEnd("");
	private JFrame frame = new JFrame("Banking app");
	private Object[] convertor;
	private JPanel panel;
	private JLabel bankName;
	private JTextField searchCustomer;
	private JList<Object> customerList;
	private JScrollPane scroll;
	private JButton add;
	private JButton remove;
	private JButton statistics;
	private JButton customers;
	
	public frontEnd(String title) 
	{
		super(title);
		frame.setDefaultCloseOperation(EXIT_ON_CLOSE);
		frame.setSize(400,500);

		int buttonWidth = 177;
		int buttonHeight = 60;
		Color buttonColor = new Color(171,236,255);
		
		this.convertor = new Object[backEnd.getBank().size()];
		
		this.panel = new JPanel();
		panel.setBackground(Color.white);
		panel.setLayout(null);
		
		this.bankName = new JLabel("Big Boy Cons");
		bankName.setLocation(150,0);
		bankName.setSize(100,15);
		bankName.setBackground(buttonColor);
		panel.add(bankName);
		
		this.searchCustomer = new JTextField("");
		searchCustomer.setSize(365,buttonHeight);
		searchCustomer.setLocation(10,40);
		searchCustomer.setBackground(buttonColor);
		panel.add(searchCustomer);
		
		convertor = (Object[]) backEnd.convert(backEnd);
		
		this.customerList = new JList<Object>(convertor);
		this.scroll = new JScrollPane(customerList);
		scroll.setSize(searchCustomer.getWidth(),200);
		scroll.setLocation(10,searchCustomer.getX() + searchCustomer.getHeight() + 40);
		panel.add(scroll);
		
		this.add = new JButton("Add");
		add.setSize(buttonWidth,buttonHeight);
		add.setLocation(10, scroll.getX() + 310);
		add.setBackground(buttonColor);
		add.addActionListener(this);
		panel.add(add);
		
		this.remove = new JButton("Remove");
		remove.setSize(buttonWidth,buttonHeight);
		remove.setLocation(add.getX() + buttonWidth + 10,add.getY());
		remove.setBackground(buttonColor);
		panel.add(remove);
		
		this.statistics = new JButton("Statistics");
		statistics.setSize(buttonWidth,buttonHeight);
		statistics.setLocation(add.getX(),390);
		statistics.setBackground(buttonColor);
		panel.add(statistics);
		
		this.customers = new JButton("Customers: " + backEnd.getBank().size());
		customers.setSize(buttonWidth,buttonHeight);
		customers.setLocation(remove.getX(),390);
		customers.setBackground(buttonColor);
		panel.add(customers);
		
		frame.add(panel);
		frame.setVisible(true);
	}
	
	@Override
	public void actionPerformed(ActionEvent e) 
	{
		//At this stage the buttons will not have any functionality
	}
	
	public static void main(String[] args)
	{
		frontEnd test = new frontEnd("bbc");
		System.out.println("Front end information: \n" + test);
	}
}
