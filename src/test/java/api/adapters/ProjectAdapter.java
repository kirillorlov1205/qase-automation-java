package api.adapters;

import api.models.Project;
import io.restassured.response.Response;
import lombok.extern.log4j.Log4j2;

@Log4j2
public class ProjectAdapter extends BaseAdapter {
    private static final String PROJECT_API_ENDPOINT = "/project/";

    public Response getProjectByCode(String projectCode) {
        log.info(String.format("Get project by project code '%s'", projectCode));
        return get(PROJECT_API_ENDPOINT + projectCode);
    }

    public Response getAllProjects() {
        log.info("Get all projects");
        return get(PROJECT_API_ENDPOINT);
    }

    public Response createNewProject(Project project) {
        log.info("Create new project");
        return post(PROJECT_API_ENDPOINT, converter.toJson(project));
    }

    public Response deleteProjectByCode(String projectCode) {
        log.info(String.format("Delete project by project code '%s'", projectCode));
        return delete(PROJECT_API_ENDPOINT + projectCode);
    }
}
