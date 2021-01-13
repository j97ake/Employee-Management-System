
package com.alpha.prts;


public class ProjectDetailsBean{
    private int profId;
    private int projectId;
    private String projectName;
    private String projectDescription;
    private String projectStartString;
    private String projectEndString;
    private String technologiesLanguages;
    private int experienceId;
     
    public ProjectDetailsBean() {
    }

    public int getProfId() {
        return profId;
    }

    public void setProfId(int profId) {
        this.profId = profId;
    }

    public int getProjectId() {
        return projectId;
    }

    public void setProjectId(int projectId) {
        this.projectId = projectId;
    }

    public String getProjectName() {
        return projectName;
    }

    public void setProjectName(String projectName) {
        this.projectName = projectName;
    }

    public String getProjectDescription() {
        return projectDescription;
    }

    public void setProjectDescription(String projectDescription) {
        this.projectDescription = projectDescription;
    }

    public String getProjectStartDate() {
        return projectStartString;
    }

    public void setProjectStartDate(String projectStartString) {
        this.projectStartString = projectStartString;
    }

    public String getProjectEndDate() {
        return projectEndString;
    }

    public void setProjectEndDate(String projectEndString) {
        this.projectEndString = projectEndString;
    }

    public String getTechnologiesLanguages() {
        return technologiesLanguages;
    }

    public void setTechnologiesLanguages(String technologiesLanguages) {
        this.technologiesLanguages = technologiesLanguages;
    }

    public int getExperienceId() {
        return experienceId;
    }

    public void setExperienceId(int experienceId) {
        this.experienceId = experienceId;
    }
   
}
