package food.gram.persistence.entity;

import javax.persistence.*;
import java.sql.Timestamp;

@Entity
@Table(name = "commentary")
public class Commentary {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "comment_id")
    private int commentId;

    @ManyToOne
    @JoinColumn(name = "post_id")
    private Post post;

    @ManyToOne
    @JoinColumn(name = "profile_id")
    private Profile profile;

    @Column(name = "description",length = 500)
    private String description;

    @Column(name = "Commentary_time")
    private Timestamp commentaryTime;

    public Commentary(){
        super();
    }

    public Commentary(Post post, Profile profile, String description, Timestamp commentaryTime) {
        this.post = post;
        this.profile = profile;
        this.description = description;
        this.commentaryTime = commentaryTime;
    }

    public int getCommentId() {
        return commentId;
    }

    public void setCommentId(int commentId) {
        this.commentId = commentId;
    }

    public Post getPost() {
        return post;
    }

    public void setPost(Post post) {
        this.post = post;
    }

    public Profile getProfile() {
        return profile;
    }

    public void setProfile(Profile profile) {
        this.profile = profile;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Timestamp getCommentaryTime() {
        return commentaryTime;
    }

    public void setCommentaryTime(Timestamp commentaryTime) {
        this.commentaryTime = commentaryTime;
    }

    @Override
    public String toString() {
        return "Commentary{" +
                "commentId=" + commentId +
                ", post=" + post +
                ", profile=" + profile +
                ", description='" + description + '\'' +
                ", commentaryTime=" + commentaryTime +
                '}';
    }
}
