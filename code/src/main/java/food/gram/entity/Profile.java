package food.gram.entity;

import javax.persistence.*;

@Entity
@Table(name = "profile")
public class Profile {
    public static final String[] STATUS_TYPE = {"PUBLIC","PRIVATE"};

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "profile_id")
    private int profileId;

    @OneToOne
    @JoinColumn(name = "account_id")
    private Account account;

    @Column(name = "profile_name",length = 40,nullable = false)
    private String profileName;

    @Column(name = "website",length = 20)
    private String website;

    @Column(name = "bio",length = 200)
    private String bio;

    @Column(name = "status",length = 10)
    private String status;

    public Profile(){
        super();
    }

    public Profile(Account account, String profileName, String website, String bio, String status) {
        this.account = account;
        this.profileName = profileName;
        this.website = website;
        this.bio = bio;
        this.status = status;
    }

    public int getProfileId() {
        return profileId;
    }

    public void setProfileId(int profileId) {
        this.profileId = profileId;
    }

    public Account getAccount() {
        return account;
    }

    public void setAccount(Account account) {
        this.account = account;
    }

    public String getProfileName() {
        return profileName;
    }

    public void setProfileName(String profileName) {
        this.profileName = profileName;
    }

    public String getWebsite() {
        return website;
    }

    public void setWebsite(String website) {
        this.website = website;
    }

    public String getBio() {
        return bio;
    }

    public void setBio(String bio) {
        this.bio = bio;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "Profile{" +
                "profileId=" + profileId +
                ", account=" + account +
                ", profileName='" + profileName + '\'' +
                ", website='" + website + '\'' +
                ", bio='" + bio + '\'' +
                ", status='" + status + '\'' +
                '}';
    }
}
