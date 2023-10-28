package api.adapters;

import api.models.Suite;
import io.restassured.response.Response;

public class SuiteAdapter extends BaseAdapter {
    private static final String SUITE_API_ENDPOINT = "/suite/";

    public Response getSuiteById(String projectCode, String suiteId) {
        return get(SUITE_API_ENDPOINT + projectCode + suiteId);
    }

    public Response getAllSuitesByProjectCode(String projectCode) {
        return get(SUITE_API_ENDPOINT + projectCode);
    }

    public Response createNewSuite(String url, Suite suite) {
        return post(SUITE_API_ENDPOINT + url, converter.toJson(suite));
    }

    public Response updateSuite(String projectCode, String suiteId, Suite suite) {
        return patch(SUITE_API_ENDPOINT + projectCode + suiteId, converter.toJson(suite));
    }

    public Response deleteSuiteById(String projectCode, String suiteId) {
        return delete(SUITE_API_ENDPOINT + projectCode + suiteId);
    }
}
