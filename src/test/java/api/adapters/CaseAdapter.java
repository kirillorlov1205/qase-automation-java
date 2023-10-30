package api.adapters;

import api.models.Case;
import io.restassured.response.Response;
import lombok.extern.log4j.Log4j2;

@Log4j2
public class CaseAdapter extends BaseAdapter {
    private static final String CASE_API_ENDPOINT = "/case/";

    public Response getCaseById(String projectCode, String caseId) {
        log.info(String.format("Get case by id '%s'", caseId));
        return get(CASE_API_ENDPOINT + projectCode + caseId);
    }

    public Response getAllCasesByProjectCode(String projectCode) {
        log.info(String.format("Get all cases by project code '%s'", projectCode));
        return get(CASE_API_ENDPOINT + projectCode);
    }

    public Response createNewCase(String url, Case testCase) {
        log.info("Create new case");
        return post(CASE_API_ENDPOINT + url, converter.toJson(testCase));
    }

    public Response updateCase(String statusCode, String caseId, Case testCase) {
        log.info("Update case");
        return patch(CASE_API_ENDPOINT + statusCode + caseId, converter.toJson(testCase));
    }

    public Response deleteCaseById(String projectCode, String caseId) {
        log.info(String.format("Delete case by id '%s'", caseId));
        return delete(CASE_API_ENDPOINT + projectCode + caseId);
    }
}
