package api.adapters;

import api.models.Run;
import io.restassured.response.Response;
import lombok.extern.log4j.Log4j2;

@Log4j2
public class RunAdapter extends BaseAdapter {
    private static final String RUN_API_ENDPOINT = "/run/";

    public Response getRunById(String projectCode, String runId) {
        log.info(String.format("Get run by run id '%s'", runId));
        return get(RUN_API_ENDPOINT + projectCode + runId);
    }

    public Response getAllRunsByProjectCode(String projectCode) {
        log.info(String.format("Get all runs by project code '%s'", projectCode));
        return get(RUN_API_ENDPOINT + projectCode);
    }

    public Response createNewRun(String url, Run run) {
        log.info("Create new run");
        return post(RUN_API_ENDPOINT + url, converter.toJson(run));
    }

    public Response updateRunPublicity(String projectCode, String runId, Run run) {
        log.info(String.format("Update run publicity to 'public' by run id '%s'", runId));
        return patch(RUN_API_ENDPOINT + projectCode + runId + "/public", converter.toJson(run));
    }

    public Response completeRun(String projectCode, String runId) {
        log.info(String.format("Complete run by run id '%s'", runId));
        return post(RUN_API_ENDPOINT + projectCode + runId + "/complete");
    }

    public Response deleteRunById(String projectCode, String runId) {
        log.info(String.format("Delete run by run id '%s'", runId));
        return delete(RUN_API_ENDPOINT + projectCode + runId);
    }
}
