package api.adapters;

import io.restassured.response.Response;
import api.models.Project;

public class ProjectAdapter extends BaseAdapter {
    private static final String PROJECT_API_ENDPOINT = "/project/";

    public Response getProjectByCode(String projectCode) {
        return get(PROJECT_API_ENDPOINT + projectCode);
    }

    public Response getAllProjects() {
        return get(PROJECT_API_ENDPOINT);
    }

    public Response createNewProject(Project project) {
        return post(PROJECT_API_ENDPOINT, converter.toJson(project));
    }

    public Response deleteProjectByCode(String code) {
        return delete(PROJECT_API_ENDPOINT + code);
    }
}
