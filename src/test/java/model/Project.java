package model;

import utils.Enums;
import utils.Utils;

public class Project {

    private String projectName = Utils.getRandomString(10);
    private String projectCode = Utils.getRandomString(10).toUpperCase();
    private String projectDescription = Utils.getRandomString(10);
    private Enums.ProjectAccessTypes projectAccessType = Enums.ProjectAccessTypes.Public;

    public Project(String projectName, String projectCode, String projectDescription, Enums.ProjectAccessTypes
            projectAccessTypes) {
        this.projectName = projectName;
        this.projectCode = projectCode;
        this.projectDescription = projectDescription;
        this.projectAccessType = projectAccessTypes;
    }

    public Project() {
    }

    public String getProjectName() {
        return projectName;
    }

    public String getProjectCode() {
        return projectCode;
    }

    public String getProjectDescription() {
        return projectDescription;
    }

    public Enums.ProjectAccessTypes getProjectAccessType() {
        return projectAccessType;
    }
}
