import java.util.ArrayList;
import java.awt.*;
import javax.swing.*;
import java.awt.event.*;
import com.alpha.prts.*;

public class ReportCompanyRequirements extends JFrame
{
	String[] headings = { "CompanyID", "Experience(years)", "Salary Offered", "Qualifications"}; 
   	
  	JScrollPane jp;
	JTable jt;
	prtsDb db_obj;
 	CompanySkillSetBean company_obj;
 	Container c = null;
 
  	public ReportCompanyRequirements() 
	{    
		db_obj=new prtsDb();
		c = this.getContentPane();
		c.setLayout(new FlowLayout());
		ArrayList arraylist_company=db_obj.getCompanyRecords();
          		if(arraylist_company.size()>0)
		{
		
			String[][] data =new String[arraylist_company.size()][4];
        			for(int i=0;i<arraylist_company.size();i++)	
			{
				company_obj=(CompanySkillSetBean)arraylist_company.get(i);
				data[i][0]=String.valueOf(company_obj.getCompanyId());
				data[i][1]=String.valueOf(company_obj.getExperience());
				data[i][2]=String.valueOf(company_obj.getSalaryOffered());
				data[i][3]=company_obj.getQualification();

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