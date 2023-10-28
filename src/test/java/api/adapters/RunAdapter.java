package api.adapters;

import io.restassured.response.Response;
import api.models.Run;

public class RunAdapter extends BaseAdapter {
    private static final String RUN_API_ENDPOINT = "/run/";

    public Response getRunById(String projectCode, String runId) {
        return get(RUN_API_ENDPOINT + projectCode + runId);
    }

    public Response getAllRunsByProjectCode(String projectCode) {
        return get(RUN_API_ENDPOINT + projectCode);
    }

    public Response createNewRun(String url, Run run) {
        return post(RUN_API_ENDPOINT + url, converter.toJson(run));
    }

    public Response updateRunPublicity(String projectCode, String runId, Run run) {
        return patch(RUN_API_ENDPOINT + projectCode + runId + "/public", converter.toJson(run));
    }

    public Response completeRun(String projectCode, String runId) {
        return postWithoutBody(RUN_API_ENDPOINT + projectCode + runId + "/complete");
    }

    public Response deleteRunById(String projectCode, String runId) {
        return delete(RUN_API_ENDPOINT + projectCode + runId);
    }
}
