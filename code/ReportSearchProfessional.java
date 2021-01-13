import java.util.ArrayList;
import java.awt.*;
import javax.swing.*;
import java.awt.event.*;
import com.alpha.prts.*;

public class ReportSearchProfessional extends JFrame
{
	String[] headings = { "ID", "FirstName","Last Name","Mobile","Email","Expected Salary" }; 
   	
  	JScrollPane jp;
	JTable jt;
	prtsDb db_obj;
 	ProfessionalPersonalBean pro_obj;
 	Container c = null;
 
  	public ReportSearchProfessional(ArrayList arraylist_prof) 
	{    
		db_obj=new prtsDb();
		c = this.getContentPane();
		c.setLayout(new FlowLayout());
    		if(arraylist_prof.size()>0)
		{
		
			String[][] data =new String[arraylist_prof.size()][6];
        			for(int i=0;i<arraylist_prof.size();i++)	
			{
				pro_obj=(ProfessionalPersonalBean)arraylist_prof.get(i);
				data[i][0]=String.valueOf(pro_obj.getProfId());
				data[i][1]=pro_obj.getProfFName();
				data[i][2]=pro_obj.getProfLName();
				data[i][3]=pro_obj.getProfMobile();
				data[i][4]=pro_obj.getProfEmail();
				data[i][5]=String.valueOf(pro_obj.getProfExpectedSalary());
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