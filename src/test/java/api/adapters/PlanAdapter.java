package api.adapters;

import api.models.Plan;
import io.restassured.response.Response;
import lombok.extern.log4j.Log4j2;

@Log4j2
public class PlanAdapter extends BaseAdapter {
    private static final String PLAN_API_ENDPOINT = "/plan/";

    public Response getPlanById(String projectCode, String planId) {
        log.info(String.format("Get plan by id '%s'", planId));
        return get(PLAN_API_ENDPOINT + projectCode + planId);
    }

    public Response getAllPlansByProjectCode(String projectCode) {
        log.info(String.format("Get all plans by project code '%s'", projectCode));
        return get(PLAN_API_ENDPOINT + projectCode);
    }

    public Response createNewPlan(String url, Plan plan) {
        log.info("Create new plan");
        return post(PLAN_API_ENDPOINT + url, converter.toJson(plan));
    }

    public Response updatePlan(String projectCode, String planId, Plan plan) {
        log.info(String.format("Update plan by id '%s'", planId));
        return patch(PLAN_API_ENDPOINT + projectCode + planId, converter.toJson(plan));
    }

    public Response deletePlanById(String projectCode, String planId) {
        log.info(String.format("Delete plan by id '%s'", planId));
        return delete(PLAN_API_ENDPOINT + projectCode + planId);
    }
}
