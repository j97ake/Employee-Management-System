import java.util.ArrayList;
import java.awt.*;
import javax.swing.*;
import java.awt.event.*;
import com.alpha.prts.*;

public class ReportCompanyDetails extends JFrame
{
	String[] headings = { "CompanyID", "Company Name", "Address", "City","State","Country","Pin","Phone","Email"}; 
   	
  	JScrollPane jp;
	JTable jt;
	prtsDb db_obj;
 	CompanySkillSetBean company_obj;
 	Container c = null;
 
  	public ReportCompanyDetails() 
	{    
		db_obj=new prtsDb();
		c = this.getContentPane();
		c.setLayout(new FlowLayout());
		ArrayList arraylist_company=db_obj.getCompanyRecords();
          		if(arraylist_company.size()>0)
		{
		
			String[][] data =new String[arraylist_company.size()][10];
        			for(int i=0;i<arraylist_company.size();i++)	
			{
				company_obj=(CompanySkillSetBean)arraylist_company.get(i);
				data[i][0]=String.valueOf(company_obj.getCompanyId());
				data[i][1]=company_obj.getCompanyName();
				data[i][2]=company_obj.getAddress();
				data[i][3]=company_obj.getCity();
				data[i][4]=company_obj.getState();
				data[i][5]=company_obj.getCountry();
				data[i][6]=company_obj.getPin();
				data[i][7]=company_obj.getPhone();
				data[i][8]=company_obj.getEmail();
		
			}	  
 			
			 jt = new JTable(data, headings); 
			jt.setPreferredScrollableViewportSize(new Dimension(1000,700)); 
			jp = new JScrollPane(jt); 
	       		c.add(jp); 
		}
		else{
			c.add(new JLabel("There are no records to display"));
		}

		setTitle("Company Records");  
		setSize(Toolkit.getDefaultToolkit().getScreenSize());   
        		setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);    
        		setVisible(true);    
  	}  
}