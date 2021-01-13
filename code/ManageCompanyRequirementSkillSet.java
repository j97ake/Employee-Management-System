import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import com.alpha.prts.*;
import java.util.ArrayList;
import java.sql.Date;
public class ManageCompanyRequirementSkillSet extends JPanel implements ActionListener
{
	JPanel panalCenter,panalSouth,panalEast,panel_heading;
	JTextField tf_companyId,tf_os,tf_technologies,tf_scriptLanguages,tf_databases,tf_frameworks,tf_testingTools,tf_otherSkills;
	JButton btn_first,btn_previous,btn_next,btn_last,btn_save,btn_view,btn_cancel,btn_add,btn_edit,btn_delete;
	JButton btn_nextRecord,btn_previousRecord;  //to navigate between professionals varius details	
	JLabel lbl_companyId,lbl_os,lbl_technologies,lbl_scriptLanguages,lbl_databases,lbl_frameworks,lbl_testingTools,lbl_heading,lblMessage,lbl_otherSkills;
	JLabel lbl_empty1,lbl_empty2,lbl_empty3,lbl_empty4,lbl_empty5,lbl_empty6;

	prtsDb db_obj=new prtsDb();
	int count=0;
	String editSaveFlag="none";
	
	public ManageCompanyRequirementSkillSet(){}
	public ManageCompanyRequirementSkillSet(int count,String type)
	{

		setLayout(new BorderLayout(5,5));
		setSize(800,500);
		panalCenter=new JPanel();
		panalSouth=new JPanel();
		panalEast=new JPanel();
		panel_heading=new JPanel();

		tf_companyId=new JTextField(10);
		tf_companyId.setEditable(false);
		tf_os=new JTextField(10);
		tf_technologies=new JTextField(10);		
		tf_scriptLanguages=new JTextField(10);
		tf_databases=new JTextField(10);
		tf_frameworks=new JTextField(10);
		tf_testingTools=new JTextField(10);
		tf_otherSkills=new JTextField(10);
		

		lbl_companyId=new JLabel("Company Id");
		lbl_os=new JLabel("Operating Systems");
		lbl_technologies=new JLabel("Technologies");
		lbl_scriptLanguages=new JLabel("Scripting Languages");
		lbl_databases=new JLabel("Databases");
		lbl_frameworks=new JLabel("Frameworks");
		lbl_testingTools=new JLabel("Testing Tools");
		lbl_otherSkills=new JLabel("Other Skills");

		lbl_empty1=new JLabel("");
		lbl_empty2=new JLabel("");


		lbl_heading=new JLabel("Company Skill Set Requirements");
		lbl_heading.setHorizontalAlignment(SwingConstants.CENTER);
		lbl_heading.setFont(new Font("Tahoma", 1, 18));

		lblMessage=new JLabel("");
		lblMessage.setHorizontalAlignment(SwingConstants.CENTER);
		lblMessage.setFont(new Font("Tahoma", 1, 11));
		lblMessage.setForeground(Color.red);
		


		btn_first=new JButton("FIRST");
		btn_previous=new JButton("PREVIOUS");
		btn_next=new JButton("NEXT ");
		btn_last=new JButton("LAST");

		btn_add=new JButton("ADD");
		btn_edit=new JButton("EDIT");
		btn_save=new JButton("SAVE & CONTINUE");
		btn_save.setEnabled(false);
		btn_delete=new JButton("DELETE");
		btn_view=new JButton("VIEW ALL");
		btn_cancel=new JButton("CANCEL");	
	
		btn_nextRecord=new JButton("NEXT RECORD");
		btn_previousRecord=new JButton("PREVIOUS RECORD");

		btn_first.addActionListener(this);
		btn_previous.addActionListener(this);
		btn_next.addActionListener(this);
		btn_last.addActionListener(this);
		btn_add.addActionListener(this);
		btn_edit.addActionListener(this);
		btn_save.addActionListener(this);
		btn_delete.addActionListener(this);
		btn_view.addActionListener(this);
		btn_cancel.addActionListener(this);
		btn_nextRecord.addActionListener(this);
		btn_previousRecord.addActionListener(this);
		
			
		
		panalCenter.setLayout(new GridLayout(15,2,5,5));
		panalSouth.setLayout(new GridLayout(1,4,5,5));
		panalEast.setLayout(new GridLayout(15,1,5,5));
		panel_heading.setLayout(new GridLayout(4,1));
		
		panel_heading.add(lbl_empty1);
		panel_heading.add(lbl_heading);
		panel_heading.add(lbl_empty2);
		panel_heading.add(lblMessage);
		
		
		panalCenter.add(lbl_companyId);
		panalCenter.add(tf_companyId);
		panalCenter.add(lbl_os);
		panalCenter.add(tf_os);
		panalCenter.add(lbl_technologies);
		panalCenter.add(tf_technologies);
		panalCenter.add(lbl_scriptLanguages);
		panalCenter.add(tf_scriptLanguages);
		panalCenter.add(lbl_databases);
		panalCenter.add(tf_databases);
		panalCenter.add(lbl_frameworks);
		panalCenter.add(tf_frameworks);
		panalCenter.add(lbl_testingTools);
		panalCenter.add(tf_testingTools);
		panalCenter.add(lbl_otherSkills);
		panalCenter.add(tf_otherSkills);


		panalSouth.add(btn_first);
		panalSouth.add(btn_previous);
		panalSouth.add(btn_next);
		panalSouth.add(btn_last);
	
		
		panalEast.add(btn_nextRecord);
		panalEast.add(btn_previousRecord);
		if(MainFrame.userStatus.equalsIgnoreCase("operator")){
			panalEast.add(btn_add);
		}
		panalEast.add(btn_edit);
		panalEast.add(btn_save);
		panalEast.add(btn_delete);
		panalEast.add(btn_view);
		panalEast.add(btn_cancel);

		
		add(panel_heading,BorderLayout.NORTH);
		add(panalCenter,BorderLayout.CENTER);
		add(panalSouth,BorderLayout.SOUTH);
		add(panalEast,BorderLayout.EAST);

		setVisible(true);
		if(type.equals("navigate")){
			this.count=count;
			MainFrame.staticArraylist=db_obj.getCompanyRecords();
			if(MainFrame.staticArraylist.size()>0){
				CompanySkillSetBean company_obj=(CompanySkillSetBean)MainFrame.staticArraylist.get(count);
				showRecords(company_obj);
			}
		}
		if(type.equals("save")) {
			MainFrame.staticArraylist=db_obj.getCompanyRecords();
			tf_companyId.setText(String.valueOf(count));
			btn_edit.setEnabled(false);
			btn_add.setEnabled(false);
			btn_nextRecord.setVisible(false);
			btn_previousRecord.setVisible(false);
			btn_save.setEnabled(true);
			editSaveFlag="save";
		}
	}
	public void actionPerformed(ActionEvent ae)
	{
	
		if(ae.getSource()==btn_add){
			clear();
			btn_save.setEnabled(true);
			editSaveFlag="save";

		}

		if(ae.getSource()==btn_edit){
			editSaveFlag="edit";
			lblMessage.setText("");
			btn_save.setEnabled(true);
		}

		if(ae.getSource()==btn_save){

			int flag=0;
			/*if(tf_testingTools.getText().equals("")){
				lblMessage.setText("Enter testing tools");
				flag=1;		
			}
			if(tf_frameworks.getText().equals("")){
				lblMessage.setText("Enter frameworks");								
				flag=1;				
			}
			if(tf_databases.getText().equals("")){
				lblMessage.setText("Enter database");								
				flag=1;				
			}

			if(tf_scriptLanguages.getText().equals("")){
				lblMessage.setText("Enter Scripting Languages");	
				flag=1;	
		  	}
			if(tf_technologies.getText().equals("")){
				lblMessage.setText("Enter technologies ");	
				flag=1;				
			}
			if(tf_os.getText().equals("")){
				lblMessage.setText("Enter operating system ");								
				flag=1;				
			}
			try{
				int pid= Integer.parseInt(tf_companyId.getText());
			}

			catch(IllegalArgumentException e)
	 		{
			  	lblMessage.setText("Enter number for company id");
				flag=1;	
		  	}
			*/

									
			if(editSaveFlag.equals("save") && flag==0){
				//String result=db_obj.saveCompanySkillSetRequirements(Integer.parseInt(tf_companyId.getText()),tf_os.getText(),tf_technologies.getText(),tf_scriptLanguages.getText(),tf_databases.getText(),tf_frameworks.getText(),tf_testingTools.getText(),tf_otherSkills.getText());
				String result=db_obj.updateCompanySkillSetRequirements(Integer.parseInt(tf_companyId.getText()),tf_os.getText(),tf_technologies.getText(),tf_scriptLanguages.getText(),tf_databases.getText(),tf_frameworks.getText(),tf_testingTools.getText(),tf_otherSkills.getText());
				if(result.equals("failed")){
					lblMessage.setText("Record not saved");					
				}
				else if(result.equals("updated")){
				                  Component[] c=MainFrame.container.getComponents();
        					  for(int i=0;i<c.length;i++)
					  {
					               	  MainFrame.container.remove(c[i]);
					  }
					MainFrame.container.add(new ManageCompanyDetail(0,"navigate"));
					MainFrame.container.setVisible(false);
					MainFrame.container.setVisible(true);
				}
			}
			if(editSaveFlag.equals("edit") && flag==0){
				String result=db_obj.updateCompanySkillSetRequirements(Integer.parseInt(tf_companyId.getText()),tf_os.getText(),tf_technologies.getText(),tf_scriptLanguages.getText(),tf_databases.getText(),tf_frameworks.getText(),tf_testingTools.getText(),tf_otherSkills.getText());
				if(result.equals("updated")){
				                  Component[] c=MainFrame.container.getComponents();
        					  for(int i=0;i<c.length;i++)
					  {
					               	  MainFrame.container.remove(c[i]);
					  }
					MainFrame.container.add(new ManageCompanyDetail(count,"navigate"));
					MainFrame.container.setVisible(false);
					MainFrame.container.setVisible(true);	
				}
				else{
					lblMessage.setText("Record not updated");
				}
			}
		}

		if(ae.getSource()==btn_first){
			count=0;
			if(MainFrame.staticArraylist.size()>0)
			{
				CompanySkillSetBean company_obj=(CompanySkillSetBean)MainFrame.staticArraylist.get(count);
				showRecords(company_obj);
			}
		}

		if(ae.getSource()==btn_previous){
			if(count==0)
			{
			}
			else
			{
				count--;
			}	
			if(MainFrame.staticArraylist.size()>0)
			{
				CompanySkillSetBean company_obj=(CompanySkillSetBean)MainFrame.staticArraylist.get(count);
				showRecords(company_obj);
			}
		}
		
		if(ae.getSource()==btn_next){
			if(MainFrame.staticArraylist.size()>0)
			{
				if(count==(MainFrame.staticArraylist.size()-1))
				{}
				else
				{
					count++;
				}
				CompanySkillSetBean company_obj=(CompanySkillSetBean)MainFrame.staticArraylist.get(count);
				showRecords(company_obj);
			}			
		}
		
		if(ae.getSource()==btn_last){
			count=MainFrame.staticArraylist.size()-1;
			if(MainFrame.staticArraylist.size()>0)
			{
				CompanySkillSetBean company_obj=(CompanySkillSetBean)MainFrame.staticArraylist.get(count);
				showRecords(company_obj);
			}
		}


		if(ae.getSource()==btn_cancel){	
		                  Component[] c=MainFrame.container.getComponents();
        			  for(int i=0;i<c.length;i++)
			  {   
		                	  MainFrame.container.remove(c[i]);
			  }
				MainFrame.container.setVisible(false);
		}
		if(ae.getSource()==btn_view){
			ReportCompanySkillSetRequirements obj=new ReportCompanySkillSetRequirements();
		
		}
	
		if(ae.getSource()==btn_nextRecord){
		
		                  Component[] c=MainFrame.container.getComponents();
        			  for(int i=0;i<c.length;i++)
			  {
       			           
		                	  MainFrame.container.remove(c[i]);
			  }
				MainFrame.container.add(new ManageCompanyDetail(count,"navigate"));
				MainFrame.container.setVisible(false);
				MainFrame.container.setVisible(true);
		}
		if(ae.getSource()==btn_previousRecord){
		                  Component[] c=MainFrame.container.getComponents();
        			  for(int i=0;i<c.length;i++)
			  {
       			               	  MainFrame.container.remove(c[i]);
			  }
				MainFrame.container.add(new ManageCompanyRequirement(count,"navigate"));
				MainFrame.container.setVisible(false);
				MainFrame.container.setVisible(true);
		}
		if(ae.getSource()==btn_delete){
			String result=db_obj.deleteCompany(Integer.parseInt(tf_companyId.getText()));
			MainFrame.staticArraylist=db_obj.getCompanyRecords();
			if(result.equals("deleted")){
				count=0;
				if(MainFrame.staticArraylist.size()>0)
				{
					CompanySkillSetBean company_obj=(CompanySkillSetBean)MainFrame.staticArraylist.get(count);
					showRecords(company_obj);
				}
			}
			else{	
				lblMessage.setText("Unable to delete");				
			}			
			
		}


	}
	
	public void clear(){
		//tf_companyId.setText("");
		tf_os.setText("");
		tf_technologies.setText("");
		tf_scriptLanguages.setText("");
		tf_databases.setText("");
		tf_frameworks.setText("");
		tf_testingTools.setText("");
		tf_otherSkills.setText("");
	}

	public void showRecords(CompanySkillSetBean company_obj){
		tf_companyId.setText(String.valueOf(company_obj.getCompanyId()));
		tf_os.setText(company_obj.getOperatingSystem());
		tf_technologies.setText(company_obj.getTechnologies());
		tf_scriptLanguages.setText(company_obj.getScriptingLanguages());
		tf_databases.setText(company_obj.getDatabases());
		tf_frameworks.setText(company_obj.getFrameworks());
		tf_testingTools.setText(company_obj.getTestingTools());
		tf_otherSkills.setText(company_obj.getOtherSkills());

		lblMessage.setText("");
		btn_add.setEnabled(true);
		btn_edit.setEnabled(true);
		btn_nextRecord.setVisible(true);
		btn_previousRecord.setVisible(true);
		btn_save.setEnabled(false);
		editSaveFlag="";
	}
	
}