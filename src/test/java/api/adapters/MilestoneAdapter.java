package api.adapters;

import io.restassured.response.Response;
import api.models.Milestone;

public class MilestoneAdapter extends BaseAdapter {
    private static final String MILESTONE_API_ENDPOINT = "/milestone/";

    public Response getMilestoneById(String projectCode, String milestoneId) {
        return get(MILESTONE_API_ENDPOINT + projectCode + milestoneId);
    }

    public Response getAllMilestonesByProjectCode(String projectCode) {
        return get(MILESTONE_API_ENDPOINT + projectCode);
    }

    public Response createNewMilestone(String url, Milestone milestone) {
        return post(MILESTONE_API_ENDPOINT + url, converter.toJson(milestone));
    }

    public Response updateMilestone(String projectCode, String milestoneId, Milestone milestone) {
        return patch(MILESTONE_API_ENDPOINT + projectCode + milestoneId, converter.toJson(milestone));
    }

    public Response deleteMilestoneById(String projectCode, String milestoneId) {
        return delete(MILESTONE_API_ENDPOINT + projectCode + milestoneId);
    }
}
