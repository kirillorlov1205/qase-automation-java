package api.tests;

import api.objects.Project;
import api.objects.TestCase;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;
import static java.net.HttpURLConnection.HTTP_OK;
import static utils.TestDataGenerator.generateRandomAlphabeticString;
import static utils.TestDataGenerator.generateRandomString;

public class QaseTest {

    private final String BASE_URL = "https://api.qase.io/v1";
    private final String API_KEY = "84e321ba4810d5091db97b68b54ec0c385217882c14fc4caa04cad0021df990f";
    private final String PROJECT_TITLE = "TEST".concat(generateRandomAlphabeticString(2,5));
    private final String TEST_CASE_TITLE = "TESTCASE".concat(generateRandomAlphabeticString(2,5));

    @Test(testName = "Verify project creation", priority = 1)
    public void verifyProjectCreation() {
        Project project = Project.builder()
                .title(PROJECT_TITLE)
                .code(PROJECT_TITLE)
                .access("all")
                .build();
        given()
                .header("Token", API_KEY)
                .header("content-Type", "application/json")
                .body(project)
                .when()
                .post(BASE_URL.concat("/project"))
                .then()
                .statusCode(HTTP_OK);
    }

    @Test(testName = "Verify project getting by code", priority = 2)
    public void verifyProjectGettingByIndex() {
        given()
                .header("Token", API_KEY)
                .when()
                .get(BASE_URL.concat("/project/".concat(PROJECT_TITLE)))
                .then()
                .statusCode(HTTP_OK);
    }

    @Test(testName = "Verify projects list getting", priority = 3)
    public void verifyProjectsListGetting() {
        given()
                .header("Token", API_KEY)
                .when()
                .get(BASE_URL.concat("/project"))
                .then()
                .statusCode(HTTP_OK);
    }

    @Test(testName = "Verify test case creation", priority = 4)
    public void verifyTestCaseCreation() {
        TestCase testCase = TestCase.builder()
                .preconditions(generateRandomString(2, 10))
                .postConditions(generateRandomString(2, 10))
                .title(TEST_CASE_TITLE)
                .severity(1)
                .priority(2)
                .build();
        given()
                .header("Token", API_KEY)
                .header("content-Type", "application/json")
                .body(testCase)
                .when()
                .post(BASE_URL.concat("/case/".concat(PROJECT_TITLE)))
                .then()
                .log().all()
                .statusCode(HTTP_OK);
    }

    @Test(testName = "Verify test case getting by id", priority = 5)
    public void verifyTestCaseGettingByIndex() {
        given()
                .header("Token", API_KEY)
                .when()
                .get(BASE_URL.concat("/case/".concat(PROJECT_TITLE).concat("/1")))
                .then()
                .statusCode(HTTP_OK);
    }

    @Test(testName = "Verify test cases list getting", priority = 6)
    public void verifyTestCasesListGetting() {
        given()
                .header("Token", API_KEY)
                .when()
                .get(BASE_URL.concat("/case/".concat(PROJECT_TITLE)))
                .then()
                .statusCode(HTTP_OK);
    }

    @Test(testName = "Verify test cases removing", priority = 7)
    public void verifyTestCaseRemoving() {
        given()
                .header("Token", API_KEY)
                .when()
                .delete(BASE_URL.concat("/case/".concat(PROJECT_TITLE)).concat("/1"))
                .then()
                .statusCode(HTTP_OK);
    }
}
