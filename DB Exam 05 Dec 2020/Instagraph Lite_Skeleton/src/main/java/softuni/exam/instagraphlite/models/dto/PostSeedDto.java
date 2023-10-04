package softuni.exam.instagraphlite.models.dto;

import softuni.exam.instagraphlite.models.entity.Picture;
import softuni.exam.instagraphlite.models.entity.User;

import javax.persistence.Column;
import javax.persistence.ManyToOne;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "post")
@XmlAccessorType(XmlAccessType.FIELD )
public class PostSeedDto {

    //    •	caption – a char sequence. Cannot be null. Must be at least 21 characters, inclusive.
//•	user – a User. Cannot be null.
//•	picture – a Picture. Cannot be null.
    @NotEmpty
    @Size(min = 21)
    @NotNull
    @XmlElement(name = "caption")
    private String caption;

    @NotNull
    @XmlElement(name = "user")
    private User user;
    @NotNull
    @XmlElement(name = "picture")
    private Picture picture;

    public PostSeedDto() {
    }

    public PostSeedDto(String caption, User user, Picture picture) {
        this.caption = caption;
        this.user = user;
        this.picture = picture;
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
