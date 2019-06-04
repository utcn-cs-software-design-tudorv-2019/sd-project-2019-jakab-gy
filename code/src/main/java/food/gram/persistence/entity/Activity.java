package food.gram.persistence.entity;

import javax.persistence.*;
import java.sql.Timestamp;

@Entity
@Table(name = "activity")
public class Activity {
    public static final String[] ACTIVITY_TYPE={"LIKE","FOLLOW","POST","COMMENT","MESSAGE"};

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "activity_id")
    private int activityId;

    @ManyToOne
    @JoinColumn(name = "profile_id")
    private Profile profile;

    @Column(name = "activity_type",length = 10,nullable = false)
    private String activityType;

    @Column(name = "activity_time",nullable = false)
    private Timestamp activityTime;

    @Column(name = "description",length = 500)
    private String description;

    public Activity(){
        super();
    }

    public Activity(Profile profile, String activityType, Timestamp activityTime, String description) {
        this.profile = profile;
        this.activityType = activityType;
        this.activityTime = activityTime;
        this.description = description;
    }

    public int getActivityId() {
        return activityId;
    }

    public void setActivityId(int activityId) {
        this.activityId = activityId;
    }

    public Profile getProfile() {
        return profile;
    }

    public void setProfile(Profile profile) {
        this.profile = profile;
    }

    public String getActivityType() {
        return activityType;
    }

    public void setActivityType(String activityType) {
        this.activityType = activityType;
    }

    public Timestamp getActivityTime() {
        return activityTime;
    }

    public void setActivityTime(Timestamp activityTime) {
        this.activityTime = activityTime;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public String toString() {
        return "Activity{" +
                "activityId=" + activityId +
                ", profile=" + profile +
                ", activityType='" + activityType + '\'' +
                ", activityTime=" + activityTime +
                ", description='" + description + '\'' +
                '}';
    }
}
