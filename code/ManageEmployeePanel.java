import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import com.alpha.prts.*;
import java.util.ArrayList;
import java.sql.Date;
public class ManageEmployeePanel extends JPanel implements ActionListener
{
	JPanel p1,p2,p3,panel_heading;
	JTextField tf_empid,tf_username,tf_password,tf_status,tf_FName,tf_MName,tf_LName,tf_FFName,tf_FMName,tf_FLName,tf_address,tf_mobile,tf_email,tf_dob,tf_pan;
	JButton btn_first,btn_previous,btn_next,btn_last,btn_save,btn_view,btn_cancel,btn_add,btn_edit,btn_delete;
	JLabel l1,l2,l3,l4,l5,l6,l7,l8,l9,l10,l11,l12,l13,l14,l15,lbl_heading,lblMessage;

	ArrayList arraylist_emp;

	prtsDb db_obj=new prtsDb();
	int count=0;
	String editSaveFlag="none";
	
	public ManageEmployeePanel()
	{

		setLayout(new BorderLayout(5,5));
		setSize(800,500);
		p1=new JPanel();
		p2=new JPanel();
		p3=new JPanel();
		panel_heading=new JPanel();

		tf_empid=new JTextField(10);
		tf_empid.setEditable(false);
		tf_username=new JTextField(10);
		tf_password=new JTextField(10);
		tf_status=new JTextField(10);
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
		
		l1=new JLabel("Employee Id");
		l2=new JLabel("Username");
		l3=new JLabel("Password");
		l4=new JLabel("Status");
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


		lbl_heading=new JLabel("Employee Personal Record");
		lbl_heading.setHorizontalAlignment(SwingConstants.CENTER);
		lbl_heading.setFont(new Font("Tahoma", 1, 18));

		lblMessage=new JLabel("");
		lblMessage.setHorizontalAlignment(SwingConstants.CENTER);
		lblMessage.setFont(new Font("Tahoma", 1, 11));
		lblMessage.setForeground(Color.red);
			
		
		p1.setLayout(new GridLayout(15,2,5,5));
		p2.setLayout(new GridLayout(1,4,5,5));
		p3.setLayout(new GridLayout(15,1,5,5));
		panel_heading.setLayout(new GridLayout(2,1));

		panel_heading.add(lbl_heading);
		panel_heading.add(lblMessage);
		
		
		p1.add(l1);
		p1.add(tf_empid);
		p1.add(l2);
		p1.add(tf_username);
		p1.add(l3);
		p1.add(tf_password);
		p1.add(l4);
		p1.add(tf_status);
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

		p2.add(btn_first);
		p2.add(btn_previous);
		p2.add(btn_next);
		p2.add(btn_last);
		
		p3.add(btn_add);
		p3.add(btn_edit);
		p3.add(btn_save);
		p3.add(btn_delete);

		p3.add(btn_view);
		p3.add(btn_cancel);

		
		add(panel_heading,BorderLayout.NORTH);
		add(p1,BorderLayout.CENTER);
		add(p2,BorderLayout.SOUTH);
		add(p3,BorderLayout.EAST);

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

		setVisible(true);

		arraylist_emp=db_obj.getEmployeeRecords();
		if(arraylist_emp.size()>0){
			EmployeeBean emp_obj=(EmployeeBean)arraylist_emp.get(count);
			showRecords(emp_obj);
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
			btn_save.setEnabled(true);
		}

		if(ae.getSource()==btn_save){

			int flag=0;
			if(tf_pan.getText().equals("")){
				lblMessage.setText("Enter employee PAN");								
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
			if(tf_username.getText().equals("")){
				lblMessage.setText("Enter employee useraname");								
				flag=1;
			}
			else if(tf_password.getText().equals("")){
				lblMessage.setText("Enter employee password");								
				flag=1;
			}
			else if(tf_status.getText().equals("")){
				lblMessage.setText("Enter employee status");								
				flag=1;
			}
			else if(tf_FName.getText().equals("")){
				lblMessage.setText("Enter employee's first name");
				flag=1;
			}
			else if(tf_LName.getText().equals("")){
				lblMessage.setText("Enter employee's last name");
				flag=1;
			}
			else if(tf_FFName.getText().equals("")){
				lblMessage.setText("Enter employee father's first name");				
				flag=1;
			}
			else if(tf_FLName.getText().equals("")){
				lblMessage.setText("Enter employee father's last name");								
				flag=1;
			}
			else if(tf_address.getText().equals("")){
				lblMessage.setText("Enter employee's address");								
				flag=1;
			}
						
			if(editSaveFlag.equals("save") && flag==0){
					String result=db_obj.saveEmployeeDetails(tf_username.getText(),tf_password.getText(),tf_status.getText(),tf_FName.getText(),tf_MName.getText(),tf_LName.getText(),tf_FFName.getText(),tf_FMName.getText(),tf_FLName.getText(),tf_address.getText(),tf_mobile.getText(),tf_email.getText(),tf_dob.getText(),tf_pan.getText());
					if(result.equals("exists")){
							lblMessage.setText("Username already exists");
					}
					else if(result.equals("failed")){
						lblMessage.setText("Record not saved");					
					}
					else if(result.equals("saved")){
							lblMessage.setText("Record added successfully");
							arraylist_emp=db_obj.getEmployeeRecords();
							count=0;
							if(arraylist_emp.size()>0){
								EmployeeBean emp_obj=(EmployeeBean)arraylist_emp.get(count);
								showRecords(emp_obj);
							}
							
							btn_save.setEnabled(false);
					}
				}
			if(editSaveFlag.equals("edit") && flag==0){
					String result=db_obj.updateEmployeeDetails(Integer.parseInt(tf_empid.getText()),tf_username.getText(),tf_password.getText(),tf_status.getText(),tf_FName.getText(),tf_MName.getText(),tf_LName.getText(),tf_FFName.getText(),tf_FMName.getText(),tf_FLName.getText(),tf_address.getText(),tf_mobile.getText(),tf_email.getText(),tf_dob.getText(),tf_pan.getText());
					if(result.equals("updated")){
						lblMessage.setText("Record updated sucessfully");
						arraylist_emp=db_obj.getEmployeeRecords();
						if(arraylist_emp.size()>0){
							EmployeeBean emp_obj=(EmployeeBean)arraylist_emp.get(count);
							showRecords(emp_obj);
							btn_save.setEnabled(false);
						}
						
					}
					else{
						lblMessage.setText("Record not updated");
						
					}
				}
		}

		if(ae.getSource()==btn_first){
			count=0;
			if(arraylist_emp.size()>0)
			{
				EmployeeBean emp_obj=(EmployeeBean)arraylist_emp.get(count);
				showRecords(emp_obj);
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
			if(arraylist_emp.size()>0)
			{
				EmployeeBean emp_obj=(EmployeeBean)arraylist_emp.get(count);
				showRecords(emp_obj);
			}
		}
		
		if(ae.getSource()==btn_next){
			if(arraylist_emp.size()>0)
			{
				if(count==(arraylist_emp.size()-1))
				{}
				else
				{
					count++;
				}
				EmployeeBean emp_obj=(EmployeeBean)arraylist_emp.get(count);
				showRecords(emp_obj);
			}			
		}
		
		if(ae.getSource()==btn_last){
			count=arraylist_emp.size()-1;
			if(arraylist_emp.size()>0)
			{
				EmployeeBean emp_obj=(EmployeeBean)arraylist_emp.get(count);
				showRecords(emp_obj);
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
			ReportEmployeeDetails obj=new ReportEmployeeDetails();
		
		}
	
		if(ae.getSource()==btn_delete){
			String delete=db_obj.deleteEmployee(Integer.parseInt(tf_empid.getText()))	;
			if(delete.equals("deleted")){
				arraylist_emp=db_obj.getEmployeeRecords();
				count=0;
				if(arraylist_emp.size()>0){
					EmployeeBean emp_obj=(EmployeeBean)arraylist_emp.get(count);
					showRecords(emp_obj);
				}
			}
		}
	}
	
	public void clear(){
		tf_empid.setText("");
		tf_username.setText("");
		tf_password.setText("");
		tf_status.setText("");
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
	}

	public void showRecords(EmployeeBean emp_obj){
		tf_empid.setText(String.valueOf(emp_obj.getEmpId()));
		tf_username.setText(emp_obj.getUsername());
		tf_password.setText(emp_obj.getEmpPassword());
		tf_status.setText(emp_obj.getEmpStatus());
		tf_FName.setText(emp_obj.getEmpFName());
		tf_MName.setText(emp_obj.getEmpMName());
		tf_LName.setText(emp_obj.getEmpLName());
		tf_FFName.setText(emp_obj.getEmpFFName());
		tf_FMName.setText(emp_obj.getEmpFMName());
		tf_FLName.setText(emp_obj.getEmpFLName());
		tf_address.setText(emp_obj.getEmpAddress());
		tf_mobile.setText(emp_obj.getEmpMobile());
		tf_email.setText(emp_obj.getEmpEmail());
		tf_dob.setText(emp_obj.getEmpDOB());
		tf_pan.setText(emp_obj.getEmpPAN());
		lblMessage.setText("");
		btn_save.setEnabled(false);
		editSaveFlag="";
	}

	
}