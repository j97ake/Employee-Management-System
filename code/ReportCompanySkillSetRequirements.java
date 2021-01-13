import java.util.ArrayList;
import java.awt.*;
import javax.swing.*;
import java.awt.event.*;
import com.alpha.prts.*;

public class ReportCompanySkillSetRequirements extends JFrame
{
	String[] headings = { "CompanyID", "Operating System", "Technologies", "Scripting Languages","Databases","Frameworks","Testing Tool","Other Skills"}; 
   	
  	JScrollPane jp;
	JTable jt;
	prtsDb db_obj;
 	CompanySkillSetBean company_obj;
 	Container c = null;
 
  	public ReportCompanySkillSetRequirements() 
	{    
		db_obj=new prtsDb();
		c = this.getContentPane();
		c.setLayout(new FlowLayout());
		ArrayList arraylist_company=db_obj.getCompanyRecords();
          		if(arraylist_company.size()>0)
		{
		
			String[][] data =new String[arraylist_company.size()][8];
        			for(int i=0;i<arraylist_company.size();i++)	
			{
				company_obj=(CompanySkillSetBean)arraylist_company.get(i);
				data[i][0]=String.valueOf(company_obj.getCompanyId());
				data[i][1]=company_obj.getOperatingSystem();
				data[i][2]=company_obj.getTechnologies();
				data[i][3]=company_obj.getScriptingLanguages();
				data[i][4]=company_obj.getDatabases();
				data[i][5]=company_obj.getFrameworks();
				data[i][6]=company_obj.getTestingTools();
				data[i][7]=company_obj.getOtherSkills();
			}	  
 			
			 jt = new JTable(data, headings); 
			jt.setPreferredScrollableViewportSize(new Dimension(1000,700)); 
			jp = new JScrollPane(jt); 
	       		c.add(jp); 
		}
		else{
			c.add(new JLabel("There are no records to display"));
		}

		setTitle("Company SkillSet Requirement Report");  
		setSize(Toolkit.getDefaultToolkit().getScreenSize());   
        		setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);    
        		setVisible(true);    
  	}  
}