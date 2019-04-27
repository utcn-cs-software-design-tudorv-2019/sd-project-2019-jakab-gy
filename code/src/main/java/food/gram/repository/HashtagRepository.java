package food.gram.repository;

import food.gram.entity.Hashtag;
import org.springframework.data.jpa.repository.JpaRepository;


public interface HashtagRepository extends JpaRepository<Hashtag,Integer> {

    public Hashtag findByHashtagId(int hashtagId);
    public Hashtag findByHashtagName(String hashtagName);
}
