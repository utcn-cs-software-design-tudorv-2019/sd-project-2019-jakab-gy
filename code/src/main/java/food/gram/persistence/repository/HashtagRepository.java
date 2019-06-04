package food.gram.persistence.repository;

import food.gram.persistence.entity.Hashtag;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface HashtagRepository extends JpaRepository<Hashtag,Integer> {

    public Hashtag findByHashtagId(int hashtagId);
    public Hashtag findByHashtagName(String hashtagName);
}
