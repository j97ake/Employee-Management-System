import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import com.alpha.prts.*;
import java.util.ArrayList;
import java.sql.Date;
class ManageCompanyRequirement extends JPanel implements ActionListener
{
	JPanel panalCenter,panalSouth,panalEast,panel_heading;
	JTextField tf_companyId,tf_experience,tf_salaryOffered,tf_qualification;
	
	JButton btn_first,btn_previous,btn_next,btn_last,btn_save,btn_view,btn_cancel,btn_add,btn_edit,btn_delete;
	JButton btn_nextRecord,btn_previousRecord;  //to navigate between companys  varius details	
	JLabel lbl_companyId,lbl_experience,lbl_salaryOffered,lbl_qualification,lblMessage,lbl_heading;
	JLabel lbl_empty1,lbl_empty2,lbl_empty3,lbl_empty4,lbl_empty5,lbl_empty6,lbl_empty7,lbl_empty8,lbl_empty9,lbl_empty10;

	prtsDb db_obj=new prtsDb();
	int count=0;
	String editSaveFlag="none";
	
	public ManageCompanyRequirement(){}
	public ManageCompanyRequirement(int count,String type)
	{
			//count has record number when type is navigate
			//count has companyid when type is save

		setLayout(new BorderLayout(5,5));
		setSize(800,500);
		panalCenter=new JPanel();
		panalSouth=new JPanel();
		panalEast=new JPanel();
		panel_heading=new JPanel();

		tf_companyId=new JTextField(10);
		tf_companyId.setEditable(false);
		tf_experience=new JTextField(10);
		tf_salaryOffered=new JTextField(10);		
		tf_qualification=new JTextField(10);
		

		lbl_companyId=new JLabel("Company Id");
		lbl_experience=new JLabel("Experience(years)");
		lbl_salaryOffered=new JLabel("Salary Offered");
		lbl_qualification=new JLabel("Qualification");
		
		lbl_empty1=new JLabel("");
		lbl_empty2=new JLabel("");
		lbl_empty3=new JLabel("");
		lbl_empty4=new JLabel("");
		lbl_empty5=new JLabel("");
		lbl_empty6=new JLabel("");
		lbl_empty7=new JLabel("");
		lbl_empty8=new JLabel("");
		lbl_empty9=new JLabel("");
		lbl_empty10=new JLabel("");


		lbl_heading=new JLabel("Company Requirements");
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
		panalSouth.setLayout(new GridLayout(2,4,5,5));
		panalEast.setLayout(new GridLayout(15,1,5,5));
		panel_heading.setLayout(new GridLayout(4,1));
		
		panel_heading.add(lbl_empty1);
		panel_heading.add(lbl_heading);
		panel_heading.add(lbl_empty2);
		panel_heading.add(lblMessage);
		
		
		panalCenter.add(lbl_companyId);
		panalCenter.add(tf_companyId);
		panalCenter.add(lbl_experience);
		panalCenter.add(tf_experience);
		panalCenter.add(lbl_salaryOffered);
		panalCenter.add(tf_salaryOffered);
		panalCenter.add(lbl_qualification);
		panalCenter.add(tf_qualification);
		panalCenter.add(lbl_empty3);
		panalCenter.add(lbl_empty4);
		panalCenter.add(lbl_empty5);
		panalCenter.add(lbl_empty6);
		panalCenter.add(lbl_empty7);
		panalCenter.add(lbl_empty8);
		panalCenter.add(lbl_empty9);
		panalCenter.add(lbl_empty10);


		if(MainFrame.userStatus.equalsIgnoreCase("operator")){
			panalSouth.add(btn_add);
		}
		else{
			panalSouth.add(new JLabel(""));
		}
		panalSouth.add(btn_edit);
		panalSouth.add(btn_save);
		panalSouth.add(btn_delete);
		panalSouth.add(btn_first);
		panalSouth.add(btn_previous);
		panalSouth.add(btn_next);
		panalSouth.add(btn_last);

		
		panalEast.add(btn_nextRecord);
		panalEast.add(btn_previousRecord);
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
			int companyId;

			if(tf_qualification.getText().equals("")){
				lblMessage.setText("Enter qualifications");
				flag=1;
			}
			try{
				Double salaryOffered=Double.parseDouble(tf_salaryOffered.getText());
			}
			catch(NumberFormatException e){
				lblMessage.setText("Enter number for salary");
				flag=1;
			}
			try{
				int experience=Integer.parseInt(tf_experience.getText());
			}
			catch(NumberFormatException e){
				lblMessage.setText("Enter number for experience");
				flag=1;
			}
			try{
				companyId=Integer.parseInt(tf_companyId.getText());
			}
			catch(NumberFormatException e){
				lblMessage.setText("Enter number for companyId");
				flag=1;
			}
						
			 if(editSaveFlag.equals("save") && flag==0){
					companyId=Integer.parseInt(tf_companyId.getText());
					//String result=db_obj.saveCompanyRequirements(Integer.parseInt(tf_experience.getText()),Double.parseDouble(tf_salaryOffered.getText()),tf_qualification.getText());
					String result=db_obj.updateCompanyRequirements(Integer.parseInt(tf_companyId.getText()),Integer.parseInt(tf_experience.getText()),Double.parseDouble(tf_salaryOffered.getText()),tf_qualification.getText());
					if(result.equals("failed")){
						lblMessage.setText("Record not saved");					
					}
					else if(result.equals("updated")){
					                  Component[] c=MainFrame.container.getComponents();
			        			  for(int i=0;i<c.length;i++)
						  {
			       			                MainFrame.container.remove(c[i]);
						  }
							MainFrame.container.add(new ManageCompanyRequirementSkillSet(companyId,"save"));
							MainFrame.container.setVisible(false);
							MainFrame.container.setVisible(true);
					}
			}  
			if(editSaveFlag.equals("edit") && flag==0){
					String result=db_obj.updateCompanyRequirements(Integer.parseInt(tf_companyId.getText()),Integer.parseInt(tf_experience.getText()),Double.parseDouble(tf_salaryOffered.getText()),tf_qualification.getText());
					if(result.equals("updated")){
					                  Component[] c=MainFrame.container.getComponents();
			        			  for(int i=0;i<c.length;i++)
						  {
			       			                MainFrame.container.remove(c[i]);
						  }
							MainFrame.container.add(new ManageCompanyRequirementSkillSet(count,"navigate"));
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
			ReportCompanyRequirements obj=new ReportCompanyRequirements();
		
		}
		if(ae.getSource()==btn_nextRecord){
		
		                  Component[] c=MainFrame.container.getComponents();
        			  for(int i=0;i<c.length;i++)
			  {
       			           
		                	  MainFrame.container.remove(c[i]);
			  }
				MainFrame.container.add(new ManageCompanyRequirementSkillSet(count,"navigate"));
				MainFrame.container.setVisible(false);
				MainFrame.container.setVisible(true);
		}
		if(ae.getSource()==btn_previousRecord){
		                  Component[] c=MainFrame.container.getComponents();
        			  for(int i=0;i<c.length;i++)
			  {
       			           
		                	  MainFrame.container.remove(c[i]);
			  }
				MainFrame.container.add(new ManageCompanyDetail(count,"navigate"));
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
		tf_experience.setText("");
		tf_salaryOffered.setText("");
		tf_qualification.setText("");
	}
	
	public void showRecords(CompanySkillSetBean company_obj){
		tf_companyId.setText(String.valueOf(company_obj.getCompanyId()));
		tf_experience.setText(String.valueOf(company_obj.getExperience()));
		tf_salaryOffered.setText(String.valueOf(company_obj.getSalaryOffered()));
		tf_qualification.setText(company_obj.getQualification());
		

		lblMessage.setText("");
		btn_add.setEnabled(true);
		btn_edit.setEnabled(true);
		btn_nextRecord.setVisible(true);
		btn_previousRecord.setVisible(true);
		btn_save.setEnabled(false);
		editSaveFlag="";
	}
	
	
}