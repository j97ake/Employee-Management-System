
import java.util.ArrayList;
import java.awt.*;
import javax.swing.*;
import java.awt.event.*;
import com.alpha.prts.*;

public class ReportEmployeeDetails extends JFrame
{
	String[] headings = { "EmpID", "UserName", "Status", "FirstName","Last Name","Fathers Name","Address","Mobile","Email","Date Of Birth" }; 
   	
  	JScrollPane jp;
	JTable jt;
	prtsDb db_obj;
 	EmployeeBean emp1_obj;
 	Container c = null;
 
  	public ReportEmployeeDetails() 
	{    
		db_obj=new prtsDb();
		c = this.getContentPane();
		c.setLayout(new FlowLayout());
		ArrayList arraylist_emp=db_obj.getEmployeeRecords();
          		if(arraylist_emp.size()>0)
		{
		
			String[][] data =new String[arraylist_emp.size()][10];
        			for(int i=0;i<arraylist_emp.size();i++)	
			{
				emp1_obj=(EmployeeBean)arraylist_emp.get(i);
				data[i][0]=String.valueOf(emp1_obj.getEmpId());
				data[i][1]=emp1_obj.getUsername();
				data[i][2]=emp1_obj.getEmpStatus();
				data[i][3]=emp1_obj.getEmpFName();
				data[i][4]=emp1_obj.getEmpLName();
				data[i][5]=emp1_obj.getEmpFFName();
				data[i][6]=emp1_obj.getEmpAddress();
				data[i][7]=emp1_obj.getEmpMobile();
				data[i][8]=emp1_obj.getEmpEmail();
				data[i][9]=emp1_obj.getEmpDOB();
			}	  
 			
			 jt = new JTable(data, headings); 
			jt.setPreferredScrollableViewportSize(new Dimension(1000,700)); 
			jp = new JScrollPane(jt); 
			c.add(jp); 
		}
		else{
			c.add(new JLabel("There are no records to display"));
		}

		setTitle("Employee Personal Records");  
		setSize(Toolkit.getDefaultToolkit().getScreenSize());   
        		setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);    
        		setVisible(true);    
  	}  
}