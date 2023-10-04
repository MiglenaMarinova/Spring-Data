package softuni.exam.instagraphlite.models.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "posts")
public class Post extends BaseEntity{

//    •	caption – a char sequence. Cannot be null. Must be at least 21 characters, inclusive.
//•	user – a User. Cannot be null.
//•	picture – a Picture. Cannot be null.
    @Column(name = "caption", nullable = false)
    private String caption;
    @ManyToOne
    private User user;
    @ManyToOne
    private Picture picture;

    public Post() {
    }

    public String getCaption() {
        return caption;
    }

    public void setCaption(String caption) {
        this.caption = caption;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Picture getPicture() {
        return picture;
    }

    public void setPicture(Picture picture) {
        this.picture = picture;
    }
}
