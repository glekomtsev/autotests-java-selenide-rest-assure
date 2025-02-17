package reqres.tests;

import io.qameta.allure.*;
import io.restassured.specification.RequestSpecification;
import org.junit.jupiter.api.*;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import reqres.specification.RequestSpecificationBuilder;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.*;

@Epic("API Testing")
@Feature("User Management")
@Story("CRUD Operations for Users")
@Tag("API_test")
public class ApiTest {

    private static RequestSpecification requestSpec;

    @BeforeAll
    public static void setup() {
        requestSpec = RequestSpecificationBuilder.getRequestSpec();
    }

    @Test
    @DisplayName("1. List Users")
    @Description("Verify that the GET /users endpoint returns a list of users.")
    @Tags({@Tag("CRITICAL")})
    @Severity(SeverityLevel.CRITICAL)
    public void testGetAllUsers() {
        given()
                .spec(requestSpec)
            .when()
                .get("/api/users?page=1")
            .then()
                .statusCode(200)
                .body("data", hasSize(greaterThan(0)));
    }

    @ParameterizedTest
    @CsvSource({
            "1, George, Bluth",
            "2, Janet, Weaver",
            "3, Emma, Wong"
    })
    @DisplayName("2. Single User")
    @Description("Verify that the GET /users/{id} endpoint returns the correct user details.")
    @Tags({@Tag("NORMAL")})
    @Severity(SeverityLevel.NORMAL)
    public void testGetUserById(int userId, String firstName, String lastName) {
        given()
                .spec(requestSpec)
            .when()
                .get("/api/users/{id}", userId)
            .then()
                .statusCode(200)
                .body("data.first_name", equalTo(firstName))
                .body("data.last_name", equalTo(lastName));
    }

    @Test
    @DisplayName("3. Single User Not Found")
    @Description("Verify that a non-existent user returns a 404 status code.")
    @Tags({@Tag("MINOR")})
    @Severity(SeverityLevel.MINOR)
    public void testUserNotFound() {
        int userId = 23;
        given()
                .spec(requestSpec)
            .when()
                .get("/api/users/{id}", userId)
            .then()
                .statusCode(404);
    }

    @Test
    @DisplayName("4. List Resources")
    @Description("Verify that the GET /unknown endpoint returns a list of resources.")
    @Tags({@Tag("CRITICAL")})
    @Severity(SeverityLevel.CRITICAL)
    public void testGetAllResources() {
        given()
                .spec(requestSpec)
            .when()
                .get("/api/unknown")
            .then()
                .statusCode(200)
                .body("data", hasSize(greaterThan(0)))
                .body("data.id", everyItem(notNullValue()))
                .body("data.name", everyItem(notNullValue()));
    }

    @ParameterizedTest
    @CsvSource({
            "1, cerulean",
            "2, fuchsia rose",
            "3, true red"
    })
    @DisplayName("5. Single Resource")
    @Description("Verify that the GET /unknown/{id} endpoint returns the correct resource details.")
    @Tags({@Tag("NORMAL")})
    @Severity(SeverityLevel.NORMAL)
    public void testGetResourceById(int resourceId, String name) {
        given()
                .spec(requestSpec)
            .when()
                .get("/api/unknown/{id}", resourceId)
            .then()
                .statusCode(200)
                .body("data.name", equalTo(name));
    }

    @Test
    @DisplayName("6. Single Resource Not Found")
    @Description("Verify that a non-existent resource returns a 404 status code.")
    @Tags({@Tag("MINOR")})
    @Severity(SeverityLevel.MINOR)
    public void testResourceNotFound() {
        int resourceId = 23;
        given()
                .spec(requestSpec)
            .when()
                .get("/api/unknown/{id}", resourceId)
            .then()
                .statusCode(404);
    }

    @ParameterizedTest
    @CsvSource({
            "George, leader",
            "Janet, dancer",
            "Emma, musician"
    })
    @DisplayName("7. Create User")
    @Description("Verify that a new user can be created via POST /users.")
    @Tags({@Tag("BLOCKER")})
    @Severity(SeverityLevel.BLOCKER)
    public void testCreateUser(String name, String job) {
        String requestBody = "{ \"name\": \"" + name + "\", \"job\": \"" + job + "\" }";
        given()
                .spec(requestSpec)
                .body(requestBody)
            .when()
                .post("/api/users")
            .then()
                .statusCode(201)
                .body("name", equalTo(name))
                .body("job", equalTo(job));
    }

    @ParameterizedTest
    @CsvSource({
            "1, Ivan, dancer",
            "2, Oleg, president",
            "3, Eva, cook"
    })
    @DisplayName("8. Update User (PUT)")
    @Description("Verify that an existing user can be updated via PUT /users/{id}.")
    @Tags({@Tag("CRITICAL")})
    @Severity(SeverityLevel.CRITICAL)
    public void testUpdateUser(int userId, String name, String job) {
        String requestBody = "{ \"name\": \"" + name + "\", \"job\": \"" + job + "\" }";
        given()
                .spec(requestSpec)
                .body(requestBody)
            .when()
                .put("/api/users/{id}", userId)
            .then()
                .statusCode(200)
                .body("name", equalTo(name))
                .body("job", equalTo(job));
    }

    @ParameterizedTest
    @CsvSource({
            "1, George, dancer",
            "2, Janet, president",
            "3, Emma, cook"
    })
    @DisplayName("9. Update User (PATCH)")
    @Description("Verify that an existing user can be updated via PATCH /users/{id}.")
    @Tags({@Tag("CRITICAL")})
    @Severity(SeverityLevel.CRITICAL)
    public void testPatchUser(int userId, String name, String job) {
        String requestBody = "{ \"name\": \"" + name + "\", \"job\": \"" + job + "\" }";
        given()
                .spec(requestSpec)
                .body(requestBody)
            .when()
                .patch("/api/users/{id}", userId)
            .then()
                .statusCode(200)
                .body("name", equalTo(name))
                .body("job", equalTo(job));
    }

    @Test
    @DisplayName("10. Delete User")
    @Description("Verify that a user can be deleted via DELETE /users/{id}.")
    @Tags({@Tag("CRITICAL")})
    @Severity(SeverityLevel.CRITICAL)
    public void testDeleteUser() {
        int userId = 2;
        given()
                .spec(requestSpec)
            .when()
                .delete("/api/users/{id}", userId)
            .then()
                .statusCode(204);
    }

    @ParameterizedTest
    @CsvSource({
            "eve.holt@reqres.in, pistol"
    })
    @DisplayName("11. Register Successful")
    @Description("Verify that a new user can register successfully via POST /register.")
    @Tags({@Tag("BLOCKER")})
    @Severity(SeverityLevel.BLOCKER)
    public void testRegisterUser(String email, String password) {
        String requestBody = "{ \"email\": \"" + email + "\", \"password\": \"" + password + "\" }";
        given()
                .spec(requestSpec)
                .body(requestBody)
            .when()
                .post("/api/register")
            .then()
                .statusCode(200)
                .body("id", notNullValue())
                .body("token", notNullValue());
    }

    @ParameterizedTest
    @CsvSource({
            "qwerty"
    })
    @DisplayName("12. Register Unsuccessful")
    @Description("Verify that registration fails when required fields are missing via POST /register.")
    @Tags({@Tag("BLOCKER")})
    @Severity(SeverityLevel.BLOCKER)
    public void testRegisterUnsuccessfulUser(String email) {
        String requestBody = "{ \"email\": \"" + email + "\"}";
        given()
                .spec(requestSpec)
                .body(requestBody)
            .when()
                .post("/api/register")
            .then()
                .statusCode(400)
                .body("error", equalTo("Missing password"));
    }

    @ParameterizedTest
    @CsvSource({
            "eve.holt@reqres.in, cityslicka"
    })
    @DisplayName("13. Login Successful")
    @Description("Verify that a user can log in successfully via POST /login.")
    @Tags({@Tag("BLOCKER")})
    @Severity(SeverityLevel.BLOCKER)
    public void testLoginUser(String email, String password) {
        String requestBody = "{ \"email\": \"" + email + "\", \"password\": \"" + password + "\" }";
        given()
                .spec(requestSpec)
                .body(requestBody)
            .when()
                .post("/api/login")
            .then()
                .statusCode(200)
                .body("token", notNullValue());
    }

    @ParameterizedTest
    @CsvSource({
            "peter@klaven"
    })
    @DisplayName("14. Login Unsuccessful")
    @Description("Verify that login fails when required fields are missing via POST /login.")
    @Tags({@Tag("BLOCKER")})
    @Severity(SeverityLevel.BLOCKER)
    public void testLoginUnsuccessfulUser(String email) {
        String requestBody = "{ \"email\": \"" + email + "\"}";
        given()
                .spec(requestSpec)
                .body(requestBody)
            .when()
                .post("/api/login")
            .then()
                .statusCode(400)
                .body("error", equalTo("Missing password"));
    }

    @Test
    @DisplayName("15. Delayed Response")
    @Description("Verify that the GET /users endpoint with delay parameter returns a delayed response.")
    @Tags({@Tag("CRITICAL")})
    @Severity(SeverityLevel.CRITICAL)
    public void testDelayedResponse() {
        given()
                .spec(requestSpec)
            .when()
                .get("/api/users?delay=3")
            .then()
                .statusCode(200)
                .body("data", hasSize(greaterThan(0)));
    }
}