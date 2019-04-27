package food.gram.repository;

import food.gram.entity.Commentary;
import food.gram.entity.Post;
import food.gram.entity.Profile;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CommentaryRepository extends JpaRepository<Commentary,Integer> {

    public Commentary findByCommentId(int commentaryId);
    public List<Commentary> findAllByPost(Post post);
    public List<Commentary> findAllByProfile(Profile profile);
}
