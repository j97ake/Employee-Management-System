import java.util.ArrayList;
import java.awt.*;
import javax.swing.*;
import java.awt.event.*;
import com.alpha.prts.*;

public class ReportSearchCompany extends JFrame
{
	String[] headings = { "CompanyID", "Company Name", "Phone","Email","Salary Offered"}; 
   	
  	JScrollPane jp;
	JTable jt;
	prtsDb db_obj;
 	CompanySkillSetBean company_obj;
 	Container c = null;
 
  	public ReportSearchCompany(ArrayList arraylist_company) 
	{    
		db_obj=new prtsDb();
		c = this.getContentPane();
		c.setLayout(new FlowLayout());
          		if(arraylist_company.size()>0)
		{
		
			String[][] data =new String[arraylist_company.size()][5];
        			for(int i=0;i<arraylist_company.size();i++)	
			{
				company_obj=(CompanySkillSetBean)arraylist_company.get(i);
				data[i][0]=String.valueOf(company_obj.getCompanyId());
				data[i][1]=company_obj.getCompanyName();
				data[i][2]=company_obj.getPhone();
				data[i][3]=company_obj.getEmail();
				data[i][4]=String.valueOf(company_obj.getSalaryOffered());
		
			}	  
 			
			 jt = new JTable(data, headings); 
			jt.setPreferredScrollableViewportSize(new Dimension(1000,700)); 
			jp = new JScrollPane(jt); 
	       		c.add(jp); 
		}
		else{
			c.add(new JLabel("There are no records to display"));
		}

		setTitle("Search result");  
		setSize(Toolkit.getDefaultToolkit().getScreenSize());   
        		setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);    
        		setVisible(true);    
  	}  
}