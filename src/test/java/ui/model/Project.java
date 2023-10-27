package ui.model;

import utils.Enums;
import utils.TestDataGenerator;

public class Project {

    private String projectName = "test".concat(TestDataGenerator.generateRandomString(2, 10));
    private String projectCode = (TestDataGenerator.generateRandomString(2, 10));
    private String projectDescription = TestDataGenerator.generateRandomString(10, 20);
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

    @Override
    public String toString() {
        return "Project{" +
                "projectName='" + projectName + '\'' +
                ", projectCode='" + projectCode + '\'' +
                ", projectDescription='" + projectDescription + '\'' +
                ", projectAccessType=" + projectAccessType +
                '}';
    }
}
