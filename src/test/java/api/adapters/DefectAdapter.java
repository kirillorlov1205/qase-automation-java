package api.adapters;

import api.models.Defect;
import io.restassured.response.Response;
import lombok.extern.log4j.Log4j2;

@Log4j2
public class DefectAdapter extends BaseAdapter {
    private static final String DEFECT_API_ENDPOINT = "/defect/";

    public Response getDefectById(String projectCode, String defectId) {
        log.info(String.format("Get defect by id '%s'", defectId));
        return get(DEFECT_API_ENDPOINT + projectCode + defectId);
    }

    public Response getAllDefectsByProjectCode(String projectCode) {
        log.info(String.format("Get all defects by project code '%s'", projectCode));
        return get(DEFECT_API_ENDPOINT + projectCode);
    }

    public Response createNewDefect(String url, Defect defect) {
        log.info("Create new defect");
        return post(DEFECT_API_ENDPOINT + url, converter.toJson(defect));
    }

    public Response updateDefect(String projectCode, String defectId, Defect defect) {
        log.info(String.format("Update defect by id '%s'", defectId));
        return patch(DEFECT_API_ENDPOINT + projectCode + defectId, converter.toJson(defect));
    }

    public Response updateDefectSpecificStatus(String projectCode, String defectId, Defect defect) {
        log.info(String.format("Update defect status by id '%s'", defectId));
        return patch(DEFECT_API_ENDPOINT + projectCode + "/status/" + defectId, converter.toJson(defect));
    }

    public Response resolveSpecificDefect(String projectCode, String defectId) {
        log.info(String.format("Resolve specific defect by id '%s'", defectId));
        return patchWithoutBody(DEFECT_API_ENDPOINT + projectCode + "/resolve" + defectId);
    }

    public Response deleteDefectById(String projectCode, String defectId) {
        log.info(String.format("Delete defect by id '%s'", defectId));
        return delete(DEFECT_API_ENDPOINT + projectCode + defectId);
    }
}
