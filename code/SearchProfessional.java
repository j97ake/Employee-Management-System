import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import com.alpha.prts.*;
import java.util.ArrayList;
import java.util.Iterator;
class SearchProfessional extends JPanel implements ActionListener
{
	JPanel panelCenter,panelSouth,panelEast,panel_heading;
	JButton btn_search,btn_cancel;

	JLabel lbl_companyId,lbl_companyName,lbl_selectSearch,lblMessage,lbl_heading;

	JLabel lbl_empty1,lbl_empty2,lbl_empty3,lbl_empty4,lbl_empty5,lbl_empty6,lbl_empty7,lbl_empty8,lbl_empty9,lbl_empty10,lbl_empty11,lbl_empty12,lbl_empty13,lbl_empty14,lbl_empty15,lbl_empty16,lbl_empty17,lbl_empty18,lbl_empty19,lbl_empty20;
	JLabel lbl_empty21,lbl_empty22,lbl_empty23,lbl_empty24,lbl_empty25,lbl_empty26,lbl_empty27,lbl_empty28,lbl_empty29,lbl_empty30,lbl_empty31,lbl_empty32,lbl_empty33,lbl_empty34,lbl_empty35,lbl_empty36,lbl_empty37,lbl_empty38,lbl_empty39,lbl_empty40;
	JLabel lbl_empty41,lbl_empty42,lbl_empty43,lbl_empty44,lbl_empty45,lbl_empty46,lbl_empty47,lbl_empty48,lbl_empty49,lbl_empty50;

	JComboBox combo_companyId,combo_companyName;
	JCheckBox check_experience,check_salary,check_skillSet,check_qualification;


	prtsDb db_obj=new prtsDb();
	int count=0;
	String editSaveFlag="none";
	
	public SearchProfessional(){}
	{
		setLayout(new BorderLayout(5,5));
		setSize(800,500);
		panelCenter=new JPanel();
		panelSouth=new JPanel();
		panelEast=new JPanel();
		panel_heading=new JPanel();

	


		check_experience=new JCheckBox("     Experience");
		check_experience.addActionListener(this);

		check_salary=new JCheckBox("     Salary");
		check_salary.addActionListener(this);

		check_skillSet=new JCheckBox("     Skill Set");
		check_skillSet.addActionListener(this);

		check_qualification=new JCheckBox("     Qualification");	
		check_qualification.addActionListener(this);	

		btn_search=new JButton("Search");
		btn_search.addActionListener(this);
	
		btn_cancel=new JButton("Cancel");	
		btn_cancel.addActionListener(this);
		

		combo_companyId=new JComboBox();
		combo_companyId.addItem("Select Company Id");
		combo_companyName=new JComboBox();
		combo_companyName.addItem("Select Company Name");		

		ArrayList al_pro=db_obj.getAllCompanies();
		if(al_pro.size()>0){
			Iterator itr = al_pro.iterator();
			while(itr.hasNext()){
				combo_companyId.addItem((Integer)itr.next());
				combo_companyName.addItem((String)itr.next());			
			}
		}

		lbl_companyId=new JLabel("Company Id");
		lbl_companyName=new JLabel("Company Name");
		
		lbl_selectSearch=new JLabel("Select Criteria for search");
		lbl_selectSearch.setHorizontalAlignment(SwingConstants.RIGHT);
		lbl_selectSearch.setFont(new Font("Tahoma", 1, 14));

		lbl_heading=new JLabel("Search Professional");
		lbl_heading.setHorizontalAlignment(SwingConstants.CENTER);
		lbl_heading.setFont(new Font("Tahoma", 1, 18));

		lblMessage=new JLabel("");
		lblMessage.setHorizontalAlignment(SwingConstants.CENTER);
		lblMessage.setFont(new Font("Tahoma", 1, 11));
		lblMessage.setForeground(Color.red);


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
		lbl_empty11=new JLabel("");
		lbl_empty12=new JLabel("");
		lbl_empty13=new JLabel("");
		lbl_empty14=new JLabel("");
		lbl_empty15=new JLabel("");
		lbl_empty16=new JLabel("");
		lbl_empty17=new JLabel("");
		lbl_empty18=new JLabel("");
		lbl_empty19=new JLabel("");
		lbl_empty20=new JLabel("");
		lbl_empty21=new JLabel("");
		lbl_empty22=new JLabel("");
		lbl_empty23=new JLabel("");
		lbl_empty24=new JLabel("");
		lbl_empty25=new JLabel("");
		lbl_empty26=new JLabel("");
		lbl_empty27=new JLabel("");
		lbl_empty28=new JLabel("");
		lbl_empty29=new JLabel("");
		lbl_empty30=new JLabel("");
		lbl_empty31=new JLabel("");
		lbl_empty32=new JLabel("");
		lbl_empty33=new JLabel("");
		lbl_empty34=new JLabel("");
		lbl_empty35=new JLabel("");
		lbl_empty36=new JLabel("");
		lbl_empty37=new JLabel("");
		lbl_empty38=new JLabel("");
		lbl_empty39=new JLabel("");
		lbl_empty40=new JLabel("");
		lbl_empty41=new JLabel("");

		panelCenter.setLayout(new GridLayout(15,4,10,15));
		panelSouth.setLayout(new FlowLayout());
		panel_heading.setLayout(new GridLayout(4,1));
		
		panel_heading.add(lbl_empty1);
		panel_heading.add(lbl_heading);
		panel_heading.add(lbl_empty2);
		panel_heading.add(lblMessage);
		
		panelCenter.add(lbl_empty3);
		panelCenter.add(lbl_companyId);
		panelCenter.add(combo_companyId);
		panelCenter.add(lbl_empty4);

		panelCenter.add(lbl_empty5);
		panelCenter.add(lbl_companyName);
		panelCenter.add(combo_companyName);
		panelCenter.add(lbl_empty6);

		panelCenter.add(lbl_empty7);
		panelCenter.add(lbl_selectSearch);

		panelCenter.add(lbl_empty8);
		panelCenter.add(lbl_empty9);


		panelCenter.add(lbl_empty10);
		panelCenter.add(lbl_empty11);
		panelCenter.add(check_experience);
		panelCenter.add(lbl_empty12);

		panelCenter.add(lbl_empty13);
		panelCenter.add(lbl_empty14);
		panelCenter.add(check_salary);
		panelCenter.add(lbl_empty15);

		panelCenter.add(lbl_empty16);
		panelCenter.add(lbl_empty17);
		panelCenter.add(check_skillSet);
		panelCenter.add(lbl_empty18);

		panelCenter.add(lbl_empty19);
		panelCenter.add(lbl_empty20);
		panelCenter.add(check_qualification);
		panelCenter.add(lbl_empty21);

		panelCenter.add(lbl_empty22);
		panelCenter.add(lbl_empty23);
		panelCenter.add(lbl_empty24);
		panelCenter.add(lbl_empty25);

		panelCenter.add(lbl_empty26);
		panelCenter.add(lbl_empty27);
		panelCenter.add(lbl_empty28);
		panelCenter.add(lbl_empty29);


		panelCenter.add(lbl_empty30);
		panelCenter.add(lbl_empty31);
		panelCenter.add(lbl_empty32);
		panelCenter.add(lbl_empty33);

		panelCenter.add(lbl_empty34);
		panelCenter.add(lbl_empty35);
		panelCenter.add(lbl_empty36);
		panelCenter.add(lbl_empty37);

		panelCenter.add(lbl_empty38);
		panelCenter.add(lbl_empty39);
		panelCenter.add(lbl_empty40);
		panelCenter.add(lbl_empty41);

		panelSouth.add(btn_search);
		panelSouth.add(btn_cancel);

		add(panel_heading,BorderLayout.NORTH);
		add(panelCenter,BorderLayout.CENTER);
		add(panelSouth,BorderLayout.SOUTH);
				
	}
	public void actionPerformed(ActionEvent ae)
	{
		Boolean checkExperience=check_experience.isSelected();
		Boolean checkSalary=check_salary.isSelected();
		Boolean checkSkillSet=check_skillSet.isSelected();
		Boolean checkQualification=check_qualification.isSelected();
		int indexId;
		int indexName;
		int companyId;
		if(ae.getSource()==btn_search){
			{
				int flag=0;
				indexId=combo_companyId.getSelectedIndex();
				indexName=combo_companyName.getSelectedIndex();
				if(indexId==0 &&  indexName==0){
					lblMessage.setText("Select company Id or company Name");
					flag=1;
				}
				else if(indexId!=0 && indexName!=0){
					lblMessage.setText("Select Either company Id or company Name");
					flag=1;
				}
				else if(!checkSalary && !checkExperience && !checkSkillSet && !checkQualification){
					lblMessage.setText("Select search criteria");
					flag=1;
				}		
				if(flag==0){
					if(indexName!=0){
						companyId=(Integer)combo_companyId.getItemAt(indexName);
					}
					else{
						companyId=(Integer)combo_companyId.getItemAt(indexId);
					}
					ArrayList al_professional=db_obj.searchProfessional1(checkExperience,checkSalary,checkSkillSet,checkQualification,companyId);
					if(al_professional.size()>0){
						ReportSearchProfessional obj=new ReportSearchProfessional(al_professional);
						lblMessage.setText("");
					}
					else{		
						lblMessage.setText("No professional found matching selected criteria");
					}
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
			/*
			combo_companyId.setSelectedIndex(0);
			combo_companyName.setSelectedIndex(0);
			check_experience.setSelected(false);
			check_salary.setSelected(false);
			check_skillSet.setSelected(false);
			check_qualification.setSelected(false);
			*/	
		}		
	}
}