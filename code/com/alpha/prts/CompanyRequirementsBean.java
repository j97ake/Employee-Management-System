

package com.alpha.prts;


public class CompanyRequirementsBean extends CompanyBean{
    
  //  private int companyId;
    private int experience;
    private Double salaryOffered;
    private String qualification;
    
    public CompanyRequirementsBean() {
    }

    
/*    public int getCompanyId() {
        return companyId;
    }

    public void setCompanyId(int companyId) {
        this.companyId = companyId;
    }
*/
    public int getExperience() {
        return experience;
    }

    public void setExperience(int experience) {
        this.experience = experience;
    }

    public Double getSalaryOffered() {
        return salaryOffered;
    }

    public void setSalaryOffered(Double salaryOffered) {
        this.salaryOffered = salaryOffered;
    }

    public String getQualification() {
        return qualification;
    }

    public void setQualification(String qualification) {
        this.qualification = qualification;
    }
}
   
