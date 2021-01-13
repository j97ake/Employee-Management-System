import java.awt.event.*;
import java.awt.*;
import javax.swing.*;
import java.util.ArrayList;
public class MainFrame extends JFrame implements ActionListener
{
	static Container container;
	static ArrayList staticArraylist;
	static String userStatus="";
	JMenu menu_file,menu_employee,menu_professional,menu_company,menu_search,menu_reports,menu_reportCompany,menu_reportProfessional;
	JMenuItem mi_exit,mi_employee,mi_professional,mi_company,mi_searchCompany,mi_searchProfessional;
	JMenuItem mi_reportEmployee,mi_reportProPersonal,mi_reportProQualification,mi_reportProSkill,mi_reportProExperience,mi_reportProProject,mi_reportComDetail,mi_reportComRequirement,mi_reportComSkillRequirement;
	JMenuBar mb;
	public void init(String userStatus)
	{
		this.userStatus=userStatus;
		container=getContentPane();
		setSize(Toolkit.getDefaultToolkit().getScreenSize());
		//setSize(800,600);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
			
		mb=new JMenuBar();

		menu_file=new JMenu("File");
		mi_exit=new JMenuItem("Exit");
		mi_exit.addActionListener(this);
		menu_file.add(mi_exit);

		menu_employee=new JMenu("Employee_Maintainence");
		mi_employee=new JMenuItem("Employee");
		mi_employee.addActionListener(this);
		menu_employee.add(mi_employee);
		
		menu_professional=new JMenu("Professional_Maintainence");
		mi_professional=new JMenuItem("Professional");
		mi_professional.addActionListener(this);
		menu_professional.add(mi_professional);

		menu_company=new JMenu("Company_Maintainence");
		mi_company=new JMenuItem("Company");
		mi_company.addActionListener(this);
		menu_company.add(mi_company);

		menu_search=new JMenu("Search");
		mi_searchCompany=new JMenuItem("Search Company");
		mi_searchCompany.addActionListener(this);
		mi_searchProfessional=new JMenuItem("Search Professional");
		mi_searchProfessional.addActionListener(this);
		menu_search.add(mi_searchCompany);
		menu_search.add(mi_searchProfessional);
	
		mi_reportEmployee=new JMenuItem("Employee Report");
		mi_reportEmployee.addActionListener(this);
		
		menu_reportProfessional=new JMenu("Professional's Report");

		mi_reportProPersonal=new JMenuItem("Personal");
		mi_reportProPersonal.addActionListener(this);
		mi_reportProQualification=new JMenuItem("Qualification");
		mi_reportProQualification.addActionListener(this);

		mi_reportProSkill=new JMenuItem("Skill Set");
		mi_reportProSkill.addActionListener(this);

		mi_reportProExperience=new JMenuItem("Experience");
		mi_reportProExperience.addActionListener(this);

		mi_reportProProject=new JMenuItem("Projects");
		mi_reportProProject.addActionListener(this);
		
		menu_reportProfessional.add(mi_reportProPersonal);
		menu_reportProfessional.add(mi_reportProQualification);
		menu_reportProfessional.add(mi_reportProSkill);
		menu_reportProfessional.add(mi_reportProExperience);
		menu_reportProfessional.add(mi_reportProProject);


		menu_reportCompany=new JMenu("Company Reports");
		
		mi_reportComDetail=new JMenuItem("Company Details");
		mi_reportComDetail.addActionListener(this);

		mi_reportComRequirement=new JMenuItem("Company Requirement");
		mi_reportComRequirement.addActionListener(this);

		mi_reportComSkillRequirement=new JMenuItem("Company Skill Set Requirement");
		mi_reportComSkillRequirement.addActionListener(this);

		menu_reportCompany.add(mi_reportComDetail);
		menu_reportCompany.add(mi_reportComRequirement);
		menu_reportCompany.add(mi_reportComSkillRequirement);

		menu_reports=new  JMenu("Reports");
		menu_reports.add(mi_reportEmployee);
		menu_reports.add(menu_reportProfessional);
		menu_reports.add(menu_reportCompany);

		mb.add(menu_file);
		if(userStatus.equalsIgnoreCase("admin")){
			mb.add(menu_employee);
		}
		mb.add(menu_professional);
		mb.add(menu_company);
		if(userStatus.equalsIgnoreCase("admin")||userStatus.equalsIgnoreCase("manager")){
				mb.add(menu_search);
				mb.add(menu_reports);
		}

		setJMenuBar(mb);
		setTitle("Professional Recruitment Tracking System");
		setVisible(true);
		setResizable(false);


ImageIcon c1 = new ImageIcon("i1.jpg");
                JLabel r1 = new JLabel(c1);
add(r1,BorderLayout.CENTER);

		
	}
	public void actionPerformed(ActionEvent ae)
	{
		if(ae.getSource()==mi_exit)
		{
			System.exit(0);
		}
		if(ae.getSource()==mi_employee)
		{
	
	                  Component[] c=container.getComponents();
        		  for(int i=0;i<c.length;i++)
		  {
       			           
		                  container.remove(c[i]);
		  }
			container.add(new ManageEmployeePanel());
			container.setVisible(false);
			container.setVisible(true);	
		
		}
		if(ae.getSource()==mi_professional)
		{
		  
		                  Component[] c=container.getComponents();
        			  for(int i=0;i<c.length;i++)
			  {
       			           
		                	  container.remove(c[i]);
			  }
				container.add(new ManageProPersonal(0,"navigate"));
				container.setVisible(false);
				container.setVisible(true);
		}
		if(ae.getSource()==mi_company)
		{
			container=getContentPane();
	       		Component[] c=container.getComponents();
        			for(int i=0;i<c.length;i++)
		  	{
       				 
		                container.remove(c[i]);
		  	}
			container.add(new ManageCompanyDetail(0,"navigate"));
			container.setVisible(false);
			container.setVisible(true);
			
		}	
	
		if(ae.getSource()==mi_searchCompany){
			container=getContentPane();
			Component[] c=container.getComponents();
			for(int i=0;i<c.length;i++){
				container.remove(c[i]);
			}
			container.add(new SearchCompany());		
			container.setVisible(false);
			container.setVisible(true);
		}

		if(ae.getSource()==mi_searchProfessional){
			container=getContentPane();
			Component[] c=container.getComponents();
			for(int i=0;i<c.length;i++){
				container.remove(c[i]);
			}
			container.add(new SearchProfessional());		
			container.setVisible(false);
			container.setVisible(true);
		}

		if(ae.getSource()==mi_reportEmployee){
			new ReportEmployeeDetails();
		}
		if(ae.getSource()==mi_reportProPersonal){
			new ReportProPersonalDetails(); 
		}
		if(ae.getSource()==mi_reportProQualification){
			new ReportProfessionalQualification();
		}
		if(ae.getSource()==mi_reportProSkill){
			new ReportProfessionalSkillSet();
		}
		if(ae.getSource()==mi_reportProExperience){
			new ReportProfessionalExperience();
		}
		if(ae.getSource()==mi_reportProProject){
			new ReportProfessionalProjects();
		}
		if(ae.getSource()==mi_reportComDetail){
			new ReportCompanyDetails();
		}
		if(ae.getSource()==mi_reportComRequirement){
			new ReportCompanyRequirements();
		}
		if(ae.getSource()==mi_reportComSkillRequirement){
			new ReportCompanySkillSetRequirements();
		}
		
	}
	public static void main(String args[])
	{
		MainFrame obj=new MainFrame();
		obj.init(" ");
	}

}		