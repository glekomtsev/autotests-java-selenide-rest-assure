package jsonplaceholder.tests;

import io.restassured.http.ContentType;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.baseURI;
import static io.restassured.RestAssured.given;

public class JsonplaceholderApiTest  extends BaseTest{



    @Test
    public void getAllPosts() {
        given()
                .when()
                .get("/posts")
                .then()
                .statusCode(200);
    }

     @Test
    public void getPosts() {
        given()
                .when()
                .get("/posts/1")
                .then()
                .statusCode(200);
    }

    @Test
    public void createPosts() {
        String requestBody = "{ \"title\": \"foo\", \"body\": \"bar\", \"userId\": 1 }";

        given()
                .contentType(ContentType.JSON)
                .body(requestBody)
            .when()
                .post("/posts")
            .then()
                .log().all()
                .statusCode(201);
    }

    @Test
    public void updatePosts() {
        String requestBody = "{ \"id\": 101, \"title\": \"foo\", \"body\": \"bar\", \"userId\": 1 }";

        given()
                .contentType(ContentType.JSON)
                .body(requestBody)
            .when()
                .put("/posts/1")
            .then()
                .log().all()
                .statusCode(200);
    }

    @Test
    public void patchPosts() {
        String requestBody = "{ \"title\": \"foo\" }";

        given()
                .contentType(ContentType.JSON)
                .body(requestBody)
            .when()
                .patch("/posts/1")
            .then()
                .log().all()
                .statusCode(200);
    }

    @Test
    public void deletePosts() {
        given()
            .when()
                .delete("/posts/1")
            .then()
                .log().all()
                .statusCode(200);
    }
}
