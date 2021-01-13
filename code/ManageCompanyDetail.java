import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import com.alpha.prts.*;
import java.util.ArrayList;
import java.sql.Date;
public class ManageCompanyDetail extends JPanel implements ActionListener
{
	JPanel panalCenter,panalSouth,panalEast,panel_heading;
	JTextField tf_companyId,tf_companyName,tf_address,tf_city,tf_state,tf_country,tf_pin,tf_phone;
	JTextField tf_email;
	JButton btn_first,btn_previous,btn_next,btn_last,btn_save,btn_view,btn_cancel,btn_add,btn_edit,btn_delete;
	JButton btn_nextRecord,btn_previousRecord;  //to navigate between company varius details	
	JLabel lbl_companyId,lbl_email,lbl_companyName,lbl_address,lbl_city,lbl_state,lbl_country,lbl_pin,lbl_heading,lblMessage,lbl_phone;
	JLabel lbl_empty1,lbl_empty2,lbl_empty3,lbl_empty4,lbl_empty5,lbl_empty6;

	prtsDb db_obj=new prtsDb();
	int count=0;
	String editSaveFlag="none";
	
	public ManageCompanyDetail(){}
	public ManageCompanyDetail(int count,String type)
	{
		
		setLayout(new BorderLayout(5,5));
		setSize(800,500);
		panalCenter=new JPanel();
		panalSouth=new JPanel();
		panalEast=new JPanel();
		panel_heading=new JPanel();

		
		tf_companyId=new JTextField(10);
		tf_companyId.setEditable(false);
		tf_companyName=new JTextField(10);
		tf_address=new JTextField(10);		
		tf_city=new JTextField(10);
		tf_state=new JTextField(10);
		tf_country=new JTextField(10);
		tf_pin=new JTextField(10);
		tf_phone=new JTextField(10);
		tf_email=new JTextField(10);
		

		lbl_companyId=new JLabel("Company Id");
		lbl_companyName=new JLabel("Company Name");
		lbl_address=new JLabel("Address");
		lbl_city=new JLabel("City");
		lbl_state=new JLabel("State");
		lbl_country=new JLabel("Country");
		lbl_pin=new JLabel("Pin");
		lbl_phone=new JLabel("Phone");
		lbl_email=new JLabel("Email Id");

		lbl_empty1=new JLabel("");
		lbl_empty2=new JLabel("");


		lbl_heading=new JLabel("Company Detail");
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
		panalCenter.add(lbl_companyName);
		panalCenter.add(tf_companyName);
		panalCenter.add(lbl_address);
		panalCenter.add(tf_address);
		panalCenter.add(lbl_city);
		panalCenter.add(tf_city);
		panalCenter.add(lbl_state);
		panalCenter.add(tf_state);
		panalCenter.add(lbl_country);
		panalCenter.add(tf_country);
		panalCenter.add(lbl_pin);
		panalCenter.add(tf_pin);
		panalCenter.add(lbl_phone);
		panalCenter.add(tf_phone);
		panalCenter.add(lbl_email);
		panalCenter.add(tf_email);


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
		}

 		MainFrame.staticArraylist=db_obj.getCompanyRecords();
		if(MainFrame.staticArraylist.size()>0){
			CompanySkillSetBean company_obj=(CompanySkillSetBean)MainFrame.staticArraylist.get(count);
			showRecords(company_obj);
		}  
		
	}
	public void actionPerformed(ActionEvent ae)
	{
	
		 if(ae.getSource()==btn_add){
			clear();
			tf_companyId.setText("");
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

			if(tf_email.getText().equals("")){
				lblMessage.setText("Enter Email");
				flag=1;		
			}
			try{
				Long phone=Long.parseLong(tf_phone.getText());
			}
			catch(NumberFormatException e){
				lblMessage.setText("Enter number for Phone Number");
				flag=1;
			}

			if(tf_companyName.getText().equals("")){
				lblMessage.setText("Enter Company name");								
				flag=1;				
			}
			else if(tf_address.getText().equals("")){
				lblMessage.setText("Enter Address");								
				flag=1;				
			}
			else if(tf_city.getText().equals("")){
				lblMessage.setText("Enter city");
				flag=1;		
			}
			else if(tf_state.getText().equals("")){
				lblMessage.setText("Enter State");								
				flag=1;				
			}
			else if(tf_country.getText().equals("")){
				lblMessage.setText("Enter Country ");								
				flag=1;				
			}
			else if(tf_pin.getText().equals("")){
				lblMessage.setText("Enter pin");
				flag=1;		
			}
				
									
			/* if(editSaveFlag.equals("save") && flag==0){
					String result=db_obj.saveCompanyInfo(tf_companyName.getText(),tf_address.getText(),tf_city.getText(),tf_state.getText(),tf_country.getText(),tf_pin.getText(),tf_phone.getText(),tf_email.getText());
					
					if(result.equals("failed")){
						lblMessage.setText("Record not saved");					
					}
					else if(result.equals("saved")){
							lblMessage.setText("Record added successfully");
							MainFrame.staticArraylist=db_obj.getCompanyRecords();
							count=0;
							if(MainFrame.staticArraylist.size()>0){
								CompanySkillSetBean company_obj=(CompanySkillSetBean)MainFrame.staticArraylist.get(count);
								showRecords(company_obj);
							}
							
							btn_save.setEnabled(false);
					}
			}*/  
			 if(editSaveFlag.equals("save") && flag==0){
				int companyId=db_obj.saveCompanyInfo(tf_companyName.getText(),tf_address.getText(),tf_city.getText(),tf_state.getText(),tf_country.getText(),tf_pin.getText(),tf_phone.getText(),tf_email.getText());
				if(companyId==0){
					lblMessage.setText("Record not saved");					
				}
				else{
				                  Component[] c=MainFrame.container.getComponents();
        					  for(int i=0;i<c.length;i++)
					  {
				                	  MainFrame.container.remove(c[i]);
					  }
					MainFrame.container.add(new ManageCompanyRequirement(companyId,"save"));
					MainFrame.container.setVisible(false);
					MainFrame.container.setVisible(true);
				}
			}
			if(editSaveFlag.equals("edit") && flag==0){
					String result=db_obj.updateCompanyInfo(Integer.parseInt(tf_companyId.getText()),tf_companyName.getText(),tf_address.getText(),tf_city.getText(),tf_state.getText(),tf_country.getText(),tf_pin.getText(),tf_phone.getText(),tf_email.getText());
					if(result.equals("updated")){
					                  Component[] c=MainFrame.container.getComponents();
        						  for(int i=0;i<c.length;i++)
						  {
					                	  MainFrame.container.remove(c[i]);
						  }
						MainFrame.container.add(new ManageCompanyRequirement(count,"navigate"));
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
			ReportCompanyDetails obj=new ReportCompanyDetails();
		
		}

		if(ae.getSource()==btn_nextRecord){
		
		                  Component[] c=MainFrame.container.getComponents();
        			  for(int i=0;i<c.length;i++)
			  {
       			           
		                	  MainFrame.container.remove(c[i]);
			  }
				MainFrame.container.add(new ManageCompanyRequirement(count,"navigate"));
				MainFrame.container.setVisible(false);
				MainFrame.container.setVisible(true);
		}
		if(ae.getSource()==btn_previousRecord){
		                  Component[] c=MainFrame.container.getComponents();
        			  for(int i=0;i<c.length;i++)
			  {
       			           
		                	  MainFrame.container.remove(c[i]);
			  }
				MainFrame.container.add(new ManageCompanyRequirementSkillSet(count,"navigate"));
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
		tf_companyName.setText("");
		tf_address.setText("");
		tf_city.setText("");
		tf_state.setText("");
		tf_country.setText("");
		tf_pin.setText("");
		tf_phone.setText("");
		tf_email.setText("");
	}

	public void showRecords(CompanySkillSetBean company_obj){
		tf_companyId.setText(String.valueOf(company_obj.getCompanyId()));
		tf_companyName.setText(company_obj.getCompanyName());
		tf_address.setText(company_obj.getAddress());
		tf_city.setText(company_obj.getCity());
		tf_state.setText(company_obj.getState());
		tf_country.setText(company_obj.getCountry());
		tf_pin.setText(company_obj.getPin());
		tf_phone.setText(company_obj.getPhone());
		tf_email.setText(company_obj.getEmail());

		lblMessage.setText("");
		btn_save.setEnabled(false);
		editSaveFlag="";
		
	}

	
}