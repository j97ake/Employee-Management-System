import java.util.ArrayList;
import java.awt.*;
import javax.swing.*;
import java.awt.event.*;
import com.alpha.prts.*;

public class ReportProfessionalSkillSet extends JFrame
{
	String[] headings = { "Professional ID", "Operating System", "Technologies", "Scripting Languages","Databases","Frameworks","Testing Tool","Other Skills"}; 
   	
  	JScrollPane jp;
	JTable jt;
	prtsDb db_obj;
 	ProfessionalSkillSetBean skill_obj;
 	Container c = null;
 
  	public ReportProfessionalSkillSet() 
	{    
		db_obj=new prtsDb();
		c = this.getContentPane();
		c.setLayout(new FlowLayout());
		ArrayList arraylist_skill=db_obj.getProfessionalSkillSetRecords();
          		if(arraylist_skill.size()>0)
		{
		
			String[][] data =new String[arraylist_skill.size()][8];
        			for(int i=0;i<arraylist_skill.size();i++)	
			{
				skill_obj=(ProfessionalSkillSetBean)arraylist_skill.get(i);
				data[i][0]=String.valueOf(skill_obj.getProfId());
				data[i][1]=skill_obj.getOperatingSystem();
				data[i][2]=skill_obj.getTechnologies();
				data[i][3]=skill_obj.getScriptingLanguages();
				data[i][4]=skill_obj.getDatabases();
				data[i][5]=skill_obj.getFrameworks();
				data[i][6]=skill_obj.getTestingTools();
				data[i][7]=skill_obj.getOtherSkills();
			}	  
 			
			 jt = new JTable(data, headings); 
			jt.setPreferredScrollableViewportSize(new Dimension(1000,700)); 
			jp = new JScrollPane(jt); 
	       		c.add(jp); 
		}
		else{
			c.add(new JLabel("There are no records to display"));
		}

		setTitle("Professional's SkillSet Report");  
		setSize(Toolkit.getDefaultToolkit().getScreenSize());   
        		setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);    
        		setVisible(true);    
  	}  
}