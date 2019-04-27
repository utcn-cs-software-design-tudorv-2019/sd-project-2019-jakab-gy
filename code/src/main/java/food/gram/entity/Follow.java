package food.gram.entity;

import javax.persistence.*;
import java.sql.Timestamp;

@Entity
@Table(name = "follow")
public class Follow {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "follow_id")
    private int followId;

    @ManyToOne
    @JoinColumn(name = "follower_id")
    private Profile followerProfile;

    @ManyToOne
    @JoinColumn(name = "followed_id")
    private Profile followedProfile;

    @Column(name = "follow_time")
    private Timestamp followTime;

    public Follow(){
        super();
    }

    public Follow(Profile followerProfile, Profile followedProfile, Timestamp followTime) {
        this.followerProfile = followerProfile;
        this.followedProfile = followedProfile;
        this.followTime = followTime;
    }

    public int getFollowId() {
        return followId;
    }

    public void setFollowId(int followId) {
        this.followId = followId;
    }

    public Profile getFollowerProfile() {
        return followerProfile;
    }

    public void setFollowerProfile(Profile followerProfile) {
        this.followerProfile = followerProfile;
    }

    public Profile getFollowedProfile() {
        return followedProfile;
    }

    public void setFollowedProfile(Profile followedProfile) {
        this.followedProfile = followedProfile;
    }

    public Timestamp getFollowTime() {
        return followTime;
    }

    public void setFollowTime(Timestamp followTime) {
        this.followTime = followTime;
    }

    @Override
    public String toString() {
        return "Follow{" +
                "followId=" + followId +
                ", followerProfile=" + followerProfile +
                ", followedProfile=" + followedProfile +
                ", followTime=" + followTime +
                '}';
    }
}
