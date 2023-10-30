package api.adapters;

import api.models.Milestone;
import io.restassured.response.Response;
import lombok.extern.log4j.Log4j2;

@Log4j2
public class MilestoneAdapter extends BaseAdapter {
    private static final String MILESTONE_API_ENDPOINT = "/milestone/";

    public Response getMilestoneById(String projectCode, String milestoneId) {
        log.info(String.format("Get milestone by id '%s'", milestoneId));
        return get(MILESTONE_API_ENDPOINT + projectCode + milestoneId);
    }

    public Response getAllMilestonesByProjectCode(String projectCode) {
        log.info(String.format("Get all milestones by project code '%s'", projectCode));
        return get(MILESTONE_API_ENDPOINT + projectCode);
    }

    public Response createNewMilestone(String url, Milestone milestone) {
        log.info("Create new milestone");
        return post(MILESTONE_API_ENDPOINT + url, converter.toJson(milestone));
    }

    public Response updateMilestone(String projectCode, String milestoneId, Milestone milestone) {
        log.info(String.format("Update milestone by id '%s'", milestoneId));
        return patch(MILESTONE_API_ENDPOINT + projectCode + milestoneId, converter.toJson(milestone));
    }

    public Response deleteMilestoneById(String projectCode, String milestoneId) {
        log.info(String.format("Delete milestone by id '%s'", milestoneId));
        return delete(MILESTONE_API_ENDPOINT + projectCode + milestoneId);
    }
}
