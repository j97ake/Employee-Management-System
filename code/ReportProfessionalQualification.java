import java.util.ArrayList;
import java.awt.*;
import javax.swing.*;
import java.awt.event.*;
import com.alpha.prts.*;

public class ReportProfessionalQualification extends JFrame
{
	String[] headings = {"Professional Id" ,"Qualification ID", "Qualification Name","Branch","Year Of Passing","UniverSity/Board","College/Institute","Percentage Marks"}; 
   	
  	JScrollPane jp;
	JTable jt;
	prtsDb db_obj;
 	ProfessionalQualificationBean qualification_obj;
 	Container c = null;

	public ReportProfessionalQualification(){
		db_obj=new prtsDb();
		c=this.getContentPane();
		c.setLayout(new FlowLayout());
		ArrayList arraylist_qualification=db_obj.getAllProfessionalQualification();	
		if(arraylist_qualification.size()>0)
		{
		
			String[][] data =new String[arraylist_qualification.size()][8];
        			for(int i=0;i<arraylist_qualification.size();i++)	
			{
				qualification_obj=(ProfessionalQualificationBean)arraylist_qualification.get(i);
				data[i][0]=String.valueOf(qualification_obj.getProfId());
				data[i][1]=String.valueOf(qualification_obj.getQualificationId());
				data[i][2]=qualification_obj.getQualificationName();
				data[i][3]=qualification_obj.getBranch();
				data[i][4]=qualification_obj.getYearOfPassing();
				data[i][5]=qualification_obj.getUniversityBoard();
				data[i][6]=qualification_obj.getCollegeInstitute();
				data[i][7]=String.valueOf(qualification_obj.getPerMarks());
			}	  
 			
			 jt = new JTable(data, headings); 
			jt.setPreferredScrollableViewportSize(new Dimension(1000,700)); 
			jp = new JScrollPane(jt); 
	       		c.add(jp); 
		}


		setTitle("Professional's Qualification Records");  
		setSize(Toolkit.getDefaultToolkit().getScreenSize());   
        		setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);  
		setVisible(true);
	}  

  	public ReportProfessionalQualification(ArrayList arraylist_qualification) 
	{    
		db_obj=new prtsDb();
		c = this.getContentPane();
		c.setLayout(new FlowLayout());
		if(arraylist_qualification.size()>0)
		{
		
			String[][] data =new String[arraylist_qualification.size()][8];
        			for(int i=0;i<arraylist_qualification.size();i++)	
			{
				qualification_obj=(ProfessionalQualificationBean)arraylist_qualification.get(i);
				data[i][0]=String.valueOf(qualification_obj.getProfId());
				data[i][1]=String.valueOf(qualification_obj.getQualificationId());
				data[i][2]=qualification_obj.getQualificationName();
				data[i][3]=qualification_obj.getBranch();
				data[i][4]=qualification_obj.getYearOfPassing();
				data[i][5]=qualification_obj.getUniversityBoard();
				data[i][6]=qualification_obj.getCollegeInstitute();
				data[i][7]=String.valueOf(qualification_obj.getPerMarks());
			}	  
 			
			 jt = new JTable(data, headings); 
			jt.setPreferredScrollableViewportSize(new Dimension(1000,700)); 
			jp = new JScrollPane(jt); 
	       		c.add(jp); 
		}

		setTitle("Professional Personal Records");  
		setSize(Toolkit.getDefaultToolkit().getScreenSize());   
        		setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);    
        		setVisible(true);    
  	}

}