
package com.alpha.prts;

public class CompanySkillSetBean extends CompanyRequirementsBean{
//    private int companyId;
    private String operatingSystem;
    private String technologies;
    private String scriptingLanguages;
    private String databases;
    private String frameworks;
    private String testingTools;
    private String otherSkills;
    
    public CompanySkillSetBean() {
    }

/*   public int getCompanyId() {
        return profId;
    }

    public void setCompanyId(int companyId) {
        this.companyId = companyId;
    }
*/
    public String getOperatingSystem() {
        return operatingSystem;
    }

    public void setOperatingSystem(String operatingSystem) {
        this.operatingSystem = operatingSystem;
    }

    public String getTechnologies() {
        return technologies;
    }

    public void setTechnologies(String technologies) {
        this.technologies = technologies;
    }

    public String getScriptingLanguages() {
        return scriptingLanguages;
    }

    public void setScriptingLanguages(String scriptingLanguages) {
        this.scriptingLanguages = scriptingLanguages;
    }

    public String getDatabases() {
        return databases;
    }

    public void setDatabases(String databases) {
        this.databases = databases;
    }

    public String getFrameworks() {
        return frameworks;
    }

    public void setFrameworks(String frameworks) {
        this.frameworks = frameworks;
    }

    public String getTestingTools() {
        return testingTools;
    }

    public void setTestingTools(String testingTools) {
        this.testingTools = testingTools;
    }

    public String getOtherSkills() {
        return otherSkills;
    }

    public void setOtherSkills(String otherSkills) {
        this.otherSkills = otherSkills;
    }

}
