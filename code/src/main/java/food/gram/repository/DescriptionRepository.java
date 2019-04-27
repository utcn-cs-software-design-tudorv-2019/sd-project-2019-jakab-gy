package food.gram.repository;

import food.gram.entity.Description;
import food.gram.entity.Post;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DescriptionRepository extends JpaRepository<Description, Integer> {

    public Description findByDescriptionId(int descriptionId);
    public Description findByPost(Post post);

}
