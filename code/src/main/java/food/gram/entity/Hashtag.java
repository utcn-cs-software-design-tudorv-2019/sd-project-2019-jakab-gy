package food.gram.entity;

import javax.persistence.*;

@Entity
@Table(name = "hashtag")
public class Hashtag {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "hashtag_id")
    private int hashtagId;

    @Column(name = "hashtag_name",length = 20,nullable = false)
    private String hashtagName;

    @Column(name = "appearance")
    private int appearance;

    public Hashtag(){
        super();
    }
    public Hashtag(String hashtagName, int appearance) {
        this.hashtagName = hashtagName;
        this.appearance = appearance;
    }

    public int getHashtagId() {
        return hashtagId;
    }

    public void setHashtagId(int hashtagId) {
        this.hashtagId = hashtagId;
    }

    public String getHashtagName() {
        return hashtagName;
    }

    public void setHashtagName(String hashtagName) {
        this.hashtagName = hashtagName;
    }

    public int getAppearance() {
        return appearance;
    }

    public void setAppearance(int appearance) {
        this.appearance = appearance;
    }

    @Override
    public String toString() {
        return "Hashtag{" +
                "hashtagId=" + hashtagId +
                ", hashtagName='" + hashtagName + '\'' +
                ", appearance=" + appearance +
                '}';
    }
}
