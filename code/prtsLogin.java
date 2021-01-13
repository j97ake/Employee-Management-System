import java.awt.event.*;
import java.awt.*;
import javax.swing.*;
import com.alpha.prts.prtsDb;

public class prtsLogin extends JFrame implements ActionListener
{
	JPanel p1,p2,p3;
	JLabel l1,l2,l3,lbl_msg;
	JTextField t1;
	JPasswordField t2;
	JButton b1,b2;
	Container c=null;
	int loginCount=0; 	
	prtsDb db_obj;
	MainFrame main_obj;
	public void init()
	{
		c=this.getContentPane();
		setBounds(350,300,350,175);
                setSize(650,300);
                setLocation(100,100);
		c.setLayout(new BorderLayout());
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setTitle("PRTS");

		db_obj=new prtsDb();

		p1=new JPanel();
		p2=new JPanel();
		p3=new JPanel();

		t1=new JTextField(10);
		t2=new JPasswordField(10);

		t1.addActionListener(this);
		t2.addActionListener(this);

		b1=new JButton("Login");
		b1.addActionListener(this);

		b2=new JButton("Cancel");
		b2.addActionListener(this);

		l1=new JLabel("  Username");
		l2=new JLabel("  Password");
		
		l3=new JLabel("Enter Login Details");
		l3.setHorizontalAlignment(SwingConstants.CENTER);
		l3.setFont(new Font("Tahoma", 1, 15));

		lbl_msg=new JLabel("");
		lbl_msg.setHorizontalAlignment(SwingConstants.CENTER);
		lbl_msg.setFont(new Font("Tahoma", 1, 11));
		lbl_msg.setForeground(Color.red);	

		c.setLayout(new BorderLayout(5,5));
		
		//p1.setLayout();
		//p2.setLayout();

		ImageIcon c1 = new ImageIcon("SwamiJi.gif");
                JLabel r1 = new JLabel(c1);

		p1.add(l3);
		p1.add(lbl_msg);

		p2.add(l1);
		p2.add(t1);

		p2.add(l2);
		p2.add(t2);

		p3.add(b1);
		p3.add(b2);
		c.add(p1,BorderLayout.NORTH);
		c.add(p2,BorderLayout.CENTER);
		c.add(p3,BorderLayout.SOUTH);
c.add(r1,BorderLayout.WEST);
		setVisible(true);
		//setResizable(false);	
		
	}
	public void actionPerformed(ActionEvent ae)
	{
		if(ae.getSource()==b2)    //cancel button
		{	
			System.exit(0);
		}
	 
		if(ae.getSource()==b1 || ae.getSource()==t2 || ae.getSource()==t1)     //login 
		{
			if(t1.getText().equals("")){
				lbl_msg.setText("Enter username");
			}
			else if(t2.getText().equals("")){	
				lbl_msg.setText("Enter password");
			}
			
			else{
				String status=db_obj.validateUser(t1.getText(),t2.getText());
javax.swing.JOptionPane.showMessageDialog(null,"st"+status);				
if(status.equals("admin")){
					System.out.println("Admin login");
					setVisible(false);
					main_obj =new MainFrame();
					main_obj.init(status);

				}
				else if(status.equals("manager")){
					System.out.println("Manager login");
					setVisible(false);
					main_obj =new MainFrame();
					main_obj.init(status);	
				}					
				else if(status.equals("operator")){
					System.out.println("operator login");
					setVisible(false);
					main_obj =new MainFrame();
					main_obj.init(status);	
				}
				else if(status.equals("invalid")){
					lbl_msg.setText("Invalid user");
					loginCount++;
					if(loginCount>2){
						lbl_msg.setText("Exit application");
						b1.setEnabled(false);
					}
					
				}
			}	
		
			
		}
		
		
	}
	public static void main(String args[])
	{
		prtsLogin obj=new prtsLogin();
		obj.init();
	}
}
			