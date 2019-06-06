package food.gram.persistence.entity;

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

    @Column(name = "image",length = Integer.MAX_VALUE)
    private byte[] image;

    @Column(name = "website",length = 20)
    private String website;

    @Column(name = "bio",length = 200)
    private String bio;

    @Column(name = "status",length = 10)
    private String status;

    public Profile(){
        super();
    }

    public Profile(Account account, String profileName, byte[] image, String website, String bio, String status) {
        this.account = account;
        this.profileName = profileName;
        this.image = image;
        this.website = website;
        this.bio = bio;
        this.status = status;
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

    public byte[] getImage() {
        return image;
    }

    public void setImage(byte[] image) {
        this.image = image;
    }

    @Override
    public String toString() {
        return "Profile{" +
                "profileId=" + profileId +
                ", account=" + account + "\n"+
                ", profileName='" + profileName + '\'' +
                ", website='" + website + '\'' +
                ", bio='" + bio + '\'' +
                ", status='" + status + '\'' +
                '}';
    }

    public Profile clone(){
        Profile profile = new Profile();
        profile.setProfileId(profileId);
        profile.setProfileName(profileName);
        profile.setImage(image);
        profile.setAccount(account);
        profile.setStatus(status);
        profile.setBio(bio);
        profile.setWebsite(website);

        return profile;
    }

    @Override
    public boolean equals(Object obj) {
        if(!(obj instanceof Profile)) return false;
        Profile other = (Profile)obj;
        if(other.getProfileId() == this.profileId)return true;
        return false;
    }
}
