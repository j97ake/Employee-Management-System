import java.util.ArrayList;
import java.awt.*;
import javax.swing.*;
import java.awt.event.*;
import com.alpha.prts.*;

public class ReportProfessionalExperience extends JFrame
{
	String[] headings = {"Professional Id" ,"Experience ID", "Start Date","End Date","Company Name","Location","Designation","Job Profile","SalaryPackage"}; 
   	
  	JScrollPane jp;
	JTable jt;
	prtsDb db_obj;
 	ProfessionalExperienceBean experience_obj;
 	Container c = null;
 	public ReportProfessionalExperience(){
		db_obj=new prtsDb();
		c=this.getContentPane();
		c.setLayout(new FlowLayout());
		ArrayList arraylist_experience=db_obj.getAllProfessionalExperience();
          		if(arraylist_experience.size()>0)
		{
		
			String[][] data =new String[arraylist_experience.size()][9];
        			for(int i=0;i<arraylist_experience.size();i++)	
			{
				experience_obj=(ProfessionalExperienceBean)arraylist_experience.get(i);
				data[i][0]=String.valueOf(experience_obj.getProfId());
				data[i][1]=String.valueOf(experience_obj.getExperienceId());
				data[i][2]=experience_obj.getStartDate();
				data[i][3]=experience_obj.getEndDate();
				data[i][4]=experience_obj.getCompanyName();
				data[i][5]=experience_obj.getLocation();
				data[i][6]=experience_obj.getDesignation();
				data[i][7]=experience_obj.getJobProfile();
				data[i][8]=String.valueOf(experience_obj.getSalaryPackage());
			}	  
 			
			 jt = new JTable(data, headings); 
			jt.setPreferredScrollableViewportSize(new Dimension(1000,700)); 
			jp = new JScrollPane(jt); 
	       		c.add(jp); 
		}
		setTitle("Professional Experience Records");  
		setSize(Toolkit.getDefaultToolkit().getScreenSize());   
        		setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);  
		setVisible(true);
	}
  	public ReportProfessionalExperience(ArrayList arraylist_experience) 
	{    
		db_obj=new prtsDb();
		c = this.getContentPane();
		c.setLayout(new FlowLayout());
          		if(arraylist_experience.size()>0)
		{
		
			String[][] data =new String[arraylist_experience.size()][9];
        			for(int i=0;i<arraylist_experience.size();i++)	
			{
				experience_obj=(ProfessionalExperienceBean)arraylist_experience.get(i);
				data[i][0]=String.valueOf(experience_obj.getProfId());
				data[i][1]=String.valueOf(experience_obj.getExperienceId());
				data[i][2]=experience_obj.getStartDate();
				data[i][3]=experience_obj.getEndDate();
				data[i][4]=experience_obj.getCompanyName();
				data[i][5]=experience_obj.getLocation();
				data[i][6]=experience_obj.getDesignation();
				data[i][7]=experience_obj.getJobProfile();
				data[i][8]=String.valueOf(experience_obj.getSalaryPackage());
			}	  
 			
			 jt = new JTable(data, headings); 
			jt.setPreferredScrollableViewportSize(new Dimension(1000,700)); 
			jp = new JScrollPane(jt); 
	       		c.add(jp); 
		}
		else{
			c.add(new JLabel("No Records to display"));
		}

		setTitle("Professional Experience Records");  
		setSize(Toolkit.getDefaultToolkit().getScreenSize());   
        		setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);    
        		setVisible(true);    
  	}  
}