package jsonplaceholder.requests;

import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;
import jsonplaceholder.model.Post;
import jsonplaceholder.spec.ReqSpecs;

import static io.restassured.RestAssured.given;

public class PostService implements CrudInterface <Post>{

    private static final String POSTS_URL = "/posts";

    private final RequestSpecification reqSpec = ReqSpecs.unAuthSpec();

    @Override
    public Post create(Post item) {
        return given()
                .spec(reqSpec)
                .body(item)
            .when()
                .get(POSTS_URL)
            .then()
                .extract()
                .body()
                .as(Post.class);
    }

    @Override
    public Post read(int id) {
        return given()
                .spec(reqSpec)
            .when()
                .get(POSTS_URL + "/" + id)
            .then()
                .extract()
                .body()
                .as(Post.class);
    }

    @Override
    public Post update(int id, Post item) {
        return given()
                .spec(reqSpec)
                .body(item)
            .when()
                .put(POSTS_URL + "/" + id)
            .then()
                .assertThat()
                .statusCode(200)
                .extract()
                .body()
                .as(Post.class);
    }

    @Override
    public String delete(int id) {
        return given()
                .spec(reqSpec)
            .when()
                .delete(POSTS_URL + "/" + id)
            .then()
                .assertThat()
                .statusCode(200)
                .extract()
                .body()
                .asString();
    }
}
