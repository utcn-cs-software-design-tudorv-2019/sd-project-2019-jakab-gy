package food.gram.persistence.repository;

import food.gram.persistence.entity.Commentary;
import food.gram.persistence.entity.Post;
import food.gram.persistence.entity.Profile;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.sql.Timestamp;
import java.util.List;

@Repository
public interface CommentaryRepository extends JpaRepository<Commentary,Integer> {

    public Commentary findByCommentId(int commentaryId);
    public List<Commentary> findAllByPost(Post post);
    public List<Commentary> findAllByProfile(Profile profile);
    public List<Commentary> findAllByCommentaryTimeIsBetween(Timestamp start,Timestamp end);
}
