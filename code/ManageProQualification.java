import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import com.alpha.prts.*;
import java.util.ArrayList;
import java.sql.Date;
public class ManageProQualification extends JPanel implements ActionListener
{
	JPanel panalCenter,panalSouth,panalEast,panel_heading;
	JTextField tf_proId,tf_qname,tf_branch,tf_yop,tf_uniBoard,tf_coll_Institute,tf_perMarks;
	JTextField tf_qId;
	JButton btn_first,btn_previous,btn_next,btn_last,btn_save,btn_view,btn_cancel,btn_add,btn_edit,btn_delete;
	JButton btn_nextRecord,btn_previousRecord;  //to navigate between professionals varius details	
	JLabel lblProfId,lbl_qId,lbl_qname,lbl_branch,lbl_yop,lbl_uniBoard,lbl_coll_Institute,lbl_perMarks,lbl_heading,lblMessage;
	JLabel lbl_empty1,lbl_empty2;

	prtsDb db_obj=new prtsDb();
	int count=0; //user to traverse between various professional detail categories 
	int localCount=0; // to traverse between qualifications
	String editSaveFlag="none";
	
	public ManageProQualification(int count,String type)
	{

		setLayout(new BorderLayout(5,5));
		setSize(800,500);
		panalCenter=new JPanel();
		panalSouth=new JPanel();
		panalEast=new JPanel();
		panel_heading=new JPanel();

		tf_proId=new JTextField(10);
		tf_proId.setEditable(false);
		tf_qId=new JTextField(10);
		tf_qId.setEditable(false);
		tf_qname=new JTextField(10);
		tf_branch=new JTextField(10);		
		tf_yop=new JTextField(10);
		tf_uniBoard=new JTextField(10);
		tf_coll_Institute=new JTextField(10);
		tf_perMarks=new JTextField(10);
		


		btn_first=new JButton("FIRST");
		btn_previous=new JButton("PREVIOUS");
		btn_next=new JButton("NEXT ");
		btn_last=new JButton("LAST");

		btn_add=new JButton("ADD QUALIFICATION");
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
		
		lblProfId=new JLabel("Professional Id");
		lbl_qId=new JLabel("Qualification Id");
		lbl_qname=new JLabel("Qualification Name");
		lbl_branch=new JLabel("Branch");
		lbl_yop=new JLabel("Year Of Passing");
		lbl_uniBoard=new JLabel("University / Board");
		lbl_coll_Institute=new JLabel("College / Institute");
		lbl_perMarks=new JLabel("Percentage Marks");

		lbl_empty1=new JLabel("");
		lbl_empty2=new JLabel("");


		lbl_heading=new JLabel("Professional Qualification Record");
		lbl_heading.setHorizontalAlignment(SwingConstants.CENTER);
		lbl_heading.setFont(new Font("Tahoma", 1, 18));

		lblMessage=new JLabel("");
		lblMessage.setHorizontalAlignment(SwingConstants.CENTER);
		lblMessage.setFont(new Font("Tahoma", 1, 11));
		lblMessage.setForeground(Color.red);
			
		
		panalCenter.setLayout(new GridLayout(15,2,5,5));
		panalSouth.setLayout(new GridLayout(1,4,5,5));
		panalEast.setLayout(new GridLayout(15,1,5,5));
		panel_heading.setLayout(new GridLayout(4,1));
		
		panel_heading.add(lbl_empty1);
		panel_heading.add(lbl_heading);
		panel_heading.add(lbl_empty2);
		panel_heading.add(lblMessage);
		
		
		panalCenter.add(lbl_qId);
		panalCenter.add(tf_qId);
		panalCenter.add(lblProfId);
		panalCenter.add(tf_proId);
		panalCenter.add(lbl_qname);
		panalCenter.add(tf_qname);
		panalCenter.add(lbl_branch);
		panalCenter.add(tf_branch);
		panalCenter.add(lbl_yop);
		panalCenter.add(tf_yop);
		panalCenter.add(lbl_uniBoard);
		panalCenter.add(tf_uniBoard);
		panalCenter.add(lbl_coll_Institute);
		panalCenter.add(tf_coll_Institute);
		panalCenter.add(lbl_perMarks);
		panalCenter.add(tf_perMarks);


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


		MainFrame.staticArraylist=db_obj.getProfessionalContainerDetails();
		
		if(MainFrame.staticArraylist.size()>0){
			ProfessionalContainerBean pro_container=(ProfessionalContainerBean)MainFrame.staticArraylist.get(count);
			ArrayList al_qualification=pro_container.getQualification();
			if(al_qualification.size()>0){
				ProfessionalQualificationBean pro_obj=(ProfessionalQualificationBean)al_qualification.get(localCount);
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
				Double per=Double.parseDouble(tf_perMarks.getText());
				if(per>100){
					lblMessage.setText("Enter correct percentage marks");
					flag=1;		
				}
			}
			catch(NumberFormatException e){
				lblMessage.setText("Enter number for  percentage marks");
				flag=1;
			}
			if(tf_coll_Institute.getText().equals("")){
				lblMessage.setText("Enter college/Institute name ");								
				flag=1;				
			}
			if(tf_uniBoard.getText().equals("")){
				lblMessage.setText("Enter University/Board name");								
				flag=1;				
			}

			try{
				String yop= tf_yop.getText();
         				Date dd= new Date(200);
		 		dd=Date.valueOf(yop);
			}

			catch(IllegalArgumentException e)
	 		{
			  	lblMessage.setText("Date Format is (yyyy-mm-dd) ");
				flag=1;	
		  	}
			if(tf_branch.getText().equals("")){
				lblMessage.setText("Enter branch name");								
				flag=1;				
			}
			if(tf_qname.getText().equals("")){
				lblMessage.setText("Enter Qualification name");								
				flag=1;				
			}
			try{
				int pid= Integer.parseInt(tf_proId.getText());
			}

			catch(IllegalArgumentException e)
	 		{
			  	lblMessage.setText("Enter number for professional id");
				flag=1;	
		  	}
			/*try{
				int qid= Integer.parseInt(tf_qId.getText());
			}

			catch(IllegalArgumentException e)
	 		{
			  	lblMessage.setText("Enter number for qualification id");
				flag=1;	
		  	}*/
			
									
			if(editSaveFlag.equals("save") && flag==0){
					String result=db_obj.saveProQualificationDetails(Integer.parseInt(tf_proId.getText()),tf_qname.getText(),tf_branch.getText(),tf_yop.getText(),tf_uniBoard.getText(),tf_coll_Institute.getText(),Double.parseDouble(tf_perMarks.getText()));
					
					if(result.equals("failed")){
						lblMessage.setText("Record not saved");					
					}
					else if(result.equals("saved")){
							lblMessage.setText("Record added successfully");
							MainFrame.staticArraylist=db_obj.getProfessionalContainerDetails();
							localCount=0;
							if(MainFrame.staticArraylist.size()>0){
								ProfessionalContainerBean pro_container=(ProfessionalContainerBean)MainFrame.staticArraylist.get(count);
								ArrayList al_qualification=pro_container.getQualification();
								if(al_qualification.size()>0){
									ProfessionalQualificationBean pro_obj=(ProfessionalQualificationBean)al_qualification.get(localCount);
									showRecords(pro_obj);
								}
							}
							btn_save.setEnabled(false);
					}
			}
			if(editSaveFlag.equals("edit") && flag==0){
					String result=db_obj.updateProQualificationDetails(Integer.parseInt(tf_qId.getText()),Integer.parseInt(tf_proId.getText()),tf_qname.getText(),tf_branch.getText(),tf_yop.getText(),tf_uniBoard.getText(),tf_coll_Institute.getText(),Double.parseDouble(tf_perMarks.getText()));
					if(result.equals("updated")){
						MainFrame.staticArraylist=db_obj.getProfessionalContainerDetails();
						if(MainFrame.staticArraylist.size()>0){
							ProfessionalContainerBean pro_container=(ProfessionalContainerBean)MainFrame.staticArraylist.get(count);
							ArrayList al_qualification=pro_container.getQualification();
							if(al_qualification.size()>0){
								ProfessionalQualificationBean pro_obj=(ProfessionalQualificationBean)al_qualification.get(localCount);
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
				ArrayList al_qualification=pro_container.getQualification();
				if(al_qualification.size()>0){
					localCount=0;
					ProfessionalQualificationBean pro_obj=(ProfessionalQualificationBean)al_qualification.get(localCount);
					showRecords(pro_obj);
				}
			}
		}

		if(ae.getSource()==btn_previous){
			if(MainFrame.staticArraylist.size()>0)
			{
				ProfessionalContainerBean pro_container=(ProfessionalContainerBean)MainFrame.staticArraylist.get(count);
				ArrayList al_qualification=pro_container.getQualification();
				if(al_qualification.size()>0){	
					if(localCount==0)
					{}
					else
					{
						localCount--;
					}
						ProfessionalQualificationBean pro_obj=(ProfessionalQualificationBean)al_qualification.get(localCount);
						showRecords(pro_obj);
				}
			}
		}
		
		if(ae.getSource()==btn_next){
			if(MainFrame.staticArraylist.size()>0)
			{
				ProfessionalContainerBean pro_container=(ProfessionalContainerBean)MainFrame.staticArraylist.get(count);
				ArrayList al_qualification=pro_container.getQualification();
				if(al_qualification.size()>0){	
					if(localCount==(al_qualification.size()-1))
					{}
					else
					{
						localCount++;
					}
						ProfessionalQualificationBean pro_obj=(ProfessionalQualificationBean)al_qualification.get(localCount);
						showRecords(pro_obj);
				}
			}			
		}
		
		if(ae.getSource()==btn_last){
			
			if(MainFrame.staticArraylist.size()>0)
			{
				ProfessionalContainerBean pro_container=(ProfessionalContainerBean)MainFrame.staticArraylist.get(count);
				ArrayList al_qualification=pro_container.getQualification();
				if(al_qualification.size()>0){
					localCount=al_qualification.size()-1;
					ProfessionalQualificationBean pro_obj=(ProfessionalQualificationBean)al_qualification.get(localCount);
					showRecords(pro_obj);
				}
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
				ArrayList al_qualification=pro_container.getQualification();
				ReportProfessionalQualification obj=new ReportProfessionalQualification(al_qualification);
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
				MainFrame.container.add(new ManageProSkillSet(count,"navigate"));
				MainFrame.container.setVisible(false);
				MainFrame.container.setVisible(true);
		}
		if(ae.getSource()==btn_previousRecord){
		                  Component[] c=MainFrame.container.getComponents();
        			  for(int i=0;i<c.length;i++)
			  {
       			           
		                	  MainFrame.container.remove(c[i]);
			  }
				MainFrame.container.add(new ManageProPersonal(count,"navigate"));
				MainFrame.container.setVisible(false);
				MainFrame.container.setVisible(true);
		}

		if(ae.getSource()==btn_delete){
			String result=db_obj.deleteProfessionalQualification(Integer.parseInt(tf_qId.getText()));
			if(result.equals("deleted")){
				MainFrame.staticArraylist=db_obj.getProfessionalContainerDetails();
				if(MainFrame.staticArraylist.size()>0){
					ProfessionalContainerBean pro_container=(ProfessionalContainerBean)MainFrame.staticArraylist.get(count);
					ArrayList al_qualification=pro_container.getQualification();
					if(al_qualification.size()>0){
						localCount=0;
						ProfessionalQualificationBean pro_obj=(ProfessionalQualificationBean)al_qualification.get(localCount);
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
		//tf_proId.setText("");
		tf_qId.setText("");
		tf_qname.setText("");
		tf_branch.setText("");
		tf_yop.setText("");
		tf_uniBoard.setText("");
		tf_coll_Institute.setText("");
		tf_perMarks.setText("");
	}

	public void showRecords(ProfessionalQualificationBean pro_obj){
		tf_proId.setText(String.valueOf(pro_obj.getProfId()));
		tf_qId.setText(String.valueOf(pro_obj.getQualificationId()));
		tf_qname.setText(pro_obj.getQualificationName());
		tf_branch.setText(pro_obj.getBranch());
		tf_yop.setText(pro_obj.getYearOfPassing());
		tf_uniBoard.setText(pro_obj.getUniversityBoard());
		tf_coll_Institute.setText(pro_obj.getCollegeInstitute());
		tf_perMarks.setText(String.valueOf(pro_obj.getPerMarks()));

		lblMessage.setText("");
		btn_save.setEnabled(false);
		editSaveFlag="";
	}

	
}