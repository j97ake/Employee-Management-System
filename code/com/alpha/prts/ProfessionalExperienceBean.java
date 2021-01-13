
package com.alpha.prts;


public class ProfessionalExperienceBean {
    private int profId;
    private int experienceId;
    private String startString;
    private String endString;
    private String companyName;
    private String location;
    private String designation;
    private String jobProfile;
    private Double salaryPackage;
    public ProfessionalExperienceBean() {
    }



    public int getProfId() {
        return profId;
    }

    public void setProfId(int profId) {
        this.profId = profId;
    }

    public int getExperienceId() {
        return experienceId;
    }

    public void setExperienceId(int experienceId) {
        this.experienceId = experienceId;
    }

    public String getStartDate() {
        return startString;
    }

    public void setStartDate(String startString) {
        this.startString = startString;
    }

    public String getEndDate() {
        return endString;
    }

    public void setEndDate(String endString) {
        this.endString = endString;
    }

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getDesignation() {
        return designation;
    }

    public void setDesignation(String designation) {
        this.designation = designation;
    }

    public String getJobProfile() {
        return jobProfile;
    }

    public void setJobProfile(String jobProfile) {
        this.jobProfile = jobProfile;
    }

    public Double getSalaryPackage() {
        return salaryPackage;
    }

    public void setSalaryPackage(Double salaryPackage) {
        this.salaryPackage = salaryPackage;
    }
   
}
