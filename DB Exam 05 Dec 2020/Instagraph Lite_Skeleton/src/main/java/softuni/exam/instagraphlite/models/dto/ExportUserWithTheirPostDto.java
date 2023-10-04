package softuni.exam.instagraphlite.models.dto;

import softuni.exam.instagraphlite.models.entity.Post;

import java.util.List;

public class ExportUserWithTheirPostDto {


    private String username;
    private Integer postCount;
    private List<Post> posts;

    public ExportUserWithTheirPostDto()  {
    }

    public ExportUserWithTheirPostDto(Integer postCount) {
              this.postCount = posts.size();

    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public Integer getPostCount() {
        return postCount;
    }

    public void setPostCount(Integer postCount) {
        this.postCount = postCount;
    }

    public List<Post> getPosts() {
        return posts;
    }

    public void setPosts(List<Post> posts) {
        this.posts = posts;
    }
}
