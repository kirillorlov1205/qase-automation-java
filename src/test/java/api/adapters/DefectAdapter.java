package api.adapters;

import io.restassured.response.Response;
import api.models.Defect;

public class DefectAdapter extends BaseAdapter {
    private static final String DEFECT_API_ENDPOINT = "/defect/";

    public Response getDefectById(String projectCode, String defectId) {
        return get(DEFECT_API_ENDPOINT + projectCode + defectId);
    }

    public Response getAllDefectsByProjectCode(String projectCode) {
        return get(DEFECT_API_ENDPOINT + projectCode);
    }

    public Response createNewDefect(String url, Defect defect) {
        return post(DEFECT_API_ENDPOINT + url, converter.toJson(defect));
    }

    public Response updateDefect(String projectCode, String defectId, Defect defect) {
        return patch(DEFECT_API_ENDPOINT + projectCode + defectId, converter.toJson(defect));
    }

    public Response updateDefectSpecificStatus(String projectCode, String defectId, Defect defect) {
        return patch(DEFECT_API_ENDPOINT + projectCode + "/status/" + defectId, converter.toJson(defect));
    }

    public Response resolveSpecificDefect(String projectCode, String defectId) {
        return patchWithoutBody(DEFECT_API_ENDPOINT + projectCode + "/resolve" + defectId);
    }

    public Response deleteDefectById(String projectCode, String defectId) {
        return delete(DEFECT_API_ENDPOINT + projectCode + defectId);
    }
}
