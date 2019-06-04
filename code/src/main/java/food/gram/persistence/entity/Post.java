package food.gram.persistence.entity;

import javax.persistence.*;
import java.sql.Timestamp;

@Entity
@Table(name = "post")
public class Post implements Comparable{

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "post_id")
    private int postId;

    @ManyToOne
    @JoinColumn(name = "profile_id")
    private Profile profile;

    @Column(name = "post_time",nullable = false)
    private Timestamp postTime;

    @Column(name = "image",length = Integer.MAX_VALUE)
    private byte[] image;

    @Column(name = "location",length = 100)
    private String location;

    @Column(name = "hashtags")
    private String hashtags;

    public Post(){
        super();
    }

    public Post(Profile profile, Timestamp postTime, byte[] image, String location, String hashtags) {
        this.profile = profile;
        this.postTime = postTime;
        this.image = image;
        this.location = location;
        this.hashtags = hashtags;
    }

    public int getPostId() {
        return postId;
    }

    public void setPostId(int postId) {
        this.postId = postId;
    }

    public Profile getProfile() {
        return profile;
    }

    public void setProfile(Profile profile) {
        this.profile = profile;
    }

    public Timestamp getPostTime() {
        return postTime;
    }

    public void setPostTime(Timestamp postTime) {
        this.postTime = postTime;
    }

    public byte[] getImage() {
        return image;
    }

    public void setImage(byte[] image) {
        this.image = image;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getHashtags() {
        return hashtags;
    }

    public void setHashtags(String hashtags) {
        this.hashtags = hashtags;
    }

    @Override
    public String toString() {
        return "Post{" +
                "postId=" + postId +
                ", profile=" + profile +
                ", postTime=" + postTime +
                ", location='" + location + '\'' +
                ", hashtags='" + hashtags + '\'' +
                '}';
    }

    @Override
    public int compareTo(Object o) {
        Post other = (Post)o;
        return postTime.compareTo(other.getPostTime());
    }
}
