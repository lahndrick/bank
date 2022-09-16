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

@SuppressWarnings("serial")
public class frontEnd extends JFrame implements ActionListener
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

	//this is still only in a test phase, only working on "add" button
	@Override
	public void actionPerformed(ActionEvent e) 
	{
		if(searchCustomer.getText() != null)
		{
			String customerName = searchCustomer.getText();

			if(customerName != null && customerName.isEmpty() == false)
			{
				Customer newCustomer = new Customer(customerName);
				backEnd.getBank().addCustomer(newCustomer);
				System.out.println("Amount of customers: " + backEnd.getBank().size());
				System.out.println("Recent customer: " + backEnd.getBank().getCustomer(backEnd.getBank().size() - 1));
			}
		}
	}

	public static void main(String[] args)
	{
		frontEnd test = new frontEnd("bbc");
		System.out.println("Front end information: \n" + test);
	}
}
