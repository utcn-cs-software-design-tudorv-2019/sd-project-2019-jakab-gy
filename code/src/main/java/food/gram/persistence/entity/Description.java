package food.gram.persistence.entity;

import javax.persistence.*;

@Entity
@Table(name = "description")
public class Description {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "description_id")
    private int descriptionId;

    @OneToOne
    @JoinColumn(name = "post_id")
    private Post post;

    @Column(name = "ingredients",length = 10485760)
    private String ingredients;

    @Column(name = "directions",length = 10485760)
    private String directions;

    @Column(name = "footnotes",length = 10485760)
    private String footnotes;

    @Column(name = "nutritional_facts",length = 10485760)
    private String nutritionalFacts;

    public Description(){
        super();
    }
    public Description(Post post, String ingredients, String directions, String footnotes, String nutritionalFacts) {
        this.post = post;
        this.ingredients = ingredients;
        this.directions = directions;
        this.footnotes = footnotes;
        this.nutritionalFacts = nutritionalFacts;
    }

    public int getDescriptionId() {
        return descriptionId;
    }

    public void setDescriptionId(int descriptionId) {
        this.descriptionId = descriptionId;
    }

    public Post getPost() {
        return post;
    }

    public void setPost(Post post) {
        this.post = post;
    }

    public String getIngredients() {
        return ingredients;
    }

    public void setIngredients(String ingredients) {
        this.ingredients = ingredients;
    }

    public String getDirections() {
        return directions;
    }

    public void setDirections(String directions) {
        this.directions = directions;
    }

    public String getFootnotes() {
        return footnotes;
    }

    public void setFootnotes(String footnotes) {
        this.footnotes = footnotes;
    }

    public String getNutritionalFacts() {
        return nutritionalFacts;
    }

    public void setNutritionalFacts(String nutritionalFacts) {
        this.nutritionalFacts = nutritionalFacts;
    }

    @Override
    public String toString() {
        return "Description{" +
                "descriptionId=" + descriptionId +
                ", post=" + post +
                ", ingredients='" + ingredients + '\'' +
                ", directions='" + directions + '\'' +
                ", footnotes='" + footnotes + '\'' +
                ", nutritionalFacts='" + nutritionalFacts + '\'' +
                '}';
    }
}
