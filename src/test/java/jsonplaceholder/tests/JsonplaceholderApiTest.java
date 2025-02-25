package jsonplaceholder.tests;

import io.restassured.http.ContentType;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.baseURI;
import static io.restassured.RestAssured.given;

public class JsonplaceholderApiTest  extends BaseTest{



    @Test
    public void getAllPosts() {

    }

     @Test
    public void getPosts() {

    }

    @Test
    public void createPosts() {
        String requestBody = "{ \"title\": \"foo\", \"body\": \"bar\", \"userId\": 1 }";


    }

    @Test
    public void updatePosts() {
        String requestBody = "{ \"id\": 101, \"title\": \"foo\", \"body\": \"bar\", \"userId\": 1 }";


    }

    @Test
    public void patchPosts() {
        String requestBody = "{ \"title\": \"foo\" }";


    }

    @Test
    public void deletePosts() {

    }
}
