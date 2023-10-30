package api.adapters;

import api.models.Suite;
import io.restassured.response.Response;
import lombok.extern.log4j.Log4j2;

@Log4j2
public class SuiteAdapter extends BaseAdapter {
    private static final String SUITE_API_ENDPOINT = "/suite/";

    public Response getSuiteById(String projectCode, String suiteId) {
        log.info(String.format("Get suite by suite id '%s'", suiteId));
        return get(SUITE_API_ENDPOINT + projectCode + suiteId);
    }

    public Response getAllSuitesByProjectCode(String projectCode) {
        log.info(String.format("Get all suites by project code '%s'", projectCode));
        return get(SUITE_API_ENDPOINT + projectCode);
    }

    public Response createNewSuite(String url, Suite suite) {
        log.info("Create new suite");
        return post(SUITE_API_ENDPOINT + url, converter.toJson(suite));
    }

    public Response updateSuite(String projectCode, String suiteId, Suite suite) {
        log.info(String.format("Update suite by suite id '%s'", suiteId));
        return patch(SUITE_API_ENDPOINT + projectCode + suiteId, converter.toJson(suite));
    }

    public Response deleteSuiteById(String projectCode, String suiteId) {
        log.info(String.format("Delete suite by suite id '%s'", suiteId));
        return delete(SUITE_API_ENDPOINT + projectCode + suiteId);
    }
}
