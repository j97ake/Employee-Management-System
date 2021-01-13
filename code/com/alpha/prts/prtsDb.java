package com.alpha.prts;

import java.sql.*;
import java.util.ArrayList;

public class prtsDb{
	Connection con=Conn.makeConnection();
	Statement st,st1,st2,st3;
	ResultSet rs,rs1,rs2,rs3;
	ArrayList arraylist_db;
	EmployeeBean empBean_obj;
	ProfessionalPersonalBean  proPersonalBean_obj;
	ProfessionalSkillSetBean proBean_obj;
	
	public prtsDb(){
		try{
			st=con.createStatement();
			st1=con.createStatement();
			st2=con.createStatement();
			st3=con.createStatement();
		}
		catch(Exception e){
			System.out.println("Exception in creating statement "+e);
		}
		
	}

	public String validateUser(String username,String password){
		try{
			rs=st.executeQuery("select * from tblLogin where username='"+username+"' ");
			if(rs.next()){
				if(rs.getString(3).equals(password)){
					return rs.getString(4);
				}
				else{
					return "invalid";
				}
			}
			else{
				return "invalid";
			}
			
		}
		catch(Exception e){
			System.out.println("Exception in method validateUser() of prtsdb "+e);
		}
		return "invalid";	
	}

	public ArrayList getEmployeeRecords(){
		arraylist_db=new ArrayList();		
		try{
			rs=st.executeQuery("select w.empId,w.username,w.empPassword,w.empStatus,x.empFName,x.empMName,x.empLName,x.empFFName,x.empFMName,x.empFLName,x.empAddress,x.empMobile,x.empEmail,x.empDoB,x.empPAN from tblLogin as w, tblEmpPersonal as x where w.empId=x.empId");
			
			while(rs.next())
			{
				empBean_obj=new EmployeeBean();
			
				empBean_obj.setEmpId(rs.getInt("empid"));
				empBean_obj.setUsername(rs.getString("username"));
				empBean_obj.setEmpPassword(rs.getString("empPassword"));
				empBean_obj.setEmpStatus(rs.getString("empStatus"));
				empBean_obj.setEmpFName(rs.getString("empFName"));
				empBean_obj.setEmpMName(rs.getString("empMName"));
				empBean_obj.setEmpLName(rs.getString("empLName"));
				empBean_obj.setEmpFFName(rs.getString("empFFName"));
				empBean_obj.setEmpFMName(rs.getString("empFMName"));
				empBean_obj.setEmpFLName(rs.getString("empFLName"));
				empBean_obj.setEmpAddress(rs.getString("empAddress"));
				empBean_obj.setEmpMobile(rs.getString("empMobile"));
				empBean_obj.setEmpEmail(rs.getString("empEmail"));				
				empBean_obj.setEmpDOB(rs.getString("empDoB"));
				empBean_obj.setEmpPAN(rs.getString("empPAN"));
				
				arraylist_db.add(empBean_obj);

			}
		}
		catch(Exception e){
			System.out.println("Exception in method getEmployeeRecords of prtsdb "+e);
		}
		//System.out.println(arraylist_db.size());
		return arraylist_db;
		
	}

	public String saveEmployeeDetails(String username,String password,String status,String Emp_FName,String Emp_MName,String Emp_LName,String Emp_FFName,String Emp_FMName,String Emp_FLName,String Emp_Address,String Emp_Mobile,String Emp_Email,String Emp_DOB,String Emp_PAN){
		try{
			rs=st.executeQuery("select * from tblLogin where username='"+username+"'");
			if(rs.next()){
				return "exists";
			}
			else{
				int i=st.executeUpdate("insert into tblLogin (username,empPassword,empStatus) values ('"+username+"','"+password+"','"+status+"')");
				if(i>0){
					rs=st.executeQuery("select max(empid) from tblLogin");
					if(rs.next()){
						i=st.executeUpdate("insert into tblEmpPersonal values('"+rs.getInt(1)+"','"+Emp_FName+"','"+Emp_MName+"','"+Emp_LName+"','"+Emp_FFName+"','"+Emp_FMName+"','"+Emp_FLName+"','"+Emp_Address+"','"+Emp_Mobile+"','"+Emp_Email+"','"+Emp_DOB+"','"+Emp_PAN+"')");	
						if(i>0){
							return "saved";
						}
					}
				}
			}
		}
		catch(Exception e){
			System.out.println("Exception in method saveEmployeeDetails of prtsdb "+e);
		}
		return "failed";
	}

	public String updateEmployeeDetails(int Emp_ID,String username,String EmpPassword,String EmpStatus,String Emp_FName,String Emp_MName,String EmpLName,String Emp_FFName,String Emp_FMName,String Emp_FLName,String Emp_Address,String Emp_Mobile,String Emp_Email,String Emp_DOB,String Emp_PAN)
	{
		String message="";
		try
		{
			int i=st.executeUpdate("update tbllogin set username='"+username+"',empPassword='"+EmpPassword+"',empStatus='"+EmpStatus+"' WHERE empId='"+Emp_ID+"';");
			int j=st.executeUpdate("update tblEmpPersonal set empFName='"+Emp_FName+"',empMName='"+Emp_MName+"',EmpLName='"+EmpLName+"',EmpFFName='"+Emp_FFName+"',EmpFMName='"+Emp_FMName+"',EmpFLName='"+Emp_FLName+"',EmpAddress='"+Emp_Address+"',EmpMobile='"+Emp_Mobile+"',EmpEmail='"+Emp_Email+"',EmpDOB='"+Emp_DOB+"',EmpPAN='"+Emp_PAN+"' WHERE EmpID='"+Emp_ID+"';");				
			if(i>0 && j>0){
				return "updated";
			}
		}
		catch(Exception e)
		{
			System.out.println("Exception in method updateEmployeeDetails of prtsdb "+e);
		}
		return "failed"; 
	}
	

	public ArrayList getProfessionalPersonalRecords(){
		arraylist_db=new ArrayList();		
		try{
			rs=st.executeQuery("select x.profId,x.profFName,x.profMName,x.profLName,x.profFFName,x.profFMName,x.profFLName,x.profAddress,x.profMobile,x.profEmail,x.profDoB,x.profPAN,x.profExpectedSalary from tblPersonal as x");
			
			while(rs.next())
			{
				proPersonalBean_obj=new ProfessionalPersonalBean();
			
				proPersonalBean_obj.setProfId(rs.getInt("profId"));
				proPersonalBean_obj.setProfFName(rs.getString("profFName"));
				proPersonalBean_obj.setProfMName(rs.getString("profMName"));
				proPersonalBean_obj.setProfLName(rs.getString("profLName"));
				proPersonalBean_obj.setProfFFName(rs.getString("profFFName"));
				proPersonalBean_obj.setProfFMName(rs.getString("profFMName"));
				proPersonalBean_obj.setProfFLName(rs.getString("profFLName"));
				proPersonalBean_obj.setProfAddress(rs.getString("profAddress"));
				proPersonalBean_obj.setProfMobile(rs.getString("profMobile"));
				proPersonalBean_obj.setProfEmail(rs.getString("profEmail"));				
				proPersonalBean_obj.setProfDOB(rs.getString("profDoB"));
				proPersonalBean_obj.setProfPAN(rs.getString("profPAN"));
				proPersonalBean_obj.setProfExpectedSalary(rs.getDouble("profExpectedSalary")) ;
				
				arraylist_db.add(proPersonalBean_obj);

			}
		}
		catch(Exception e){
			System.out.println("Exception in method getProfessionalPersonalRecords of prtsdb "+e);
		}
		return arraylist_db;
	}

	public ArrayList getProfessionalSkillSetRecords(){
		arraylist_db=new ArrayList();
		ProfessionalSkillSetBean skill_obj;
		try{
			rs=st.executeQuery("select profId,OperatingSystem,Technologies,ScriptingLanguages,DB,Frameworks,TestingTools,OtherSkills from tblProfSkillSet");
			while(rs.next()){
				skill_obj=new ProfessionalSkillSetBean();
				skill_obj.setProfId(rs.getInt("profId"));
				skill_obj.setOperatingSystem(rs.getString("OperatingSystem"));
				skill_obj.setTechnologies(rs.getString("Technologies"));
				skill_obj.setScriptingLanguages(rs.getString("ScriptingLanguages"));
				skill_obj.setDatabases(rs.getString("DB"));
				skill_obj.setFrameworks(rs.getString("Frameworks"));
				skill_obj.setTestingTools(rs.getString("TestingTools"));
				skill_obj.setOtherSkills(rs.getString("OtherSkills"));
				arraylist_db.add(skill_obj);
			}
		}
		catch(Exception e){
			System.out.println("Exception in method getProfessionalSkillSetRecords of prtsdb "+e);
		}
		return arraylist_db;
	}
	public String saveProfessionalDetails(String profFName,String profMName,String profLName,String profFFName,String profFMName,String profFLName,String profAddress,String profMobile,String profEmail,String profDOB,String profPAN,Double profExpectedSalary){
		try{
			int l=0;	
			int i=st.executeUpdate("insert into tblPersonal (ProfFName,ProfMName,ProfLName,ProfFFName,ProfFMName,ProfFLName,ProfAddress,ProfMobile,ProfEmail,ProfDOB,ProfPAN,ProfExpectedSalary)values('"+profFName+"','"+profMName+"','"+profLName+"','"+profFFName+"','"+profFMName+"','"+profFLName+"','"+profAddress+"','"+profMobile+"','"+profEmail+"','"+profDOB+"','"+profPAN+"','"+profExpectedSalary+"')");	
			if(i>0){
				rs=st.executeQuery("select max(profid) from tblPersonal");
				if(rs.next()){
					int profid=rs.getInt(1);
					i=st.executeUpdate("insert into tblProfQualification (profId)  values ('"+profid+"')");	
					int j=st.executeUpdate("insert into tblProfSkillSet (profId)  values ('"+profid+"')");	
					int k=st.executeUpdate("insert into tblExperience (profId)  values ('"+profid+"')");	
					rs1=st1.executeQuery("select max(experienceId) from tblExperience");
					if(rs1.next()){
						int experienceId=rs1.getInt(1);
						l=st.executeUpdate("insert into tblProjectDetails (profId,experienceId)  values ('"+profid+"',"+experienceId+")");
					}
					if(i>0 && j>0 && k>0 && l >0){
					//if(j>0){
						System.out.println("all saved");
						return "saved";
					}
				}
			}
		}
		catch(Exception e){
			System.out.println("Exception in method saveProfessionalDetails of prtsdb "+e);
		}
		return "failed";
	}
	public String updateProPersonalDetails(int profId,String profFName,String profMName,String profLName,String profFFName,String profFMName,String profFLName,String profAddress,String profMobile,String profEmail,String profDOB,String profPAN,Double profExpectedSalary)
	{
		String message="";
		try
		{
			int j=st.executeUpdate("update tblPersonal set profFName='"+profFName+"',profMName='"+profMName+"',profLName='"+profLName+"',profFFName='"+profFFName+"',profFMName='"+profFMName+"',profFLName='"+profFLName+"',profAddress='"+profAddress+"',profMobile='"+profMobile+"',profEmail='"+profEmail+"',profDOB='"+profDOB+"',profPAN='"+profPAN+"',ProfExpectedSalary='"+profExpectedSalary+"' WHERE profId='"+profId+"';");				
			if(j>0){
				return "updated";
			}
		}
		catch(Exception e)
		{
			System.out.println("Exception in method updateProfessionalDetails of prtsdb "+e);
		}
		return "failed"; 
	}

	public String saveProQualificationDetails(int profId,String QualificationName,String Branch,String  YearOfPassing,String UniversityBoard,String CollegeInstitute,Double perMarks)
	{
		String message="";
		try
		{
			int j=st.executeUpdate("insert into tblProfQualification (profid,QualificationName,Branch,YearOfPassing,UniversityBoard,CollegeInstitute,perMarks) values ("+profId+",'"+QualificationName+"','"+Branch+"','"+YearOfPassing+"','"+UniversityBoard+"','"+CollegeInstitute+"','"+perMarks+"') ");				
			if(j>0){
				return "saved";
			}
		}
		catch(Exception e)
		{
			System.out.println("Exception in method saveProQualificationDetails of prtsdb "+e);
		}
		return "failed"; 
	}

	public String updateProQualificationDetails(int qId,int profId,String QualificationName,String Branch,String  YearOfPassing,String UniversityBoard,String CollegeInstitute,Double perMarks)
	{
		String message="";
		try
		{
			int j=st.executeUpdate("update tblProfQualification set QualificationName='"+QualificationName+"',Branch='"+Branch+"',YearOfPassing='"+YearOfPassing+"',UniversityBoard='"+UniversityBoard+"',CollegeInstitute='"+CollegeInstitute+"',perMarks='"+perMarks+"' WHERE  QualificationId='"+qId+"'  and profId='"+profId+"' ");				
			if(j>0){
				return "updated";
			}
		}
		catch(Exception e)
		{
			System.out.println("Exception in method updateProQualificationDetails of prtsdb "+e);
		}
		return "failed"; 
	}

	public String saveProSkillSetDetails(int profId,String OperatingSystem,String Technologies,String  ScriptingLanguages,String Databases,String Frameworks,String TestingTools,String OtherSkills)
	{
		String message="";
		try
		{
			int j=st.executeUpdate("insert into tblProfSkillSet  (profId,OperatingSystem,Technologies,ScriptingLanguages,DB,Frameworks,TestingTools,OtherSkills) values ("+profId+",'"+OperatingSystem+"','"+Technologies+"','"+ScriptingLanguages+"','"+Databases+"','"+Frameworks+"','"+TestingTools+"','"+OtherSkills+"') ");				
			if(j>0){
				return "saved";
			}
		}
		catch(Exception e)
		{
			System.out.println("Exception in method saveProSkillSetDetails of prtsdb "+e);
		}
		return "failed"; 
	}
	public String updateProSkillSetDetails(int profId,String OperatingSystem,String Technologies,String  ScriptingLanguages,String Database,String Frameworks,String TestingTools,String OtherSkills)
	{
		String message="";
		try
		{
			//System.out.println("update tblProfSkillSet set OperatingSystem='"+OperatingSystem+"',Technologies='"+Technologies+"',DB='"+Database+"',ScriptingLanguages='"+ScriptingLanguages+"',Frameworks='"+Frameworks+"',TestingTools='"+TestingTools+"',OtherSkills='"+OtherSkills+"' WHERE profId="+profId);
			int j=st.executeUpdate("update tblProfSkillSet set OperatingSystem='"+OperatingSystem+"',Technologies='"+Technologies+"',ScriptingLanguages='"+ScriptingLanguages+"',DB='"+Database+"',Frameworks='"+Frameworks+"',TestingTools='"+TestingTools+"',OtherSkills='"+OtherSkills+"' WHERE profId="+profId);				
			if(j>0){
				return "updated";
			}
		}
		catch(Exception e)
		{
			System.out.println("Exception in  updateProSkillSetDetails of prtsdb "+e);
		}
		return "failed"; 
	}
	public String updateProExperienceDetails(int experienceId,int profId,String StartDate,String EndDate,String  CompanyName,String Location,String Designation,String JobProfile,Double SalaryPackage)
	{
		String message="";
		try
		{
			int j=st.executeUpdate("update tblExperience set StartDate='"+StartDate+"',EndDate='"+EndDate+"',CompanyName='"+CompanyName+"',Location='"+Location+"',Designation='"+Designation+"',JobProfile='"+JobProfile+"',SalaryPackage='"+SalaryPackage+"' WHERE profId="+profId+" and experienceId="+experienceId);				
			if(j>0){
				return "updated";
			}
		}
		catch(Exception e)
		{
			System.out.println("Exception in method updateProExperienceDetails of prtsdb "+e);
		}
		return "failed"; 
	}
	public String saveProExperienceDetails(int profId,String StartDate,String EndDate,String  CompanyName,String Location,String Designation,String JobProfile,Double SalaryPackage)
	{
		String message="";
		try
		{
			int j=st.executeUpdate("insert into tblExperience (profId,StartDate,EndDate,CompanyName,Location,Designation,JobProfile,SalaryPackage) values ("+profId+",'"+StartDate+"','"+EndDate+"','"+CompanyName+"','"+Location+"','"+Designation+"','"+JobProfile+"','"+SalaryPackage+"')");				
			if(j>0){
				rs=st.executeQuery("select max(experienceId) from tblExperience");
				if(rs.next()){
					int experienceId=rs.getInt(1);
					st.executeUpdate("insert into tblProjectDetails (profId,experienceId)  values ('"+profId+"',"+experienceId+")");
					return "saved";
				}
			}
		}
		catch(Exception e)
		{
			System.out.println("Exception in method saveProExperienceDetails of prtsdb "+e);
		}
		return "failed"; 
	}	
	public String updateProProjectDetails(int projectId,String ProjectName,String ProjectDescription,String ProjectStartDate,String  ProjectEndDate,String TechnologiesLanguages,int experienceId)
	{
		String message="";
		try
		{
			int j=st.executeUpdate("update tblProjectDetails set ProjectName='"+ProjectName+"',ProjectStartDate='"+ProjectStartDate+"',ProjectEndDate='"+ProjectEndDate+"',TechnologiesLanguages='"+TechnologiesLanguages+"',ProjectDescription='"+ProjectDescription+"',experienceId="+experienceId+"  WHERE ProjectId='"+projectId+"' ");				
			if(j>0){
				return "updated";
			}
		}
		catch(Exception e)
		{
			System.out.println("Exception in method updateProProjectDetails of prtsdb "+e);
		}
		return "failed"; 
	}	
	public String saveProProjectDetails(int profId,String ProjectName,String ProjectDescription,String ProjectStartDate,String ProjectEndDate,String TechnologiesLanguages,int experienceId)
	{
		String message="";
		try
		{
			int j=st.executeUpdate("insert into tblProjectDetails (profId,ProjectName,ProjectDescription,ProjectStartDate,ProjectEndDate,TechnologiesLanguages,experienceId) values ("+profId+",'"+ProjectName+"','"+ProjectDescription+"','"+ProjectStartDate+"','"+ProjectEndDate+"','"+TechnologiesLanguages+"',"+experienceId+")");
			if(j>0){
				return "saved";
			}
		}
		catch(Exception e)
		{
			System.out.println("Exception in method saveProProjectDetails of prtsdb "+e);
		}
		return "failed"; 
	}

	public ArrayList getProfessionalContainerDetails(){
		ArrayList arraylist_pro=new ArrayList();

		ArrayList arraylist_qual;
		ArrayList arraylist_experience;
		ArrayList arraylist_project;		

		ProfessionalContainerBean containerBean;

		ProfessionalPersonalBean personal_obj;
		ProfessionalQualificationBean qualification_obj;
		ProfessionalSkillSetBean skill_obj;
		ProfessionalExperienceBean experience_obj;
		ProjectDetailsBean project_obj;

		try{
			rs=st.executeQuery("select a.profId,a.profFName,a.profMName,a.profLName,a.profFFName,a.profFMName,a.profFLName,a.profAddress,a.profMobile,a.profEmail,a.profDOB,a.profPAN,a.profExpectedSalary,b.OperatingSystem,b.Technologies,b. ScriptingLanguages,b.DB,b.Frameworks,b.TestingTools,b.OtherSkills from tblPersonal as a,tblProfSkillSet as b WHERE a.profId=b.profId");
			while(rs.next()){
				containerBean=new ProfessionalContainerBean();

				int profid=rs.getInt("profId");
				personal_obj=new ProfessionalPersonalBean();
				personal_obj.setProfId(profid);
				personal_obj.setProfFName(rs.getString("profFName"));
				personal_obj.setProfMName(rs.getString("profMName"));
				personal_obj.setProfLName(rs.getString("profLName"));
				personal_obj.setProfFFName(rs.getString("profFFName"));
				personal_obj.setProfFMName(rs.getString("profFMName"));
				personal_obj.setProfFLName(rs.getString("profFLName"));
				personal_obj.setProfAddress(rs.getString("profAddress"));
				personal_obj.setProfMobile(rs.getString("profMobile"));
				personal_obj.setProfEmail(rs.getString("profEmail"));				
				personal_obj.setProfDOB(rs.getString("profDoB"));
				personal_obj.setProfPAN(rs.getString("profPAN"));
				personal_obj.setProfExpectedSalary(rs.getDouble("profExpectedSalary")) ;
					
			
				containerBean.setPersonalDetail(personal_obj);
				
				rs1=st1.executeQuery("select * from tblProfQualification where profId="+profid);
				arraylist_qual=new ArrayList();
				while(rs1.next()){
					
					qualification_obj=new ProfessionalQualificationBean();
					qualification_obj.setProfId(rs1.getInt("profId"));
					qualification_obj.setQualificationId(rs1.getInt("QualificationId"));
					qualification_obj.setQualificationName(rs1.getString("QualificationName"));
					qualification_obj.setBranch(rs1.getString("Branch"));	
					qualification_obj.setYearOfPassing(rs1.getString("YearOfPassing"));
					qualification_obj.setUniversityBoard(rs1.getString("UniversityBoard"));
					qualification_obj.setCollegeInstitute(rs1.getString("CollegeInstitute"));
					qualification_obj.setPerMarks(rs1.getDouble("perMarks"));
					
					arraylist_qual.add(qualification_obj);
				}				
				containerBean.setQualification(arraylist_qual);

				skill_obj=new ProfessionalSkillSetBean();
				skill_obj.setProfId(profid);
				skill_obj.setOperatingSystem(rs.getString("OperatingSystem"));
				skill_obj.setTechnologies(rs.getString("Technologies"));
				skill_obj.setScriptingLanguages(rs.getString("ScriptingLanguages"));
				skill_obj.setDatabases(rs.getString("DB"));
				skill_obj.setFrameworks(rs.getString("Frameworks"));
				skill_obj.setTestingTools(rs.getString("TestingTools"));
				skill_obj.setOtherSkills(rs.getString("OtherSkills"));
			
				containerBean.setSkillSet(skill_obj);


				rs2=st2.executeQuery("select * from tblExperience where profId="+profid);
				arraylist_experience=new ArrayList();
				while(rs2.next()){
					experience_obj=new ProfessionalExperienceBean();
					experience_obj.setProfId(profid);
					experience_obj.setExperienceId(rs2.getInt("ExperienceId"));					
					experience_obj.setStartDate(rs2.getString("StartDate"));
					experience_obj.setEndDate(rs2.getString("EndDate"));
					experience_obj.setCompanyName(rs2.getString("CompanyName"));
					experience_obj.setLocation(rs2.getString("Location"));
					experience_obj.setDesignation(rs2.getString("Designation"));
					experience_obj.setJobProfile(rs2.getString("JobProfile"));
					experience_obj.setSalaryPackage(rs2.getDouble("SalaryPackage"));
					
					arraylist_experience.add(experience_obj);
				}				
				containerBean.setExperience(arraylist_experience);



				rs3=st3.executeQuery("select * from tblProjectDetails where profId="+profid);
				arraylist_project=new ArrayList();
				while(rs3.next()){
					project_obj=new ProjectDetailsBean();
					project_obj.setProjectId(rs3.getInt("ProjectId"));
					project_obj.setProfId(profid);
					project_obj.setExperienceId(rs3.getInt("ExperienceId"));	
					project_obj.setProjectName(rs3.getString("ProjectName"));
					project_obj.setProjectDescription(rs3.getString("ProjectDescription"));				
					project_obj.setProjectStartDate(rs3.getString("ProjectStartDate"));
					project_obj.setProjectEndDate(rs3.getString("ProjectEndDate"));
					project_obj.setTechnologiesLanguages(rs3.getString("TechnologiesLanguages"));
				
					arraylist_project.add(project_obj);
				}				
				containerBean.setProject(arraylist_project);


				arraylist_pro.add(containerBean);
				
			} 
		}
		catch(Exception e){
			System.out.println("Exception in getProfessionalContainerDetails of prtsdb : "+e);
		}

		return arraylist_pro;
		
	}	



	public String deleteProfessional(int profId){
		try{
			st.executeUpdate("delete from tblPersonal where profId="+profId);
			return "deleted";
		}
		catch(Exception e){
			System.out.println("Exception in method deleteProfessional of prtsdb "+e);
		}
		return "failed";
	}
	
	public String deleteProfessionalQualification(int qId){
		try{
			st.executeUpdate("delete from tblProfQualification where QualificationId="+qId);
			return "deleted";
		}
		catch(Exception e){
			System.out.println("Exception in method deleteProfessionalQualification of prtsdb "+e);
		}
		return "failed";
	}	
	public String deleteProfessionalExperience(int expId){
		try{
			st.executeUpdate("delete from tblExperience where ExperienceId="+expId);
			return "deleted";
		}
		catch(Exception e){
			System.out.println("Exception in method deleteProfessionalExperience of prtsdb "+e);
		}
		return "failed";
	}
	public String deleteProfessionalProject(int proId){
		try{
			st.executeUpdate("delete from tblProjectDetails where ProjectId="+proId);
			return "deleted";
		}
		catch(Exception e){
			System.out.println("Exception in method deleteProfessionalProduct of prtsdb "+e);
		}
		return "failed";
	}		


//***********************************************Company *********************************************//
			
	public int saveCompanyInfo(String CompanyName,String Address,String City,String State,String Country,String Pin,String Phone,String Email){
		try{
			int i=st.executeUpdate("insert into tblCompany(CompanyName,Address,City,State,Country,Pin,Phone,Email) values ('"+CompanyName+"','"+Address+"','"+City+"','"+State+"','"+Country+"','"+Pin+"','"+Phone+"','"+Email+"')");
			if(i>0){	
				rs=st.executeQuery("select max(companyId) from tblCompany");
				if(rs.next()){
					int companyId=rs.getInt(1);
					int j=st.executeUpdate("insert into tblRequirementSkillSet (companyId) values("+companyId+")");
					int k=st.executeUpdate("insert into tblCompanyRequirements (companyId) values("+companyId+")");
					if(j>0 && k>0){
						System.out.println("company info saved");
						//return "saved";
						return companyId;
					}
				}
			}
		}
		catch(Exception e){
			System.out.println("Exception in method saveCompanyInfo of prtsdb "+e);
		}
		//return "failed";
		return 0;
	}	
	public String updateCompanyInfo(int CompanyId,String CompanyName,String Address,String City,String State,String Country,String Pin,String Phone,String Email){
		try{
			int i=st.executeUpdate("update  tblCompany set CompanyName='"+CompanyName+"',Address='"+Address+"',City='"+City+"',State='"+State+"',Country='"+Country+"',Pin='"+Pin+"',Phone='"+Phone+"',Email='"+Email+"' WHERE CompanyId="+CompanyId );
			System.out.println("updated");
			return "updated";
			
		}
		catch(Exception e){
			System.out.println("Exception in method updateCompanyInfo of prtsdb "+e);
		}
		return "failed";
	}	

	public String saveCompanyRequirements(int Experience,Double SalaryOffered,String Qualification){
		try{
			int i=st.executeUpdate("insert into tblCompanyRequirements (Experience,SalaryOffered,Qualification) values ('"+Experience+"','"+SalaryOffered+"','"+Qualification+"')");
			if(i>0){	
				System.out.println("company requirement saved");
				return "saved";
			}
		}
		catch(Exception e){
			System.out.println("Exception in method saveCompanyRequirements of prtsdb "+e);
		}
		return "failed"	;
	}	
	public String updateCompanyRequirements(int CompanyId,int Experience,Double SalaryOffered,String Qualification){
		try{
			int i=st.executeUpdate("update  tblCompanyRequirements set Experience='"+Experience+"',SalaryOffered='"+SalaryOffered+"',Qualification='"+Qualification+"' WHERE CompanyId="+CompanyId );
			System.out.println("Company Requirements updated");
			return "updated";
			
		}
		catch(Exception e){
			System.out.println("Exception in method updateCompanyRequirements of prtsdb "+e);
		}
		return "failed";
	}

	public ArrayList getCompanyRecords( ){
		ArrayList arraylist_company=new ArrayList();
		CompanySkillSetBean company_obj;
		try{
			rs=st.executeQuery("select x.CompanyId,x.CompanyName,x.Address,x.City,x.State,x.Country,x.Pin,x.Phone,x.Email,y.Experience,y.SalaryOffered,y.Qualification,z.OperatingSystem,z.Technologies,z.ScriptingLanguages,z.DB,z.Frameworks,z.TestingTools,z.OtherSkills from tblCompany as x,tblCompanyRequirements as y,tblRequirementSkillSet as z where x.companyId=y.companyId and y.companyId=z.companyId");
			while (rs.next()){
				company_obj=new CompanySkillSetBean();
				company_obj.setCompanyId(rs.getInt("CompanyId"));
				company_obj.setCompanyName(rs.getString("CompanyName"));
				company_obj.setAddress(rs.getString("Address"));
				company_obj.setCity(rs.getString("City"));
				company_obj.setState(rs.getString("State"));
				company_obj.setCountry(rs.getString("Country"));
				company_obj.setPin(rs.getString("Pin"));
				company_obj.setPhone(rs.getString("Phone"));
				company_obj.setEmail(rs.getString("Email"));

				company_obj.setExperience(rs.getInt("experience"));
				company_obj.setSalaryOffered(rs.getDouble("SalaryOffered"));
				company_obj.setQualification(rs.getString("Qualification"));

				company_obj.setOperatingSystem(rs.getString("OperatingSystem"));
				company_obj.setTechnologies(rs.getString("Technologies"));
				company_obj.setScriptingLanguages(rs.getString("ScriptingLanguages"));
				company_obj.setDatabases(rs.getString("DB"));
				company_obj.setFrameworks(rs.getString("Frameworks"));
				company_obj.setTestingTools(rs.getString("TestingTools"));
				company_obj.setOtherSkills(rs.getString("OtherSkills"));

				arraylist_company.add(company_obj);

			}
		}
		catch(Exception e){
			System.out.println("Exception in method getCompanyRecords of prtsdb "+e);
		}
		return arraylist_company;
	}
		
	public String deleteCompany(int companyId){
		try{
			int i=st.executeUpdate("delete from tblCompany where CompanyId="+companyId);
			if(i>0){
				return "deleted";
			}
		}
		catch(Exception e){
			System.out.println("Exception in method deleteCompany of prtsdb "+e);
		}
		return "failed";
	}

/*	public String saveCompanySkillSetPrquirements(int CompanyId,String OperatingSystem,String Technologies,String  ScriptingLanguages,String Databases,String Frameworks,String TestingTools,String OtherSkills)
	{
		String message="";
		try
		{
			int j=st.executeUpdate("insert into tblProfSkillSet  (profId,OperatingSystem,Technologies,ScriptingLanguages,DB,Frameworks,TestingTools,OtherSkills) values ("+profId+",'"+OperatingSystem+"','"+Technologies+"','"+ScriptingLanguages+"','"+Databases+"','"+Frameworks+"','"+TestingTools+"','"+OtherSkills+"') ");				
			if(j>0){
				return "saved";
			}
		}
		catch(Exception e)
		{
			System.out.println("Exception in method saveProSkillSetDetails of prtsdb "+e);
		}
		return "failed"; 
	}
*/
	public String updateCompanySkillSetRequirements(int CompanyId,String OperatingSystem,String Technologies,String  ScriptingLanguages,String Database,String Frameworks,String TestingTools,String OtherSkills)
	{
		String message="";
		try
		{
			int j=st.executeUpdate("update tblRequirementSkillSet set OperatingSystem='"+OperatingSystem+"',Technologies='"+Technologies+"',ScriptingLanguages='"+ScriptingLanguages+"',DB='"+Database+"',Frameworks='"+Frameworks+"',TestingTools='"+TestingTools+"',OtherSkills='"+OtherSkills+"' WHERE CompanyId="+CompanyId);				
			if(j>0){
				return "updated";
			}
		}
		catch(Exception e)
		{
			System.out.println("Exception in  updateProSkillSetDetails of prtsdb "+e);
		}
		return "failed"; 
	}

	public String deleteEmployee(int empId){
		try{
			int i=st.executeUpdate("delete from tblLogin where EmpId="+empId);
			if(i>0){
				return "deleted";
			}
		}
		catch(Exception e){
			System.out.println("Exception in method deleteEmployee of prtsdb "+e);
		}
		return "failed";
	}

///*************************************************Search***************************************************//
	
	public ArrayList getAllProfessional(){
		ArrayList al=new ArrayList();
		try{
			rs=st.executeQuery("select profId,concat(profFName,' ',profLname) from tblPersonal");
			while(rs.next()){
				al.add(rs.getInt(1));
				al.add(rs.getString(2));			
			}
		}
		catch(Exception e){
			System.out.println("Exception in method getAllProfessional prtsdb "+e);
		}
		return al;
	}	

	
	public ArrayList getAllCompanies(){
		ArrayList al=new ArrayList();
		try{
			rs=st.executeQuery("select companyId,companyName from tblCompany");
			while(rs.next()){
				al.add(rs.getInt(1));
				al.add(rs.getString(2));			
			}
		}
		catch(Exception e){
			System.out.println("Exception in method getAllCompanies() of prtsdb "+e);
		}
		return al;
	}
	public ArrayList searchCompany(Boolean b_experience,Boolean b_salary,Boolean b_skillSet,Boolean b_qualification,int profId){
		ArrayList al_company=new ArrayList();
		Double salary=9999999.0;
		String operatingSystem="";
		String technologies="";
		String scriptingLanguages="";
		String databases="";
		String frameworks="";
		String testingTools="";
		String otherSkills="";
		String qualification="";
		CompanySkillSetBean company_obj;
		Date start=new Date(0);
	                Date end=new Date(0);
		int experience=0;
	             
		try{
			
			if(b_experience){
				rs=st.executeQuery("select startDate,endDate from tblExperience where profId="+profId);
				while(rs.next()){
					start=Date.valueOf(rs.getString("startDate"));
					end=Date.valueOf(rs.getString("endDate"));
				                int year=end.getYear()-start.getYear();
				                int month=end.getMonth()-start.getMonth();
				                if(month<0){
				                	  year=year-1;                  
				                }
					
					experience=experience+year;
				}
				System.out.println("Experience "+experience);
			}
			if(b_salary){
				rs=st.executeQuery("select profExpectedSalary from tblPersonal where profId="+profId);
				if(rs.next()){
					salary=rs.getDouble(1);
				}
	
				
			}
			if(b_skillSet){
				rs=st.executeQuery("select * from tblProfSkillSet where profId="+profId);
				while(rs.next()){
					operatingSystem=rs.getString("OperatingSystem");
					technologies=rs.getString("Technologies");
					scriptingLanguages=rs.getString("ScriptingLanguages");
					databases=rs.getString("DB");
					frameworks=rs.getString("Frameworks");
					testingTools=rs.getString("TestingTools");
					otherSkills=rs.getString("OtherSkills");
				}
			}	
					
			if(b_experience){
				rs=st.executeQuery("select x.CompanyId,x.CompanyName,x.Phone,x.Email,y.experience,y.salaryOffered from tblCompany as x,tblCompanyRequirements as y where x.companyId=y.companyId and y.experience<="+experience);
				while(rs.next()){
					company_obj=new CompanySkillSetBean();
					company_obj.setCompanyId(rs.getInt("CompanyId"));
					company_obj.setCompanyName(rs.getString("CompanyName"));
					company_obj.setPhone(rs.getString("Phone"));
					company_obj.setEmail(rs.getString("Email"));				
					company_obj.setSalaryOffered(rs.getDouble("SalaryOffered"));
					al_company.add(company_obj);						
				}		
			}
			if(b_salary){
				rs=st.executeQuery("select x.CompanyId,x.CompanyName,x.Phone,x.Email,y.SalaryOffered from tblCompany as x,tblCompanyRequirements as y where x.companyId=y.companyId and y.salaryOffered>="+salary);
				while(rs.next()){
					int flag=0;
					if(al_company.size()>0){
						for(int i=0;i<al_company.size();i++){
							company_obj=(CompanySkillSetBean)al_company.get(i);
							if(company_obj.getCompanyId()==rs.getInt("companyId")){	
								flag=1;
								break;	
							}
						}
					}
					if(flag==0){
						company_obj=new CompanySkillSetBean();
						company_obj.setCompanyId(rs.getInt("CompanyId"));
						company_obj.setCompanyName(rs.getString("CompanyName"));
						company_obj.setPhone(rs.getString("Phone"));
						company_obj.setEmail(rs.getString("Email"));				
						company_obj.setSalaryOffered(rs.getDouble("SalaryOffered"));
						al_company.add(company_obj);						
					}	
				}		
			}
			if(b_skillSet){
				rs=st.executeQuery("select x.CompanyId,x.CompanyName,x.Phone,x.Email,y.salaryOffered,z.OperatingSystem,z.Technologies,z.ScriptingLanguages,z.DB,z.Frameworks,z.TestingTools,z.OtherSkills from tblCompany as x,tblCompanyRequirements as y,tblRequirementSkillSet as z where x.companyId=z.companyId ");
				while(rs.next()){
					int flag=0;
					if(rs.getString("OperatingSystem").equalsIgnoreCase(operatingSystem) || rs.getString("Technologies").equalsIgnoreCase(technologies) || rs.getString("ScriptingLanguages").equalsIgnoreCase(scriptingLanguages) || rs.getString("DB").equalsIgnoreCase(databases) || rs.getString("Frameworks").equalsIgnoreCase(frameworks) || rs.getString("TestingTools").equalsIgnoreCase(testingTools) || rs.getString("OtherSkills").equalsIgnoreCase(otherSkills)){
						if(al_company.size()>0){
							for(int i=0;i<al_company.size();i++){
								company_obj=(CompanySkillSetBean)al_company.get(i);
								if(company_obj.getCompanyId()==rs.getInt("companyId")){	
									flag=1;
									break;	
								}
							}
						}
						if(flag==0){
							company_obj=new CompanySkillSetBean();
							company_obj.setCompanyId(rs.getInt("CompanyId"));
							company_obj.setCompanyName(rs.getString("CompanyName"));
							company_obj.setPhone(rs.getString("Phone"));
							company_obj.setEmail(rs.getString("Email"));				
							company_obj.setSalaryOffered(rs.getDouble("SalaryOffered"));
							al_company.add(company_obj);						
						}	
					}	
				}
			}
		
			if(b_qualification){
				rs=st.executeQuery("select qualificationName from tblProfQualification where profId="+profId);
				while(rs.next()){
					
					rs1=st1.executeQuery("select x.CompanyId,x.CompanyName,x.Phone,x.Email,y.Experience,y.SalaryOffered,y.Qualification from tblCompany as x,tblCompanyRequirements as y WHERE x.companyId=y.companyId and y.qualification='"+rs.getString(1)+"'");				
					if(rs1.next()){
						int flag=0;
						if(al_company.size()>0){
							for(int i=0;i<al_company.size();i++){
								company_obj=(CompanySkillSetBean)al_company.get(i);
								if(company_obj.getCompanyId()==rs1.getInt("companyId")){	
									flag=1;
									break;	
								}
							}
						}
						if(flag==0){
							company_obj=new CompanySkillSetBean();
							company_obj.setCompanyId(rs1.getInt("CompanyId"));
							company_obj.setCompanyName(rs1.getString("CompanyName"));
							company_obj.setPhone(rs1.getString("Phone"));
							company_obj.setEmail(rs1.getString("Email"));				
							company_obj.setSalaryOffered(rs1.getDouble("SalaryOffered"));
							al_company.add(company_obj);						
						}
						
					}
				}				
			}
		}
		catch(Exception e){
			System.out.println("Exception in method searchCompany of prtsdb "+e);
		}
		return al_company;
	}
	
	public ArrayList searchProfessional1(Boolean b_experience,Boolean b_salary,Boolean b_skillSet,Boolean b_qualification,int companyId){
		ArrayList al_professional=new ArrayList();
		Double salary=0.0;
		String operatingSystem="";
		String technologies="";
		String scriptingLanguages="";
		String databases="";
		String frameworks="";
		String testingTools="";
		String otherSkills="";
		String qualification="";
		int experience=0;
		ProfessionalPersonalBean  personal_obj;
		
		try{
			if(b_experience){
				rs=st.executeQuery("select experience from tblCompanyRequirements where companyId="+companyId);
				if(rs.next()){	
					experience=rs.getInt(1);
				}
				
			}
			if(b_salary){
				rs=st.executeQuery("select SalaryOffered from tblCompanyRequirements where companyId="+companyId);
				if(rs.next()){
					salary=rs.getDouble(1);
				}
			}
			if(b_skillSet){
				rs=st.executeQuery("select * from tblRequirementSkillSet where companyId="+companyId);
				while(rs.next()){
					operatingSystem=rs.getString("OperatingSystem");
					technologies=rs.getString("Technologies");
					scriptingLanguages=rs.getString("ScriptingLanguages");
					databases=rs.getString("DB");
					frameworks=rs.getString("Frameworks");
					testingTools=rs.getString("TestingTools");
					otherSkills=rs.getString("OtherSkills");
				}
			}	
			if(b_qualification){
				rs=st.executeQuery("select Qualification from tblCompanyRequirements where companyId="+companyId);
				if(rs.next()){
					qualification=rs.getString("Qualification");
				}
			}

			
			if(b_experience){	
				int profExperience=0;
				Date start=new Date(0);
				Date end=new Date(0);
				rs=st.executeQuery("select a.profId,a.profFName,a.profLName,a.profMobile,a.profEmail,a.profExpectedSalary from tblPersonal as a");			
				while(rs.next()){
					profExperience=0;
					rs1=st1.executeQuery("select startDate,endDate from tblExperience where profId="+rs.getInt(1));
					while(rs1.next()){
						start=Date.valueOf(rs1.getString("startDate"));
						end=Date.valueOf(rs1.getString("endDate"));
					                int year=end.getYear()-start.getYear();
					                int month=end.getMonth()-start.getMonth();
					                if(month<0){
				                		  year=year-1;                  
					                }					
						profExperience=profExperience+year;
					}
					if(profExperience>=experience){
							personal_obj=new ProfessionalPersonalBean();
							personal_obj.setProfId(rs.getInt("profId"));
							personal_obj.setProfFName(rs.getString("profFName"));
							personal_obj.setProfLName(rs.getString("profLName"));
							personal_obj.setProfMobile(rs.getString("profMobile"));
							personal_obj.setProfEmail(rs.getString("profEmail"));				
							personal_obj.setProfExpectedSalary(rs.getDouble("profExpectedSalary")) ;
							al_professional.add(personal_obj);
					}
				}
			}
			if(b_salary){
				rs=st.executeQuery("select a.profId,a.profFName,a.profLName,a.profMobile,a.profEmail,a.profExpectedSalary,b.OperatingSystem,b.Technologies,b. ScriptingLanguages,b.DB,b.Frameworks,b.TestingTools,b.OtherSkills from tblPersonal as a,tblProfSkillSet as b WHERE a.profId=b.profId and a.profExpectedSalary<="+salary);
				while(rs.next()){
					int flag=0;
					if(al_professional.size()>0){
						for(int i=0;i<al_professional.size();i++){
							personal_obj=(ProfessionalPersonalBean)al_professional.get(i);
							if(personal_obj.getProfId()==rs.getInt("profId")){	
								flag=1;
							}
						}
					}
					if(flag==0){
						personal_obj=new ProfessionalPersonalBean();
						personal_obj.setProfId(rs.getInt("profId"));
						personal_obj.setProfFName(rs.getString("profFName"));
						personal_obj.setProfLName(rs.getString("profLName"));
						personal_obj.setProfMobile(rs.getString("profMobile"));
						personal_obj.setProfEmail(rs.getString("profEmail"));				
						personal_obj.setProfExpectedSalary(rs.getDouble("profExpectedSalary")) ;
						al_professional.add(personal_obj);
					}	
				}	
			}

			if(b_skillSet){
				rs=st.executeQuery("select a.profId,a.profFName,a.profLName,a.profMobile,a.profEmail,a.profExpectedSalary,b.OperatingSystem,b.Technologies,b. ScriptingLanguages,b.DB,b.Frameworks,b.TestingTools,b.OtherSkills from tblPersonal as a,tblProfSkillSet as b WHERE a.profId=b.profId" );
				while(rs.next()){
					if(rs.getString("OperatingSystem").equalsIgnoreCase(operatingSystem) || rs.getString("Technologies").equalsIgnoreCase(technologies) || rs.getString("ScriptingLanguages").equalsIgnoreCase(scriptingLanguages) || rs.getString("DB").equalsIgnoreCase(databases) || rs.getString("Frameworks").equalsIgnoreCase(frameworks) || rs.getString("TestingTools").equalsIgnoreCase(testingTools) || rs.getString("OtherSkills").equalsIgnoreCase(otherSkills)){
						int flag=0;
						if(al_professional.size()>0){
							for(int i=0;i<al_professional.size();i++){
								personal_obj=(ProfessionalPersonalBean)al_professional.get(i);
								if(personal_obj.getProfId()==rs.getInt("profId")){	
									flag=1;
								}
							}
						}
						if(flag==0){
							personal_obj=new ProfessionalPersonalBean();
							personal_obj.setProfId(rs.getInt("profId"));
							personal_obj.setProfFName(rs.getString("profFName"));
							personal_obj.setProfLName(rs.getString("profLName"));
							personal_obj.setProfMobile(rs.getString("profMobile"));
							personal_obj.setProfEmail(rs.getString("profEmail"));				
							personal_obj.setProfExpectedSalary(rs.getDouble("profExpectedSalary")) ;
							al_professional.add(personal_obj);
						}	
					}	
				}
			}

			if(b_qualification){
				rs=st.executeQuery("select a.profId,a.profFName,a.profLName,a.profMobile,a.profEmail,a.profExpectedSalary,b.OperatingSystem,b.Technologies,b. ScriptingLanguages,b.DB,b.Frameworks,b.TestingTools,b.OtherSkills,c.qualificationName from tblPersonal as a,tblProfSkillSet as b,tblprofqualification as c WHERE a.profId=b.profId and c.profId=b.profId and c.qualificationName='"+qualification+"'");
				while(rs.next()){
						int flag=0;
						if(al_professional.size()>0){
							for(int i=0;i<al_professional.size();i++){
								personal_obj=(ProfessionalPersonalBean)al_professional.get(i);
								if(personal_obj.getProfId()==rs.getInt("profId")){	
									flag=1;
								}
							}
						}
						if(flag==0){
							personal_obj=new ProfessionalPersonalBean();
							personal_obj.setProfId(rs.getInt("profId"));
							personal_obj.setProfFName(rs.getString("profFName"));
							personal_obj.setProfLName(rs.getString("profLName"));
							personal_obj.setProfMobile(rs.getString("profMobile"));
							personal_obj.setProfEmail(rs.getString("profEmail"));				
							personal_obj.setProfExpectedSalary(rs.getDouble("profExpectedSalary")) ;
							al_professional.add(personal_obj);
						}	

				}
			}
			
					
		}
		catch(Exception e){
			System.out.println("Exception in method searchProfessional1 of prtsdb "+e);
		}
		return al_professional;
	}	
	public ArrayList getAllProfessionalQualification(){
		ArrayList arraylist_qual=new ArrayList();	
		ProfessionalQualificationBean qualification_obj;
		try{
			rs1=st1.executeQuery("select * from tblProfQualification order by profId");
			while(rs1.next()){
				qualification_obj=new ProfessionalQualificationBean();
				qualification_obj.setProfId(rs1.getInt("profId"));
				qualification_obj.setQualificationId(rs1.getInt("QualificationId"));
				qualification_obj.setQualificationName(rs1.getString("QualificationName"));
				qualification_obj.setBranch(rs1.getString("Branch"));	
				qualification_obj.setYearOfPassing(rs1.getString("YearOfPassing"));
				qualification_obj.setUniversityBoard(rs1.getString("UniversityBoard"));
				qualification_obj.setCollegeInstitute(rs1.getString("CollegeInstitute"));
				qualification_obj.setPerMarks(rs1.getDouble("perMarks"));
		
				arraylist_qual.add(qualification_obj);
			}						
		}
		catch(Exception e){
			System.out.println("Exception in method  getAllProfessionalQualificationof prtsdb "+e);
		}
		return arraylist_qual;		
	}
	public ArrayList getAllProfessionalExperience(){
		ArrayList arraylist_experience=new ArrayList();	
		ProfessionalExperienceBean experience_obj;
		try{
			rs2=st2.executeQuery("select * from tblExperience order by profId");
			arraylist_experience=new ArrayList();
			while(rs2.next()){
				experience_obj=new ProfessionalExperienceBean();
				experience_obj.setProfId(rs2.getInt(1));
				experience_obj.setExperienceId(rs2.getInt("ExperienceId"));					
				experience_obj.setStartDate(rs2.getString("StartDate"));
				experience_obj.setEndDate(rs2.getString("EndDate"));
				experience_obj.setCompanyName(rs2.getString("CompanyName"));
				experience_obj.setLocation(rs2.getString("Location"));
				experience_obj.setDesignation(rs2.getString("Designation"));
				experience_obj.setJobProfile(rs2.getString("JobProfile"));
				experience_obj.setSalaryPackage(rs2.getDouble("SalaryPackage"));

				arraylist_experience.add(experience_obj);
			}						
		}
		catch(Exception e){
			System.out.println("Exception in method  getAllProfessionalExperience of prtsdb "+e);
		}
		return arraylist_experience;		
	}	

	public ArrayList getAllProfessionalProjects(){
		ArrayList arraylist_project=new ArrayList();	
		ProjectDetailsBean project_obj;
		try{
			rs3=st3.executeQuery("select * from tblProjectDetails order by  profId");
			while(rs3.next()){
				project_obj=new ProjectDetailsBean();
				project_obj.setProjectId(rs3.getInt("ProjectId"));
				project_obj.setProfId(rs3.getInt("profId"));
				project_obj.setExperienceId(rs3.getInt("ExperienceId"));	
				project_obj.setProjectName(rs3.getString("ProjectName"));
				project_obj.setProjectDescription(rs3.getString("ProjectDescription"));				
				project_obj.setProjectStartDate(rs3.getString("ProjectStartDate"));
				project_obj.setProjectEndDate(rs3.getString("ProjectEndDate"));
				project_obj.setTechnologiesLanguages(rs3.getString("TechnologiesLanguages"));
				arraylist_project.add(project_obj);
			}						
		}
		catch(Exception e){
			System.out.println("Exception in method  getAllProfessionalProjects of prtsdb "+e);
		}
		return arraylist_project;		
	}
					
}

/*
		try{
		}
		catch(Exception e){
			System.out.println("Exception in method of prtsdb "+e);
		}
*/