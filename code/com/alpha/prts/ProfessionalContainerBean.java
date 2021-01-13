package com.alpha.prts;
import java.util.*;

public class ProfessionalContainerBean{

	private ProfessionalPersonalBean professionalPersonalBean;
	private ArrayList<ProfessionalQualificationBean>  al_professionalQualification;
	private ProfessionalSkillSetBean professionalSkillSetBean;
	private ArrayList<ProfessionalExperienceBean>  al_professionalExperience;
	private ArrayList<ProjectDetailsBean> al_projectDetails;

	public void setPersonalDetail(ProfessionalPersonalBean professionalPersonalBean){
		this.professionalPersonalBean=professionalPersonalBean;
	}
	public ProfessionalPersonalBean getPersonalDetail(){
		return professionalPersonalBean;
	}


	public void setQualification(ArrayList al_professionalQualification){
		this.al_professionalQualification=al_professionalQualification;
	}
	public  ArrayList<ProfessionalQualificationBean> getQualification(){
		return al_professionalQualification;
	}

	
	public void setSkillSet(ProfessionalSkillSetBean professionalSkillSetBean){
		this.professionalSkillSetBean=professionalSkillSetBean;			
	}
	public ProfessionalSkillSetBean getSkillSet(){
		return professionalSkillSetBean;
	}


	public void setExperience(ArrayList al_professionalExperience){
		this.al_professionalExperience=al_professionalExperience;
	}
	public ArrayList getExperience(){
		return al_professionalExperience;
	}

	public void setProject(ArrayList al_projectDetails){
		this.al_projectDetails=al_projectDetails;
	}
	public ArrayList getProject(){
		return al_projectDetails;
	}

}