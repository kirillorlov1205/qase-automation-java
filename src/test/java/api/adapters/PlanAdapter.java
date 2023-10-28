package api.adapters;

import io.restassured.response.Response;
import api.models.Plan;

public class PlanAdapter extends BaseAdapter {
    private static final String PLAN_API_ENDPOINT = "/plan/";

    public Response getPlanById(String projectCode, String planId) {
        return get(PLAN_API_ENDPOINT + projectCode + planId);
    }

    public Response getAllPlansByProjectCode(String projectCode) {
        return get(PLAN_API_ENDPOINT + projectCode);
    }

    public Response createNewPlan(String url, Plan plan) {
        return post(PLAN_API_ENDPOINT + url, converter.toJson(plan));
    }

    public Response updatePlan(String projectCode, String planId, Plan plan) {
        return patch(PLAN_API_ENDPOINT + projectCode + planId, converter.toJson(plan));
    }

    public Response deletePlanById(String projectCode, String planId) {
        return delete(PLAN_API_ENDPOINT + projectCode + planId);
    }
}
