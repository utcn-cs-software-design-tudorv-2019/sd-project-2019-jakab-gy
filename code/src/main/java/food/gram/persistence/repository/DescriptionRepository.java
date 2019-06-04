package food.gram.persistence.repository;

import food.gram.persistence.entity.Description;
import food.gram.persistence.entity.Post;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DescriptionRepository extends JpaRepository<Description, Integer> {

    public Description findByDescriptionId(int descriptionId);
    public Description findByPost(Post post);

}
