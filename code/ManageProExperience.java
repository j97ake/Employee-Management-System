import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import com.alpha.prts.*;
import java.util.ArrayList;
import java.sql.Date;
public class ManageProExperience extends JPanel implements ActionListener
{
	JPanel panalCenter,panalSouth,panalEast,panel_heading;
	JTextField tf_proId,tf_startDate,tf_endDate,tf_companyName,tf_location,tf_designation,tf_jobProfile,tf_salaryPackage;
	JTextField tf_experienceId;
	JButton btn_first,btn_previous,btn_next,btn_last,btn_save,btn_view,btn_cancel,btn_add,btn_edit,btn_delete;
	JButton btn_nextRecord,btn_previousRecord;  //to navigate between professionals varius details	
	JButton btn_addExperience;
	JLabel lblProfId,lbl_experienceId,lbl_startDate,lbl_endDate,lbl_companyName,lbl_location,lbl_designation,lbl_jobProfile,lbl_heading,lblMessage,lbl_salaryPackage;
	JLabel lbl_empty1,lbl_empty2,lbl_empty3,lbl_empty4,lbl_empty5,lbl_empty6;
	
	

	prtsDb db_obj=new prtsDb();
	int count=0;
	int localCount=0;
	String editSaveFlag="none";
	
	
	public ManageProExperience(int count,String type)
	{
		setLayout(new BorderLayout(5,5));
		setSize(800,500);
		panalCenter=new JPanel();
		panalSouth=new JPanel();
		panalEast=new JPanel();
		panel_heading=new JPanel();

		tf_experienceId=new JTextField(10);
		tf_experienceId.setEditable(false);
		tf_proId=new JTextField(10);
		tf_proId.setEditable(false);
		tf_startDate=new JTextField(10);
		tf_endDate=new JTextField(10);		
		tf_companyName=new JTextField(10);
		tf_location=new JTextField(10);
		tf_designation=new JTextField(10);
		tf_jobProfile=new JTextField(10);
		tf_salaryPackage=new JTextField(10);
		
		lbl_experienceId=new JLabel("Experience Id");
		lblProfId=new JLabel("Professional Id");
		lbl_startDate=new JLabel("Start Date");
		lbl_endDate=new JLabel("End Date");
		lbl_companyName=new JLabel("Company Name");
		lbl_location=new JLabel("Location");
		lbl_designation=new JLabel("Designation");
		lbl_jobProfile=new JLabel("Job Profile");
		lbl_salaryPackage=new JLabel("Salary Package");

		lbl_empty1=new JLabel("");
		lbl_empty2=new JLabel("");


		lbl_heading=new JLabel("Professional Experience Record");
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

		btn_add=new JButton("ADD EXPERIENCE");
		btn_edit=new JButton("EDIT");
		btn_save=new JButton("SAVE");
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

		btn_addExperience=new JButton("Add Experience");
		
		panalCenter.setLayout(new GridLayout(15,2,5,5));
		panalSouth.setLayout(new GridLayout(1,4,5,5));
		panalEast.setLayout(new GridLayout(15,1,5,5));
		panel_heading.setLayout(new GridLayout(4,1));
		
		panel_heading.add(lbl_empty1);
		panel_heading.add(lbl_heading);
		panel_heading.add(lbl_empty2);
		panel_heading.add(lblMessage);
		
		panalCenter.add(lbl_experienceId);
		panalCenter.add(tf_experienceId);		
		panalCenter.add(lblProfId);
		panalCenter.add(tf_proId);
		panalCenter.add(lbl_startDate);
		panalCenter.add(tf_startDate);
		panalCenter.add(lbl_endDate);
		panalCenter.add(tf_endDate);
		panalCenter.add(lbl_companyName);
		panalCenter.add(tf_companyName);
		panalCenter.add(lbl_location);
		panalCenter.add(tf_location);
		panalCenter.add(lbl_designation);
		panalCenter.add(tf_designation);
		panalCenter.add(lbl_jobProfile);
		panalCenter.add(tf_jobProfile);
		panalCenter.add(lbl_salaryPackage);
		panalCenter.add(tf_salaryPackage);


		panalSouth.add(btn_first);
		panalSouth.add(btn_previous);
		panalSouth.add(btn_next);
		panalSouth.add(btn_last);
		
		panalEast.add(btn_nextRecord);
		panalEast.add(btn_previousRecord);
		//panalEast.add(btn_addExperience);
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
		}


		MainFrame.staticArraylist=db_obj.getProfessionalContainerDetails();
		
		if(MainFrame.staticArraylist.size()>0){
			ProfessionalContainerBean pro_container=(ProfessionalContainerBean)MainFrame.staticArraylist.get(count);
			ArrayList al_experience=pro_container.getExperience();
			if(al_experience.size()>0){
				ProfessionalExperienceBean pro_obj=(ProfessionalExperienceBean)al_experience.get(localCount);
				showRecords(pro_obj);
			}
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
			try{	
				Double per=Double.parseDouble(tf_salaryPackage.getText());
			}
			catch(NumberFormatException e){
				lblMessage.setText("Enter number for  salary");
				flag=1;
			}
			if(tf_jobProfile.getText().equals("")){
				lblMessage.setText("Enter Job Profile ");								
				flag=1;				
			}
			if(tf_designation.getText().equals("")){
				lblMessage.setText("Enter designtion ");								
				flag=1;				
			}
			if(tf_location.getText().equals("")){
				lblMessage.setText("Enter location name");								
				flag=1;				
			}

			if(tf_companyName.getText().equals("")){
				lblMessage.setText("Enter company  name");
				flag=1;				
			}
			try{
				String end= tf_endDate.getText();
         				Date dd= new Date(200);
		 		dd=Date.valueOf(end);
			}
			catch(IllegalArgumentException e)
	 		{
			  	lblMessage.setText("End Date Format is (yyyy-mm-dd) ");
				flag=1;	
		  	}

			try{
				String start= tf_startDate.getText();
         				Date dd= new Date(200);
		 		dd=Date.valueOf(start);
			}
			catch(IllegalArgumentException e)
	 		{
			  	lblMessage.setText("Start Date Format is (yyyy-mm-dd) ");
				flag=1;	
		  	}
									
			if(editSaveFlag.equals("save") && flag==0){
				System.out.println("here");
					String result=db_obj.saveProExperienceDetails(Integer.parseInt(tf_proId.getText()),tf_startDate.getText(),tf_endDate.getText(),tf_companyName.getText(),tf_location.getText(),tf_designation.getText(),tf_jobProfile.getText(),Double.parseDouble(tf_salaryPackage.getText()));
					if(result.equals("failed")){
						lblMessage.setText("Record not saved");					
					}
					else if(result.equals("saved")){
							lblMessage.setText("Record added successfully");
							MainFrame.staticArraylist=db_obj.getProfessionalContainerDetails();
							localCount=0;
							if(MainFrame.staticArraylist.size()>0){
								ProfessionalContainerBean pro_container=(ProfessionalContainerBean)MainFrame.staticArraylist.get(count);
								ArrayList al_experience=pro_container.getExperience();
								if(al_experience.size()>0){
									ProfessionalExperienceBean pro_obj=(ProfessionalExperienceBean)al_experience.get(localCount);
									showRecords(pro_obj);
								}
							}
							btn_save.setEnabled(false);
					}
			}
			if(editSaveFlag.equals("edit") && flag==0){
					String result=db_obj.updateProExperienceDetails(Integer.parseInt(tf_experienceId.getText()),Integer.parseInt(tf_proId.getText()),tf_startDate.getText(),tf_endDate.getText(),tf_companyName.getText(),tf_location.getText(),tf_designation.getText(),tf_jobProfile.getText(),Double.parseDouble(tf_salaryPackage.getText()));
					if(result.equals("updated")){
						MainFrame.staticArraylist=db_obj.getProfessionalContainerDetails();
						if(MainFrame.staticArraylist.size()>0){
							ProfessionalContainerBean pro_container=(ProfessionalContainerBean)MainFrame.staticArraylist.get(count);
							ArrayList al_experience=pro_container.getExperience();
							if(al_experience.size()>0){
								ProfessionalExperienceBean pro_obj=(ProfessionalExperienceBean)al_experience.get(localCount);
								showRecords(pro_obj);
							}
						}
						btn_save.setEnabled(false);						
						lblMessage.setText("Record updated sucessfully");						
					}
					else{
						lblMessage.setText("Record not updated");
						
					}
			}
		}

		if(ae.getSource()==btn_first){
			if(MainFrame.staticArraylist.size()>0){
				ProfessionalContainerBean pro_container=(ProfessionalContainerBean)MainFrame.staticArraylist.get(count);
				ArrayList al_experience=pro_container.getExperience();
				if(al_experience.size()>0){
					localCount=0;
					ProfessionalExperienceBean pro_obj=(ProfessionalExperienceBean)al_experience.get(localCount);
					showRecords(pro_obj);
				}
			}
		}

		if(ae.getSource()==btn_previous){
			if(MainFrame.staticArraylist.size()>0)
			{
				ProfessionalContainerBean pro_container=(ProfessionalContainerBean)MainFrame.staticArraylist.get(count);
				ArrayList al_experience=pro_container.getExperience();
				if(al_experience.size()>0){	
					if(localCount==0)
					{}
					else
					{
						localCount--;
					}
						ProfessionalExperienceBean pro_obj=(ProfessionalExperienceBean)al_experience.get(localCount);
						showRecords(pro_obj);
				}
			}
		}
		
		if(ae.getSource()==btn_next){
			if(MainFrame.staticArraylist.size()>0)
			{
				ProfessionalContainerBean pro_container=(ProfessionalContainerBean)MainFrame.staticArraylist.get(count);
				ArrayList al_experience=pro_container.getExperience();
				if(al_experience.size()>0){	
					if(localCount==(al_experience.size()-1))
					{}
					else
					{
						localCount++;
					}
						ProfessionalExperienceBean pro_obj=(ProfessionalExperienceBean)al_experience.get(localCount);
						showRecords(pro_obj);
				}
			}			
		}
		
		if(ae.getSource()==btn_last){
				ProfessionalContainerBean pro_container=(ProfessionalContainerBean)MainFrame.staticArraylist.get(count);
				ArrayList al_experience=pro_container.getExperience();
				if(al_experience.size()>0){
					localCount=al_experience.size()-1;
					ProfessionalExperienceBean pro_obj=(ProfessionalExperienceBean)al_experience.get(localCount);
					showRecords(pro_obj);
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
			if(MainFrame.staticArraylist.size()>0){
				ProfessionalContainerBean pro_container=(ProfessionalContainerBean)MainFrame.staticArraylist.get(count);
				ArrayList al_experience=pro_container.getExperience();
				ReportProfessionalExperience obj=new ReportProfessionalExperience(al_experience);
			}
			else{
				JOptionPane.showMessageDialog(this,"There are no records to display","",JOptionPane.INFORMATION_MESSAGE);
			}
		
		}

		if(ae.getSource()==btn_nextRecord){
		
		                  Component[] c=MainFrame.container.getComponents();
        			  for(int i=0;i<c.length;i++)
			  {
       			           
		                	  MainFrame.container.remove(c[i]);
			  }
				MainFrame.container.add(new ManageProProjects(count,"navigate"));
				MainFrame.container.setVisible(false);
				MainFrame.container.setVisible(true);
		}
		if(ae.getSource()==btn_previousRecord){
		                  Component[] c=MainFrame.container.getComponents();
        			  for(int i=0;i<c.length;i++)
			  {
       			           
		                	  MainFrame.container.remove(c[i]);
			  }
				MainFrame.container.add(new ManageProSkillSet(count,"navigate"));
				MainFrame.container.setVisible(false);
				MainFrame.container.setVisible(true);
		}

		if(ae.getSource()==btn_delete){
			String result=db_obj.deleteProfessionalExperience(Integer.parseInt(tf_experienceId.getText()));
			if(result.equals("deleted")){
				MainFrame.staticArraylist=db_obj.getProfessionalContainerDetails();
				if(MainFrame.staticArraylist.size()>0){
					ProfessionalContainerBean pro_container=(ProfessionalContainerBean)MainFrame.staticArraylist.get(count);
					ArrayList al_experience=pro_container.getExperience();
					if(al_experience.size()>0){
						localCount=0;
						ProfessionalExperienceBean pro_obj=(ProfessionalExperienceBean)al_experience.get(localCount);
						showRecords(pro_obj);
					}
				}
			}
			else{
				lblMessage.setText("Unable to delete");
			}
		}
		


	}
	
	public void clear(){
		tf_experienceId.setText("");
		tf_startDate.setText("");
		tf_endDate.setText("");
		tf_companyName.setText("");
		tf_location.setText("");
		tf_designation.setText("");
		tf_jobProfile.setText("");
		tf_salaryPackage.setText("");
	}

	public void showRecords(ProfessionalExperienceBean pro_obj){
		
		tf_experienceId.setText(String.valueOf(pro_obj.getExperienceId()));
		tf_proId.setText(String.valueOf(pro_obj.getProfId()));
		tf_startDate.setText(pro_obj.getStartDate());
		tf_endDate.setText(pro_obj.getEndDate());
		tf_companyName.setText(pro_obj.getCompanyName());
		tf_location.setText(pro_obj.getLocation());
		tf_designation.setText(pro_obj.getDesignation());
		tf_jobProfile.setText(pro_obj.getJobProfile());
		tf_salaryPackage.setText(String.valueOf(pro_obj.getSalaryPackage()));

		lblMessage.setText("");
		btn_save.setEnabled(false);
		editSaveFlag="";
	}

	
}