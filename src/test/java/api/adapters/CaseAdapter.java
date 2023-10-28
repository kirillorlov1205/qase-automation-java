package api.adapters;

import io.restassured.response.Response;
import api.models.Case;

public class CaseAdapter extends BaseAdapter {
    private static final String CASE_API_ENDPOINT = "/case/";

    public Response getCaseById(String projectCode, String caseId) {
        return get(CASE_API_ENDPOINT + projectCode + caseId);
    }

    public Response getAllCasesByProjectCode(String projectCode) {
        return get(CASE_API_ENDPOINT + projectCode);
    }

    public Response createNewCase(String url, Case testCase) {
        return post(CASE_API_ENDPOINT + url, converter.toJson(testCase));
    }

    public Response updateCase(String statusCode, String caseId, Case testCase) {
        return patch(CASE_API_ENDPOINT + statusCode + caseId, converter.toJson(testCase));
    }

    public Response deleteCaseById(String projectCode, String caseId) {
        return delete(CASE_API_ENDPOINT + projectCode + caseId);
    }
}
