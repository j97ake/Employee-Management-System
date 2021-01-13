import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import com.alpha.prts.*;
import java.util.ArrayList;
import java.sql.Date;
public class ManageProPersonal extends JPanel implements ActionListener
{
	JPanel p1,p2,p3,panel_heading;
	JTextField tf_proId,tf_FName,tf_MName,tf_LName,tf_FFName,tf_FMName,tf_FLName,tf_address,tf_mobile,tf_email,tf_dob,tf_pan,tf_expectedSalary;
	JButton btn_first,btn_previous,btn_next,btn_last,btn_save,btn_view,btn_cancel,btn_add,btn_edit,btn_delete;
	JButton btn_nextRecord,btn_previousRecord;  //to navigate between professionals varius details	
	JLabel l1,l5,l6,l7,l8,l9,l10,l11,l12,l13,l14,l15,l16,lbl_heading,lblMessage;
	JLabel lbl_empty1,lbl_empty2;

	prtsDb db_obj=new prtsDb();
	int count=0;
	String editSaveFlag="none";
	
	public ManageProPersonal(int count,String type)
	{

		setLayout(new BorderLayout(5,5));
		setSize(800,500);
		p1=new JPanel();
		p2=new JPanel();
		p3=new JPanel();
		panel_heading=new JPanel();

		tf_proId=new JTextField(10);
		tf_proId.setEditable(false);
		tf_FName=new JTextField(10);
		tf_MName=new JTextField(10);
		tf_LName=new JTextField(10);		
		tf_FFName=new JTextField(10);
		tf_FMName=new JTextField(10);
		tf_FLName=new JTextField(10);
		tf_address=new JTextField(10);
		tf_mobile=new JTextField(10);
		tf_email=new JTextField(10);
		tf_dob=new JTextField(10);
		tf_pan=new JTextField(10);
		tf_expectedSalary=new JTextField(10);

		btn_first=new JButton("FIRST");
		btn_previous=new JButton("PREVIOUS");
		btn_next=new JButton("NEXT ");
		btn_last=new JButton("LAST");

		btn_add=new JButton("ADD");
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
		
		l1=new JLabel("Professional Id");
		l5=new JLabel("First Name");
		l6=new JLabel("Middle Name");
		l7=new JLabel("Last Name");
		l8=new JLabel("Father's First Name");
		l9=new JLabel("Father's Middle Name");
		l10=new JLabel("Father's Last Name");
		l11=new JLabel("Address");
		l12=new JLabel("Mobile");
		l13=new JLabel("Email");
		l14=new JLabel("Date Of Birth");
		l15=new JLabel("PAN");
		l16=new JLabel("Expected Salary");
		lbl_empty1=new JLabel("");
		lbl_empty2=new JLabel("");

		
		lbl_heading=new JLabel("Professional Personal Record");
		lbl_heading.setHorizontalAlignment(SwingConstants.CENTER);
		lbl_heading.setFont(new Font("Tahoma", 1, 18));

		lblMessage=new JLabel("");
		lblMessage.setHorizontalAlignment(SwingConstants.CENTER);
		lblMessage.setFont(new Font("Tahoma", 1, 11));
		lblMessage.setForeground(Color.red);
			
		
		p1.setLayout(new GridLayout(15,2,5,5));
		p2.setLayout(new GridLayout(1,4,5,5));
		p3.setLayout(new GridLayout(15,1,5,5));
		panel_heading.setLayout(new GridLayout(4,1));
		
		panel_heading.add(lbl_empty1);
		panel_heading.add(lbl_heading);
		panel_heading.add(lbl_empty2);
		panel_heading.add(lblMessage);
		
		
		p1.add(l1);
		p1.add(tf_proId);
		p1.add(l5);
		p1.add(tf_FName);
		p1.add(l6);
		p1.add(tf_MName);
		p1.add(l7);
		p1.add(tf_LName);
		p1.add(l8);
		p1.add(tf_FFName);
		p1.add(l9);
		p1.add(tf_FMName);
		p1.add(l10);
		p1.add(tf_FLName);
		p1.add(l11);
		p1.add(tf_address);
		p1.add(l12);
		p1.add(tf_mobile);
		p1.add(l13);
		p1.add(tf_email);
		p1.add(l14);
		p1.add(tf_dob);
		p1.add(l15);
		p1.add(tf_pan);
		p1.add(l16);
		p1.add(tf_expectedSalary);

		p2.add(btn_first);
		p2.add(btn_previous);
		p2.add(btn_next);
		p2.add(btn_last);
		
		p3.add(btn_nextRecord);
		p3.add(btn_previousRecord);
		if(MainFrame.userStatus.equalsIgnoreCase("operator")){
			p3.add(btn_add);
		}
		p3.add(btn_edit);
		p3.add(btn_save);
		p3.add(btn_delete);

		p3.add(btn_view);
		p3.add(btn_cancel);

		
		add(panel_heading,BorderLayout.NORTH);
		add(p1,BorderLayout.CENTER);
		add(p2,BorderLayout.SOUTH);
		add(p3,BorderLayout.EAST);


		setVisible(true);
		if(type.equals("navigate")){
			this.count=count;
		}

		MainFrame.staticArraylist=db_obj.getProfessionalContainerDetails();
		
		if(MainFrame.staticArraylist.size()>0){
			ProfessionalContainerBean pro_container=(ProfessionalContainerBean)MainFrame.staticArraylist.get(count);
			ProfessionalPersonalBean pro_obj=pro_container.getPersonalDetail();
			showRecords(pro_obj);
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
				Double salary=Double.parseDouble(tf_expectedSalary.getText());
			}
			catch(NumberFormatException e){
				lblMessage.setText("Enter number for  expected salary");
				flag=1;
			}
			if(tf_pan.getText().equals("")){
				lblMessage.setText("Enter professional PAN");								
				flag=1;				
			}
			try{
				String dob= tf_dob.getText();
				Date dd= new Date(200);
	 			dd=Date.valueOf(dob);
			}
			catch(IllegalArgumentException e)
	 		{
			  	lblMessage.setText("Date Format is (yyyy-mm-dd) ");
				flag=1;	
		  	}

			try{
				Long mobile=Long.parseLong(tf_mobile.getText());
			}
			catch(NumberFormatException e)
 			{
				lblMessage.setText("Enter number for mobile number");
				flag=1;
	           		}

			if(tf_FName.getText().equals("")){
				lblMessage.setText("Enter professional's first name");
				flag=1;
			}
			else if(tf_LName.getText().equals("")){
				lblMessage.setText("Enter professional's last name");
				flag=1;
			}
			else if(tf_FFName.getText().equals("")){
				lblMessage.setText("Enter professional father's first name");				
				flag=1;
			}
			else if(tf_FLName.getText().equals("")){
				lblMessage.setText("Enter professional father's last name");								
				flag=1;
			}
			else if(tf_address.getText().equals("")){
				lblMessage.setText("Enter professional's address");								
				flag=1;
			}

						
			if(editSaveFlag.equals("save") && flag==0){
					String result=db_obj.saveProfessionalDetails(tf_FName.getText(),tf_MName.getText(),tf_LName.getText(),tf_FFName.getText(),tf_FMName.getText(),tf_FLName.getText(),tf_address.getText(),tf_mobile.getText(),tf_email.getText(),tf_dob.getText(),tf_pan.getText(),Double.parseDouble(tf_expectedSalary.getText()));
					
					if(result.equals("failed")){
						lblMessage.setText("Record not saved");					
					}
					else if(result.equals("saved")){
							lblMessage.setText("Record added successfully");
							MainFrame.staticArraylist=db_obj.getProfessionalContainerDetails();
							count=0;
							if(MainFrame.staticArraylist.size()>0){
							ProfessionalContainerBean pro_container=(ProfessionalContainerBean)MainFrame.staticArraylist.get(count);
							ProfessionalPersonalBean pro_obj=pro_container.getPersonalDetail();
								showRecords(pro_obj);
							}
							
							btn_save.setEnabled(false);
					}
				}
			if(editSaveFlag.equals("edit") && flag==0){
					String result=db_obj.updateProPersonalDetails(Integer.parseInt(tf_proId.getText()),tf_FName.getText(),tf_MName.getText(),tf_LName.getText(),tf_FFName.getText(),tf_FMName.getText(),tf_FLName.getText(),tf_address.getText(),tf_mobile.getText(),tf_email.getText(),tf_dob.getText(),tf_pan.getText(),Double.parseDouble(tf_expectedSalary.getText()));
					if(result.equals("updated")){
						lblMessage.setText("Record updated sucessfully");
						MainFrame.staticArraylist=db_obj.getProfessionalContainerDetails();
						if(MainFrame.staticArraylist.size()>0){
							ProfessionalContainerBean pro_container=(ProfessionalContainerBean)MainFrame.staticArraylist.get(count);
							ProfessionalPersonalBean pro_obj=pro_container.getPersonalDetail();
							showRecords(pro_obj);
							btn_save.setEnabled(false);
						}
						lblMessage.setText("Record updated sucessfully");
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
				ProfessionalContainerBean pro_container=(ProfessionalContainerBean)MainFrame.staticArraylist.get(count);
				ProfessionalPersonalBean pro_obj=pro_container.getPersonalDetail();
				showRecords(pro_obj);
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
				ProfessionalContainerBean pro_container=(ProfessionalContainerBean)MainFrame.staticArraylist.get(count);
				ProfessionalPersonalBean pro_obj=pro_container.getPersonalDetail();
				showRecords(pro_obj);
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
				ProfessionalContainerBean pro_container=(ProfessionalContainerBean)MainFrame.staticArraylist.get(count);
				ProfessionalPersonalBean pro_obj=pro_container.getPersonalDetail();
				showRecords(pro_obj);
			}			
		}
		
		if(ae.getSource()==btn_last){
			count=MainFrame.staticArraylist.size()-1;
			if(MainFrame.staticArraylist.size()>0)
			{
				ProfessionalContainerBean pro_container=(ProfessionalContainerBean)MainFrame.staticArraylist.get(count);
				ProfessionalPersonalBean pro_obj=pro_container.getPersonalDetail();
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
			ReportProPersonalDetails obj=new ReportProPersonalDetails();	
		}

		if(ae.getSource()==btn_nextRecord){
		                  Component[] c=MainFrame.container.getComponents();
        			  for(int i=0;i<c.length;i++)
			  {
       			           
		                	  MainFrame.container.remove(c[i]);
			  }
				MainFrame.container.add(new ManageProQualification(count,"navigate"));
				MainFrame.container.setVisible(false);
				MainFrame.container.setVisible(true);

		}
		if(ae.getSource()==btn_previousRecord){
		                  Component[] c=MainFrame.container.getComponents();
        			  for(int i=0;i<c.length;i++)
			  {
       			           
		                	  MainFrame.container.remove(c[i]);
			  }
				MainFrame.container.add(new ManageProProjects(count,"navigate"));
				MainFrame.container.setVisible(false);
				MainFrame.container.setVisible(true);
		}

		if(ae.getSource()==btn_delete){
			String result=db_obj.deleteProfessional(Integer.parseInt(tf_proId.getText()));
			MainFrame.staticArraylist=db_obj.getProfessionalContainerDetails();
			if(result.equals("deleted")){
				count=0;
				if(MainFrame.staticArraylist.size()>0)
				{
					ProfessionalContainerBean pro_container=(ProfessionalContainerBean)MainFrame.staticArraylist.get(count);
					ProfessionalPersonalBean pro_obj=pro_container.getPersonalDetail();
					showRecords(pro_obj);
				}
			}
			else{	
				lblMessage.setText("Unable to delete");				
			}
		}		


	}
	
	public void clear(){
		tf_proId.setText("");
		tf_FName.setText("");
		tf_MName.setText("");
		tf_LName.setText("");
		tf_FFName.setText("");
		tf_FMName.setText("");
		tf_FLName.setText("");
		tf_address.setText("");
		tf_mobile.setText("");
		tf_email.setText("");
		tf_dob.setText("");
		tf_pan.setText("");
		tf_expectedSalary.setText("");
	}

	public void showRecords(ProfessionalPersonalBean pro_obj){
		tf_proId.setText(String.valueOf(pro_obj.getProfId()));
		tf_FName.setText(pro_obj.getProfFName());
		tf_MName.setText(pro_obj.getProfMName());
		tf_LName.setText(pro_obj.getProfLName());
		tf_FFName.setText(pro_obj.getProfFFName());
		tf_FMName.setText(pro_obj.getProfFMName());
		tf_FLName.setText(pro_obj.getProfFLName());
		tf_address.setText(pro_obj.getProfAddress());
		tf_mobile.setText(pro_obj.getProfMobile());
		tf_email.setText(pro_obj.getProfEmail());
		tf_dob.setText(pro_obj.getProfDOB());
		tf_pan.setText(pro_obj.getProfPAN());
		tf_expectedSalary.setText(String.valueOf(pro_obj. getProfExpectedSalary()));
		lblMessage.setText("");
		btn_save.setEnabled(false);
		editSaveFlag="";
	}

	
}