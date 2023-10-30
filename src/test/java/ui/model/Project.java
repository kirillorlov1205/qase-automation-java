package ui.model;

import lombok.Builder;
import lombok.Data;
import utils.Enums;
import utils.TestDataGenerator;

@Data
@Builder
public class Project {
    private String projectName;
    private String projectCode;
    private String projectDescription;
    private Enums.ProjectAccessTypes projectAccessType;
}
