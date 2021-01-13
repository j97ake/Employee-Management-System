import java.util.ArrayList;
import java.awt.*;
import javax.swing.*;
import java.awt.event.*;
import com.alpha.prts.*;

public class ReportProPersonalDetails extends JFrame
{
	String[] headings = { "ID", "FirstName","Last Name","Fathers Name","Address","Mobile","Email","Date Of Birth","PAN","Expected Salary" }; 
   	
  	JScrollPane jp;
	JTable jt;
	prtsDb db_obj;
 	ProfessionalPersonalBean pro_obj;
 	Container c = null;
 
  	public ReportProPersonalDetails() 
	{    
		db_obj=new prtsDb();
		c = this.getContentPane();
		c.setLayout(new FlowLayout());
		ArrayList arraylist_prof=db_obj.getProfessionalPersonalRecords();
          		if(arraylist_prof.size()>0)
		{
		
			String[][] data =new String[arraylist_prof.size()][10];
        			for(int i=0;i<arraylist_prof.size();i++)	
			{
				pro_obj=(ProfessionalPersonalBean)arraylist_prof.get(i);
				data[i][0]=String.valueOf(pro_obj.getProfId());
				data[i][1]=pro_obj.getProfFName();
				data[i][2]=pro_obj.getProfLName();
				data[i][3]=pro_obj.getProfFFName();
				data[i][4]=pro_obj.getProfAddress();
				data[i][5]=pro_obj.getProfMobile();
				data[i][6]=pro_obj.getProfEmail();
				data[i][7]=pro_obj.getProfDOB();
				data[i][8]=pro_obj.getProfPAN();
				data[i][9]=String.valueOf(pro_obj.getProfExpectedSalary());
			}	  
 			
			 jt = new JTable(data, headings); 
			jt.setPreferredScrollableViewportSize(new Dimension(1000,700)); 
			jp = new JScrollPane(jt); 
	       		c.add(jp); 
		}
		else{
			c.add(new JLabel("There are no records to display"));
		}

		setTitle("Professional Personal Records");  
		setSize(Toolkit.getDefaultToolkit().getScreenSize());   
        		setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);    
        		setVisible(true);    
  	}  
}