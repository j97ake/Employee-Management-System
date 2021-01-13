

package com.alpha.prts;


public class ProfessionalQualificationBean extends ProfessionalPersonalBean{
    private int profId;
    private int qualificationId;
    private String qualificationName;
    private String branch;
    private String yearOfPassing;
    private String universityBoard;
    private String collegeInstitute;
    private Double perMarks;
    
    public ProfessionalQualificationBean() {
    }

       public int getProfId() {
        return profId;
    }

    public void setProfId(int profId) {
        this.profId = profId;
    }

    public int getQualificationId() {
        return qualificationId;
    }

    public void setQualificationId(int qualificationId) {
        this.qualificationId = qualificationId;
    }

    public String getQualificationName() {
        return qualificationName;
    }

    public void setQualificationName(String qualificationName) {
        this.qualificationName = qualificationName;
    }

    public String getBranch() {
        return branch;
    }

    public void setBranch(String branch) {
        this.branch = branch;
    }

    public String getYearOfPassing() {
        return yearOfPassing;
    }

    public void setYearOfPassing(String yearOfPassing) {
        this.yearOfPassing = yearOfPassing;
    }

    public String getUniversityBoard() {
        return universityBoard;
    }

    public void setUniversityBoard(String universityBoard) {
        this.universityBoard = universityBoard;
    }

    public String getCollegeInstitute() {
        return collegeInstitute;
    }

    public void setCollegeInstitute(String collegeInstitute) {
        this.collegeInstitute = collegeInstitute;
    }

    public Double getPerMarks() {
        return perMarks;
    }

    public void setPerMarks(Double perMarks) {
        this.perMarks = perMarks;
    }
}
