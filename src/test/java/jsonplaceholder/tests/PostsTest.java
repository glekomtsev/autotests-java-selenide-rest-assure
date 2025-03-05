package jsonplaceholder.tests;

import io.restassured.builder.RequestSpecBuilder;
import jsonplaceholder.model.Post;
import jsonplaceholder.requests.PostService;
import jsonplaceholder.spec.ReqSpecs;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static jsonplaceholder.generators.TestDataGenerator.generate;
import static org.assertj.core.api.Assertions.assertThat;

public class PostsTest extends BaseTest {

    private  PostService postService;

    @BeforeEach
    public void setupTestData() {
         postService = new PostService(ReqSpecs.unAuthSpec());
    }



    @Test
    public void getPost() {
        Post expectedPost = generate(Post.class);
        Post actualResponse = postService.read(expectedPost.getId());
        assertThat(actualResponse).isEqualTo(expectedPost);
    }

    @Test
    public void createPosts() {
        Post expectedPost = new Post(1, 1, "qwer", "qwer");
        Post actualResponse = postService.create(expectedPost);
        assertThat(actualResponse).isEqualTo(expectedPost);

    }

    @Test
    public void updatePosts() {
        Post expectedPost = new Post(1, 1, "qwer", "qwer");
        Post actualResponse = postService.update(expectedPost.getId(), expectedPost);
        assertThat(actualResponse).isEqualTo(expectedPost);
    }

    @Test
    public void deletePosts() {
        Post expectedPost = new Post(1, 1, "qwer", "qwer");
        postService.delete(expectedPost.getId());
    }


    @Test
    public void getAllPosts() {

    }
}
