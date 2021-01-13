import java.util.ArrayList;
import java.awt.*;
import javax.swing.*;
import java.awt.event.*;
import com.alpha.prts.*;

public class ReportProfessionalProjects extends JFrame
{
	String[] headings = {"Professional Id" ,"Experience ID","Project Id","Project Name","Start Date","End Date","Technologies Used","Project Description"}; 
   	
  	JScrollPane jp;
	JTable jt;
	prtsDb db_obj;
 	ProjectDetailsBean project_obj;
 	Container c = null;
 
	public ReportProfessionalProjects(){
		db_obj=new prtsDb();
		c=this.getContentPane();
		c.setLayout(new FlowLayout());
		ArrayList arraylist_project=db_obj.getAllProfessionalProjects();
          		if(arraylist_project.size()>0)
		{
		
			String[][] data =new String[arraylist_project.size()][8];
        			for(int i=0;i<arraylist_project.size();i++)	
			{
				project_obj=(ProjectDetailsBean)arraylist_project.get(i);
				data[i][0]=String.valueOf(project_obj.getProfId());
				data[i][1]=String.valueOf(project_obj.getExperienceId());
				data[i][2]=String.valueOf(project_obj.getProjectId());
				data[i][3]=project_obj.getProjectName();
				data[i][4]=project_obj.getProjectStartDate();
				data[i][5]=project_obj.getProjectEndDate();
				data[i][6]=project_obj.getTechnologiesLanguages();
				data[i][7]=project_obj.getProjectDescription();
			}	  
 			
			 jt = new JTable(data, headings); 
			jt.setPreferredScrollableViewportSize(new Dimension(1000,700)); 
			jp = new JScrollPane(jt); 
	       		c.add(jp); 
		}
	
		setTitle("Professional's Project Records");  
		setSize(Toolkit.getDefaultToolkit().getScreenSize());   
        		setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);  
		setVisible(true);
	}
  	public ReportProfessionalProjects(ArrayList arraylist_project) 
	{    
		db_obj=new prtsDb();
		c = this.getContentPane();
		c.setLayout(new FlowLayout());
          		if(arraylist_project.size()>0)
		{
		
			String[][] data =new String[arraylist_project.size()][8];
        			for(int i=0;i<arraylist_project.size();i++)	
			{
				project_obj=(ProjectDetailsBean)arraylist_project.get(i);
				data[i][0]=String.valueOf(project_obj.getProfId());
				data[i][1]=String.valueOf(project_obj.getExperienceId());
				data[i][2]=String.valueOf(project_obj.getProjectId());
				data[i][3]=project_obj.getProjectName();
				data[i][4]=project_obj.getProjectStartDate();
				data[i][5]=project_obj.getProjectEndDate();
				data[i][6]=project_obj.getTechnologiesLanguages();
				data[i][7]=project_obj.getProjectDescription();
			}	  
 			
			 jt = new JTable(data, headings); 
			jt.setPreferredScrollableViewportSize(new Dimension(1000,700)); 
			jp = new JScrollPane(jt); 
	       		c.add(jp); 
		}
		else{
			c.add(new JLabel("No Records to display"));
		}

		setTitle("Professional's Project Records");  
		setSize(Toolkit.getDefaultToolkit().getScreenSize());   
        		setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);    
        		setVisible(true);    
  	}  
}